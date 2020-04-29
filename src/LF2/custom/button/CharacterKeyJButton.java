package LF2.custom.button;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

public class CharacterKeyJButton extends CustomJButton {

	private int fontSize;
	private Color fontColor = Color.WHITE;

	public CharacterKeyJButton(String title, int fontSize) {
		super(title);
		this.fontSize = fontSize;
		this.setForeground(fontColor);
		this.setFont(new Font("仿宋體", Font.TYPE1_FONT, fontSize));
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setWinSize(this.getParent().getWidth(), this.getParent().getHeight());
		switch (this.getState()) {
		case "key":
			fontColor = Color.WHITE;
			break;
		case "pressed":
			fontColor = new Color(25, 70, 255);
			break;
		}
		this.setForeground(fontColor);
		this.setFont(new Font("仿宋體", Font.TYPE1_FONT, (int) (fontSize * this.getWinHeight() * 0.001)));
		this.setSize(new Dimension((int) (this.getWinWidth() * this.getWidthSize()),
				(int) (this.getWinHeight() * this.getHeightSize())));
	}

}
