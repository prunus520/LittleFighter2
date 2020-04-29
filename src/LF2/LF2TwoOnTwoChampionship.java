package LF2;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import LF2.custom.panel.CustomJPanel;

public class LF2TwoOnTwoChampionship extends CustomJPanel {

	BufferedImage img;
	int winWidth, winHeight;

	public LF2TwoOnTwoChampionship() {
		try {
			img = ImageIO.read(getClass().getResource("image/system/twoOnTwoChampionship.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		init();
	}

	public void init() {

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		winWidth = this.getWidth();
		winHeight = this.getHeight();
		g.drawImage(img, 0, 0, winWidth, winHeight, null);
	}
}
