package LF2.custom.button;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

public class AreaFilledJButton extends CustomJButton {

	private int fontSize;
	private Color fontColor = new Color(90, 115, 214);
	private int index;

	public AreaFilledJButton(String title, int fontSize, boolean isFilled) {
		super(title);
		this.fontSize = fontSize;
		this.setFilled(isFilled);
		this.setForeground(fontColor);
		this.setFont(new Font("仿宋體", Font.TYPE1_FONT, fontSize));
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setWinSize(this.getParent().getWidth(), this.getParent().getHeight());
		switch (this.getState()) {
		case "exited":
			fontColor = new Color(90, 115, 214);
			break;
		case "entered":
			fontColor = Color.WHITE;
			break;
		}
		this.setContentAreaFilled(this.isFilled());
		this.setBackground(new Color(59, 78, 142));
		this.setForeground(fontColor);
		this.setFont(new Font("仿宋體", Font.TYPE1_FONT, (int) (fontSize * this.getWinHeight() * 0.001)));
		if (this.isSize())
			this.setSize(new Dimension((int) (this.getWinWidth() * this.getWidthSize()),
					(int) (this.getWinHeight() * this.getHeightSize())));
	}

}
