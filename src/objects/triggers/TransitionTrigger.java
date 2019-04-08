package objects.triggers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.io.IOException;
import java.util.ConcurrentModificationException;

import main.Main;
import state.GameState;

public class TransitionTrigger extends Trigger {

	public TransitionTrigger(int x, int y, int width, int height) {
		super(x, y, width, height);
		super.id = ID.TRANSITION;
	}

	@Override
	public void tick() {}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.CYAN);
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
		try {
			String oldLevel = ((GameState) (Main.getWindow().getGame())).getCurrentLevel().getLevelName();
			String newLevel = oldLevel.substring(0, 3) + (Integer.parseInt("" + oldLevel.charAt(3)) + 1);
			((GameState) (Main.getWindow().getGame())).loadLevel(newLevel);
		} catch (IOException | ConcurrentModificationException ex) {
			ex.printStackTrace();
			System.out.println("Someone fucked up during transition");
		}
	}
}
