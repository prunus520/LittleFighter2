package LF2.custom.label;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

public class FlickerJLabel extends CustomJLabel {

	private int index = 0;
	private boolean isReady = false;

	public FlickerJLabel(String title, int fontSize, Color fontColor) {
		super(title, fontSize, fontColor);
	}

	public boolean isReady() {
		return isReady;
	}

	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setWinSize(this.getParent().getWidth(), this.getParent().getHeight());
		if (index == 0 && isReady == false)
			this.setForeground(this.getFontColor());
		else
			this.setForeground(Color.white);
		this.setFont(new Font("仿宋體", Font.TYPE1_FONT, (int) (this.getFontSize() * this.getWinHeight() * 0.001)));
		if (this.isSize())
			this.setSize(new Dimension((int) (this.getWinWidth() * this.getWidthSize()),
					(int) (this.getWinHeight() * this.getHeightSize())));
	}

}