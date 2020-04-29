package LF2;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;

import LF2.custom.button.CharacterKeyJButton;
import LF2.custom.button.ClickListener;
import LF2.custom.button.HardwareModeJButton;
import LF2.custom.button.PictureJButton;
import LF2.custom.mp3.MP3Player;
import LF2.custom.panel.CustomJPanel;
import LF2.player_key.Keyboard;
import LF2.player_key.Player;

public class LF2ControlSettings extends CustomJPanel implements ClickListener {

	BufferedImage img, btn_exited, btn_entered;
	CharacterKeyJButton name[] = new CharacterKeyJButton[4];
	CharacterKeyJButton key[][] = new CharacterKeyJButton[4][7];
	PictureJButton ok, cancel;
	HardwareModeJButton[] mode = new HardwareModeJButton[4];
	ArrayList<BufferedImage> keyboardMode = new ArrayList<BufferedImage>();
	String event;

	public LF2ControlSettings() {
		init();
	}

	public void init() {
		try {
			img = ImageIO.read(getClass().getResource("image/system/controlSettings.png"));
			btn_exited = ImageIO.read(getClass().getResource("image/button/custombtn_exited.png"));
			btn_entered = ImageIO.read(getClass().getResource("image/button/custombtn_entered.png"));
			for (int i = 1; i <= 3; i++)
				keyboardMode.add(ImageIO.read(getClass().getResource("image/system/keyboardMode" + i + ".png")));
		} catch (IOException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 4; i++) {
			name[i] = new CharacterKeyJButton("1", 25);
			name[i].setSize(0.133, 0.033);
			name[i].setActionCommand("name: " + i);
			name[i].setHorizontalAlignment(JButton.LEFT);
			name[i].addClickListener(this);
			this.add(name[i]);
		}

		for (int i = 0; i < 4; i++) {
			mode[i] = new HardwareModeJButton(keyboardMode, 0);
			mode[i].setActionCommand("mode: " + i);
			mode[i].addClickListener(this);
			this.add(mode[i]);
		}

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 7; j++) {
				key[i][j] = new CharacterKeyJButton("A", 25);
				key[i][j].setSize(0.133, 0.033);
				key[i][j].setActionCommand("key: " + i + " " + (j));
				key[i][j].addClickListener(this);
				this.add(key[i][j]);
			}
		}

		readingFile();

		ok = new PictureJButton("ok (確定)", 29, btn_entered, btn_exited);
		ok.addClickListener(this);
		this.add(ok);
		cancel = new PictureJButton("cancel (取消)", 29, btn_entered, btn_exited);
		cancel.addClickListener(this);
		this.add(cancel);
	}

	public void readingFile() {
		if (!isFIle())
			reloadFile();
		readFile();
	}

	public boolean isFIle() {
		try {
			new FileReader("keyboard.txt");
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void readFile() {
		try {
			FileReader in = new FileReader("keyboard.txt");
			BufferedReader br = new BufferedReader(in);
			int i = 0, j = 0;
			while (br.ready()) {
				String string = br.readLine();
				if (j == 0) {
					name[i].setText(string);
					name[i].setTitle(string);
					Player.playerKeyInfors[i].setName(string);
				} else if (j == 1) {
					mode[i].setHardward(Integer.valueOf(string));
					mode[i].setHardward(Integer.valueOf(string));
				} else {
					key[i][j - 2].setText(string);
					key[i][j - 2].setTitle(string);
					setPlayerKey(i, j - 2, string);
				}
				j++;
				if (j == 9) {
					i++;
					j = 0;
				}
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void setPlayerKey(int i, int j, String string) {
		switch (j) {
		case 0:
			Player.playerKeyInfors[i].setUp(string);
			break;
		case 1:
			Player.playerKeyInfors[i].setDown(string);
			break;
		case 2:
			Player.playerKeyInfors[i].setLeft(string);
			break;
		case 3:
			Player.playerKeyInfors[i].setRight(string);
			break;
		case 4:
			Player.playerKeyInfors[i].setAttack(string);
			break;
		case 5:
			Player.playerKeyInfors[i].setJump(string);
			break;
		case 6:
			Player.playerKeyInfors[i].setDefend(string);
			break;
		}
	}

	public void reloadFile() {
		try {
			InputStream in = getClass().getResourceAsStream("keyboard.txt");
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			FileWriter fw = new FileWriter("keyboard.txt");
			while (br.ready()) {
				fw.write(br.readLine() + "\r\n");
			}
			fw.flush();
			fw.close();
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
		for (int i = 0; i < 4; i++) {
			if (event == name[i].getActionCommand() && Keyboard.isPressed == true) {
				String buf;
				if (Keyboard.code == 8) {
					buf = name[i].getTitle();
					if (buf.length() > 0)
						buf = buf.substring(0, buf.length() - 1);
				} else if (Keyboard.code >= 32 && Keyboard.code <= 126)
					buf = name[i].getTitle() + String.valueOf(Keyboard.codeChar);
				else {
					buf = name[i].getTitle();
				}
				name[i].setTitle(buf);
				name[i].setText(buf + "_");
				Keyboard.isPressed = false;
			}
			this.setJLocation(name[i], 0.246 + i * 0.1738, 0.287);
		}
		for (int i = 0; i < 4; i++)
			this.setJLocation(mode[i], 0.246 + i * 0.1738, 0.333);
		for (int i = 0; i < 4; i++)
			for (int j = 0; j < 7; j++) {
				if (event == key[i][j].getActionCommand() && Keyboard.isPressed == true) {
					key[i][j].setText(Keyboard.codeString);
					key[i][j].setState("key");
					event = "";
				}
				this.setJLocation(key[i][j], 0.246 + i * 0.1738, 0.511 + j * 0.03975);
			}
		this.setJLocation(ok, 0.496, 0.794);
		this.setJLocation(cancel, 0.704, 0.794);
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void previous() {
		LF2Windows.window[0] = new LF2Main();
		LF2Windows.cardPanel.add(LF2Windows.window[0], "main");
		LF2Windows.card.show(this.getParent(), "main");
	}

	@Override
	public void ClickListener(String event) {
		this.event = event;
		if (event == ok.getActionCommand() && ok.getMouseStatus() == 16) {
			new MP3Player(getClass().getResourceAsStream("music/m_cancel.mp3")).play();
			try {
				FileWriter fw = new FileWriter("keyboard.txt");
				StringBuffer buf = new StringBuffer();
				int i = 0, j = 0;
				while (true) {
					if (i == 4) {
						break;
					}
					if (j == 0) {
						buf.append(name[i].getTitle() + "\r\n");
						Player.playerKeyInfors[i].setName(name[i].getTitle());
					} else if (j == 1) {
						buf.append(mode[i].getHardward() + "\r\n");
					} else {
						buf.append(key[i][j - 2].getText() + "\r\n");
						setPlayerKey(i, j - 2, key[i][j - 2].getText());
					}
					j++;
					if (j == 9) {
						i++;
						j = 0;
					}
				}
				fw.write(buf.toString());
				fw.flush();
				fw.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			previous();
			for (int i = 0; i < 4; i++) {
				name[i].setState("key");
				for (int j = 0; j < 7; j++) {
					key[i][j].setState("key");
				}
			}
		} else if (event == cancel.getActionCommand() && cancel.getMouseStatus() == 16) {
			new MP3Player(getClass().getResourceAsStream("music/m_cancel.mp3")).play();
			previous();
			for (int i = 0; i < 4; i++) {
				name[i].setState("key");
				for (int j = 0; j < 7; j++) {
					key[i][j].setState("key");
				}
			}
		} else {
			for (int i = 0; i < 4; i++) {
				if (event == name[i].getActionCommand()) {
					name[i].setText(name[i].getTitle());
					name[i].setText(name[i].getText() + "_");
					name[i].setState("pressed");
				} else {
					name[i].setText(name[i].getTitle());
					name[i].setState("key");
				}
				if (event == mode[i].getActionCommand()) {
					mode[i].setHardward(mode[i].getHardward() + 1);
				}
				for (int j = 0; j < 7; j++) {
					if (event == key[i][j].getActionCommand()) {
						key[i][j].setState("pressed");
					} else {
						key[i][j].setState("key");
					}
				}
			}
		}
	}
}
