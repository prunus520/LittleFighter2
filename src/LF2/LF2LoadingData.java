package LF2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import LF2.custom.label.FlickerJLabel;
import LF2.custom.panel.CustomJPanel;

public class LF2LoadingData extends CustomJPanel implements Runnable {

	BufferedImage img;
	int j = 0, k = 0, l = 0;
	FlickerJLabel loading[] = new FlickerJLabel[2];
	String per[] = { "初始化", "載入bg資料", "載入capoo資料", "載入jake資料", "載入doraemon資料", "載入octopus資料", "載入中" };
	String wait[] = { ".", "..", "...", "....", ".....", "......" };
	Thread threadGameStart, threadWait, threadfont;
	int winWidth, winHeight;

	public LF2LoadingData() {
		init();
	}

	public void init() {
		try {
			img = ImageIO.read(getClass().getResource("image/system/loadingData.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 2; i++) {
			Color fontColor[] = { new Color(55, 100, 255), Color.white };
			loading[i] = new FlickerJLabel("", 50, fontColor[i]);
			loading[i].setSize(0.4, 0.1);
			this.add(loading[i]);
		}
	}

	public void initThread() {
		threadGameStart = new Thread(this);
		threadWait = new Thread(waiting);
		threadfont = new Thread(fonting);
		threadGameStart.start();
		threadWait.start();
		threadfont.start();
	}

	public void interruptThread() {
		threadGameStart.interrupt();
		threadWait.interrupt();
		threadfont.interrupt();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		winWidth = this.getWidth();
		winHeight = this.getHeight();
		Graphics2D g2 = (Graphics2D) g;
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
		for (int i = 0; i < 2; i++) {
			this.setJLocation(loading[i], 0.32, 0.65 + i * 0.05);
		}
		loading[0].setText(per[j]);
		loading[0].setIndex(l % 2);
		loading[1].setText("請稍後" + wait[k % 6]);
	}

	@Override
	public void run() {
		LF2Data data = null;
		while (true) {
			switch (j) {
			case 0:
				data = new LF2Data();
				break;
			case 1:
				data.bg();
				break;
			case 2:
				data.capoo();
				break;
			case 3:
				data.jake();
				break;
			case 4:
				data.doraemon();
				break;
			case 5:
				data.octopus();
				break;
			default:
				LF2Windows.window[0] = new LF2GameStart();
				LF2Windows.cardPanel.add(LF2Windows.window[0], "gameStart");
				LF2Windows.card.show(this.getParent(), "gameStart");
				interruptThread();
				LF2Windows.cardPanel.remove(LF2Windows.window[1]);
				break;
			}
			j++;
			repaint();
		}
	}

	Runnable waiting = new Runnable() {

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(400);
					k++;
				} catch (InterruptedException e) {
					// e.printStackTrace();
				}
				repaint();
			}
		}
	};

	Runnable fonting = new Runnable() {

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(200);
					l++;
				} catch (InterruptedException e) {
					// e.printStackTrace();
				}
				repaint();
			}
		}
	};
}
