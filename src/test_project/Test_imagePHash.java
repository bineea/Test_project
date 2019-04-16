package test_project;


//  感知哈希算法实现
//  1.缩小尺寸：pHash以小图片开始，但图片大于8*8，32*32是最好的。这样做的目的是简化了DCT的计算，而不是减小频率。
//  2.简化色彩：将图片转化成灰度图像，进一步简化计算量。
//  3.计算DCT：计算图片的DCT变换，得到32*32的DCT系数矩阵。
//  4.缩小DCT：虽然DCT的结果是32*32大小的矩阵，但我们只要保留左上角的8*8的矩阵，这部分呈现了图片中的最低频率。
//  5.计算平均值：如同均值哈希一样，计算DCT的均值。
//  6.计算hash值：这是最主要的一步，根据8*8的DCT矩阵，设置0或1的64位的hash值，大于等于DCT均值的设为”1”，小于DCT均值的设为“0”。组合在一起，就构成了一个64位的整数，这就是这张图片的指纹。

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
//        File sourceImage = new File("D:\\Project\\java\\Test_project\\src\\resource\\img\\post1.jpg");
//        File image1 = new File("D:\\Project\\java\\Test_project\\src\\resource\\img\\post1-gray-b.jpg");
//        System.out.println(t.imagePHash(ImageIO.read(sourceImage)));
//        System.out.println(t.imagePHash(ImageIO.read(image1)));
//        t.isDiffence(t.imagePHash(ImageIO.read(sourceImage)), t.imagePHash(ImageIO.read(image1)));
        t.imageOppoDCT();
        System.out.println(Math.cos(Math.PI / (double)4));
        System.out.println(Math.cos(Math.PI * (double)3 / (double)4));
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
//        BufferedImage reduce = reduceImage(ImageIO.read(sourceImage), 300, 300, false);
//        BufferedImage gray = grayImage(reduce);
//        ImageIO.write(gray,"jpg",reduceImage);
        int[][] imgDCT = image2DCT(ImageIO.read(reduceImage), 300);
        BufferedImage result = new BufferedImage(300,300,ImageIO.read(reduceImage).getType());
        for(int w=0;w<300;w++) {
            for(int h=0; h<300;h++) {
                result.setRGB(w,h,imgDCT[w][h]);
            }
        }
        ImageIO.write(result,"jpg",resultImage);
    }

    public void imageOppoDCT() throws IOException {
        File sourceImage = new File("D:\\Project\\java\\Test_project\\src\\resource\\img\\post1-oppo.jpg");
        File resultImage = new File("D:\\Project\\java\\Test_project\\src\\resource\\img\\post1-result-DCT.jpg");
        int[][] imgOppoDCT = imageOppoDCT(ImageIO.read(resultImage), 300);
        BufferedImage result = new BufferedImage(300,300,ImageIO.read(resultImage).getType());
        for(int w=0;w<300;w++) {
            for(int h=0; h<300;h++) {
                result.setRGB(w,h,imgOppoDCT[w][h]);
            }
        }
        ImageIO.write(result,"jpg",sourceImage);
    }

    public void image2gray() throws IOException {
        BufferedImage img = ImageIO.read(new File("D:\\Project\\java\\Test_project\\src\\resource\\img\\post1.jpg"));
        File grayImg = new File("D:\\Project\\java\\Test_project\\src\\resource\\img\\post1-gray-b.jpg");
        ImageIO.write(grayImage(img),"jpg",grayImg);
    }

    //  第一步：缩小尺寸。
    //  将图片缩小到32*32的尺寸，总共1024个像素。这一步的作用是去除图片的细节，只保留结构、明暗等基本信息，摒弃不同尺寸、比例带来的图片差异。
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
            //TODO 处理自定义图片类型
        }
        g = reduce.createGraphics();
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.drawRenderedImage(image, AffineTransform.getScaleInstance(wm,hm));
        g.dispose();
        return reduce;
    }

    //  第二步：简化色彩。
    //  将缩小后的图片，转为64级灰度。也就是说，所有像素点总共只有64种颜色。
    //  加权法：对RGB三分量进行加权平均能得到较合理的灰度图像。一般情况按照：Y = 0.30R + 0.59G + 0.11B
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

    // 二维离散余弦变换的正变换公式，其中f(x,y)是空间域一个N*N的二维向量元素，即一个N*N的矩阵，x,y = 0,1,2，…，N-1;F(U,V)是经计算后得到的变换域矩阵，u,v = 0,1,2，….，N-1.求和可分性是二维离散余弦变换的一个重要特征
    public int[][] image2DCT(BufferedImage image, int n) {
        int[][] fuv = new int[n][n];
        for(int u=0; u<n; u++) {
            for(int v=0; v<n; v++) {
                double f = 0;
                for(int x=0; x<n; x++) {
                    for(int y=0; y<n; y++) {
                        f += (double)image.getRGB(x,y) * (double)Math.cos((double)u * (double)Math.PI * ((double)2 * (double)x + (double)1) / ((double)2 * (double)n)) * (double)Math.cos((double)v * (double)Math.PI * ((double)2 * (double)y + (double)1) / ((double)2 * (double)n));
                    }
                }
                if(u == 0 && v == 0) {
                    fuv[u][v] = new Double((double)1 / (double)n * (double)f).intValue();
                    System.out.println("f = " + f+"；1 / n * f = " + ((double)1 / (double)n * (double)f));
                } else if ((u != 0 && v == 0) || (u == 0 && v != 0)) {
                    fuv[u][v] = new Double((double)Math.sqrt(2) / (double)n * (double)f).intValue();
                    System.out.println("f = " + f+"；Math.sqrt(2) / n * f = " + ((double)Math.sqrt(2) / (double)n * (double)f));
                } else {
                    fuv[u][v] = new Double((double)2 / (double)n * (double)f).intValue();
                    System.out.println("f = " + f+"；2 / n * f = " + ((double)2 / (double)n * (double)f));
                }
                System.out.println("~~~转换为DCT【"+u+"，"+v+"】像素为:"+fuv[u][v]);
            }
        }
        return fuv;
    }

    public int[][] imageOppoDCT(BufferedImage image, int n) {
        int[][] fxy = new int[n][n];
        for(int x=0; x<n; x++) {
            for(int y=0; y<n; y++) {
                double f = 0;
                for(int u=0; u<n; u++) {
                    for(int v=0; v<n; v++) {
                        double a = 0;
                        if(u ==0 && v==0) {
                            a = (double) 1 / (double)n * (double)image.getRGB(u,v);
                        } else if((u != 0 && v == 0) || (u == 0 && v != 0)) {
                            a = (double)Math.sqrt(2) / (double)n * (double)image.getRGB(u,v);
                        } else {
                            a = (double) 2 / (double)n * (double)image.getRGB(u,v);
                        }
                        f += a * ((double)image.getRGB(u,v) * (double)Math.cos(((double)2 * (double)x + (double)1) * (double)u * (double)Math.PI / ((double)2 * (double)n))) * (double)Math.cos(((double)2 * (double)y + (double)1) * (double)v * (double)Math.PI / ((double)2 * (double)n));
                    }
                }
                fxy[x][y] = new Double(f).intValue();
                System.out.println("~~~DCT反转换为【"+x+"，"+y+"】像素为:"+fxy[x][y]);
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

    //  比较64位中有多少位是不一样的。在理论上，这等同于计算“汉明距离”（Hammingdistance）。如果不相同的数据位不超过5，就说明两张图片很相似；如果大于10，就说明这是两张不同的图片。
    public void isDiffence(String sourceHashCode, String hashCode) {
        int diffence = 0;
        for(int i=0; i<sourceHashCode.length(); i++) {
            if(sourceHashCode.charAt(i) != hashCode.charAt(i))
                diffence++;
        }
        if (diffence > 5)
            System.out.println("两张图片相似度较低，NONONONO，差异性为："+diffence);
        else
            System.out.println("两张图片相似度较高，YESYESYES，差异性为："+diffence);
    }
}
