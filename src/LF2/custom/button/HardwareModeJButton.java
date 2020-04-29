package LF2.custom.button;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class HardwareModeJButton extends CustomJButton {

	private ArrayList<BufferedImage> keyboardMode;
	int hardward;

	public HardwareModeJButton(ArrayList<BufferedImage> keyboardMode, int hardward) {
		super();
		this.keyboardMode = keyboardMode;
		this.hardward = hardward;
	}

	public void setHardward(int hardward) {
		this.hardward = hardward % keyboardMode.size();
	}

	public int getHardward() {
		return hardward;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		this.setWinSize((int) (this.getParent().getWidth() * 0.133), (int) (this.getParent().getHeight() * 0.1647));
		g.drawImage(keyboardMode.get(hardward), 0, 0, this.getWinWidth(), this.getWinHeight(), null);
		this.setSize(new Dimension(this.getWinWidth(), this.getWinHeight()));
	}

}
