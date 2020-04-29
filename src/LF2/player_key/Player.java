package LF2.player_key;

public class Player {

	public static PlayerKeyInfor playerKeyInfors[] = new PlayerKeyInfor[4];
	public static PlayerKeyState playerKeyStates[] = new PlayerKeyState[4];
	public static PlayerInfor playerInfors[] = new PlayerInfor[8];

	public Player() {
		for (int i = 0; i < 4; i++) {
			playerKeyInfors[i] = new PlayerKeyInfor();
			playerKeyStates[i] = new PlayerKeyState();
		}
		for (int i = 0; i < 8; i++) {
			playerInfors[i] = new PlayerInfor();
		}
	}

}