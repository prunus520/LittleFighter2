package LF2.custom.label;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;

public class CustomJLabel extends JLabel {

	private double widthSize = 0, heightSize = 0;
	private boolean isSize = false;
	private int fontSize;
	private Color fontColor = Color.WHITE;
	private int winWidth, winHeight;

	public CustomJLabel(String title, int fontSize, Color fontColor) {
		super(title);
		this.fontSize = fontSize;
		this.fontColor = fontColor;
		this.setSize(new Dimension(1, 1));
		this.setHorizontalAlignment(JLabel.CENTER);
	}

	public Color getFontColor() {
		return fontColor;
	}

	public void setFontColor(Color fontColor) {
		this.fontColor = fontColor;
	}

	public int getFontSize() {
		return fontSize;
	}

	public boolean isSize() {
		return isSize;
	}

	public void setSize(double widthSize, double heightSize) {
		this.widthSize = widthSize;
		this.heightSize = heightSize;
		isSize = true;
	}

	public double getWidthSize() {
		return widthSize;
	}

	public double getHeightSize() {
		return heightSize;
	}

	public int getWinWidth() {
		return winWidth;
	}

	public void setWinWidth(int winWidth) {
		this.winWidth = winWidth;
	}

	public int getWinHeight() {
		return winHeight;
	}

	public void setWinHeight(int winHeight) {
		this.winHeight = winHeight;
	}

	public void setWinSize(int winWidth, int winHeight) {
		this.winWidth = winWidth;
		this.winHeight = winHeight;
	}

}
