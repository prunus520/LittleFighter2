package LF2.custom.panel;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JPanel;

import LF2.LF2Data;

public class CharacterChoose extends JPanel implements Runnable {

	private int point, index, number = 4;
	private List<BufferedImage> character, num;
	private double widthSize = 0, heightSize = 0;
	private Thread thread;
	private int choose = 0;
	private boolean numFlag = false, characterFlag = true;
	private int sleep = 1000;

	public CharacterChoose() {
		character = LF2Data.join;
		num = LF2Data.num;
		this.setSize(new Dimension(1, 1));
		this.setOpaque(false);
		thread = new Thread(this);
	}

	public void interrupt() {
		thread.interrupt();
	}

	public void setSize(double widthSize, double heightSize) {
		this.widthSize = widthSize;
		this.heightSize = heightSize;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
		if (index == 1) {
			point++;
			if (point == 8)
				point = 3;
		} else {
			point--;
			if (point == 2)
				point = 7;
		}
	}

	public int getChoose() {
		return choose;
	}

	public void setChoose(int choose) {
		this.choose = choose;
	}

	public void setPoint(int point) {
		this.point = point;
	}

	public int getPoint() {
		return point;
	}

	public boolean isNumFlag() {
		return numFlag;
	}

	public void setNumFlag(boolean numFlag) {
		this.numFlag = numFlag;
		if (numFlag)
			thread.start();
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		if (this.number - number <= 0) {
			this.number = 0;
			sleep = 0;
		} else
			this.number -= number;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		if (choose == 0) {
			point++;
			point %= 3;
		}

		this.setSize(new Dimension((int) (this.getParent().getWidth() * widthSize),
				(int) (this.getParent().getHeight() * heightSize)));
		if (numFlag)
			g.drawImage(num.get(number), 0, 0, (int) (this.getParent().getWidth() * widthSize),
					(int) (this.getParent().getHeight() * heightSize), null);
		if (characterFlag)
			g.drawImage(character.get(point), 0, 0, (int) (this.getParent().getWidth() * widthSize),
					(int) (this.getParent().getHeight() * heightSize), null);
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (numFlag)
			repaint();
	}

	@Override
	public void run() {
		while (true) {
			if (number > 0) {
				characterFlag = false;
				try {
					Thread.sleep(sleep);
					number--;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				this.repaint();
			} else if (!characterFlag) {
				number = 0;
				try {
					Thread.sleep(sleep);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				this.setVisible(false);
				// this.interrupt();
				break;
			}
		}
	}

}
