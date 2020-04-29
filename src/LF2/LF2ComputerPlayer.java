package LF2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import LF2.custom.label.NumJLabel;
import LF2.custom.panel.CustomJPanel;

public class LF2ComputerPlayer extends CustomJPanel {

	BufferedImage img;
	private double widthSize = 0, heightSize = 0;
	NumJLabel num[] = new NumJLabel[8];
	int index = 0;

	public LF2ComputerPlayer(BufferedImage img) {
		this.img = img;
		init();
	}

	public void init() {
		for (int i = 0; i < 8; i++) {
			num[i] = new NumJLabel(String.valueOf(i), 150, Color.white);
			num[i].setSize(0.06, 0.19);
			this.add(num[i]);
		}
		num[index].setBorder(true);
	}

	public int getIndex() {
		num[index].setBorder(false);
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setSize(double widthSize, double heightSize) {
		this.widthSize = widthSize;
		this.heightSize = heightSize;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		num[index].setBorder(true);
		this.setSize(new Dimension((int) (this.getParent().getWidth() * widthSize),
				(int) (this.getParent().getHeight() * heightSize)));
		g.drawImage(img, 0, 0, (int) (this.getParent().getWidth() * widthSize),
				(int) (this.getParent().getHeight() * heightSize), null);
		for (int i = 0; i < 8; i++) {
			this.setJLocation(num[i], 0.168 + i * 0.082, 0.62);
		}
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
