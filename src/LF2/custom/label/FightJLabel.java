package LF2.custom.label;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;

public class FightJLabel extends CustomJLabel {

	boolean isFilled;

	public FightJLabel(String title, int fontSize, Color fontColor) {
		super(title, fontSize, fontColor);
	}

	public void setFilled(boolean isFilled) {
		this.isFilled = isFilled;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setWinSize(this.getParent().getWidth(), this.getParent().getHeight());
		if (isFilled) {
			setOpaque(true);
			setBackground(new Color(86, 112, 199));
		}else{
			setOpaque(false);
		}
		this.setForeground(this.getFontColor());
		this.setFont(new Font("仿宋體", Font.TYPE1_FONT, (int) (this.getFontSize() * this.getWinHeight() * 0.001)));
		if (this.isSize())
			this.setSize(new Dimension((int) (this.getWinWidth() * this.getWidthSize()),
					(int) (this.getWinHeight() * this.getHeightSize())));
	}

}
