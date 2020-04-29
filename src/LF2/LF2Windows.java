package LF2;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import LF2.custom.mp3.MP3Player;
import LF2.player_key.Keyboard;
import LF2.player_key.Player;

public class LF2Windows extends JFrame implements Runnable {
	public static CardLayout card = new CardLayout();
	public static JPanel cardPanel = new JPanel();
	public static Component window[] = new Component[4];
	MP3Player mp3Player;

	public LF2Windows() {

		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int WinWidth = (int) (screenSize.width * 0.527);
		int WinHeight = (int) (screenSize.height * 0.679);

		super.setTitle("Little Fighter 2");
		ImageIcon ico = new ImageIcon(getClass().getResource("image/ico/lf2.jpg"));
		this.setIconImage(ico.getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(WinWidth, WinHeight);
		this.enableInputMethods(false);
		this.setLocationRelativeTo(this);
		this.setResizable(true);

		this.addKeyListener(new Keyboard(this));
		this.setFocusTraversalKeysEnabled(false);

		cardPanel.setLayout(card);

		new Thread(this).start();

		new Player();

		window[0] = new LF2Main();
		window[3] = new LF2ControlSettings();

		cardPanel.add(window[0], "main");
		LF2Windows.cardPanel.add(window[3], "controlSettings");

		this.add(cardPanel, BorderLayout.CENTER);

		this.setVisible(true);

	}

	public static void main(String[] args) {
		new LF2Windows();
	}

	@Override
	public void run() {
		while (true) {
			mp3Player = new MP3Player(getClass().getResourceAsStream("music/main.mp3"));
			mp3Player.play();
			try {
				Thread.sleep(150100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
