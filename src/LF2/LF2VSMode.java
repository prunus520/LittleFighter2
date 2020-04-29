package LF2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import LF2.custom.label.FlickerJLabel;
import LF2.custom.mp3.MP3Player;
import LF2.custom.panel.CharacterChoose;
import LF2.custom.panel.CustomJPanel;
import LF2.player_key.Keyboard;
import LF2.player_key.Player;

public class LF2VSMode extends CustomJPanel implements Runnable {

	BufferedImage img, img1, img2;
	CharacterChoose character[] = new CharacterChoose[8];
	int winWidth, winHeight;
	FlickerJLabel player[] = new FlickerJLabel[8];
	FlickerJLabel fighter[] = new FlickerJLabel[8];
	FlickerJLabel team[] = new FlickerJLabel[8];
	String fighterName[] = { "Random", "咖波", "哆啦A夢", "老皮", "水母" };
	String teamName[] = { "Independent", "Team 1", "Team 2", "Team 3", "Team 4" };
	int teamIndex = 0, isPlayer = 0, playerCount, choose = 0;
	boolean isNum = false;
	LF2ComputerPlayer lf2ComputerPlayer;
	LF2FightSelect lf2FightSelect;
	int reinitCharacter[] = new int[8], reinitChoose[] = new int[8];
	String reinitPlayerText[] = new String[8], reinitFighterText[] = new String[8], reinitTeamText[] = new String[8];
	boolean reinitCharacterVisible[] = new boolean[8], reinitPlayerReady[] = new boolean[8],
			reinitFighterReady[] = new boolean[8], reinitTeamReady[] = new boolean[8];
	Random random;
	int index = 0, point[] = new int[8];
	Thread thread;

	public LF2VSMode() {
		try {
			img = ImageIO.read(getClass().getResource("image/system/vsMode.png"));
			img1 = ImageIO.read(getClass().getResource("image/system/computer.png"));
			img2 = ImageIO.read(getClass().getResource("image/system/fight.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		init();
		random = new Random(System.currentTimeMillis());
	}

	public void threadStart() {
		thread = new Thread(this);
		thread.start();
	}

	public void init() {
		lf2ComputerPlayer = new LF2ComputerPlayer(img1);
		lf2ComputerPlayer.setSize(0.457, 0.202);

		lf2FightSelect = new LF2FightSelect(img2);
		lf2FightSelect.setSize(0.381, 0.299);

		for (int i = 0; i < 8; i++) {
			character[i] = new CharacterChoose();
			character[i].setSize(0.1515, 0.216);
			player[i] = new FlickerJLabel("Join?", 27, new Color(55, 100, 255));
			player[i].setSize(0.15, 0.035);
			fighter[i] = new FlickerJLabel("", 27, new Color(55, 100, 255));
			fighter[i].setSize(0.15, 0.035);
			team[i] = new FlickerJLabel("", 27, new Color(55, 100, 255));
			team[i].setSize(0.15, 0.035);
		}
		initADD();
	}

	public void initADD() {
		teamIndex = 0;
		isPlayer = 0;
		choose = 0;
		isNum = false;
		this.add(lf2ComputerPlayer);
		lf2ComputerPlayer.setVisible(false);

		this.add(lf2FightSelect);
		lf2FightSelect.setVisible(false);

		for (int i = 0; i < 8; i++) {
			point[i] = 3;
			if (i < 4) {
				Player.playerKeyStates[i].setState(true);
				Player.playerInfors[i].setFighter(-1);
			}
			this.add(character[i]);
			this.add(player[i]);
			this.add(fighter[i]);
			this.add(team[i]);
		}
	}

	public void previous() {
		LF2Windows.window[0] = new LF2GameStart();
		LF2Windows.cardPanel.add(LF2Windows.window[0], "gameStart");
		LF2Windows.card.show(this.getParent(), "gameStart");
		LF2Windows.cardPanel.remove(LF2Windows.window[1]);
	}

	public void backup() {
		for (int i = 0; i < 8; i++) {
			reinitCharacterVisible[i] = character[i].isVisible();
			if (character[i].getChoose() == 3)
				reinitChoose[i] = 4;
			else
				reinitChoose[i] = character[i].getChoose();
			reinitCharacter[i] = character[i].getPoint();
			reinitPlayerText[i] = player[i].getText();
			reinitPlayerReady[i] = player[i].isReady();
			reinitFighterText[i] = fighter[i].getText();
			reinitFighterReady[i] = fighter[i].isReady();
			reinitTeamText[i] = team[i].getText();
			reinitTeamReady[i] = team[i].isReady();
		}
	}

	public void reduction() {
		for (int i = 0; i < 8; i++) {
			character[i].setVisible(reinitCharacterVisible[i]);
			character[i].setChoose(reinitChoose[i]);
			character[i].setPoint(reinitCharacter[i]);
			if (i < 4)
				Player.playerInfors[i].setFighter(character[i].getPoint() - 4);
			player[i].setText(reinitPlayerText[i]);
			player[i].setReady(reinitPlayerReady[i]);
			fighter[i].setText(reinitFighterText[i]);
			fighter[i].setReady(reinitFighterReady[i]);
			team[i].setText(reinitTeamText[i]);
			team[i].setReady(reinitTeamReady[i]);
		}
		isPlayer++;
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		winWidth = this.getWidth();
		winHeight = this.getHeight();
		if (isNum) {
			for (int i = 0; i < 8; i++) {
				if (!character[i].isNumFlag()) {
					Player.playerKeyStates[i].setState(true);
				} else if (!character[i].isVisible()) {
					player[i].setText("-");
					player[i].setReady(true);
					if (!lf2ComputerPlayer.isVisible()) {
						try {
							Thread.sleep(10);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						lf2ComputerPlayer.setVisible(true);
					}
				}
			}
		}

		if (Keyboard.isPressed == true) {
			for (int i = 0; i < 4; i++) {
				if (Player.playerKeyInfors[i].getUp() == Keyboard.code && Player.playerKeyStates[i].isState()) {
					if (character[i].getChoose() == 4) {
						lf2FightSelect.setIndex((lf2FightSelect.getIndex() + 3) % 4);
					}
				} else if (Player.playerKeyInfors[i].getDown() == Keyboard.code
						&& Player.playerKeyStates[i].isState()) {
					if (character[i].getChoose() == 4) {
						lf2FightSelect.setIndex((lf2FightSelect.getIndex() + 5) % 4);
					}
				} else if (Player.playerKeyInfors[i].getLeft() == Keyboard.code
						&& Player.playerKeyStates[i].isState()) {
					if (character[i].getChoose() == 1) {
						character[i].setIndex(-1);
						point[i]--;
						if (point[i] == 2)
							point[i] = 7;
						fighter[i].setText(fighterName[point[i] - 3]);
					} else if (character[i].getChoose() == 2) {
						teamIndex = (teamIndex + 4) % 5;
						team[i].setText(teamName[teamIndex]);
					} else if (character[i].getChoose() == 3 && lf2ComputerPlayer.isVisible()) {
						lf2ComputerPlayer.setIndex((lf2ComputerPlayer.getIndex() + 7) % 8);
					}
				} else if (Player.playerKeyInfors[i].getRight() == Keyboard.code
						&& Player.playerKeyStates[i].isState()) {
					if (character[i].getChoose() == 1) {
						character[i].setIndex(1);
						point[i]++;
						if (point[i] == 8)
							point[i] = 3;
						fighter[i].setText(fighterName[point[i] - 3]);
					} else if (character[i].getChoose() == 2) {
						teamIndex = (teamIndex + 6) % 5;
						team[i].setText(teamName[teamIndex]);
					} else if (character[i].getChoose() == 3 && lf2ComputerPlayer.isVisible()) {
						lf2ComputerPlayer.setIndex((lf2ComputerPlayer.getIndex() + 9) % 8);
					}
				} else if (Player.playerKeyInfors[i].getAttack() == Keyboard.code
						&& Player.playerKeyStates[i].isState()) {
					if (character[i].getChoose() < 3 && !lf2FightSelect.isVisible())
						new MP3Player(getClass().getResourceAsStream("music/m_join.mp3")).play();
					else if (character[i].getChoose() > 3
							|| character[i].getChoose() == 3 && lf2ComputerPlayer.isVisible())
						new MP3Player(getClass().getResourceAsStream("music/m_ok.mp3")).play();
					if (character[i].getChoose() == 0 && !lf2FightSelect.isVisible()) {
						character[i].setChoose(1);
						choose++;
						player[i].setText(Player.playerKeyInfors[i].getName());
						player[i].setReady(true);
						character[i].setPoint(point[i]);
						fighter[i].setText(fighterName[point[i] - 3]);
						Player.playerInfors[i].setPlayer(Player.playerKeyInfors[i].getName());
					} else if (character[i].getChoose() == 1) {
						character[i].setChoose(2);
						fighter[i].setReady(true);
						team[i].setText(teamName[teamIndex]);
						Player.playerInfors[i].setTeam(teamIndex);
					} else if (character[i].getChoose() == 2) {
						character[i].setChoose(3);
						Player.playerInfors[i].setFighter(character[i].getPoint() - 4);
						team[i].setReady(true);
						isPlayer++;
					} else if (character[i].getChoose() == 3 && lf2ComputerPlayer.isVisible()) {
						backup();
						this.removeAll();
						initADD();
						choose++;
						reduction();
						for (int j = 0; j < 4; j++) {
							if (Player.playerInfors[j].getFighter() == -1) {
								character[j].setPoint(random.nextInt(4) + 4);
							}
						}
						character[i].setChoose(4);
						lf2FightSelect.setVisible(true);
					} else if (character[i].getChoose() == 4) {
						switch (lf2FightSelect.getIndex()) {
						case 0:
							thread.interrupt();
							for (int j = 0; j < 8; j++)
								character[j].interrupt();
							for (int j = 0; j < 4; j++) {
								if (Player.playerInfors[j].getFighter() == -1) {
									Player.playerInfors[j].setFighter(character[j].getPoint() - 4);
								}
							}
							LF2Windows.window[2] = new LF2Fight();
							LF2Windows.cardPanel.add(LF2Windows.window[2], "fight");
							LF2Windows.card.show(this.getParent(), "fight");
							((LF2Fight) LF2Windows.window[2]).init();
							break;
						case 1:
							this.removeAll();
							init();
							character[i].setChoose(0);
							character[i].setPoint(0);
							break;
						case 2:
							for (int j = 0; j < 4; j++) {
								if (Player.playerInfors[j].getFighter() == -1) {
									character[j].setPoint(random.nextInt(4) + 4);
								}
							}
							break;
						case 3:
							previous();
							this.removeAll();
							init();
							character[i].setChoose(0);
							break;
						default:
							break;
						}
					}
				} else if (Player.playerKeyInfors[i].getJump() == Keyboard.code) {
					if (character[i].getChoose() != 3 && !isNum)
						new MP3Player(getClass().getResourceAsStream("music/m_cancel.mp3")).play();
					if (character[i].getChoose() == 0 && choose == 0) {
						previous();
					} else if (character[i].getChoose() == 1) {
						character[i].setChoose(0);
						choose--;
						player[i].setText("Join?");
						player[i].setReady(false);
						fighter[i].setText("");
					} else if (character[i].getChoose() == 2) {
						character[i].setChoose(1);
						fighter[i].setReady(false);
						team[i].setText("");
					} else if (character[i].getChoose() == 3 && !isNum) {
						character[i].setChoose(2);
						team[i].setReady(false);
						isPlayer--;
					} else if (character[i].getChoose() == 3 && isNum) {
						for (int j = 0; j < 8; j++) {
							character[j].setNumber(1);
						}
					}
				} else if (Player.playerKeyInfors[i].getDefend() == Keyboard.code
						&& Player.playerKeyStates[i].isState()) {
				}
			}
			Keyboard.isPressed = false;
		}
		if (!isNum) {
			playerCount = 0;
			for (int i = 0; i < 8; i++) {
				if (isPlayer > 0 && character[i].getChoose() != 0 && character[i].getChoose() != 3) {
					playerCount++;
				}
			}
			for (int i = 0; i < 8; i++) {
				if (isPlayer != 0 && playerCount == 0 && character[i].getChoose() == 0) {
					character[i].setNumFlag(true);
					isNum = true;
				}
			}
		}
		if (isNum && !lf2ComputerPlayer.isVisible()) {
			for (int i = 0; i < 4; i++) {
				Player.playerKeyStates[i].setState(false);
			}
		}
		g.drawImage(img, 0, 0, winWidth, winHeight, null);

		for (int i = 0; i < 8; i++) {
			this.setJLocation(character[i], 0.187 + i % 4 * 0.192, 0.176 + i / 4 * 0.381);
			this.setJLocation(player[i], 0.187 + i % 4 * 0.192, 0.397 + i / 4 * 0.381);
			player[i].setIndex(index);
			this.setJLocation(fighter[i], 0.187 + i % 4 * 0.192, 0.437 + i / 4 * 0.381);
			fighter[i].setIndex(index);
			this.setJLocation(team[i], 0.187 + i % 4 * 0.192, 0.477 + i / 4 * 0.381);
			team[i].setIndex(index);
		}
		if (lf2ComputerPlayer.isVisible())
			this.setJLocation(lf2ComputerPlayer, 0.276, 0.391);
		if (lf2FightSelect.isVisible())
			this.setJLocation(lf2FightSelect, 0.007, 0.01);
	}

	@Override
	public void run() {
		while (true) {
			index++;
			index %= 2;
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.repaint();
		}
	}

}
