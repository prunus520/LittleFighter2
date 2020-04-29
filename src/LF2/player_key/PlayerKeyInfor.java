package LF2.player_key;

public class PlayerKeyInfor {

	private String name;
	private int up, down, left, right, attack, jump, defend;
	private boolean isJoin = false;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setUp(String up) {
		this.up = coder(up);
	}

	public void setDown(String down) {
		this.down = coder(down);
	}

	public void setLeft(String left) {
		this.left = coder(left);
	}

	public void setRight(String right) {
		this.right = coder(right);
	}

	public void setAttack(String attack) {
		this.attack = coder(attack);
	}

	public void setJump(String jump) {
		this.jump = coder(jump);
	}

	public void setDefend(String defend) {
		this.defend = coder(defend);
	}

	public int getUp() {
		return up;
	}

	public int getDown() {
		return down;
	}

	public int getLeft() {
		return left;
	}

	public int getRight() {
		return right;
	}

	public int getAttack() {
		return attack;
	}

	public int getJump() {
		return jump;
	}

	public int getDefend() {
		return defend;
	}

	public void setUp(int up) {
		this.up = up;
	}

	public boolean isJoin() {
		return isJoin;
	}

	public void setJoin(boolean isJoin) {
		this.isJoin = isJoin;
	}

	int coder(String string) {
		int code = 0;

		switch (string) {
		case "Backspace":
			code = 8;
			break;
		case "Tab":
			code = 9;
			break;
		case "Enter":
			code = 10;
			break;
		case "Shift":
			code = 16;
			break;
		case "Ctrl":
			code = 17;
			break;
		case "CapsLock":
			code = 20;
			break;
		case "Space":
			code = 32;
			break;
		case "PageUp":
			code = 33;
			break;
		case "PageDown":
			code = 34;
			break;
		case "End":
			code = 35;
			break;
		case "Home":
			code = 36;
			break;
		case "Left":
			code = 37;
			break;
		case "Up":
			code = 38;
			break;
		case "Right":
			code = 39;
			break;
		case "Down":
			code = 40;
			break;
		case "Delete":
			code = 127;
			break;
		case "Insert":
			code = 155;
			break;
		case "Keypad: 0":
			code = 96;
			break;
		case "Keypad: 1":
			code = 97;
			break;
		case "Keypad: 2":
			code = 98;
			break;
		case "Keypad: 3":
			code = 99;
			break;
		case "Keypad: 4":
			code = 100;
			break;
		case "Keypad: 5":
			code = 101;
			break;
		case "Keypad: 6":
			code = 102;
			break;
		case "Keypad: 7":
			code = 103;
			break;
		case "Keypad: 8":
			code = 104;
			break;
		case "Keypad: 9":
			code = 105;
			break;
		case "Keypad: *":
			code = 106;
			break;
		case "Keypad: +":
			code = 107;
			break;
		case "Keypad: -":
			code = 109;
			break;
		case "Keypad: .":
			code = 110;
			break;
		case "Keypad: /":
			code = 111;
			break;
		case "`":
			code = 192;
			break;
		case "'":
			code = 222;
			break;
		}

		if (code == 0)
			code = string.charAt(0);

		return code;
	}

}
