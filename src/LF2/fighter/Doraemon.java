package LF2.fighter;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import LF2.LF2Data;
import LF2.custom.CustomThread;
import LF2.custom.CustomThread.ThreadListener;
import LF2.player_key.Keyboard;
import LF2.player_key.Player;

public class Doraemon extends Fighter implements ThreadListener {

	// 此處為匯入圖片 無需跟動
	public Doraemon() {
		super();
		customThread = new CustomThread();
		customThread.addThreadListener(this);
		s = LF2Data.s;
	}

	// 所有狀態所產生的位移與相消
	@Override
	public void State() {
		if (state != "attack" && state != "defend" && state != "skill") {// 在ADS時不能走
			if (isStandX > 0) {// 當位移X>0時 為向右邊走
				isMirror = false;// 是否為朝向左邊 不是
				if (isMirror)// 是的話圖片重製
					frame = 0;
				frame += 0.1;// 圖片跟換速度
				frame %= (double) LF2Data.alldoraemon_runImage.size();// 圖片張數以防溢出
				fighter = LF2Data.alldoraemon_runImage.get((int) frame);// 為第幾張圖片
			} else if (isStandX < 0) {
				isMirror = true;
				if (!isMirror)
					frame = 0;
				frame += 0.1;
				frame %= LF2Data.alldoraemon_runmirrorImage.size();
				fighter = LF2Data.alldoraemon_runmirrorImage.get((int) frame);
			} else if (isStandY > 0) {
				frame += 0.1;
				if (isMirror) {
					frame %= LF2Data.alldoraemon_runmirrorImage.size();
					fighter = LF2Data.alldoraemon_runmirrorImage.get((int) frame);
				} else {
					frame %= (double) LF2Data.alldoraemon_runImage.size();
					fighter = LF2Data.alldoraemon_runImage.get((int) frame);
				}
			} else if (isStandY < 0) {
				frame += 0.1;
				if (isMirror) {
					frame %= LF2Data.alldoraemon_runmirrorImage.size();
					fighter = LF2Data.alldoraemon_runmirrorImage.get((int) frame);
				} else {
					frame %= (double) LF2Data.alldoraemon_runImage.size();
					fighter = LF2Data.alldoraemon_runImage.get((int) frame);
				}
			}
			if (isStandX == 0 && isStandY == 0)// 計算結果沒有移動時就是站
				Stand();
			x += isStandX;// 跟動圖片x軸
			y += isStandY;// 跟動圖片y軸
			positionX = 0;// 位移x軸重製
			positionY = 0;// 位移y軸重製
			isStandX = 0;// 位移計算到是否為站x軸重製
			isStandY = 0;// 位移計算到是否為站y軸重製
		}
	}

	// 上
	@Override
	public void Up() {
		state = "up";// 狀態為上
		positionY = 0.0008;// 位移y軸速度
		isStandY -= positionY;// 計算到站y軸
	}

	@Override
	public void Down() {
		state = "down";
		positionY = 0.0008;
		isStandY += positionY;
	}

	// 左
	@Override
	public void Left() {
		state = "left";
		if (Math.abs(Keyboard.Time[player][0][0] - Keyboard.Time[player][0][1]) <= 300
				&& Math.abs(Keyboard.Time[player][0][0] - Keyboard.Time[player][0][1]) != 0)// 計算兩次按下的時間是否有小於500毫秒且不為0
																							// 有為跑
			positionX = 0.0032;
		else
			positionX = 0.0016;
		isStandX -= positionX;
	}

	@Override
	public void Right() {
		state = "right";
		if (Math.abs(Keyboard.Time[player][1][0] - Keyboard.Time[player][1][1]) <= 200
				&& Math.abs(Keyboard.Time[player][1][0] - Keyboard.Time[player][1][1]) != 0)
			positionX = 0.0032;
		else
			positionX = 0.0016;
		isStandX += positionX;
	}

	// 攻擊
	@Override
	public void Attack(Fighter enemy[]) {
		this.enemy = enemy;// 此為所有人物(包括自己) 這是用來敵人被打時調用
		if (!isJump && !isDefend && !isSkill) {// 狀態為JDS時無法A
			isAttack = true;// 是攻擊狀態(此為用在執行續)
			customThread.start();// 執行續開始
			state = "attack";
			if (isMirror) {
				fighter = LF2Data.doraemon_attack_mirrorImage;
			} else {
				fighter = LF2Data.doraemon_attackImage;
			}
		}
	}

	@Override
	public void Jump() {
		if (!isAttack && !isDefend && !isSkill) {
			isJump = true;
			customThread.start();
			state = "jump";
		}
	}

	@Override
	public void Defend() {
		if (!isJump && !isAttack && !isSkill) {
			customThread.start();
			state = "defend";
			if (isMirror)
				fighter = LF2Data.doraemon_defense_mirrorImage;
			else
				fighter = LF2Data.doraemon_defenseImage;
			isDefend = true;
		}
	}

	@Override
	public void Stand() {
		state = "stand";
		if (isMirror)
			fighter = LF2Data.doraemon_stand_mirrorImage;
		else
			fighter = LF2Data.doraemon_standImage;
		if (HP > 0) {
			if (HP <= f_HP)
				HP += 0.0001; // 此為回血 但有點奇怪
			if (MP <= 1)
				MP += 0.0001;
		}
	}

	// 同上 此為技能開始動作
	@Override
	public void Skill(int skill, Fighter enemy[]) {
		this.skill = skill; // 傳入第幾招技能
		this.enemy = enemy;
		isSkill = true;
		customThread.join();// 暫時暫停所有執行續 以當前的執行續來執行
		customThread.start();
	}

	// 繪圖的部分
	@Override
	public void drawImage(Graphics g, int winWidth, int winHeight) {
		if (HP <= 0) {
			f_HP = 0;
			MP = 0;
			f_MP = 0;
			Player.playerKeyStates[player].setState(false);
		}
		// 邊界
		if (y < 0.47)
			y = 0.47;
		if (y > 0.698)
			y = 0.698;
		if (x < 0.01)
			x = 0.01;
		if (x > 0.92)
			x = 0.92;
		// 名字
		if (HP > 0) {
			g.setColor(Color.BLACK);
			g.setFont(new Font("仿宋體", Font.TYPE1_FONT, (int) (20 * winHeight * 0.001)));
			g.drawString(Player.playerInfors[player].getPlayer(), (int) (winWidth * (x + 0.025)),
					(int) (winHeight * (y + 0.135)));
		}
		g.drawImage(LF2Data.doraemon_standImage, (int) (winWidth * (0.001 + player % 4 * 0.2484)),
				(int) (winHeight * (0.01 + player / 4 * 0.098)), (int) (winWidth * 0.075), (int) (winHeight * 0.075),
				null);
		// HP
		g.setColor(new Color(111, 8, 31));
		g.fillRect((int) (winWidth * (0.0744 + player % 4 * 0.2484)), (int) (winHeight * (0.03 + player / 4 * 0.098)),
				(int) (winWidth * 0.1555 * f_HP), (int) (winHeight * 0.0185));
		g.setColor(Color.RED);
		g.fillRect((int) (winWidth * (0.0744 + player % 4 * 0.2484)), (int) (winHeight * (0.03 + player / 4 * 0.098)),
				(int) (winWidth * 0.1555 * HP), (int) (winHeight * 0.0185));
		// MP
		g.setColor(new Color(31, 8, 111));
		g.fillRect((int) (winWidth * (0.0744 + player % 4 * 0.2484)), (int) (winHeight * (0.0659 + player / 4 * 0.098)),
				(int) (winWidth * 0.1555 * f_MP), (int) (winHeight * 0.0185));
		g.setColor(Color.BLUE);
		g.fillRect((int) (winWidth * (0.0744 + player % 4 * 0.2484)), (int) (winHeight * (0.0659 + player / 4 * 0.098)),
				(int) (winWidth * 0.1555 * MP), (int) (winHeight * 0.0185));
		if (HP > 0) {
			// 影子
			g.drawImage(s, (int) (winWidth * (x + 0.0113)), (int) (winHeight * (y + 0.105)), (int) (winWidth * 0.0572),
					(int) (winHeight * 0.0131), null);
			// 人物
			g.drawImage(fighter, (int) (winWidth * x), (int) (winHeight * (y + z)), (int) (winWidth * 0.0805),
					(int) (winHeight * 0.1166), null);
		}
	}

	// 執行續 啟動動作開關
	@Override
	public void ThreadListener() {
		if (isAttack) {
			attackState();
		} else if (isJump) {
			jumpState();
		} else if (isDefend) {
			defendState();
		} else if (isSkill) {
			skillState();
		}
		state = "stand"; // 所有狀態完畢時 為站
	}

	@Override
	protected void attackState() {
		try {
			Thread.sleep(50); // 延遲250毫秒
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for (int i = 0; i < 10; i++) { // 重複10次 比較會有動畫效果
			z -= 0.005; // 往上跳的位移 跟往上走不一樣
			if (isMirror) {
				x -= 0.0025; // 動作時的位移量
				EnemyValue(0.08, 0.025, 0.04, -0.0025, 0, 0, 0.01, 0);// 當打到敵人時的動作(哪個敵人,攻擊範圍x,y,z軸,x,y,z軸位移量,扣多少HP,MP)
				fighter = LF2Data.doraemon_attack_mirrorImage;// 換下一張圖
			} else {
				x += 0.0025;
				EnemyValue(0.08, 0.025, 0.04, 0.0025, 0, 0, 0.01, 0);
				fighter = LF2Data.doraemon_attackImage;
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		if (isMirror) {
			fighter = LF2Data.doraemon_attack_mirrorImage;
		} else {
			fighter = LF2Data.doraemon_attackImage;
		}
		for (int i = 0; i < 10; i++) {
			z += 0.005;
			try {
				Thread.sleep(20);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		try {
			Thread.sleep(100);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		isAttack = false;
	}

	@Override
	protected void jumpState() {
		if (z >= 0) {
			for (int i = 0; i < 10; i++) {
				z -= 0.01;
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			try {
				Thread.sleep(80);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			for (int i = 0; i < 10; i++) {
				z += 0.01;
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		isJump = false;
	}

	@Override
	protected void defendState() {
		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		isDefend = false;
	}

	// 計算技能是第幾招並回傳 目前還未弄完 可以省略 而且也很複雜不好弄
	@Override
	public int skill() {
		skill = 0;
		skillRemake();
		skill = skillCode(DRA, 1);
		skill = skillCode(DLA, 2);
		skill = skillCode(DUJ, 3);
		skillKeyClear();
		return skill;
	}

	// 此為技能有點複雜暫可略
	@Override
	protected void skillState() {
		state = "skill";
		switch (skill) {
		case 1:
			if (MP >= 0.1) {
				MP -= 0.1;
				EnemyValue(0.08, 0.025, 0.04, -0.005, 0, 0, 0.025, 0.3);// 當打到敵人時的動作(哪個敵人,攻擊範圍x,y,z軸,x,y,z軸位移量,扣多少HP,MP)
				fighter = LF2Data.doraemon_0Image;
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				isMirror = false;
			}
			break;
		case 2:
			if (MP >= 0.1) {
				MP -= 0.1;
				EnemyValue(0.08, 0.025, 0.04, 0.005, 0, 0, 0.025, 0);// 當打到敵人時的動作(哪個敵人,攻擊範圍x,y,z軸,x,y,z軸位移量,扣多少HP,MP)
				fighter = LF2Data.doraemon_0_mirrorImage;
				try {
					Thread.sleep(400);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				isMirror = true;
			}
			break;
		case 3:
			if (MP >= 0.2) {
				MP -= 0.2;
				if (isMirror) {
					EnemyValue(0.08, 0.025, 0.04, -0.005, 0, 0, 0.05, 0);// 當打到敵人時的動作(哪個敵人,攻擊範圍x,y,z軸,x,y,z軸位移量,扣多少HP,MP)
					fighter = LF2Data.doraemon_1_mirrorImage;
					try {
						Thread.sleep(400);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					EnemyValue(0.08, 0.025, 0.04, 0.005, 0, 0, 0.05, 0);// 當打到敵人時的動作(哪個敵人,攻擊範圍x,y,z軸,x,y,z軸位移量,扣多少HP,MP)
					fighter = LF2Data.doraemon_1Image;
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		default:
			break;
		}
		isSkill = false;
	}
}
