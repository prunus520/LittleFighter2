package LF2;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import javax.swing.JButton;

import LF2.custom.CustomThread;
import LF2.custom.CustomThread.ThreadListener;
import LF2.custom.button.CharacterKeyJButton;
import LF2.custom.button.ClickListener;
import LF2.custom.button.PictureJButton;
import LF2.custom.mp3.MP3Player;
import LF2.custom.panel.CustomJPanel;
import LF2.player_key.Keyboard;

public class LF2ConnentToOpponent extends CustomJPanel implements ClickListener, ThreadListener {

	BufferedImage img, btn_exited, btn_entered;
	PictureJButton ok, cancel;
	CharacterKeyJButton ip;
	double widthSize = 0, heightSize = 0;
	CustomThread serverThread;

	public LF2ConnentToOpponent(BufferedImage img, BufferedImage btn_exited, BufferedImage btn_entered) {
		this.img = img;
		this.btn_exited = btn_exited;
		this.btn_entered = btn_entered;
		init();
	}

	public void init() {
		ip = new CharacterKeyJButton("", 150);
		ip.setSize(0.6, 0.265);
		ip.setHorizontalAlignment(JButton.LEFT);
		ip.addClickListener(this);
		this.add(ip);

		ok = new PictureJButton("ok (確定)", 150, btn_entered, btn_exited);
		ok.setSize(0.42, 0.2);
		ok.addClickListener(this);
		this.add(ok);
		cancel = new PictureJButton("cancel (取消)", 150, btn_entered, btn_exited);
		cancel.setSize(0.42, 0.2);
		cancel.addClickListener(this);
		this.add(cancel);
		serverThread = new CustomThread();
		serverThread.addThreadListener(this);
	}

	public void clientGo() {
		new LF2Client();
		try {
			LF2Client.socket = new Socket(ip.getTitle(), 6666);
		} catch (IOException e) {
			e.printStackTrace();
		}
		serverThread.start();
	}

	public void setSize(double widthSize, double heightSize) {
		this.widthSize = widthSize;
		this.heightSize = heightSize;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (Keyboard.isPressed == true) {
			String buf;
			if (Keyboard.code == 8) {
				buf = ip.getTitle();
				if (buf.length() > 0)
					buf = buf.substring(0, buf.length() - 1);
			} else if (Keyboard.code >= 32 && Keyboard.code <= 126)
				buf = ip.getTitle() + String.valueOf(Keyboard.codeChar);
			else {
				buf = ip.getTitle();
			}
			ip.setTitle(buf);
			ip.setText(ip.getTitle());
			Keyboard.isPressed = false;
		}
		this.setSize(new Dimension((int) (this.getParent().getWidth() * widthSize),
				(int) (this.getParent().getHeight() * heightSize)));
		g.drawImage(img, 0, 0, (int) (this.getParent().getWidth() * widthSize),
				(int) (this.getParent().getHeight() * heightSize), null);
		this.setJLocation(ip, 0.206, 0.418);
		this.setJLocation(ok, 0.056, 0.734);
		this.setJLocation(cancel, 0.527, 0.734);
		repaint();
	}

	@Override
	public void ClickListener(String event) {
		if (event == cancel.getActionCommand()) {
			new MP3Player(getClass().getResourceAsStream("music/m_cancel.mp3")).play();
			ip.setText("");
			ip.setTitle("");
			this.setVisible(false);
		} else if (event == ok.getActionCommand()) {
			new MP3Player(getClass().getResourceAsStream("music/m_ok.mp3")).play();
			clientGo();
		}
	}

	@Override
	public void ThreadListener() {
		try {
			if (LF2Client.socket != null) {
				LF2Client.writer = new PrintStream(LF2Client.socket.getOutputStream());
				LF2Client.reader = new BufferedReader(new InputStreamReader(LF2Client.socket.getInputStream()));

				LF2Windows.window[1] = new LF2LoadingData();
				LF2Windows.cardPanel.add(LF2Windows.window[1], "loadingData");
				LF2Windows.card.show(this.getParent().getParent(), "loadingData");
				((LF2LoadingData) LF2Windows.window[1]).initThread();
				LF2Windows.cardPanel.remove(LF2Windows.window[2]);
			} else {
				this.setVisible(false);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
