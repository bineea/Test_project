package test_project.basic.swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test_sonw1 {
	public static void main(String[] args) {
		JFrame w = new JFrame();
		w.setSize(1240, 700);
		w.setLocation(100, 10);
		w.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		MyPanel mp = new MyPanel();
		mp.setBackground(new Color(125, 190, 255));
		w.add(mp);
		Thread t = new Thread(mp);
		t.start();
		w.setVisible(true);
	}
}

class MyPanel extends JPanel implements Runnable {
	int[] x = new int[400];
	int[] y = new int[400];

	MyPanel() {
		for (int i = 0; i < 200; i++) {
			x[i] = (int) (Math.random() * 1240);
			y[i] = (int) (Math.random() * 700);
		}
	}

	private static int[] size = new int[] { 20, 30, 40 };
	private static int[] snow = new int[400];

	public void paint(Graphics g) {
		Random r = new Random();
		super.paint(g);
		g.setColor(Color.WHITE);
		for (int i = 0; i < 400; i++) {
			int s = snow[i];
			if (s < 10) {
				Font f = new Font(null, Font.ROMAN_BASELINE, size[r.nextInt(3)]);
				g.setFont(f);
				snow[i] = f.getSize();
			} else {
				Font f = new Font(null, Font.ROMAN_BASELINE, s);
				g.setFont(f);
			}
			g.drawString("❉", x[i], y[i]);
		}
	}
	
	/*public void paintComponent(Graphics g) {
		// 通过相对路径获取图片的位置
		try {
			double theta = 0.33;
//			Image image1 = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("C:\\Users\\Administrator\\Pictures\\redhair.png"));
			Image image1 = ImageIO.read(new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\Desert2.jpg"));
//			Image image1 = ImageIO.read(new File("C:\\Users\\Administrator\\Pictures\\redhair.png"));
			Graphics2D g2 = (Graphics2D) g;
			double x = 580 - 15 * (28 * Math.pow(Math.sin(theta), 3));
			double y = 240 - 15 * (20 * Math.cos(theta) - 6 * Math.cos(2 * theta) - 3 * Math.cos(3 * theta) - Math.cos(4 * theta));
			g2.drawImage(image1, (int) x, (int) y, null);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}*/
	
	@Override
	public void run() {
		Random r = new Random();
		int flag = 0;
		while (true) {
			for (int i = 0; i < 400; i++) {
				y[i]++;
				flag = r.nextInt(2);
				if (flag > 0) {
					x[i]++;
				}
				if (x[i] > 1240) {
					x[i] = 0;
				}
				if (y[i] > 700) {
					y[i] = 0;
				}
			}
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			repaint();
		}
	}
}