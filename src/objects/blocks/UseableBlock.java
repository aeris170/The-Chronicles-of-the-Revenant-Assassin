package objects.blocks;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class UseableBlock extends Block {

	public UseableBlock(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public void tick() {}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.YELLOW);
		g.drawRect((int) x, (int) y, width, height);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, (int) width, (int) height);
	}
}
