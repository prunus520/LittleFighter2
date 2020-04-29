package LF2.custom.button;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class PictureJButton extends CustomJButton {

	private int fontSize;
	private BufferedImage btn_exited, btn_entered;
	private Color fontColor = new Color(90, 115, 214);
	private double widthSize = 0.192, heightSize = 0.046;

	public PictureJButton(String title, int fontSize, BufferedImage btn_entered, BufferedImage btn_exited) {
		super(title);
		this.fontSize = fontSize;
		this.btn_entered = btn_entered;
		this.btn_exited = btn_exited;
		this.setForeground(fontColor);
		this.setFont(new Font("仿宋體", Font.TYPE1_FONT, fontSize));
	}

	public void setSize(double widthSize, double heightSize) {
		this.widthSize = widthSize;
		this.heightSize = heightSize;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setWinSize(this.getParent().getWidth(), this.getParent().getHeight());
		switch (this.getState()) {
		case "released":
		case "exited":
			g.drawImage(btn_exited, 0, 0, (int) (this.getWinWidth() * widthSize), (int) (this.getWinHeight() * heightSize),
					null);
			fontColor = new Color(90, 115, 214);
			break;
		default:
			g.drawImage(btn_entered, 0, 0, (int) (this.getWinWidth() * widthSize), (int) (this.getWinHeight() * heightSize),
					null);
			fontColor = Color.WHITE;
			break;
		}
		this.setForeground(fontColor);
		this.setFont(new Font("仿宋體", Font.TYPE1_FONT, (int) (fontSize * this.getWinHeight() * 0.001)));
		this.setSize(new Dimension((int) (this.getWinWidth() *widthSize), (int) (this.getWinHeight() * heightSize)));
	}

}
