package test_project;


//  ��֪��ϣ�㷨ʵ��
//  1.��С�ߴ磺pHash��СͼƬ��ʼ����ͼƬ����8*8��32*32����õġ���������Ŀ���Ǽ���DCT�ļ��㣬�����Ǽ�СƵ�ʡ�
//  2.��ɫ�ʣ���ͼƬת���ɻҶ�ͼ�񣬽�һ���򻯼�������
//  3.����DCT������ͼƬ��DCT�任���õ�32*32��DCTϵ������
//  4.��СDCT����ȻDCT�Ľ����32*32��С�ľ��󣬵�����ֻҪ�������Ͻǵ�8*8�ľ����ⲿ�ֳ�����ͼƬ�е����Ƶ�ʡ�
//  5.����ƽ��ֵ����ͬ��ֵ��ϣһ��������DCT�ľ�ֵ��
//  6.����hashֵ����������Ҫ��һ��������8*8��DCT��������0��1��64λ��hashֵ�����ڵ���DCT��ֵ����Ϊ��1����С��DCT��ֵ����Ϊ��0���������һ�𣬾͹�����һ��64λ�����������������ͼƬ��ָ�ơ�

import cn.com.taiji.common.manager.pub.ImgFontByte;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

public class Test_imagePHash {
    public static void main(String[] args) throws IOException {
        Test_imagePHash t = new Test_imagePHash();
        File sourceImage = new File("D:\\Project\\java\\Test_project\\src\\resource\\img\\post1.jpg");
        File image1 = new File("D:\\Project\\java\\Test_project\\src\\resource\\img\\post1-gray-b.jpg");
        System.out.println(t.imagePHash(ImageIO.read(sourceImage)));
        System.out.println(t.imagePHash(ImageIO.read(image1)));
        t.isDiffence(t.imagePHash(ImageIO.read(sourceImage)), t.imagePHash(ImageIO.read(image1)));
    }

    public String imagePHash(BufferedImage image) {
        int n = 32;
        int smallN = 8;
        BufferedImage reduce = reduceImage(image, n, n, false);
        BufferedImage gray = grayImage(reduce);
        int[][] imgDCT = image2DCT(gray, n);
        int[] reduceImgDCT = reduceImgDCT(imgDCT, smallN);
        int avg = avgDCT(reduceImgDCT);
        return toHashCode(reduceImgDCT, avg);
    }

    public void image2gray() throws IOException {
        BufferedImage img = ImageIO.read(new File("D:\\Project\\java\\Test_project\\src\\resource\\img\\post1.jpg"));
        File grayImg = new File("D:\\Project\\java\\Test_project\\src\\resource\\img\\post1-gray-b.jpg");
        ImageIO.write(grayImage(img),"jpg",grayImg);
    }

    //  ��һ������С�ߴ硣
    //  ��ͼƬ��С��32*32�ĳߴ磬�ܹ�1024�����ء���һ����������ȥ��ͼƬ��ϸ�ڣ�ֻ�����ṹ�������Ȼ�����Ϣ��������ͬ�ߴ硢����������ͼƬ���졣
    public BufferedImage reduceImage(BufferedImage image, int newWidth, int newHeight, boolean isRatio) {
        BufferedImage reduce = null;
        Graphics2D g = null;
        double wm = (double)newWidth / (double)image.getWidth();
        double hm = (double)newHeight / (double)image.getHeight();
        if(isRatio) {
            if(wm > hm ) {
                wm = hm;
                newWidth = new Double((double)image.getWidth() * wm).intValue();
            }else {
                hm = wm;
                newHeight = new Double((double)image.getHeight() * hm).intValue();
            }
        }
        if(image.getType() != BufferedImage.TYPE_CUSTOM) {
            reduce = new BufferedImage(newWidth,newHeight,image.getType());
        }else {
            //TODO �����Զ���ͼƬ����
        }
        g = reduce.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.drawRenderedImage(image, AffineTransform.getScaleInstance(wm,hm));
        g.dispose();
        return reduce;
    }

    //  �ڶ�������ɫ�ʡ�
    //  ����С���ͼƬ��תΪ64���Ҷȡ�Ҳ����˵���������ص��ܹ�ֻ��64����ɫ��
    //  ��Ȩ������RGB���������м�Ȩƽ���ܵõ��Ϻ���ĻҶ�ͼ��һ��������գ�Y = 0.30R + 0.59G + 0.11B
    public BufferedImage grayImage(BufferedImage image) {
        BufferedImage gray = new BufferedImage(image.getWidth(), image.getHeight(), image.getType());
        for (int w = 0; w < image.getWidth(); w++) {
            for (int h = 0; h < image.getHeight(); h++) {
                int pixel = image.getRGB(w, h);
                int red = (pixel >> 16) & 0xFF;
                int green = (pixel >> 8) & 0xFF;
                int blue = (pixel) & 0xFF;
                //gray.setRGB(w,h,new Double(0.3 * red + 0.59 * green + 0.11 * blue).intValue());
                int grayPixel = new Double(0.3 * red + 0.59 * green + 0.11 * blue).intValue();
                int grayp = 255;
                grayp = grayp << 8;
                grayp += grayPixel;
                grayp = grayp << 8;
                grayp += grayPixel;
                grayp = grayp << 8;
                grayp += grayPixel;
                gray.setRGB(w,h,grayp);
            }
        }
        return gray;
    }

    // ��ά��ɢ���ұ任�����任��ʽ������f(x,y)�ǿռ���һ��N*N�Ķ�ά����Ԫ�أ���һ��N*N�ľ���x,y = 0,1,2������N-1;F(U,V)�Ǿ������õ��ı任�����u,v = 0,1,2����.��N-1.��Ϳɷ����Ƕ�ά��ɢ���ұ任��һ����Ҫ����
    public int[][] image2DCT(BufferedImage image, int n) {
        int[][] fuv = new int[n][n];
        for(int u=0; u<n; u++) {
            for(int v=0; v<n; v++) {
                double f = 0;
                for(int x=0; x<n; x++) {
                    for(int y=0; y<n; y++) {
                        f += image.getRGB(x,y) * Math.cos(u * Math.PI * (2 * x + 1) / (2 * n)) * Math.cos(v * Math.PI * (2 * y + 1) / (2 * n));
                    }
                }
                if(u == 0 && v == 0) {
                    fuv[u][v] = new Double(1 / n * f).intValue();
                } else if ((u != 0 && v == 0) || (u == 0 && v != 0)) {
                    fuv[u][v] = new Double(Math.sqrt(2) / n * f).intValue();
                } else {
                    fuv[u][v] = new Double(2 / n * f).intValue();
                }
            }
        }
        return fuv;
    }

    public int[] reduceImgDCT(int[][] imgDCT, int smallN) {
        int[] imgDCTS = new int[smallN * smallN];
        for(int u=0; u<smallN; u++) {
            for(int v=0; v<smallN; v++) {
                imgDCTS[u * smallN + v] = imgDCT[u][v];
            }
        }
        return imgDCTS;
    }

    public int avgDCT(int[] imgDCTS) {
        return new Double(Arrays.stream(imgDCTS).average().getAsDouble()).intValue();
    }

    public String toHashCode(int[] imgDCTS, int avgDCT) {
        int[] compareDCT = new int[imgDCTS.length];
        StringBuffer buffer = new StringBuffer();
        for(int i=0; i<imgDCTS.length; i++) {
            if(imgDCTS[i] > avgDCT) {
                compareDCT[i] = 1;
            } else {
                compareDCT[i] = 0;
            }
            buffer.append(compareDCT[i]);
        }

        return buffer.toString();
    }

    //  �Ƚ�64λ���ж���λ�ǲ�һ���ġ��������ϣ����ͬ�ڼ��㡰�������롱��Hammingdistance�����������ͬ������λ������5����˵������ͼƬ�����ƣ��������10����˵���������Ų�ͬ��ͼƬ��
    public void isDiffence(String sourceHashCode, String hashCode) {
        int diffence = 0;
        for(int i=0; i<sourceHashCode.length(); i++) {
            if(sourceHashCode.charAt(i) != hashCode.charAt(i))
                diffence++;
        }
        if (diffence > 5)
            System.out.println("����ͼƬ���ƶȽϵͣ�NONONONO��������Ϊ��"+diffence);
        else
            System.out.println("����ͼƬ���ƶȽϸߣ�YESYESYES��������Ϊ��"+diffence);
    }
}
