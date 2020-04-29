package LF2;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import LF2.custom.panel.CustomJPanel;

public class LF2OneOnOneChampionship extends CustomJPanel {

	BufferedImage img;
	int winWidth, winHeight;

	public LF2OneOnOneChampionship() {
		try {
			img = ImageIO.read(getClass().getResource("image/system/oneOnOneChampionship.png"));
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
