package objects.blocks;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class MoveableBlock extends Block {

	public MoveableBlock(int x, int y, int width, int height) {
		super(x, y, width, height);
	}

	@Override
	public void tick() {}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.WHITE);
		g.drawRect((int) x, (int) y, width, height);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, (int) width, (int) height);
	}
}