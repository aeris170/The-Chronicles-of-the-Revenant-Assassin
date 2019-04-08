package state;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import components.HUD;
import components.TransparentButton;
import handler.Handler;
import io.KeyManager;
import io.sound.AudioPlayer;
import level.Level;
import level.LevelBuilder;
import main.Main;
import objects.GameObject;
import objects.blocks.Block;
import objects.characters.npcs.NPC;
import objects.triggers.Trigger;

public class GameState extends State implements Runnable {

	private static final long serialVersionUID = -3990554550715465745L;

	private Level currentLevel;
	private HUD hud;
	private final TransparentButton backButton;
	private boolean running = false;
	private boolean loading = false;

	public GameState() throws IOException {
		super();
		this.hud = new HUD();
		this.backButton = new TransparentButton("Back");
		super.setLayout(null);
		this.backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Main.getWindow().add(Main.getWindow().getMainMenu());
				currentLevel.pauseMusic();
				AudioPlayer.mainMenuMusicStream.resume();
			}
		});

		super.setOpaque(true);
		super.add(this.backButton);
		super.setBackground(Color.BLACK);
		this.backButton.setSize(300, 60);
		this.backButton.setLocation(810, 890);

		init();
	}

	private void init() {
		try {
			loadLevel("lvl0");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public synchronized void paintComponent(Graphics g) {
		if (!loading) {
			super.paintComponent(g);
			currentLevel.render(g);
			hud.paintComponent(g);
		}
	}

	public synchronized void pause() {
		running = false;
	}

	@Override
	public void run() {
		if (running) {
			long lastTime = System.nanoTime();
			double amountOfTicks = 60.0;
			double ns = 1000000000 / amountOfTicks;
			double delta = 0;
			long timer = System.currentTimeMillis();
			int updates = 0;
			int frames = 0;
			while (running) {
				long now = System.nanoTime();
				delta += (now - lastTime) / ns;
				lastTime = now;
				while (delta >= 1) {
					if (!loading)
						currentLevel.tick();
					updates++;
					delta--;
				}
				repaint();
				revalidate();
				requestFocus();
				frames++;
				if (System.currentTimeMillis() - timer > 1000) {
					timer += 1000;
					System.out.println("FPS: " + frames + " TICKS: " + updates);
					frames = 0;
					updates = 0;
				}
			}
		} else {
			KeyManager.getInstance();
			running = true;
			run();
		}
	}

	public Level getCurrentLevel() {
		return currentLevel;
	}

	public void loadLevel(String newLevel) throws IOException {
		loading = true;
		boolean processed = false;
		if (currentLevel != null) {
			processed = true;
			Handler.loading = true;
			currentLevel.pauseMusic();
		}
		System.out.println(newLevel);
		BufferedImage levelLayout = ImageIO.read(getClass().getResource("/" + newLevel + "layout.png")); // LAYOUT
		try {
			File NPCStorage = new File(getClass().getResource("/" + newLevel + "NPCs.txt").toURI());
			Map<Integer, NPC> NPCList = LevelBuilder.buildNPCList(NPCStorage);
			List<GameObject[]> allEntities = LevelBuilder.build(levelLayout, NPCList);
			Block[] blocks = (Block[]) allEntities.get(0);
			Trigger[] triggers = (Trigger[]) allEntities.get(1);
			NPC[] npcs = (NPC[]) allEntities.get(2);
			BufferedImage bgImage = ImageIO.read(getClass().getResource("/" + newLevel + ".png")); // ACTUAL MAP
			currentLevel = new Level(newLevel, npcs, triggers, blocks, bgImage);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		if (currentLevel != null && processed) {
			Handler.loading = false;
			currentLevel.playMusic();
		}
		loading = false;
	}
}