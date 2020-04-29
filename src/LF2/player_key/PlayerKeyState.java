package LF2.player_key;

public class PlayerKeyState {

	private boolean state = true;
	private boolean up = false, down = false, left = false, right = false, attack = false, jump = false, defend = false,
			upState = false, downState = false, leftState = false, rightState = false, attackState = false,
			jumpState = false, defendState = false;

	public boolean isState() {
		return state;
	}

	public void setState(boolean state) {
		this.state = state;
	}

	public boolean isUp() {
		return up;
	}

	public void setUp(boolean up) {
		this.up = up;
	}

	public boolean isDown() {
		return down;
	}

	public void setDown(boolean down) {
		this.down = down;
	}

	public boolean isLeft() {
		return left;
	}

	public void setLeft(boolean left) {
		this.left = left;
	}

	public boolean isRight() {
		return right;
	}

	public void setRight(boolean right) {
		this.right = right;
	}

	public boolean isAttack() {
		return attack;
	}

	public void setAttack(boolean attack) {
		this.attack = attack;
	}

	public boolean isJump() {
		return jump;
	}

	public void setJump(boolean jump) {
		this.jump = jump;
	}

	public boolean isDefend() {
		return defend;
	}

	public void setDefend(boolean defend) {
		this.defend = defend;
	}

	public boolean isUpState() {
		return upState;
	}

	public void setUpState(boolean upState) {
		this.upState = upState;
	}

	public boolean isDownState() {
		return downState;
	}

	public void setDownState(boolean downState) {
		this.downState = downState;
	}

	public boolean isLeftState() {
		return leftState;
	}

	public void setLeftState(boolean leftState) {
		this.leftState = leftState;
	}

	public boolean isRightState() {
		return rightState;
	}

	public void setRightState(boolean rightState) {
		this.rightState = rightState;
	}

	public boolean isAttackState() {
		return attackState;
	}

	public void setAttackState(boolean attackState) {
		this.attackState = attackState;
	}

	public boolean isJumpState() {
		return jumpState;
	}

	public void setJumpState(boolean jumpState) {
		this.jumpState = jumpState;
	}

	public boolean isDefendState() {
		return defendState;
	}

	public void setDefendState(boolean defendState) {
		this.defendState = defendState;
	}
}
