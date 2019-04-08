package objects.characters.npcs;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Scanner;

import objects.characters.ID;
import objects.characters.Character;

public abstract class NPC extends Character {

	public NPC(String name, int id, int hitPoints, int gold, int experiencePoints) {
		super();
		switch (id) {
			case 1:
				super.ID = objects.characters.ID.NPC_VENDOR;
				break;
			case 2:
				super.ID = objects.characters.ID.NPC_FRIENDLY;
				break;
			case 3:
				super.ID = objects.characters.ID.NPC_COMPANION;
				break;
			case 4:
				super.ID = objects.characters.ID.NPC_ENEMY;
				break;
			case 5:
				super.ID = objects.characters.ID.NPC_BOSS;
				break;
		}
		super.name = name;
		super.hitPoints = hitPoints;
		this.gold = gold;
		super.experiencePoints = experiencePoints;
	}
	
	public static NPC parseStringToNPC(String stringRepOfNPC) {
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(stringRepOfNPC);
		scan.useDelimiter(";");
		String type = scan.next();
		if(NPCType.valueOf("TEST").toString().equals(type)) {
			return (NPC) new Test(scan.next(), scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt());
		}
		else if(NPCType.valueOf("TEST2").toString().equals(type)) {
			return (NPC) new Test2(scan.next(), scan.nextInt(), scan.nextInt(), scan.nextInt(), scan.nextInt());
		}
		return null;
	}

	@Override
	public void tick() {}

	@Override
	public void render(Graphics g) {}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, (int) width, (int) height);
	}
	/*
	 * public static NPC[] init(String levelName) { try { File npcFile = new
	 * File("levels/" + levelName + "/npc.txt"); Scanner scanner = new
	 * Scanner(npcFile); ArrayList<NPC> npcs = new ArrayList<NPC>(); while
	 * (scanner.hasNextLine()) { try { npcs.add(new NPC(scanner.nextLine(),
	 * Integer.parseInt(scanner.nextLine()),
	 * Integer.parseInt(scanner.nextLine()),
	 * Integer.parseInt(scanner.nextLine()),
	 * Integer.parseInt(scanner.nextLine()))); } catch (NoSuchElementException
	 * ex) { ex.printStackTrace(); } break; } scanner.close(); return
	 * npcs.toArray(new NPC[npcs.size()]); } catch (FileNotFoundException e) {
	 * e.printStackTrace();
	 * System.out.println("Fucked up during init! Created 0 npcs!"); return
	 * null; } }
	 */

	@Override
	public ID getID() {
		return super.ID;
	}
	
	public String toString() {
		return name + " " + ID + " " + hitPoints + " " + gold + " " + experiencePoints + " " + x + " " + y + " " + width + " " + height;
	}
}