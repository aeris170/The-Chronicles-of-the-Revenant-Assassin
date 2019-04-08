package state;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import components.TransparentButton;
import display.Window;
import main.Main;

public class CreditsState extends State {

	private static final long serialVersionUID = 5549790029246070884L;

	private BufferedImage bgImage;
	private final TransparentButton backButton;

	public CreditsState() {
		super();
		super.setLayout(null);

		this.backButton = new TransparentButton("Back");

		try {
			bgImage = ImageIO.read(new File("/backgrounds/mainmenu.png"));
		} catch (IOException ex) {
			System.out.println("Fucked up during credits menu bg image");
		}

		this.backButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Main.getWindow().add(Main.getWindow().getMainMenu());
			}
		});

		super.setOpaque(true);
		super.add(this.backButton);

		this.backButton.setSize(300, 60);
		this.backButton.setLocation(810, 890);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		super.setBackground(Color.YELLOW);
		g.drawImage(bgImage, 0, 0, Window.WIDTH, Window.HEIGHT, null);
	}
}
