package LF2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.imageio.ImageIO;

import LF2.custom.button.AreaFilledJButton;
import LF2.custom.button.ClickListener;
import LF2.custom.button.PictureJButton;
import LF2.custom.label.FlickerJLabel;
import LF2.custom.mp3.MP3Player;
import LF2.custom.panel.CustomJPanel;

public class LF2NetworkGame extends CustomJPanel implements ClickListener {

	BufferedImage img, img1, img2, btn_exited, btn_entered;
	FlickerJLabel IP;
	AreaFilledJButton waitingForOpponent, connectToOpponent;
	LF2WaitingForOpponent lf2WaitingForOpponent;
	LF2ConnentToOpponent lf2ConnentToOpponent;
	PictureJButton cancel;

	public LF2NetworkGame() {
		init();
	}

	public void init() {
		try {
			img = ImageIO.read(getClass().getResource("image/system/networkGame.png"));
			img1 = ImageIO.read(getClass().getResource("image/system/waitingForOpponent.png"));
			img2 = ImageIO.read(getClass().getResource("image/system/connectToOpponent.png"));
			btn_exited = ImageIO.read(getClass().getResource("image/button/custombtn_exited.png"));
			btn_entered = ImageIO.read(getClass().getResource("image/button/custombtn_entered.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		InetAddress myIP = null;
		try {
			myIP = InetAddress.getLocalHost();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}

		lf2WaitingForOpponent = new LF2WaitingForOpponent(img1, btn_exited, btn_entered);
		lf2WaitingForOpponent.setSize(0.419, 0.195);
		this.add(lf2WaitingForOpponent);
		lf2WaitingForOpponent.setVisible(false);

		lf2ConnentToOpponent = new LF2ConnentToOpponent(img2, btn_exited, btn_entered);
		lf2ConnentToOpponent.setSize(0.455, 0.224);
		this.add(lf2ConnentToOpponent);
		lf2ConnentToOpponent.setVisible(false);

		IP = new FlickerJLabel("Your IP Address: " + myIP.getHostAddress(), 29, Color.white);
		IP.setSize(0.372, 0.052);
		this.add(IP);
		waitingForOpponent = new AreaFilledJButton("Waiting for Opponent (等待對手)", 29, false);
		waitingForOpponent.setSize(0.372, 0.052);
		waitingForOpponent.addClickListener(this);
		this.add(waitingForOpponent);
		connectToOpponent = new AreaFilledJButton("Conect to Opponent (連接對手)", 29, false);
		connectToOpponent.setSize(0.372, 0.052);
		connectToOpponent.addClickListener(this);
		this.add(connectToOpponent);
		cancel = new PictureJButton("cancel (取消)", 29, btn_entered, btn_exited);
		cancel.addClickListener(this);
		this.add(cancel);
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), null);
		this.setJLocation(IP, 0.322, 0.445);
		this.setJLocation(waitingForOpponent, 0.322, 0.495);
		this.setJLocation(connectToOpponent, 0.322, 0.553);
		this.setJLocation(cancel, 0.412, 0.612);
		if (lf2WaitingForOpponent.isVisible())
			this.setJLocation(lf2WaitingForOpponent, 0.299, 0.528);
		if (lf2ConnentToOpponent.isVisible())
			this.setJLocation(lf2ConnentToOpponent, 0.277, 0.487);
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void ClickListener(String event) {
		if (event == cancel.getActionCommand() && !lf2ConnentToOpponent.isVisible()
				&& !lf2WaitingForOpponent.isVisible()) {
			new MP3Player(getClass().getResourceAsStream("music/m_cancel.mp3")).play();
			LF2Windows.window[0] = new LF2Main();
			LF2Windows.cardPanel.add(LF2Windows.window[0], "main");
			LF2Windows.card.show(this.getParent(), "main");
			LF2Windows.cardPanel.remove(LF2Windows.window[2]);
		} else if (event == waitingForOpponent.getActionCommand() && !lf2ConnentToOpponent.isVisible()) {
			new MP3Player(getClass().getResourceAsStream("music/m_ok.mp3")).play();
			lf2WaitingForOpponent.setVisible(true);
			lf2WaitingForOpponent.serverGO();
		} else if (event == connectToOpponent.getActionCommand() && !lf2WaitingForOpponent.isVisible()) {
			new MP3Player(getClass().getResourceAsStream("music/m_ok.mp3")).play();
			lf2ConnentToOpponent.setVisible(true);
		}
	}
}
