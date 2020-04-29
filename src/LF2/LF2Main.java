package LF2;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import LF2.custom.button.AreaFilledJButton;
import LF2.custom.button.ClickListener;
import LF2.custom.mp3.MP3Player;
import LF2.custom.panel.CustomJPanel;

public class LF2Main extends CustomJPanel implements ClickListener {

	BufferedImage bg;
	AreaFilledJButton main[] = new AreaFilledJButton[3];

	public LF2Main() {
		init();
	}

	public void init() {
		try {
			bg = ImageIO.read(getClass().getResource("image/system/main.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 3; i++) {
			String mainTitle[] = { "game start (開始遊戲)", "network game (連線遊戲)", "control settings (控制設定)" };
			main[i] = new AreaFilledJButton(mainTitle[i], 38, false);
			main[i].setSize(0.366, 0.05);
			main[i].addClickListener(this);
			this.add(main[i]);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bg, 0, 0, this.getWidth(), this.getHeight(), null);
		for (int i = 0; i < 3; i++) {
			this.setJLocation(main[i], 0.316, 0.425 + i * 0.1);
		}
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void ClickListener(String event) {
		for (int i = 0; i < 3; i++) {
			if (event == main[i].getActionCommand() && main[i].getMouseStatus() == 16) {
				new MP3Player(getClass().getResourceAsStream("music/m_ok.mp3")).play();
				switch (i) {
				case 0:
					LF2Windows.window[1] = new LF2LoadingData();
					LF2Windows.cardPanel.add(LF2Windows.window[1], "loadingData");
					LF2Windows.card.show(this.getParent(), "loadingData");
					LF2Windows.cardPanel.remove(LF2Windows.window[0]);
					((LF2LoadingData) LF2Windows.window[1]).initThread();
					break;
				case 1:
					LF2Windows.window[2] = new LF2NetworkGame();
					LF2Windows.cardPanel.add(LF2Windows.window[2], "networkGame");
					LF2Windows.card.show(this.getParent(), "networkGame");
					LF2Windows.cardPanel.remove(LF2Windows.window[0]);
					break;
				case 2:
					LF2Windows.card.show(this.getParent(), "controlSettings");
					((LF2ControlSettings) LF2Windows.window[3]).readingFile();
					LF2Windows.cardPanel.remove(LF2Windows.window[0]);
					break;
				}
			}
		}
	}

}
