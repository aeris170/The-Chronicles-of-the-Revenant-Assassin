package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import io.image.Assets;
import objects.characters.player.Player;

public class HUD extends JPanel {

	private static final long serialVersionUID = 1343610949450357316L;

	private static BufferedImage special = Assets.HUDSpecial;
	private static BufferedImage shield = Assets.HUDShield;
	private static BufferedImage hpFull = Assets.HUDHPFull;
	private static BufferedImage hp2 = Assets.HUDHP2;
	private static BufferedImage hp1 = Assets.HUDHP1;
	private static BufferedImage hpEmpty = Assets.HUDHPEmpty;

	public HUD() {
		super();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Player p = Player.getInstance();
		switch (p.getHitPoints()) {
			case 0:
				g.drawImage(hpEmpty, 20, 20, 512, 162, null);
				break;
			case 1:
				g.drawImage(hp1, 20, 20, 512, 162, null);
				break;
			case 2:
				g.drawImage(hp2, 20, 20, 512, 162, null);
				break;
			case 3:
				g.drawImage(hpFull, 20, 20, 512, 162, null);
				break;
		}
		switch (p.getSpecial()) {
			case 0:
				break;
			case 1:
				g.drawImage(special, 700, 15, 120, 100, null);
				// g.drawImage(special, 700, 15, 240, 160, null);
				break;
			case 2:
				g.drawImage(special, 700, 15, 120, 100, null);
				g.drawImage(special, 730, 45, 120, 100, null);
				// g.drawImage(special, 700, 15, 180, 120, null);
				// g.drawImage(special, 760, 45, 180, 120, null);
				break;
			case 3:
				g.drawImage(special, 700, 15, 120, 100, null);
				g.drawImage(special, 730, 45, 120, 100, null);
				g.drawImage(special, 760, 75, 120, 100, null);
				break;
		}
		g.setColor(Color.DARK_GRAY);
		g.fillRect(550, 32, 120, 145);
		Color shieldFillColor = new Color(200, 200, 30);
		g.setColor(shieldFillColor);
		int shieldInteger = (int) (p.getShield() * 120.0);
		for (int i = 0; i < shieldInteger; i++) {
			g.drawLine(550, 166 - i, 669, 166 - i);
		}
		g.drawImage(shield, 550, 32, 120, 145, null);
	}
}
