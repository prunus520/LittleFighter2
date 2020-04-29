package LF2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import LF2.custom.label.FightJLabel;
import LF2.custom.panel.CustomJPanel;

public class LF2FightSelect extends CustomJPanel {

	BufferedImage img;
	private double widthSize = 0, heightSize = 0;
	FightJLabel fight[] = new FightJLabel[4];
	String fightString[] = { "Fight! (開始)", "Reset All (重新選擇角色)", "Reset Random (更新隨機角色)", "Exit (離開)" };
	int index = 2;

	public LF2FightSelect(BufferedImage img) {
		this.img = img;
		init();
	}

	public void init() {
		for (int i = 0; i < 4; i++) {
			fight[i] = new FightJLabel(fightString[i], 110, new Color(149, 189, 255));
			fight[i].setSize(0.9, 0.19);
			this.add(fight[i]);
		}
		fight[index].setFilled(true);
	}

	public int getIndex() {
		fight[index].setFilled(false);
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public void setSize(double widthSize, double heightSize) {
		this.widthSize = widthSize;
		this.heightSize = heightSize;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		fight[index].setFilled(true);
		this.setSize(new Dimension((int) (this.getParent().getWidth() * widthSize),
				(int) (this.getParent().getHeight() * heightSize)));
		g.drawImage(img, 0, 0, (int) (this.getParent().getWidth() * widthSize),
				(int) (this.getParent().getHeight() * heightSize), null);
		for (int i = 0; i < 4; i++) {
			this.setJLocation(fight[i], 0.052, 0.1 + i * 0.2);
		}
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
