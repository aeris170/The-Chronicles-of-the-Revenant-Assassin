package io.image;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Assets {

	public static BufferedImage HUDHPFull;
	public static BufferedImage HUDHP2;
	public static BufferedImage HUDHP1;
	public static BufferedImage HUDHPEmpty;
	public static BufferedImage HUDShield;
	public static BufferedImage HUDSpecial;
	public static BufferedImage[] playerIdleR = new BufferedImage[4];
	public static BufferedImage[] playerIdleL = new BufferedImage[4];
	public static BufferedImage[] playerJumpR = new BufferedImage[13];
	public static BufferedImage[] playerJumpMoveR = new BufferedImage[11];
	public static BufferedImage[] playerSpringR = new BufferedImage[4];
	public static BufferedImage[] playerAttackR1 = new BufferedImage[6];
	public static BufferedImage[] playerAttackR2 = new BufferedImage[5];
	public static BufferedImage[] playerAttackL1 = new BufferedImage[6];
	public static BufferedImage[] playerAttackL2 = new BufferedImage[5];
	public static BufferedImage[] playerMoveR = new BufferedImage[16];
	public static BufferedImage[] playerMoveL = new BufferedImage[2];

	static {
		try {
			HUDHPFull = ImageIO.read(Assets.class.getResource("/hud/health_full.png"));
			HUDHP2 = ImageIO.read(Assets.class.getResource("/hud/health_almost_full.png"));
			HUDHP1 = ImageIO.read(Assets.class.getResource("/hud/health_almost_dead.png"));
			HUDHPEmpty = ImageIO.read(Assets.class.getResource("/hud/health_empty.png"));
			HUDShield = ImageIO.read(Assets.class.getResource("/hud/shield.png"));
			HUDSpecial = ImageIO.read(Assets.class.getResource("/hud/special.png"));

			playerIdleR[0] = ImageIO.read(Assets.class.getResource("/characters/newPlayer/idleR1.png"));
			playerIdleR[1] = ImageIO.read(Assets.class.getResource("/characters/newPlayer/idleR2.png"));
			playerIdleR[2] = ImageIO.read(Assets.class.getResource("/characters/newPlayer/idleR3.png"));
			playerIdleR[3] = ImageIO.read(Assets.class.getResource("/characters/newPlayer/idleR4.png"));

			playerJumpR[0] = ImageIO.read(Assets.class.getResource("/characters/newPlayer/jumpR1.png"));
			playerJumpR[1] = ImageIO.read(Assets.class.getResource("/characters/newPlayer/jumpR2.png"));
			playerJumpR[2] = ImageIO.read(Assets.class.getResource("/characters/newPlayer/jumpR3.png"));
			playerJumpR[3] = ImageIO.read(Assets.class.getResource("/characters/newPlayer/jumpR4.png"));
			playerJumpR[4] = ImageIO.read(Assets.class.getResource("/characters/newPlayer/jumpR5.png"));
			playerJumpR[5] = ImageIO.read(Assets.class.getResource("/characters/newPlayer/jumpR6.png"));
			playerJumpR[6] = ImageIO.read(Assets.class.getResource("/characters/newPlayer/jumpR7.png"));
			playerJumpR[7] = ImageIO.read(Assets.class.getResource("/characters/newPlayer/jumpR7.png"));
			playerJumpR[8] = ImageIO.read(Assets.class.getResource("/characters/newPlayer/jumpR7.png"));
			playerJumpR[9] = ImageIO.read(Assets.class.getResource("/characters/newPlayer/springR1.png"));
			playerJumpR[10] = ImageIO.read(Assets.class.getResource("/characters/newPlayer/springR2.png"));
			playerJumpR[11] = ImageIO.read(Assets.class.getResource("/characters/newPlayer/springR3.png"));
			playerJumpR[12] = ImageIO.read(Assets.class.getResource("/characters/newPlayer/springR4.png"));
			
			playerJumpMoveR[0] = ImageIO.read(Assets.class.getResource("/characters/newPlayer/jumpMoveR1.png"));
			playerJumpMoveR[1] = ImageIO.read(Assets.class.getResource("/characters/newPlayer/jumpMoveR2.png"));
			playerJumpMoveR[2] = ImageIO.read(Assets.class.getResource("/characters/newPlayer/jumpMoveR3.png"));
			playerJumpMoveR[3] = ImageIO.read(Assets.class.getResource("/characters/newPlayer/jumpMoveR4.png"));
			playerJumpMoveR[4] = ImageIO.read(Assets.class.getResource("/characters/newPlayer/jumpMoveR5.png"));
			playerJumpMoveR[5] = ImageIO.read(Assets.class.getResource("/characters/newPlayer/jumpMoveR6.png"));
			
			playerSpringR[0] = ImageIO.read(Assets.class.getResource("/characters/newPlayer/springR1.png"));
			playerSpringR[1] = ImageIO.read(Assets.class.getResource("/characters/newPlayer/springR2.png"));
			playerSpringR[2] = ImageIO.read(Assets.class.getResource("/characters/newPlayer/springR3.png"));
			playerSpringR[3] = ImageIO.read(Assets.class.getResource("/characters/newPlayer/springR4.png"));
			
			playerAttackR1[0] = ImageIO.read(Assets.class.getResource("/characters/player/playerAttackR11.png"));
			playerAttackR1[1] = ImageIO.read(Assets.class.getResource("/characters/player/playerAttackR12.png"));
			playerAttackR1[2] = ImageIO.read(Assets.class.getResource("/characters/player/playerAttackR13.png"));
			playerAttackR1[3] = ImageIO.read(Assets.class.getResource("/characters/player/playerAttackR14.png"));
			playerAttackR1[4] = ImageIO.read(Assets.class.getResource("/characters/player/playerAttackR15.png"));
			playerAttackR1[5] = ImageIO.read(Assets.class.getResource("/characters/player/playerAttackR16.png"));

			playerAttackR2[0] = ImageIO.read(Assets.class.getResource("/characters/player/playerAttackR21.png"));
			playerAttackR2[1] = ImageIO.read(Assets.class.getResource("/characters/player/playerAttackR22.png"));
			playerAttackR2[2] = ImageIO.read(Assets.class.getResource("/characters/player/playerAttackR23.png"));
			playerAttackR2[3] = ImageIO.read(Assets.class.getResource("/characters/player/playerAttackR24.png"));
			playerAttackR2[4] = ImageIO.read(Assets.class.getResource("/characters/player/playerAttackR25.png"));

			playerMoveR[0] = ImageIO.read(Assets.class.getResource("/characters/newPlayer/moveR1.png"));
			playerMoveR[1] = ImageIO.read(Assets.class.getResource("/characters/newPlayer/moveR2.png"));
			playerMoveR[2] = ImageIO.read(Assets.class.getResource("/characters/newPlayer/moveR3.png"));
			playerMoveR[3] = ImageIO.read(Assets.class.getResource("/characters/newPlayer/moveR4.png"));
			playerMoveR[4] = ImageIO.read(Assets.class.getResource("/characters/newPlayer/moveR5.png"));
			playerMoveR[5] = ImageIO.read(Assets.class.getResource("/characters/newPlayer/moveR6.png"));
			playerMoveR[6] = ImageIO.read(Assets.class.getResource("/characters/newPlayer/moveR7.png"));
			playerMoveR[7] = ImageIO.read(Assets.class.getResource("/characters/newPlayer/moveR8.png"));
			playerMoveR[8] = ImageIO.read(Assets.class.getResource("/characters/newPlayer/moveR9.png"));
			playerMoveR[9] = ImageIO.read(Assets.class.getResource("/characters/newPlayer/moveR10.png"));
			playerMoveR[10] = ImageIO.read(Assets.class.getResource("/characters/newPlayer/moveR11.png"));
			playerMoveR[11] = ImageIO.read(Assets.class.getResource("/characters/newPlayer/moveR12.png"));
			playerMoveR[12] = ImageIO.read(Assets.class.getResource("/characters/newPlayer/moveR13.png"));
			playerMoveR[13] = ImageIO.read(Assets.class.getResource("/characters/newPlayer/moveR14.png"));			
			playerMoveR[14] = ImageIO.read(Assets.class.getResource("/characters/newPlayer/moveR15.png"));
			playerMoveR[15] = ImageIO.read(Assets.class.getResource("/characters/newPlayer/moveR16.png"));

			playerIdleL[0] = ImageIO.read(Assets.class.getResource("/characters/player/playerIdleL1.png"));
			playerIdleL[1] = ImageIO.read(Assets.class.getResource("/characters/player/playerIdleL2.png"));
			playerIdleL[2] = ImageIO.read(Assets.class.getResource("/characters/player/playerIdleL3.png"));
			playerIdleL[3] = ImageIO.read(Assets.class.getResource("/characters/player/playerIdleL4.png"));

			playerAttackL1[0] = ImageIO.read(Assets.class.getResource("/characters/player/playerAttackL11.png"));
			playerAttackL1[1] = ImageIO.read(Assets.class.getResource("/characters/player/playerAttackL12.png"));
			playerAttackL1[2] = ImageIO.read(Assets.class.getResource("/characters/player/playerAttackL13.png"));
			playerAttackL1[3] = ImageIO.read(Assets.class.getResource("/characters/player/playerAttackL14.png"));
			playerAttackL1[4] = ImageIO.read(Assets.class.getResource("/characters/player/playerAttackL15.png"));
			playerAttackL1[5] = ImageIO.read(Assets.class.getResource("/characters/player/playerAttackL16.png"));

			playerAttackL2[0] = ImageIO.read(Assets.class.getResource("/characters/player/playerAttackL21.png"));
			playerAttackL2[1] = ImageIO.read(Assets.class.getResource("/characters/player/playerAttackL22.png"));
			playerAttackL2[2] = ImageIO.read(Assets.class.getResource("/characters/player/playerAttackL23.png"));
			playerAttackL2[3] = ImageIO.read(Assets.class.getResource("/characters/player/playerAttackL24.png"));
			playerAttackL2[4] = ImageIO.read(Assets.class.getResource("/characters/player/playerAttackL25.png"));

			playerMoveL[0] = ImageIO.read(Assets.class.getResource("/characters/player/playerMoveL1.png"));
			playerMoveL[1] = ImageIO.read(Assets.class.getResource("/characters/player/playerMoveL2.png"));
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	@SuppressWarnings("unused")
	private static BufferedImage[] processSpriteSheet(BufferedImage img, int startx, int starty, int widthOfEach, int heightOfEach) {
		BufferedImage[] images = new BufferedImage[img.getWidth() / widthOfEach];
		int xx = startx;
		int yy = starty;
		for (int i = 0; xx < img.getWidth() - widthOfEach; i++) {
			images[i] = img.getSubimage(xx, yy, widthOfEach, yy + heightOfEach);
			xx += widthOfEach;
		}
		return images;
	}
}