package level;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

import javax.imageio.ImageIO;

import display.Camera;
import handler.Handler;
import io.sound.AudioPlayer;
import objects.blocks.Block;
import objects.characters.npcs.NPC;
import objects.characters.player.Player;
import objects.triggers.Trigger;

public class Level {

	// MAYBE TRY SPLITTING HANDLER INTO 4?
	private Handler handler;
	private BufferedImage bgImage;
	private BufferedImage overlay;
	private String name;
	private NPC[] npcs;
	private Trigger[] triggers;
	private Block[] blocks;
	private double gravity;

	public Level(String name, NPC[] npcs, Trigger[] triggers, Block[] blocks, BufferedImage bgImage) {
		super();
		this.name = name;
		this.npcs = npcs;
		this.triggers = triggers;
		this.blocks = blocks;
		this.bgImage = bgImage;
		try {
			this.overlay = ImageIO.read(getClass().getResource("/overlays/gameOverlay.png"));
		} catch (IOException e) {
			System.out.println("NO OVERLAY FOUND FOR GAME!!!!");
			e.printStackTrace();
		}
		this.handler = Handler.getInstance();
		this.handler.removeAll();
		this.handler.add(Player.getInstance());
		this.handler.addAll(Arrays.asList(npcs));
		this.handler.addAll(Arrays.asList(triggers));
		this.handler.addAll(Arrays.asList(blocks));
		setGravityConstant(0.35);
	}

	public synchronized void tick() {
		this.handler.tick();
		Camera.getInstance().tick();
	}

	public synchronized void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		float transformX = Camera.getInstance().getX();
		float transformY = Camera.getInstance().getY();

		if (transformX > Camera.offsetMaxX)
			transformX = Camera.offsetMaxX;
		else if (transformX < Camera.offsetMinX)
			transformX = Camera.offsetMinX;

		if (transformY < 412.35 && !Player.getInstance().getCameraBehaviour())
			transformY = Camera.offsetMinY;
		else if (transformY > Camera.offsetMaxY)
			transformY = Camera.offsetMaxY;

		g2d.translate(-transformX, -transformY); // BEGIN RELATIVE RENDER

		g.drawImage(bgImage, 57, 215, 16384, 16384, null);// BG IMAGE A.K.A. MAP
		this.handler.render(g);// OBJECTS LIKE ENEMIES PLAYER ETC...

		g2d.translate(transformX, transformY); // END RELATIVE RENDER

		g.drawImage(overlay, 0, 0, 1920, 1080, null); // OVERLAY
	}

	public double getGravityConstant() {
		return gravity;
	}

	public void setGravityConstant(double gravity) {
		this.gravity = gravity;
	}

	public Collection<NPC> getNPCs() {
		return Arrays.asList(npcs);
	}

	public Collection<Trigger> getTriggers() {
		return Arrays.asList(triggers);
	}

	public Collection<Block> getBlocks() {
		return Arrays.asList(blocks);
	}

	public double getTerminalVelocity() {
		return gravity * 250;
	}

	public String getLevelName() {
		return name;
	}

	public void pauseMusic() {
		int levelNumber = name.charAt(3) - 48;
		switch (levelNumber) {
			case 0:
				AudioPlayer.tutorialMusicStream.pause();
				break;
			case 1:
				AudioPlayer.act1MusicStream.pause();
				break;
			case 2:
				AudioPlayer.act2MusicStream.pause();
				break;
			case 3:
				AudioPlayer.act3MusicStream.pause();
				break;
			case 4:
				AudioPlayer.act5MusicStream.pause();
				break;
		}
	}

	public void playMusic() {
		int levelNumber = name.charAt(3) - 48;
		switch (levelNumber) {
			case 0:
				AudioPlayer.tutorialMusicStream.resume();
				break;
			case 1:
				AudioPlayer.act1MusicStream.resume();
				break;
			case 2:
				AudioPlayer.act2MusicStream.resume();
				break;
			case 3:
				AudioPlayer.act3MusicStream.resume();
				break;
			case 4:
				AudioPlayer.act5MusicStream.resume();
				break;
		}
	}
}
