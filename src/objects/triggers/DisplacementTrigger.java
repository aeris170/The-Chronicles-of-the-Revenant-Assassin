package objects.triggers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import objects.characters.player.Player;

public class DisplacementTrigger extends Trigger {

	private int destinationX;
	private int destinationY;

	public DisplacementTrigger(int x, int y, int width, int height, int destinationX, int destinationY) {
		super(x, y, width, height);
		super.id = ID.DISPLACEMENT;
		this.destinationX = destinationX;
		this.destinationY = destinationY;
	}

	@Override
	public void tick() {}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.BLUE);
		g.drawRect((int) x, (int) y, width, height);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, (int) width, (int) height);
	}

	@Override
	public ID getID() {
		return super.id;
	}

	@Override
	public void onTouch() {
		Player player = Player.getInstance();
		player.setX(destinationX);
		player.setY(destinationY);
		System.out.println("Player's position set to: " + destinationX + " " + destinationY + "!");
	}
}
