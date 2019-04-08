package objects.blocks;

import java.awt.Graphics;
import java.awt.Rectangle;

import objects.GameObject;

public abstract class Block extends GameObject {

	public Block(int x, int y, int width, int height) {
		super();
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public abstract void tick();

	public abstract void render(Graphics g);

	public abstract Rectangle getBounds();
}