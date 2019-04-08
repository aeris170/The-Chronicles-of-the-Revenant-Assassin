package objects.blocks;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Air extends Block {

	public Air(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public void tick() {}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect((int) x, (int) y, width, height);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, (int) width, (int) height);
	}
}