package test_project.similar;


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
import java.math.BigDecimal;
import java.util.Arrays;

public class Test_imagePHash {
    public static void main(String[] args) throws IOException {
        Test_imagePHash t = new Test_imagePHash();
//        File sourceImage = new File("D:\\Project\\java\\Test_project\\src\\resource\\img\\post1.jpg");
//        File image1 = new File("D:\\Project\\java\\Test_project\\src\\resource\\img\\post1-gray-b.jpg");
//        System.out.println(t.imagePHash(ImageIO.read(sourceImage)));
//        System.out.println(t.imagePHash(ImageIO.read(image1)));
//        t.isDiffence(t.imagePHash(ImageIO.read(sourceImage)), t.imagePHash(ImageIO.read(image1)));
        t.image2DCT();
        t.image2IDCT();
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

    public void image2DCT() throws IOException {
        File sourceImage = new File("D:\\Project\\java\\Test_project\\src\\resource\\img\\post1.jpg");
        File reduceImage = new File("D:\\Project\\java\\Test_project\\src\\resource\\img\\post1-reduce.jpg");
        File resultImage = new File("D:\\Project\\java\\Test_project\\src\\resource\\img\\post1-result-DCT.jpg");
        BufferedImage reduce = reduceImage(ImageIO.read(sourceImage), 300, 300, false);
        BufferedImage gray = grayImage(reduce);
        ImageIO.write(gray,"jpg",reduceImage);
        int[][] imgDCT = image2DCT(gray, 30);
        BufferedImage result = new BufferedImage(30,30,gray.getType());
        for(int w=0;w<30;w++) {
            for(int h=0; h<30;h++) {
                result.setRGB(w,h,imgDCT[w][h]);
            }
        }
        ImageIO.write(result,"jpg",resultImage);
    }

    public void image2IDCT() throws IOException {
        File sourceImage = new File("D:\\Project\\java\\Test_project\\src\\resource\\img\\post1-oppo.jpg");
        File resultImage = new File("D:\\Project\\java\\Test_project\\src\\resource\\img\\post1-result-DCT.jpg");
        int[][] imgIDCT = image2IDCT(ImageIO.read(resultImage), 30);
        BufferedImage result = new BufferedImage(30,30,ImageIO.read(resultImage).getType());
        for(int w=0;w<30;w++) {
            for(int h=0; h<30;h++) {
                result.setRGB(w,h,imgIDCT[w][h]);
            }
        }
        ImageIO.write(result,"jpg",sourceImage);
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
        BigDecimal piDec = new BigDecimal(Double.toString(Math.PI));
        BigDecimal coefficient2 = new BigDecimal(Integer.toString(2));
        BigDecimal coefficient1 = new BigDecimal(Integer.toString(1));
        BigDecimal nDec = new BigDecimal(Integer.toString(n));
        for(int u=0; u<n; u++) {
            for(int v=0; v<n; v++) {
                BigDecimal f = new BigDecimal(Integer.toString(0));
                BigDecimal uDec = new BigDecimal(Integer.toString(u));
                BigDecimal vDec = new BigDecimal(Integer.toString(v));
                for(int x=0; x<n; x++) {
                    for(int y=0; y<n; y++) {
                        BigDecimal sourceDec = new BigDecimal(Integer.toString(image.getRGB(x,y)));
                        BigDecimal xDec = new BigDecimal(Integer.toString(x));
                        BigDecimal yDec = new BigDecimal(Integer.toString(y));
                        f = f.add(sourceDec.multiply(new BigDecimal(Double.toString(Math.cos(uDec.multiply(piDec).multiply(coefficient2.multiply(xDec).add(coefficient1)).divide(coefficient2.multiply(nDec),15,BigDecimal.ROUND_HALF_UP).doubleValue()))))
                                           .multiply(new BigDecimal(Double.toString(Math.cos(vDec.multiply(piDec).multiply(coefficient2.multiply(yDec).add(coefficient1)).divide(coefficient2.multiply(nDec),15,BigDecimal.ROUND_HALF_UP).doubleValue())))));
                    }
                }
                if(u == 0 && v == 0) {
                    fuv[u][v] = new Double(Math.rint(coefficient1.divide(nDec,15,BigDecimal.ROUND_HALF_UP).multiply(f).doubleValue())).intValue();
                } else if ((u != 0 && v == 0) || (u == 0 && v != 0)) {
                    fuv[u][v] = new Double(Math.rint(new BigDecimal(Double.toString(Math.sqrt(2))).divide(nDec,15,BigDecimal.ROUND_HALF_UP).multiply(f).doubleValue())).intValue();
                } else {
                    fuv[u][v] = new Double(Math.rint(coefficient2.divide(nDec,15,BigDecimal.ROUND_HALF_UP).multiply(f).doubleValue())).intValue();
                }
                System.out.println("~~~ת��ΪDCT��"+u+"��"+v+"������Ϊ:"+fuv[u][v]);
            }
        }
        return fuv;
    }

    public int[][] image2IDCT(BufferedImage image, int n) {
        int[][] fxy = new int[n][n];
        BigDecimal piDec = new BigDecimal(Double.toString(Math.PI));
        BigDecimal coefficient2 = new BigDecimal(Integer.toString(2));
        BigDecimal coefficient1 = new BigDecimal(Integer.toString(1));
        BigDecimal nDec = new BigDecimal(Integer.toString(n));
        for(int x=0; x<n; x++) {
            for(int y=0; y<n; y++) {
                BigDecimal f = new BigDecimal(Integer.toString(0));
                BigDecimal xDec = new BigDecimal(Integer.toString(x));
                BigDecimal yDec = new BigDecimal(Integer.toString(y));
                for(int u=0; u<n; u++) {
                    for(int v=0; v<n; v++) {
                        BigDecimal a = new BigDecimal(Integer.toString(0));
                        BigDecimal resultDec = new BigDecimal(Integer.toString(image.getRGB(u,v)));
                        BigDecimal uDec = new BigDecimal(Integer.toString(u));
                        BigDecimal vDec = new BigDecimal(Integer.toString(v));
                        if(u ==0 && v==0) {
                            a = coefficient1.divide(nDec,15,BigDecimal.ROUND_HALF_UP).multiply(resultDec);
                        } else if((u != 0 && v == 0) || (u == 0 && v != 0)) {
                            a = new BigDecimal(Double.toString(Math.sqrt(2))).divide(nDec,15,BigDecimal.ROUND_HALF_UP).multiply(resultDec);
                        } else {
                            a = coefficient2.divide(nDec,15,BigDecimal.ROUND_HALF_UP).multiply(resultDec);
                        }
                        f = f.add(a.multiply(new BigDecimal(Double.toString(Math.cos(coefficient2.multiply(xDec).add(coefficient1).multiply(uDec).multiply(piDec).divide(coefficient2.multiply(nDec),15,BigDecimal.ROUND_HALF_UP).doubleValue()))))
                                   .multiply(new BigDecimal(Double.toString(Math.cos(coefficient2.multiply(yDec).add(coefficient1).multiply(vDec).multiply(piDec).divide(coefficient2.multiply(nDec),15,BigDecimal.ROUND_HALF_UP).doubleValue())))));
                    }
                }
                fxy[x][y] = new Double(Math.rint(f.doubleValue())).intValue();
                System.out.println("~~~DCT��ת��Ϊ��"+x+"��"+y+"������Ϊ:"+fxy[x][y]);
            }
        }
        return fxy;
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
