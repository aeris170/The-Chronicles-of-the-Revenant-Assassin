package state;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import components.TransparentButton;
import display.Window;
import io.sound.AudioPlayer;
import main.Main;

public class ExitMenuState extends State {

	private static final long serialVersionUID = 4548543840942961704L;

	private BufferedImage normal;
	private BufferedImage hoverYes;
	private BufferedImage hoverNo;
	private BufferedImage bgImage;
	private final TransparentButton yesButton;
	private final TransparentButton noButton;

	public ExitMenuState() {
		super();
		super.setLayout(null);

		yesButton = new TransparentButton();
		noButton = new TransparentButton();

		try {
			this.normal = ImageIO.read(getClass().getResource("/backgrounds/exitMenu.png"));
			this.hoverYes = ImageIO.read(getClass().getResource("/backgrounds/exitMenuHoverYes.png"));
			this.hoverNo = ImageIO.read(getClass().getResource("/backgrounds/exitMenuHoverNo.png"));
			this.bgImage = normal;
		} catch (IOException ex) {
			System.out.println("FUCKED UP! EXIT MENU BG!");
			this.normal = null;
			this.hoverYes = null;
			this.hoverNo = null;
			this.bgImage = null;
		}

		this.yesButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(1);
			}
		});

		this.yesButton.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				ExitMenuState.this.bgImage = hoverYes;
				AudioPlayer.menuButtonHoverStream.loop(0);
			}

			public void mouseExited(MouseEvent e) {
				ExitMenuState.this.bgImage = normal;
			}
		});

		this.noButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ExitMenuState.this.bgImage = normal;
				Main.getWindow().add(Main.getWindow().getMainMenu());
			}
		});

		this.noButton.addMouseListener(new MouseAdapter() {

			public void mouseEntered(MouseEvent e) {
				ExitMenuState.this.bgImage = hoverNo;
				AudioPlayer.menuButtonHoverStream.loop(0);
			}

			public void mouseExited(MouseEvent e) {
				ExitMenuState.this.bgImage = normal;
			}
		});

		super.setOpaque(true);
		super.add(this.yesButton);
		super.add(this.noButton);

		this.yesButton.setSize(300, 120);
		this.yesButton.setLocation(560, 520);

		this.noButton.setSize(300, 120);
		this.noButton.setLocation(1060, 520);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(bgImage, 0, 0, Window.WIDTH, Window.HEIGHT, null);
	}
}
