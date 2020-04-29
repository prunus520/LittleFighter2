package LF2;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;

import LF2.custom.panel.CustomJPanel;
import LF2.fighter.Capoo;
import LF2.fighter.Doraemon;
import LF2.fighter.Fighter;
import LF2.fighter.Jake;
import LF2.fighter.Octopus;
import LF2.player_key.Keyboard;
import LF2.player_key.Player;

public class LF2Fight extends CustomJPanel implements Runnable {

	BufferedImage bg, img;
	List<BufferedImage> back = new ArrayList<BufferedImage>();
	int winWidth, winHeight;
	int index = 0;
	Thread key;
	boolean init = false;
	Fighter fighter[];
	int skill;
	Random random = new Random(System.currentTimeMillis());
	int alive = 0, total = 0;
	LF2Alive lf2Alive;

	public LF2Fight() {
		try {
			img = ImageIO.read(getClass().getResource("image/system/alive.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		bg = LF2Data.bg;
		back = LF2Data.back;
		key = new Thread(keyThread);
		key.start();
	}

	public void init() {
		new Thread(this).start();

		lf2Alive = new LF2Alive(img);
		lf2Alive.setSize(0.457, 0.6);
		this.add(lf2Alive);
		lf2Alive.setVisible(false);

		fighter = new Fighter[8];
		for (int i = 0; i < 8; i++) {
			switch (Player.playerInfors[i].getFighter()) {
			case 0:
				fighter[i] = new Capoo();
				infors(i);
				break;
			case 1:
				fighter[i] = new Doraemon();
				infors(i);
				break;
			case 2:
				fighter[i] = new Jake();
				infors(i);
				break;
			case 3:
				fighter[i] = new Octopus();
				infors(i);
				break;
			default:
				fighter[i] = null;
				break;
			}
		}
	}

	private void infors(int i) {
		fighter[i].player = i;
		fighter[i].x = random.nextDouble();
		fighter[i].y = random.nextDouble();
		fighter[i].isMirror = random.nextBoolean();
		Player.playerInfors[i].setX(fighter[i].x);
		Player.playerInfors[i].setY(fighter[i].y);
		Player.playerInfors[i].setMirror(fighter[i].isMirror);
		alive++;
		total++;
	}

	private void press(int i) {
		if (Keyboard.isPressed) {
			if (115 == Keyboard.code) {
				for (int j = 0; j < 4; j++) {
					Player.playerKeyStates[j].setState(true);
				}
				LF2Windows.card.show(this.getParent(), "VSMode");
				LF2Windows.cardPanel.remove(LF2Windows.window[2]);
			} else if (118 == Keyboard.code) {
				if (fighter[i] != null) {
					fighter[i].HP = 1;
					fighter[i].f_HP = 1;
					fighter[i].MP = 1;
					fighter[i].f_MP = 1;
				}
			}
			if (Player.playerKeyInfors[i].getUp() == Keyboard.code)
				Player.playerKeyStates[i].setUp(true);
			else if (Player.playerKeyInfors[i].getDown() == Keyboard.code)
				Player.playerKeyStates[i].setDown(true);
			else if (Player.playerKeyInfors[i].getLeft() == Keyboard.code)
				Player.playerKeyStates[i].setLeft(true);
			else if (Player.playerKeyInfors[i].getRight() == Keyboard.code)
				Player.playerKeyStates[i].setRight(true);
			else if (Player.playerKeyInfors[i].getAttack() == Keyboard.code
					&& !Player.playerKeyStates[i].isAttackState())
				Player.playerKeyStates[i].setAttack(true);
			else if (Player.playerKeyInfors[i].getJump() == Keyboard.code && !Player.playerKeyStates[i].isJumpState())
				Player.playerKeyStates[i].setJump(true);
			else if (Player.playerKeyInfors[i].getDefend() == Keyboard.code
					&& !Player.playerKeyStates[i].isDefendState())
				Player.playerKeyStates[i].setDefend(true);
		}
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Arrays.sort(fighter, new Comparator<Fighter>() {
			public int compare(Fighter f1, Fighter f2) {
				if (f1 == null) {
					return 1;
				}
				if (f2 == null) {
					return -1;
				}
				if (f1.y == f2.y) {
					return 0;
				}
				if (f1.y > f2.y) {
					return 1;
				}
				return -1;
			}
		});
		for (int i = 0; i < 4; i++) {
			if (fighter[i] != null) {
				if (Player.playerKeyStates[fighter[i].player].isState()) {
					skill = fighter[i].skill();
					if (skill != 0) {
						if (fighter[i].state != "skill")
							fighter[i].Skill(skill, fighter);
					} else if (Player.playerKeyStates[fighter[i].player].isAttack()
							&& Player.playerKeyStates[fighter[i].player].isAttackState()) {
						if (alive <= 1 && total != 1) {
							for (int j = 0; j < 4; j++) {
								Player.playerKeyStates[j].setState(true);
							}
							LF2Windows.card.show(this.getParent(), "VSMode");
							LF2Windows.cardPanel.remove(LF2Windows.window[2]);
						}
						if (fighter[i].state != "attack" && init)
							fighter[i].Attack(fighter);
						else
							init = true;
						Player.playerKeyStates[fighter[i].player].setAttack(false);
					} else if (Player.playerKeyStates[fighter[i].player].isJump()
							&& Player.playerKeyStates[fighter[i].player].isJumpState()) {
						if (alive <= 1 && total != 1) {
							for (int j = 0; j < 4; j++) {
								Player.playerKeyStates[j].setState(true);
							}
							LF2Windows.card.show(this.getParent(), "VSMode");
							LF2Windows.cardPanel.remove(LF2Windows.window[2]);
						}
						if (fighter[i].state != "jump")
							fighter[i].Jump();
						Player.playerKeyStates[fighter[i].player].setJump(false);
					} else if (Player.playerKeyStates[fighter[i].player].isDefend()
							&& Player.playerKeyStates[fighter[i].player].isDefendState()) {
						if (fighter[i].state != "defend")
							fighter[i].Defend();
						Player.playerKeyStates[fighter[i].player].setDefend(false);
					} else if (fighter[i].state != "attack" && fighter[i].state != "defend"
							&& fighter[i].state != "skill") {
						if (Player.playerKeyStates[fighter[i].player].isUp()) {
							fighter[i].Up();
						}
						if (Player.playerKeyStates[fighter[i].player].isDown()) {
							fighter[i].Down();
						}
						if (Player.playerKeyStates[fighter[i].player].isLeft()) {
							fighter[i].Left();
						}
						if (Player.playerKeyStates[fighter[i].player].isRight()) {
							fighter[i].Right();
						}
					}
					fighter[i].State();
				}
			}
		}
		winWidth = this.getWidth();
		winHeight = this.getHeight();
		g.drawImage(bg, 0, (int) (winHeight * -0.003), winWidth, winHeight, null);
		g.drawImage(back.get(0), 0, (int) (winHeight * 0.235), winWidth, (int) (winHeight * 0.185), null);
		g.drawImage(back.get(index % 2 + 1), 0, (int) (winHeight * 0.435), winWidth, (int) (winHeight * 0.167), null);
		g.drawImage(back.get(3), 0, (int) (winHeight * 0.579), winWidth, (int) (winHeight * 0.36), null);
		alive = 0;
		for (int i = 0; i < 8; i++) {
			if (fighter[i] != null) {
				fighter[i].drawImage(g, winWidth, winHeight);
				if (fighter[i].HP > 0) {
					alive++;
					lf2Alive.win(fighter[i].fighter);
				}
			}
		}
		if (alive <= 1 && total != 1) {
			lf2Alive.setVisible(true);
			this.setJLocation(lf2Alive, 0.276, 0.2);
		}
	}

	@Override
	public void run() {
		while (true) {
			index++;
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.repaint();
		}
	}

	Runnable keyThread = new Runnable() {

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				for (int i = 0; i < 4; i++)
					press(i);
				getParent().repaint();
			}
		}
	};
}
