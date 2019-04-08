package handler;

import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import objects.GameObject;
import objects.characters.player.Player;

public class Handler {

	// MB TRY TREE SET???

	private volatile static List<GameObject> objects = new ArrayList<>();
	private static boolean hasPlayerAdded = false;
	public static boolean loading = false;

	private static Handler handler = new Handler();

	private Handler() {
		super();
	}

	public void add(GameObject object) {
		if (object instanceof Player && hasPlayerAdded) {
			return;
		} else if (object instanceof Player && !hasPlayerAdded) {
			hasPlayerAdded = true;
			objects.add(object);
		} else {
			objects.add(object);
		}
	}

	public void addAll(Collection<GameObject> object) {
		objects.addAll(object);
	}

	public void remove(GameObject object) {
		if(object instanceof Player) {
			hasPlayerAdded = false;
		}
		objects.remove(object);
	}

	public void removeAll() {
		try {
			/*
			System.out.println("NPC: " + objects.removeAll(((GameState) (Main.getWindow().getGame())).getCurrentLevel().getNPCs()));
			System.out.println("Trigger: " + objects.removeAll(((GameState) (Main.getWindow().getGame())).getCurrentLevel().getTriggers()));
			System.out.println("Block: " + objects.removeAll(((GameState) (Main.getWindow().getGame())).getCurrentLevel().getBlocks()));
			*/
			objects = new ArrayList<>();
			hasPlayerAdded = false;
		} catch (NullPointerException ex) {
			System.out.println("Don't worry, be happy!");
		}
	}

	public int size() {
		return objects.size();
	}

	public GameObject get(int i) {
		return objects.get(i);
	}

	public void tick() {
		if (!loading) {
			for (int i = 0; i < objects.size(); i++) {
				objects.get(i).tick();
			}
		}
		// Player.getInstance().tick();
	}

	public void render(Graphics g) {
		if (!loading) {
			for (GameObject object : objects) {
				object.render(g);
			}
		}
	}

	public static Handler getInstance() {
		return handler;
	}
}
