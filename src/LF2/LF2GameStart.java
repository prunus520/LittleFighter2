package LF2;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import LF2.custom.button.AreaFilledJButton;
import LF2.custom.button.ClickListener;
import LF2.custom.mp3.MP3Player;
import LF2.custom.panel.CustomJPanel;
import LF2.player_key.Keyboard;
import LF2.player_key.Player;

public class LF2GameStart extends CustomJPanel implements ClickListener, Runnable {

	BufferedImage img;
	AreaFilledJButton gameStart[] = new AreaFilledJButton[4];
	int index = 0, TelnetIndex = 0, TelnetEnter = -1;
	int indexFlag = 0;
	Thread TelnetThread;

	public LF2GameStart() {
		init();
	}

	public void init() {
		try {
			img = ImageIO.read(getClass().getResource("image/system/gameStart.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		String gameStartTitle[] = { "VS mode (對決模式)", "1 on 1 Championship (淘汰賽)", "2 on 2 Championship (淘汰賽)",
				"Quit (離開遊戲)" };
		for (int i = 0; i < 4; i++) {
			gameStart[i] = new AreaFilledJButton(gameStartTitle[i], 38, false);
			gameStart[i].setSize(0.407, 0.05);
			gameStart[i].setIndex(i);
			gameStart[i].addClickListener(this);
			this.add(gameStart[i]);
		}
		gameStart[index].setFilled(true);
		if (LF2Server.socket != null || LF2Client.socket != null) {
			TelnetThread = new Thread(this);
			TelnetThread.start();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
		for (int i = 0; i < 4; i++) {
			this.setJLocation(gameStart[i], 0.296, 0.366 + i * 0.1);
			if (gameStart[i].getState() == "entered") {
				index = gameStart[i].getIndex();
				gameStart[indexFlag].setFilled(false);
				gameStart[index].setFilled(true);
			}
		}
		if (Keyboard.isPressed == true) {
			for (int i = 0; i < 4; i++) {
				if (Player.playerKeyInfors[i].getUp() == Keyboard.code) {
					gameStart[index].setFilled(false);
					index = (index + 3) % 4;
					gameStart[index].setFilled(true);
				} else if (Player.playerKeyInfors[i].getDown() == Keyboard.code) {
					gameStart[index].setFilled(false);
					index = (index + 5) % 4;
					gameStart[index].setFilled(true);
				} else if (Player.playerKeyInfors[i].getAttack() == Keyboard.code) {
					TelnetEnter = index;
					gotoNext(index);
				}
			}
			Keyboard.isPressed = false;
		}
		indexFlag = index;
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// e.printStackTrace();
		}
	}

	private void gotoNext(int i) {
		switch (i) {
		case 0:
			new MP3Player(getClass().getResourceAsStream("music/m_ok.mp3")).play();
			LF2Windows.window[1] = new LF2VSMode();
			LF2Windows.cardPanel.add(LF2Windows.window[1], "VSMode");
			LF2Windows.card.show(this.getParent(), "VSMode");
			((LF2VSMode) LF2Windows.window[1]).threadStart();
			LF2Windows.cardPanel.remove(LF2Windows.window[0]);
			break;
		case 1:
			new MP3Player(getClass().getResourceAsStream("music/m_ok.mp3")).play();
			LF2Windows.window[1] = new LF2OneOnOneChampionship();
			LF2Windows.cardPanel.add(LF2Windows.window[1], "LF2OOOC");
			LF2Windows.card.show(this.getParent(), "LF2OOOC");
			LF2Windows.cardPanel.remove(LF2Windows.window[0]);
			break;
		case 2:
			new MP3Player(getClass().getResourceAsStream("music/m_ok.mp3")).play();
			LF2Windows.window[1] = new LF2TwoOnTwoChampionship();
			LF2Windows.cardPanel.add(LF2Windows.window[1], "LF2TOTC");
			LF2Windows.card.show(this.getParent(), "LF2TOTC");
			LF2Windows.cardPanel.remove(LF2Windows.window[0]);
			break;
		case 3:
			System.exit(0);
			break;
		}
		if (LF2Server.socket != null || LF2Client.socket != null)
			TelnetThread.interrupt();
	}

	@Override
	public void ClickListener(String event) {
		for (int i = 0; i < 4; i++) {
			if (event == gameStart[i].getActionCommand()) {
				TelnetEnter = i;
				gotoNext(i);
			}
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				String message;
				String[] messageSplit;
				if (LF2Server.socket != null) {
					LF2Server.writer.println(indexFlag + "~" + TelnetEnter);
					LF2Server.writer.flush();
					message = LF2Server.reader.readLine();
					messageSplit = message.split("~");
					TelnetIndex = Integer.valueOf(messageSplit[0]);
					if (Integer.valueOf(messageSplit[1]) != -1) {
						gotoNext(Integer.valueOf(messageSplit[1]));
					}
					for (int i = 0; i < 4; i++)
						gameStart[i].setFilled(false);
					gameStart[TelnetIndex].setFilled(true);
					indexFlag = TelnetIndex;
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// e.printStackTrace();
					}
				} else if (LF2Client.socket != null) {
					TelnetIndex = indexFlag;
					LF2Client.writer.println(TelnetIndex + "~" + TelnetEnter);
					LF2Client.writer.flush();
					message = LF2Client.reader.readLine();
					messageSplit = message.split("~");
					TelnetIndex = Integer.valueOf(messageSplit[0]);
					if (Integer.valueOf(messageSplit[1]) != -1) {
						gotoNext(Integer.valueOf(messageSplit[1]));
					}
					for (int i = 0; i < 4; i++)
						gameStart[i].setFilled(false);
					gameStart[TelnetIndex].setFilled(true);
					indexFlag = TelnetIndex;
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// e.printStackTrace();
					}
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
