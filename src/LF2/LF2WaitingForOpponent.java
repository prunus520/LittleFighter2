package LF2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;

import LF2.custom.CustomThread;
import LF2.custom.CustomThread.ThreadListener;
import LF2.custom.button.ClickListener;
import LF2.custom.button.PictureJButton;
import LF2.custom.mp3.MP3Player;
import LF2.custom.panel.CustomJPanel;

public class LF2WaitingForOpponent extends CustomJPanel implements ClickListener, ThreadListener {

	BufferedImage img, btn_exited, btn_entered;
	double widthSize = 0, heightSize = 0;
	PictureJButton cancel;
	double index = 0;
	CustomThread serverThread;

	public LF2WaitingForOpponent(BufferedImage img, BufferedImage btn_exited, BufferedImage btn_entered) {
		this.img = img;
		this.btn_exited = btn_exited;
		this.btn_entered = btn_entered;
		init();
	}

	public void init() {
		cancel = new PictureJButton("cancel (取消)", 150, btn_entered, btn_exited);
		cancel.setSize(0.452, 0.235);
		cancel.addClickListener(this);
		this.add(cancel);
		serverThread = new CustomThread();
		serverThread.addThreadListener(this);
	}

	public void serverGO() {
		new LF2Server();
		try {
			LF2Server.server = new ServerSocket(6666);
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
		this.setSize(new Dimension((int) (this.getParent().getWidth() * widthSize),
				(int) (this.getParent().getHeight() * heightSize)));
		g.drawImage(img, 0, 0, (int) (this.getParent().getWidth() * widthSize),
				(int) (this.getParent().getHeight() * heightSize), null);
		this.setJLocation(cancel, 0.259, 0.668);
		g.setColor(new Color(87, 127, 215));
		g.setFont(new Font("仿宋體", Font.TYPE1_FONT, (int) (30 * this.getParent().getHeight() * 0.001)));
		for (int i = 0; i < index; i++)
			g.drawString("▌", (int) (this.getParent().getWidth() * (0.0402 + i * 0.03)),
					(int) (this.getParent().getHeight() * 0.115));
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (this.isVisible()) {
			index += 0.1;
			index %= 12;
			if (index == 0)
				index++;
			repaint();
		}
	}

	@Override
	public void ClickListener(String event) {
		if (event == cancel.getActionCommand()) {
			new MP3Player(getClass().getResourceAsStream("music/m_cancel.mp3")).play();
			this.setVisible(false);
			try {
				LF2Server.server.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			index = 0;
		}
	}

	@Override
	public void ThreadListener() {
		try {
			System.out.println("等待連線......");
			LF2Server.socket = LF2Server.server.accept();
			System.out.println("連線成功！");
			LF2Server.writer = new PrintStream(LF2Server.socket.getOutputStream());
			LF2Server.reader = new BufferedReader(new InputStreamReader(LF2Server.socket.getInputStream()));

			LF2Windows.window[1] = new LF2LoadingData();
			LF2Windows.cardPanel.add(LF2Windows.window[1], "loadingData");
			LF2Windows.card.show(this.getParent().getParent(), "loadingData");
			((LF2LoadingData) LF2Windows.window[1]).initThread();
			LF2Windows.cardPanel.remove(LF2Windows.window[2]);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
