package test_project.basic.swing;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test_swing extends JPanel {

	private Image image1;
	private String str1;// ͼƬ��λ�ã������srcĿ¼�£�ֱ������ͼƬ��λ��
	private double theta = 0.00;
	private double x, y;
	private Graphics g;

	public double getTheta() {
		return theta;
	}

	public void setTheta(double theta) {
		this.theta += theta;
	}

	public Test_swing(String str1) {
		this.str1 = str1;
		this.setLayout(null);

	}

	public void paintComponent(Graphics g) {

		// ͨ�����·����ȡͼƬ��λ��
		try {
			image1 = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream(str1));
		} catch (IOException e) {
			e.printStackTrace();
		}

		Graphics2D g2 = (Graphics2D) g;
		x = 580 - 15 * (28 * Math.pow(Math.sin(theta), 3));
		y = 240 - 15 * (20 * Math.cos(theta) - 6 * Math.cos(2 * theta) - 3 * Math.cos(3 * theta) - Math.cos(4 * theta));
		g2.drawImage(image1, (int) x, (int) y, null);
	}

	
	public static void main(String[] args) {
		Test_swing t = new Test_swing("C:\\Users\\Administrator\\Pictures\\redhair.png");
		JFrame frame = new JFrame();
		while(t.getTheta()< 2*Math.PI){
            t.repaint();//�������ػ�
            t.revalidate();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            frame.getContentPane().add(t);
            t.setTheta(0.33);//��������
        }
        frame.getContentPane().remove(t);
	}
}

	// ����ֻ�ǻ�һ�仨�Ĵ��룬Ҫ��һ�������İ��ģ�����ͨ���߳������ѭ�������������ʵ�ֵġ�

	/*public void run() {
        while(t.getTheta()< 2*Math.PI){
            t.repaint();//�������ػ�
            t.revalidate();
            try {
                Thread.sleep(time);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            frame.getContentPane().add(t);
            t.setTheta(0.33);//��������
        }
        frame.getContentPane().remove(t);
        
    }*/