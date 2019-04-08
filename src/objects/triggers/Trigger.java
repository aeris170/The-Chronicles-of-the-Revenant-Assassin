package objects.triggers;

import java.awt.Graphics;

import objects.GameObject;

public abstract class Trigger extends GameObject {

	protected ID id;

	public Trigger(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	@Override
	public abstract void tick();

	@Override
	public abstract void render(Graphics g);

	public abstract ID getID();

	public abstract void onTouch();
}
