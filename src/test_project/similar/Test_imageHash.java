package test_project.similar;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

// 最简单的哈希算法实现
// 1.缩小图片尺寸
// 2.转为灰度图片
// 3.计算灰度平均值
// 4.比较像素灰度
// 5.计算哈希值
// 6.对比图片指纹

public class Test_imageHash {
    public static void main(String[] args) throws IOException {
        Test_imageHash t = new Test_imageHash();
        File sourceImage = new File("D:\\Project\\java\\Test_project\\src\\resource\\img\\timg.jpg");
        File image1 = new File("D:\\Project\\java\\Test_project\\src\\resource\\img\\timg1.jpg");
        System.out.println(t.handleImage(ImageIO.read(sourceImage)));
        System.out.println(t.handleImage(ImageIO.read(image1)));
        t.isDiffence(t.handleImage(ImageIO.read(sourceImage)), t.handleImage(ImageIO.read(image1)));
    }

    public String handleImage(BufferedImage image) {
        BufferedImage reduce = reduceImage(image, 8,8, false);
        int[] pixels = grayImage(reduce, 8, 8);
        int avg = calculateGrapImage(pixels);
        int[] compares = comparePixelGrap(pixels,avg);
        return toHash(compares);
    }

//  第一步：缩小尺寸。
//  将图片缩小到8*8的尺寸，总共64个像素。这一步的作用是去除图片的细节，只保留结构、明暗等基本信息，摒弃不同尺寸、比例带来的图片差异。
    public BufferedImage reduceImage(BufferedImage image, int newWidth, int newHeight, boolean isRatio) {
        BufferedImage reduceImg = null;
        double w = (double)newWidth / (double)image.getWidth();
        double h = (double)newHeight / (double)image.getHeight();
        if(isRatio) {
            if(w > h){
                w = h;
                newWidth = new Double(image.getWidth() * h).intValue();
            }
            else {
                h = w;
                newHeight = new Double(image.getHeight() * w).intValue();
            }
        }
        if(image.getType() != BufferedImage.TYPE_CUSTOM ) {
            reduceImg = new BufferedImage(newWidth, newHeight, image.getType());
        }
        else {
            //TODO 针对自定义图片处理
        }
        //调用画图类画缩小尺寸后的图
        Graphics2D g = reduceImg.createGraphics();
        //smoother than exlax:
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.drawRenderedImage(image, AffineTransform.getScaleInstance(w, h));
        g.dispose();
        return reduceImg;
    }

//  第二步：简化色彩。
//  将缩小后的图片，转为64级灰度。也就是说，所有像素点总共只有64种颜色。
    public int[] grayImage(BufferedImage image, int newWidth, int newHeight) {
        int[] pixels = new int[newWidth * newHeight];
        for (int w = 0; w < newWidth; w++) {
            for (int h = 0; h < newHeight; h++) {
                int pixel = image.getRGB(w, h);
                int red = (pixel >> 16) & 0xFF;
                int green = (pixel >> 8) & 0xFF;
                int blue = (pixel) & 0xFF;
                pixels[w * image.getHeight() + h] = new Double(0.3 * red + 0.59 * green + 0.11 * blue).intValue();
            }
        }
        return pixels;
    }

//  第三步：计算平均值。
//  计算所有64个像素的灰度平均值。
    public int calculateGrapImage(int[] pixels) {
        Double avg =Arrays.stream(pixels).average().getAsDouble();
        return avg.intValue();
    }

//  第四步：比较像素的灰度。
//  将每个像素的灰度，与平均值进行比较。大于或等于平均值，记为1；小于平均值记为0。
    public int[] comparePixelGrap(int[] pixels, int avg) {
        int[] compares = new int[pixels.length];
        for(int i=0; i<pixels.length; i++) {
            if(pixels[i] >= avg)
                compares[i] = 1;
            else
                compares[i] = 0;
        }
        return compares;
    }

//  第五步：计算哈希值。
//  将上一步的比较结果组合在一起，就构成了一个64位的整数，这就是这张图片指纹。组合的次序并不重要，只要保证所有图片都采用同样的次序就行
    public String toHash(int[] compares) {
        StringBuffer buffer = new StringBuffer();
        for(int i=0; i<compares.length; i+=4) {
            int result = compares[i] * (int)Math.pow(2,3) + compares[i+1] * (int)Math.pow(2,2) + compares[i+2] * (int)Math.pow(2,1) + compares[i+3] * (int)Math.pow(2,0);
            buffer.append(Integer.toHexString(result));
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
