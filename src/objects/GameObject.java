package objects;

import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class GameObject {

	public static int NO = 0;

	protected float x;
	protected float y;
	protected int width;
	protected int height;

	public abstract void tick();

	public abstract void render(Graphics g);

	public abstract Rectangle getBounds();

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
}
