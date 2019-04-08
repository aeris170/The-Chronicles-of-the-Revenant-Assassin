package display;

import objects.characters.player.Player;

public class Camera {

	public static int playSpaceWidth = 1804;
	public static int playSpaceHeight = 826;
	public static float offsetMaxX = 16384 - playSpaceWidth;
	public static float offsetMaxY = 16384 - playSpaceHeight;
	public static float offsetMinX = 0;
	public static float offsetMinY = 0;

	private float x;
	private float y;

	private static Camera camera = new Camera(0, 300);

	private Camera(float x, float y) {
		super();
		this.x = x;
		this.y = y;
	}

	public void tick() {
		this.x = Player.getInstance().getX() - (playSpaceWidth / 2);
		this.y = +Player.getInstance().getY() - (playSpaceHeight / 2) - 100;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getX() {
		return x;
	}

	public void setY(float y) {
		this.y = y;
	}

	public float getY() {
		return y;
	}

	public static Camera getInstance() {
		return camera;
	}
}
