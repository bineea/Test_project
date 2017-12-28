package test_project;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JFrame;

public class Test_snow2 {
	public static void main(String[] args) {
		MainFrame2 mf = new MainFrame2();

		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

		mf.setSize(dim.width, dim.height);
		mf.setVisible(true);
	}
}

class MainFrame2 extends JFrame {
	private static final long serialVersionUID = 1L;
	private Dimension scrSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int SNOW_NUM = 400;
	public static final int SPEED = 20;

	private int[] xs = new int[scrSize.width];
	private int[] ys = new int[scrSize.height];

	public MainFrame2() {
		initSnow();
		fallSnow();

		setUndecorated(true);
		setBackground(new Color(0, 0, 0, 0));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void initSnow() {
		Random rd = new Random();
		for (int i = 0; i < SNOW_NUM; i++) {
			xs[i] = rd.nextInt(scrSize.width);
			ys[i] = rd.nextInt(scrSize.height);
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		try {
			Image image1 = ImageIO.read(new File("C:\\Users\\Public\\Pictures\\Sample Pictures\\08.png"));
			Graphics2D g2 = (Graphics2D) g;
			for (int i = 0; i < SNOW_NUM; i++) {
				g2.drawImage(image1, xs[i], ys[i], 15, 15, null);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void fallSnow() {
		new Thread() {
			@Override
			public void run() {
				while (true) {
					for (int i = 0; i < SNOW_NUM; i++) {
						ys[i]++;
						if (ys[i] > scrSize.height) {
							ys[i] = 0;
						}
					}
					try {
						Thread.sleep(SPEED);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					repaint();
				}
			}
		}.start();
	}
}