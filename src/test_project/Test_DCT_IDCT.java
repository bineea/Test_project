package test_project;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;

public class Test_DCT_IDCT {

    public static void main(String[] args) throws IOException {
        Test_DCT_IDCT t = new Test_DCT_IDCT();
        t.testImg();
    }

    public void testData() {
        int[][] source = {{-7763575,-7434610,-7105645},{-6974059,-6974059,-7039852},{-9276814,-9803158,-10132123}};
        int[][] result = dct(source,3);
        idct(result,3);
    }

    public void testImg() throws IOException {
        File sourceImage = new File("D:\\Project\\java\\Test_project\\src\\resource\\img\\post1-oppo.jpg");
        File reduceImage = new File("D:\\Project\\java\\Test_project\\src\\resource\\img\\post1-reduce.jpg");
        File resultImage = new File("D:\\Project\\java\\Test_project\\src\\resource\\img\\post1-result-DCT.jpg");
        BufferedImage reduce = ImageIO.read(reduceImage);
        int[][] source = new int[reduce.getWidth()][reduce.getHeight()];
        for(int x=0; x<reduce.getWidth(); x++) {
            for(int y=0; y<reduce.getHeight(); y++) {
                source[x][y] = reduce.getRGB(x,y);
                System.out.println("~~~原【"+x+"，"+y+"】像素为:"+source[x][y]);
            }
        }
        int[][] result = dct(source,300);
        int[][] rawData = idct(result,300);
        System.out.println("reduce.getType():"+reduce.getType());
        BufferedImage rawImg = new BufferedImage(300,300, reduce.getType());
        BufferedImage dctImg = new BufferedImage(300,300, reduce.getType());
        for(int w=0;w<300;w++) {
            for(int h=0; h<300;h++) {
                rawImg.setRGB(w,h,rawData[w][h]);
                dctImg.setRGB(w,h,result[w][h]);
            }
        }
        ImageIO.write(rawImg,"jpg",sourceImage);
        ImageIO.write(dctImg,"jpg",resultImage);
    }

    public int[][] dct(int[][] source,int n) {
        int[][] fuv = new int[n][n];
        BigDecimal piDec = new BigDecimal(Double.toString(Math.PI));
        BigDecimal coefficient2 = new BigDecimal(Integer.toString(2));
        BigDecimal coefficient1 = new BigDecimal(Integer.toString(1));
        BigDecimal nDec = new BigDecimal(Integer.toString(n));
        for(int u=0; u<n; u++) {
            for(int v=0; v<n; v++) {
                BigDecimal f = new BigDecimal(0);
                BigDecimal uDec = new BigDecimal(Integer.toString(u));
                BigDecimal vDec = new BigDecimal(Integer.toString(v));
                for(int x=0; x<n; x++) {
                    for(int y=0; y<n; y++) {
                        BigDecimal sourceDec = new BigDecimal(Integer.toString(source[x][y]));
                        BigDecimal xDec = new BigDecimal(Integer.toString(x));
                        BigDecimal yDec = new BigDecimal(Integer.toString(y));
                        f = f.add(sourceDec.multiply(new BigDecimal(Double.toString(Math.cos(uDec.multiply(piDec).multiply(coefficient2.multiply(xDec).add(coefficient1)).divide(coefficient2.multiply(nDec),15,BigDecimal.ROUND_HALF_UP).doubleValue()))))
                                           .multiply(new BigDecimal(Double.toString(Math.cos(vDec.multiply(piDec).multiply(coefficient2.multiply(yDec).add(coefficient1)).divide(coefficient2.multiply(nDec),15,BigDecimal.ROUND_HALF_UP).doubleValue())))));
                    }
                }
                if(u == 0 && v == 0) {
                    fuv[u][v] = new Double(Math.rint(coefficient1.divide(nDec,15,BigDecimal.ROUND_HALF_UP).multiply(f).doubleValue())).intValue();
                    System.out.println("f = " + f+"；1 / n * f = " + Math.rint((double)1 / (double)n * f.doubleValue()));
                } else if ((u != 0 && v == 0) || (u == 0 && v != 0)) {
                    fuv[u][v] = new Double(Math.rint(new BigDecimal(Double.toString(Math.sqrt(2))).divide(nDec,15,BigDecimal.ROUND_HALF_UP).multiply(f).doubleValue())).intValue();
                    System.out.println("f = " + f+"；Math.sqrt(2) / n * f = " + Math.rint((double)Math.sqrt(2) / (double)n * f.doubleValue()));
                } else {
                    fuv[u][v] = new Double(Math.rint(coefficient2.divide(nDec,15,BigDecimal.ROUND_HALF_UP).multiply(f).doubleValue())).intValue();
                    System.out.println("f = " + f+"；2 / n * f = " + Math.rint((double)2 / (double)n * f.doubleValue()));
                }
                System.out.println("~~~转换为DCT【"+u+"，"+v+"】像素为:"+fuv[u][v]);
            }
        }
        return fuv;
    }

    public int[][] idct(int[][] result,int n) {
        int[][] fxy = new int[n][n];
        BigDecimal piDec = new BigDecimal(Double.toString(Math.PI));
        BigDecimal coefficient2 = new BigDecimal(Integer.toString(2));
        BigDecimal coefficient1 = new BigDecimal(Integer.toString(1));
        BigDecimal nDec = new BigDecimal(Integer.toString(n));
        for(int x=0; x<n; x++) {
            for(int y=0; y<n; y++) {
                BigDecimal f = new BigDecimal(0);
                BigDecimal xDec = new BigDecimal(Integer.toString(x));
                BigDecimal yDec = new BigDecimal(Integer.toString(y));
                for(int u=0; u<n; u++) {
                    for(int v=0; v<n; v++) {
                        BigDecimal a = new BigDecimal(0);
                        BigDecimal resultDec = new BigDecimal(Integer.toString(result[u][v]));
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
                System.out.println("~~~DCT反转换为【"+x+"，"+y+"】像素为:"+fxy[x][y]);
            }
        }
        return fxy;
    }
}
