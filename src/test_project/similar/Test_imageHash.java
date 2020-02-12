package test_project.similar;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

// ��򵥵Ĺ�ϣ�㷨ʵ��
// 1.��СͼƬ�ߴ�
// 2.תΪ�Ҷ�ͼƬ
// 3.����Ҷ�ƽ��ֵ
// 4.�Ƚ����ػҶ�
// 5.�����ϣֵ
// 6.�Ա�ͼƬָ��

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

//  ��һ������С�ߴ硣
//  ��ͼƬ��С��8*8�ĳߴ磬�ܹ�64�����ء���һ����������ȥ��ͼƬ��ϸ�ڣ�ֻ�����ṹ�������Ȼ�����Ϣ��������ͬ�ߴ硢����������ͼƬ���졣
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
            //TODO ����Զ���ͼƬ����
        }
        //���û�ͼ�໭��С�ߴ���ͼ
        Graphics2D g = reduceImg.createGraphics();
        //smoother than exlax:
        g.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        g.drawRenderedImage(image, AffineTransform.getScaleInstance(w, h));
        g.dispose();
        return reduceImg;
    }

//  �ڶ�������ɫ�ʡ�
//  ����С���ͼƬ��תΪ64���Ҷȡ�Ҳ����˵���������ص��ܹ�ֻ��64����ɫ��
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

//  ������������ƽ��ֵ��
//  ��������64�����صĻҶ�ƽ��ֵ��
    public int calculateGrapImage(int[] pixels) {
        Double avg =Arrays.stream(pixels).average().getAsDouble();
        return avg.intValue();
    }

//  ���Ĳ����Ƚ����صĻҶȡ�
//  ��ÿ�����صĻҶȣ���ƽ��ֵ���бȽϡ����ڻ����ƽ��ֵ����Ϊ1��С��ƽ��ֵ��Ϊ0��
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

//  ���岽�������ϣֵ��
//  ����һ���ıȽϽ�������һ�𣬾͹�����һ��64λ�����������������ͼƬָ�ơ���ϵĴ��򲢲���Ҫ��ֻҪ��֤����ͼƬ������ͬ���Ĵ������
    public String toHash(int[] compares) {
        StringBuffer buffer = new StringBuffer();
        for(int i=0; i<compares.length; i+=4) {
            int result = compares[i] * (int)Math.pow(2,3) + compares[i+1] * (int)Math.pow(2,2) + compares[i+2] * (int)Math.pow(2,1) + compares[i+3] * (int)Math.pow(2,0);
            buffer.append(Integer.toHexString(result));
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
