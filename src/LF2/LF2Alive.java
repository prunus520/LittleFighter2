package LF2;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import LF2.custom.panel.CustomJPanel;

public class LF2Alive extends CustomJPanel {

	BufferedImage img, fighter;
	private double widthSize = 0, heightSize = 0;

	public LF2Alive(BufferedImage img) {
		this.img = img;
	}

	public void setSize(double widthSize, double heightSize) {
		this.widthSize = widthSize;
		this.heightSize = heightSize;
	}

	public void win(BufferedImage fighter) {
		this.fighter = fighter;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setSize(new Dimension((int) (this.getParent().getWidth() * widthSize),
				(int) (this.getParent().getHeight() * heightSize)));
		g.drawImage(img, 0, 0, (int) (this.getParent().getWidth() * widthSize),
				(int) (this.getParent().getHeight() * heightSize), null);
		g.drawImage(fighter, (int) (this.getParent().getWidth() * widthSize * 0.2),
				(int) (this.getParent().getHeight() * heightSize * 0.1),
				(int) (this.getParent().getWidth() * widthSize * 0.6),
				(int) (this.getParent().getHeight() * heightSize * 0.6), null);
		try {
			Thread.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
