package state;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import components.TransparentButton;
import components.VolumeSlider;
import display.Window;
import io.sound.AudioPlayer;
import main.Main;

public class MainMenuState extends State {

	private static final long serialVersionUID = -5818628262287325668L;

	private BufferedImage normal;
	private BufferedImage hoverPlay;
	private BufferedImage hoverCredits;
	private BufferedImage hoverExit;

	private BufferedImage bgImage;
	private final TransparentButton playButton;
	private final TransparentButton creditsButton;
	private final TransparentButton exitButton;
	private final VolumeSlider volumeBar;

	public MainMenuState() {
		super();
		super.setLayout(null);

		this.playButton = new TransparentButton();
		this.creditsButton = new TransparentButton();
		this.exitButton = new TransparentButton();
		this.volumeBar = new VolumeSlider(0, 100, 80); // don't ask :(
		try {
			this.normal = ImageIO.read(getClass().getResource("/backgrounds/mainMenu.png"));
			this.hoverPlay = ImageIO.read(getClass().getResource("/backgrounds/mainMenuHoverPlay.png"));
			this.hoverCredits = ImageIO.read(getClass().getResource("/backgrounds/mainMenuHoverCredits.png"));
			this.hoverExit = ImageIO.read(getClass().getResource("/backgrounds/mainMenuHoverExit.png"));
			this.bgImage = normal;
		} catch (IOException ex) {
			System.out.println("FUCKED UP! MAIN MENU BG!");
			this.normal = null;
			this.hoverPlay = null;
			this.hoverCredits = null;
			this.hoverExit = null;
			this.bgImage = null;
		}

		this.playButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainMenuState.this.bgImage = normal;
				Main.getWindow().add(Main.getWindow().getGame());
				AudioPlayer.mainMenuMusicStream.pause();
				AudioPlayer.tutorialMusicStream.resume();
			}
		});

		this.playButton.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				MainMenuState.this.bgImage = hoverPlay;
				AudioPlayer.menuButtonHoverStream.loop(0);
			}

			public void mouseExited(MouseEvent e) {
				MainMenuState.this.bgImage = normal;
			}
		});

		this.creditsButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainMenuState.this.bgImage = normal;
				Main.getWindow().add(Main.getWindow().getCredits());
			}
		});

		this.creditsButton.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				MainMenuState.this.bgImage = hoverCredits;
				AudioPlayer.menuButtonHoverStream.loop(0);
			}

			public void mouseExited(MouseEvent e) {
				MainMenuState.this.bgImage = normal;
			}
		});

		this.exitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainMenuState.this.bgImage = normal;
				Main.getWindow().add(Main.getWindow().getExitMenu());
				System.out.println("ok!");
			}
		});

		this.exitButton.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				MainMenuState.this.bgImage = hoverExit;
				AudioPlayer.menuButtonHoverStream.loop(0);
			}

			public void mouseExited(MouseEvent e) {
				MainMenuState.this.bgImage = normal;
			}
		});

		super.addMouseWheelListener(new MouseWheelListener() {

			@Override
			public void mouseWheelMoved(MouseWheelEvent e) {
				int amount = e.getWheelRotation();
				if (amount > 0) {
					while (amount > 0) {
						volumeBar.setValue(volumeBar.getValue() - 5);
						amount -= 5;
					}
				} else {
					while (amount < 0) {
						volumeBar.setValue(volumeBar.getValue() + 5);
						amount += 5;
					}
				}
				AudioPlayer.setGlobalVolume();
			}
		});
		super.setOpaque(true);
		super.add(this.playButton);
		super.add(this.creditsButton);
		super.add(this.exitButton);
		super.add(this.volumeBar);

		this.playButton.setSize(600, 140);
		this.playButton.setLocation(0, 330);

		this.creditsButton.setSize(600, 140);
		this.creditsButton.setLocation(0, 530);

		this.exitButton.setSize(600, 140);
		this.exitButton.setLocation(0, 740);

		this.volumeBar.setSize(300, 100);
		this.volumeBar.setLocation(30, 965);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bgImage, 0, 0, Window.WIDTH, Window.HEIGHT, null);
	}

	public VolumeSlider getVolumeSlider() {
		return this.volumeBar;
	}
}
