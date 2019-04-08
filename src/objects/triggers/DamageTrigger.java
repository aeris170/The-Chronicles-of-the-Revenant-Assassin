package objects.triggers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import objects.characters.player.Player;

public class DamageTrigger extends Trigger {

	private int damage;

	public DamageTrigger(int x, int y, int width, int height, int damage) {
		super(x, y, width, height);
		super.id = ID.DAMAGE;
		this.damage = damage;
	}

	@Override
	public void tick() {}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
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
		if (!player.isInvincible())
			player.damage(damage);
	}
}
