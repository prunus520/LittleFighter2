package LF2;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;

public class LF2Data {

	public static BufferedImage bg = null;

	public static List<BufferedImage> back = new ArrayList<BufferedImage>();

	public static BufferedImage s = null;

	public static List<BufferedImage> join = new ArrayList<BufferedImage>();

	public static List<BufferedImage> num = new ArrayList<BufferedImage>();

	public LF2Data() {

	}

	public void bg() {
		try {
			bg = ImageIO.read(getClass().getResource("image/system/fightStage.png"));
			for (int i = 1; i < 5; i++)
				back.add(ImageIO.read(getClass().getResource("image/bg/hkc/back" + i + ".png")));
			for (int i = 0; i < 8; i++)
				join.add(ImageIO.read(getClass().getResource("image/join/join" + i + ".png")));
			for (int i = 1; i < 6; i++)
				num.add(ImageIO.read(getClass().getResource("image/system/num" + i + ".png")));
			s = ImageIO.read(getClass().getResource("image/bg/s.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static List<BufferedImage> allcapoo_0Image = new ArrayList<BufferedImage>();

	public static List<BufferedImage> allcapoo_0mirrorImage = new ArrayList<BufferedImage>();

	public static List<BufferedImage> allcapoo_1Image = new ArrayList<BufferedImage>();

	public static List<BufferedImage> allcapoo_1mirrorImage = new ArrayList<BufferedImage>();

	public static List<BufferedImage> allcapoo_2Image = new ArrayList<BufferedImage>();

	public static List<BufferedImage> allcapoo_2mirrorImage = new ArrayList<BufferedImage>();

	public static List<BufferedImage> capooAttackImage = new ArrayList<BufferedImage>();

	public static List<BufferedImage> capooAttack_mirrorImage = new ArrayList<BufferedImage>();

	public static BufferedImage capooDefenseImage = null;

	public static BufferedImage capooDefense_mirrorImage = null;

	public static List<BufferedImage> allcapooMoveImage = new ArrayList<BufferedImage>();

	public static List<BufferedImage> allcapooMove_mirrorImage = new ArrayList<BufferedImage>();

	public static BufferedImage capooRunImage = null;

	public static BufferedImage capooRun_mirrorImage = null;

	public static List<BufferedImage> allcapooball_Image = new ArrayList<BufferedImage>();

	public static List<BufferedImage> allcapooball_mirror_Image = new ArrayList<BufferedImage>();

	public static List<BufferedImage> allcapooball2_Image = new ArrayList<BufferedImage>();

	public static List<BufferedImage> allcapooball2_mirror_Image = new ArrayList<BufferedImage>();

	public void capoo() {
		for (int i = 1; i <= 2; i++) {
			try {
				allcapoo_0Image
						.add(ImageIO.read(getClass().getResource("image/character/capoo/capoo_0_" + i + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		for (int i = 1; i <= 2; i++) {
			try {
				allcapoo_0mirrorImage
						.add(ImageIO.read(getClass().getResource("image/character/capoo/capoo_0mirror_" + i + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		for (int i = 1; i <= 3; i++) {
			try {
				allcapoo_1Image
						.add(ImageIO.read(getClass().getResource("image/character/capoo/capoo_1_" + i + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		for (int i = 1; i <= 3; i++) {
			try {
				allcapoo_1mirrorImage
						.add(ImageIO.read(getClass().getResource("image/character/capoo/capoo_1mirror_" + i + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		for (int i = 1; i <= 7; i++) {
			try {
				allcapoo_2Image
						.add(ImageIO.read(getClass().getResource("image/character/capoo/capoo_2_" + i + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		for (int i = 1; i <= 7; i++) {
			try {
				allcapoo_2mirrorImage
						.add(ImageIO.read(getClass().getResource("image/character/capoo/capoo_2mirror_" + i + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		for (int i = 1; i <= 2; i++) {
			try {
				capooAttackImage
						.add(ImageIO.read(getClass().getResource("image/character/capoo/capooAttack" + i + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		for (int i = 1; i <= 2; i++) {
			try {
				capooAttack_mirrorImage.add(
						ImageIO.read(getClass().getResource("image/character/capoo/capooAttack_mirror" + i + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			capooDefenseImage = ImageIO.read(getClass().getResource("image/character/capoo/capooDefense" + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			capooDefense_mirrorImage = ImageIO
					.read(getClass().getResource("image/character/capoo/capooDefense_mirror" + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 1; i <= 5; i++) {
			try {
				allcapooMoveImage
						.add(ImageIO.read(getClass().getResource("image/character/capoo/capooMove_" + i + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		for (int i = 1; i <= 5; i++) {
			try {
				allcapooMove_mirrorImage.add(
						ImageIO.read(getClass().getResource("image/character/capoo/capooMove_mirror_" + i + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			capooRunImage = ImageIO.read(getClass().getResource("image/character/capoo/capooRun" + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			capooRun_mirrorImage = ImageIO
					.read(getClass().getResource("image/character/capoo/capooRun_mirror" + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 1; i <= 10; i++) {
			try {
				allcapooball_Image
						.add(ImageIO.read(getClass().getResource("image/character/skill/capooball_" + i + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		for (int i = 1; i <= 10; i++) {
			try {
				allcapooball_mirror_Image.add(
						ImageIO.read(getClass().getResource("image/character/skill/capooball_mirror_" + i + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		for (int i = 1; i <= 6; i++) {
			try {
				allcapooball2_Image
						.add(ImageIO.read(getClass().getResource("image/character/skill/capooball2_" + i + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		for (int i = 1; i <= 6; i++) {
			try {
				allcapooball2_mirror_Image.add(
						ImageIO.read(getClass().getResource("image/character/skill/capooball2_mirror_" + i + ".png")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static List<BufferedImage> alljake_0Image = new ArrayList<BufferedImage>();

	public static List<BufferedImage> alljake_0mirrorImage = new ArrayList<BufferedImage>();

	public static List<BufferedImage> alljake_1Image = new ArrayList<BufferedImage>();

	public static List<BufferedImage> alljake_1mirrorImage = new ArrayList<BufferedImage>();

	public static List<BufferedImage> alljakeAttackImage = new ArrayList<BufferedImage>();

	public static List<BufferedImage> alljakeAttack_mirrorImage = new ArrayList<BufferedImage>();

	public static BufferedImage jakeDefenseImage = null;

	public static BufferedImage jakeDefense_mirrorImage = null;

	public static List<BufferedImage> alljakeMoveImage = new ArrayList<BufferedImage>();

	public static List<BufferedImage> alljakeMove_mirrorImage = new ArrayList<BufferedImage>();

	public static BufferedImage jakeStandImage = null;

	public static BufferedImage jakeStand_mirrorImage = null;

	public static List<BufferedImage> alljakeexp_Image = new ArrayList<BufferedImage>();

	public static List<BufferedImage> alljakeexp_mirror_Image = new ArrayList<BufferedImage>();

	public void jake() {
		for (int i = 1; i <= 4; i++) {
			try {
				alljake_0Image.add(ImageIO.read(getClass().getResource("image/character/jake/jake_0_" + i + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		for (int i = 1; i <= 4; i++) {
			try {
				alljake_0mirrorImage
						.add(ImageIO.read(getClass().getResource("image/character/jake/jake_0mirror_" + i + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		for (int i = 1; i <= 3; i++) {
			try {
				alljake_1Image.add(ImageIO.read(getClass().getResource("image/character/jake/jake_1_" + i + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		for (int i = 1; i <= 3; i++) {
			try {
				alljake_1mirrorImage
						.add(ImageIO.read(getClass().getResource("image/character/jake/jake_1mirror_" + i + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		for (int i = 1; i <= 5; i++) {
			try {
				alljakeAttackImage
						.add(ImageIO.read(getClass().getResource("image/character/jake/jakeAttack_" + i + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		for (int i = 1; i <= 5; i++) {
			try {
				alljakeAttackImage
						.add(ImageIO.read(getClass().getResource("image/character/jake/jakeAttack_" + i + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		for (int i = 1; i <= 5; i++) {
			try {
				alljakeAttack_mirrorImage.add(
						ImageIO.read(getClass().getResource("image/character/jake/jakeAttack_mirror_" + i + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		for (int i = 1; i <= 2; i++) {
			try {
				alljakeMoveImage
						.add(ImageIO.read(getClass().getResource("image/character/jake/jakeMove_" + i + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		for (int i = 1; i <= 2; i++) {
			try {
				alljakeMove_mirrorImage.add(
						ImageIO.read(getClass().getResource("image/character/jake/jakeMove_mirror_" + i + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			jakeDefenseImage = ImageIO.read(getClass().getResource("image/character/jake/jakeDefense" + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			jakeDefense_mirrorImage = ImageIO
					.read(getClass().getResource("image/character/jake/jakeDefense_mirror" + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			jakeStandImage = ImageIO.read(getClass().getResource("image/character/jake/jakeStand" + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			jakeStand_mirrorImage = ImageIO
					.read(getClass().getResource("image/character/jake/jakeStand_mirror" + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 1; i <= 11; i++) {
			try {
				alljakeexp_Image
						.add(ImageIO.read(getClass().getResource("image/character/skill/jakeexp_" + i + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		for (int i = 1; i <= 11; i++) {
			try {
				alljakeexp_mirror_Image.add(
						ImageIO.read(getClass().getResource("image/character/skill/jakeexp_mirror_" + i + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static BufferedImage doraemon_0Image = null;

	public static BufferedImage doraemon_0_mirrorImage = null;

	public static BufferedImage doraemon_1Image = null;

	public static BufferedImage doraemon_1_mirrorImage = null;

	public static BufferedImage doraemon_attackImage = null;

	public static BufferedImage doraemon_attack_mirrorImage = null;

	public static BufferedImage doraemon_choiceImage = null;

	public static BufferedImage doraemon_defenseImage = null;

	public static BufferedImage doraemon_defense_mirrorImage = null;

	public static List<BufferedImage> alldoraemon_runImage = new ArrayList<BufferedImage>();

	public static List<BufferedImage> alldoraemon_runmirrorImage = new ArrayList<BufferedImage>();

	public static BufferedImage doraemon_standImage = null;

	public static BufferedImage doraemon_stand_mirrorImage = null;

	public static List<BufferedImage> alldoraemonball_Image = new ArrayList<BufferedImage>();

	public static List<BufferedImage> alldoraemonball_mirror_Image = new ArrayList<BufferedImage>();

	public void doraemon() {
		try {
			doraemon_0Image = ImageIO.read(getClass().getResource("image/character/doraemon/doraemon_0.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			doraemon_0_mirrorImage = ImageIO
					.read(getClass().getResource("image/character/doraemon/doraemon_0_mirror.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			doraemon_1Image = ImageIO.read(getClass().getResource("image/character/doraemon/doraemon_1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			doraemon_1_mirrorImage = ImageIO
					.read(getClass().getResource("image/character/doraemon/doraemon_1_mirror.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			doraemon_attackImage = ImageIO.read(getClass().getResource("image/character/doraemon/doraemon_attack.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			doraemon_attack_mirrorImage = ImageIO
					.read(getClass().getResource("image/character/doraemon/doraemon_attack_mirror.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			doraemon_defenseImage = ImageIO
					.read(getClass().getResource("image/character/doraemon/doraemon_defense.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			doraemon_defense_mirrorImage = ImageIO
					.read(getClass().getResource("image/character/doraemon/doraemon_defense_mirror.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 1; i <= 2; i++) {
			try {
				alldoraemon_runImage.add(
						ImageIO.read(getClass().getResource("image/character/doraemon/doraemon_run_" + i + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		for (int i = 1; i <= 2; i++) {
			try {
				alldoraemon_runmirrorImage.add(ImageIO
						.read(getClass().getResource("image/character/doraemon/doraemon_runmirror_" + i + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		try {
			doraemon_standImage = ImageIO.read(getClass().getResource("image/character/doraemon/doraemon_stand.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			doraemon_stand_mirrorImage = ImageIO
					.read(getClass().getResource("image/character/doraemon/doraemon_stand_mirror.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 1; i <= 8; i++) {
			try {
				alldoraemonball_Image
						.add(ImageIO.read(getClass().getResource("image/character/skill/doraemonball_" + i + ".png")));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		for (int i = 1; i <= 8; i++) {
			try {
				alldoraemonball_mirror_Image.add(ImageIO
						.read(getClass().getResource("image/character/skill/doraemonball_mirror_" + i + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static BufferedImage octopus_0Image = null;

	public static BufferedImage octopus_0_mirrorImage = null;

	public static BufferedImage octopus_1Image = null;

	public static BufferedImage octopus_1_mirrorImage = null;

	public static BufferedImage octopus_attackImage = null;

	public static BufferedImage octopus_attack_mirrorImage = null;

	public static BufferedImage octopus_choiceImage = null;

	public static BufferedImage octopus_defenseImage = null;

	public static BufferedImage octopus_defense_mirrorImage = null;

	public static BufferedImage octopus_runImage = null;

	public static BufferedImage octopus_run_mirrorImage = null;

	public static BufferedImage octopus_walkImage = null;

	public static BufferedImage octopus_walk_mirrorImage = null;

	public static List<BufferedImage> alloctopusball_Image = new ArrayList<BufferedImage>();

	public static List<BufferedImage> alloctopusball_mirror_Image = new ArrayList<BufferedImage>();

	public void octopus() {

		try {
			octopus_0Image = ImageIO.read(getClass().getResource("image/character/octopus/octopus_0.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			octopus_0_mirrorImage = ImageIO
					.read(getClass().getResource("image/character/octopus/octopus_0_mirror.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			octopus_1Image = ImageIO.read(getClass().getResource("image/character/octopus/octopus_1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			octopus_1_mirrorImage = ImageIO
					.read(getClass().getResource("image/character/octopus/octopus_1_mirror.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			octopus_attackImage = ImageIO.read(getClass().getResource("image/character/octopus/octopus_attack.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			octopus_attack_mirrorImage = ImageIO
					.read(getClass().getResource("image/character/octopus/octopus_attack_mirror.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			octopus_defenseImage = ImageIO.read(getClass().getResource("image/character/octopus/octopus_defense.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			octopus_defense_mirrorImage = ImageIO
					.read(getClass().getResource("image/character/octopus/octopus_defense_mirror.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			octopus_runImage = ImageIO.read(getClass().getResource("image/character/octopus/octopus_run.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			octopus_run_mirrorImage = ImageIO
					.read(getClass().getResource("image/character/octopus/octopus_run_mirror.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			octopus_walkImage = ImageIO.read(getClass().getResource("image/character/octopus/octopus_walk.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			octopus_walk_mirrorImage = ImageIO
					.read(getClass().getResource("image/character/octopus/octopus_walk_mirror.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		for (int i = 1; i <= 4; i++) {
			try {
				alloctopusball_Image
						.add(ImageIO.read(getClass().getResource("image/character/skill/octopusball_" + i + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		for (int i = 1; i <= 4; i++) {
			try {
				alloctopusball_mirror_Image.add(
						ImageIO.read(getClass().getResource("image/character/skill/octopusball_mirror_" + i + ".png")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
