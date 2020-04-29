package LF2.fighter;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import LF2.custom.CustomThread;
import LF2.player_key.Keyboard;
import LF2.player_key.Player;

public abstract class Fighter {

	public Fighter enemy[];
	public CustomThread customThread;
	public BufferedImage fighter, s;
	public double frame = 0;
	public int index, player;
	public double HP = 1, MP = 1, f_HP = 1, f_MP = 1;
	public String state = "stand";
	public double isStandX = 0, isStandY = 0, positionX = 0, positionY = 0, x, y, z;
	public boolean isMirror = false, isAttack = false, isJump = false, isDefend = false, isSkill = false, isRun = false;
	public int skill = 0;
	protected int DUA[][] = new int[3][2];
	protected int DDA[][] = new int[3][2];
	protected int DRA[][] = new int[3][2];
	protected int DLA[][] = new int[3][2];
	protected int DUJ[][] = new int[3][2];
	protected int DDJ[][] = new int[3][2];
	protected int DRJ[][] = new int[3][2];
	protected int DLJ[][] = new int[3][2];

	public Fighter() {

	}

	public abstract void State();

	public abstract int skill();

	public abstract void Skill(int skill, Fighter enemy[]);

	public abstract void Up();

	public abstract void Down();

	public abstract void Left();

	public abstract void Right();

	public abstract void Attack(Fighter enemy[]);

	public abstract void Jump();

	public abstract void Defend();

	public abstract void Stand();

	public abstract void drawImage(Graphics g, int winWidth, int winHeight);

	protected abstract void attackState();

	protected abstract void jumpState();

	protected abstract void defendState();

	protected abstract void skillState();

	protected void EnemyValue(double AttackRangeX, double AttackRangeY, double AttackRangeZ, double x, double y,
			double z, double HP, double MP) {
		for (int i = 0; i < 8; i++) {
			if (enemy[i] != null) {
				if (this.x + AttackRangeX > enemy[i].x && this.x - AttackRangeX < enemy[i].x
						&& this.y + AttackRangeY > enemy[i].y && this.y - AttackRangeY < enemy[i].y
						&& this.z + AttackRangeZ > enemy[i].z && this.z - AttackRangeZ < enemy[i].z
						&& enemy[i].player != this.player && !enemy[i].isDefend) {
					enemy[i].x += x;
					enemy[i].y += y;
					enemy[i].z += z;
					enemy[i].HP -= HP;
					enemy[i].f_HP -= HP * 0.4;
					enemy[i].MP -= MP;
				}
			}
		}
	}

	protected void skillRemake() {
		int dua[][] = { { Player.playerKeyInfors[player].getDefend(), 0 },
				{ Player.playerKeyInfors[player].getUp(), 0 }, { Player.playerKeyInfors[player].getAttack(), 0 } };
		DUA = dua;
		int dda[][] = { { Player.playerKeyInfors[player].getDefend(), 0 },
				{ Player.playerKeyInfors[player].getDown(), 0 }, { Player.playerKeyInfors[player].getAttack(), 0 } };
		DDA = dda;
		int dra[][] = { { Player.playerKeyInfors[player].getDefend(), 0 },
				{ Player.playerKeyInfors[player].getRight(), 0 }, { Player.playerKeyInfors[player].getAttack(), 0 } };
		DRA = dra;
		int dla[][] = { { Player.playerKeyInfors[player].getDefend(), 0 },
				{ Player.playerKeyInfors[player].getLeft(), 0 }, { Player.playerKeyInfors[player].getAttack(), 0 } };
		DLA = dla;
		int duj[][] = { { Player.playerKeyInfors[player].getDefend(), 0 },
				{ Player.playerKeyInfors[player].getUp(), 0 }, { Player.playerKeyInfors[player].getJump(), 0 } };
		DUJ = duj;
		int ddj[][] = { { Player.playerKeyInfors[player].getDefend(), 0 },
				{ Player.playerKeyInfors[player].getDown(), 0 }, { Player.playerKeyInfors[player].getJump(), 0 } };
		DDJ = ddj;
		int drj[][] = { { Player.playerKeyInfors[player].getDefend(), 0 },
				{ Player.playerKeyInfors[player].getRight(), 0 }, { Player.playerKeyInfors[player].getJump(), 0 } };
		DRJ = drj;
		int dlj[][] = { { Player.playerKeyInfors[player].getDefend(), 0 },
				{ Player.playerKeyInfors[player].getLeft(), 0 }, { Player.playerKeyInfors[player].getJump(), 0 } };
		DLJ = dlj;
		for (int i = 0; i < 3; i++) {
			DUA[i][1] = 0;
			DDA[i][1] = 0;
			DRA[i][1] = 0;
			DLA[i][1] = 0;
			DUJ[i][1] = 0;
			DDJ[i][1] = 0;
			DRJ[i][1] = 0;
			DLJ[i][1] = 0;
		}
	}

	protected int skillCode(int skillCode[][], int code) {
		for (int i = 0; i < 3; i++) {
			if (Keyboard.recordKey[player][(Keyboard.currentKey[player] + 1 + i) % 3] == skillCode[i][0]) {
				skillCode[i][1] = code;
			}
			if (skillCode[0][1] == code && skillCode[1][1] == code && skillCode[2][1] == code)
				skill = code;
		}
		return skill;
	}

	protected void skillKeyClear() {
		if (skill != 0)
			for (int i = 0; i < 3; i++) {
				Keyboard.recordKey[player][(Keyboard.currentKey[player] + 1 + i) % 3] = 0;
			}
	}

}
