package LF2.player_key;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Keyboard implements KeyListener {

	private JFrame jFrame;
	private int message;
	public static int code;
	public static int recode;
	public static char codeChar;
	public static String codeString;
	public static boolean isPressed = false;
	public static boolean isRelease = false;
	public static int currentKey[] = new int[4];
	public static int recordKey[][] = new int[4][3];
	public static long Time[][][] = new long[4][2][2];
	private int index[] = new int[2];
	private boolean run[][] = new boolean[4][2];

	public Keyboard(JFrame jFrame) {
		this.jFrame = jFrame;
	}

	@Override
	public void keyPressed(KeyEvent e) {
//		System.out.println(e.getKeyCode());
		code = e.getKeyCode();
		codeChar = e.getKeyChar();
		isPressed = true;

		for (int i = 0; i < 4; i++) {
			if (Player.playerKeyInfors[i].getUp() == e.getKeyCode()) {
				if (recordKey[i][currentKey[i]] != e.getKeyCode()) {
					currentKey[i] = (currentKey[i] + 4) % 3;
					recordKey[i][currentKey[i]] = e.getKeyCode();
				}
				Player.playerKeyStates[i].setUp(true);
			} else if (Player.playerKeyInfors[i].getDown() == e.getKeyCode()) {
				if (recordKey[i][currentKey[i]] != e.getKeyCode()) {
					currentKey[i] = (currentKey[i] + 4) % 3;
					recordKey[i][currentKey[i]] = e.getKeyCode();
				}
				Player.playerKeyStates[i].setDown(true);
			} else if (Player.playerKeyInfors[i].getLeft() == e.getKeyCode()) {
				if (!run[i][0]) {
					Time[i][0][index[0]] = System.currentTimeMillis();
					index[0] = (index[0] + 1) % 2;
					run[i][0] = true;
				}
				if (recordKey[i][currentKey[i]] != e.getKeyCode()) {
					currentKey[i] = (currentKey[i] + 4) % 3;
					recordKey[i][currentKey[i]] = e.getKeyCode();
				}
				Player.playerKeyStates[i].setLeft(true);
			} else if (Player.playerKeyInfors[i].getRight() == e.getKeyCode()) {
				if (!run[i][1]) {
					Time[i][1][index[1] % 2] = System.currentTimeMillis();
					index[1] = (index[1] + 1) % 2;
					run[i][1] = true;
				}
				if (recordKey[i][currentKey[i]] != e.getKeyCode()) {
					currentKey[i] = (currentKey[i] + 4) % 3;
					recordKey[i][currentKey[i]] = e.getKeyCode();
				}
				Player.playerKeyStates[i].setRight(true);
			} else if (Player.playerKeyInfors[i].getAttack() == e.getKeyCode()
					&& !Player.playerKeyStates[i].isAttackState()) {
				currentKey[i] = (currentKey[i] + 4) % 3;
				recordKey[i][currentKey[i]] = e.getKeyCode();
				Player.playerKeyStates[i].setAttack(true);
				Player.playerKeyStates[i].setAttackState(true);
			} else if (Player.playerKeyInfors[i].getJump() == e.getKeyCode() && !Player.playerKeyStates[i].isJumpState()) {
				currentKey[i] = (currentKey[i] + 4) % 3;
				recordKey[i][currentKey[i]] = e.getKeyCode();
				Player.playerKeyStates[i].setJump(true);
				Player.playerKeyStates[i].setJumpState(true);
			} else if (Player.playerKeyInfors[i].getDefend() == e.getKeyCode()
					&& !Player.playerKeyStates[i].isDefendState()) {
				currentKey[i] = (currentKey[i] + 4) % 3;
				recordKey[i][currentKey[i]] = e.getKeyCode();
				Player.playerKeyStates[i].setDefend(true);
				Player.playerKeyStates[i].setDefendState(true);
			}
		}

		switch (e.getKeyCode()) {
		case 27:
			message = JOptionPane.showConfirmDialog(jFrame, "Are you sure to quit?", "LF2", JOptionPane.YES_NO_OPTION,
					JOptionPane.WARNING_MESSAGE);
			if (message == JOptionPane.YES_OPTION)
				System.exit(0);
			break;
		}
		if (e.getKeyCode() >= 96 && e.getKeyCode() <= 111) {
			codeString = "Keypad: " + String.valueOf(e.getKeyChar());
		} else if (e.getKeyCode() >= 65 && e.getKeyCode() <= 90) {
			codeString = String.valueOf((char) e.getKeyCode());
		} else if (e.getKeyCode() >= 44 && e.getKeyCode() <= 57 || e.getKeyCode() == 59 || e.getKeyCode() == 61
				|| e.getKeyCode() >= 91 && e.getKeyCode() <= 93 || e.getKeyCode() == 192 || e.getKeyCode() == 222) {
			codeString = String.valueOf(Keyboard.codeChar);
		} else if (e.getKeyCode() >= 32 && e.getKeyCode() <= 40) {
			String code[] = { "Space", "PageUp", "PageDown", "End", "Home", "Left", "Up", "Right", "Down" };
			codeString = code[e.getKeyCode() - 32];
		} else {
			switch (e.getKeyCode()) {
			case 8:
				codeString = "Backspace";
				break;
			case 9:
				codeString = "Tab";
				break;
			case 10:
				codeString = "Enter";
				break;
			case 16:
				codeString = "Shift";
				break;
			case 17:
				codeString = "Ctrl";
				break;
			case 20:
				codeString = "CapsLock";
				break;
			case 127:
				codeString = "Delete";
				break;
			case 155:
				codeString = "Insert";
				break;
			default:
				codeString = "none";
				break;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		isPressed = false;
		for (int i = 0; i < 4; i++) {
			if (Player.playerKeyInfors[i].getUp() == e.getKeyCode())
				Player.playerKeyStates[i].setUp(false);
			else if (Player.playerKeyInfors[i].getDown() == e.getKeyCode())
				Player.playerKeyStates[i].setDown(false);
			else if (Player.playerKeyInfors[i].getLeft() == e.getKeyCode()) {
				run[i][0] = false;
				Player.playerKeyStates[i].setLeft(false);
			} else if (Player.playerKeyInfors[i].getRight() == e.getKeyCode()) {
				run[i][1] = false;
				Player.playerKeyStates[i].setRight(false);
			} else if (Player.playerKeyInfors[i].getAttack() == e.getKeyCode()
					&& Player.playerKeyStates[i].isAttackState()) {
				Player.playerKeyStates[i].setAttack(false);
				Player.playerKeyStates[i].setAttackState(false);
			} else if (Player.playerKeyInfors[i].getJump() == e.getKeyCode() && Player.playerKeyStates[i].isJumpState()) {
				Player.playerKeyStates[i].setJump(false);
				Player.playerKeyStates[i].setJumpState(false);
			} else if (Player.playerKeyInfors[i].getDefend() == e.getKeyCode()
					&& Player.playerKeyStates[i].isDefendState()) {
				Player.playerKeyStates[i].setDefend(false);
				Player.playerKeyStates[i].setDefendState(false);
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {

	}

}
