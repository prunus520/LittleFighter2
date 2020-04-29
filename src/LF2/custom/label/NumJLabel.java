package LF2.custom.label;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.BorderFactory;

public class NumJLabel extends CustomJLabel {

	boolean isBorder;

	public NumJLabel(String title, int fontSize, Color fontColor) {
		super(title, fontSize, fontColor);
	}

	public boolean isBorder() {
		return isBorder;
	}

	public void setBorder(boolean isBorder) {
		this.isBorder = isBorder;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setWinSize(this.getParent().getWidth(), this.getParent().getHeight());
		if (isBorder)
			this.setBorder(BorderFactory.createMatteBorder(2, 2, 2, 2, this.getFontColor()));
		else
			this.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, this.getFontColor()));
		this.setForeground(this.getFontColor());
		this.setFont(new Font("仿宋體", Font.TYPE1_FONT, (int) (this.getFontSize() * this.getWinHeight() * 0.001)));
		if (this.isSize())
			this.setSize(new Dimension((int) (this.getWinWidth() * this.getWidthSize()),
					(int) (this.getWinHeight() * this.getHeightSize())));
	}

}
