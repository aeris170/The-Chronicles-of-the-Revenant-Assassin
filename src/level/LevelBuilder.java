package level;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import objects.GameObject;
import objects.blocks.Block;
import objects.blocks.MoveableBlock;
import objects.characters.npcs.NPC;
import objects.characters.npcs.Test;
import objects.characters.npcs.Test2;
import objects.characters.player.Player;
import objects.triggers.DamageTrigger;
import objects.triggers.DisplacementTrigger;
import objects.triggers.PushTrigger;
import objects.triggers.TransitionTrigger;
import objects.triggers.Trigger;

public class LevelBuilder {

	private LevelBuilder() {
		throw new RuntimeException("Cannot instantiate a builder!!");
	}

	public static List<GameObject[]> build(BufferedImage layoutImage, Map<Integer, NPC> NPCList) {
		List<Block> blocks = new ArrayList<>();
		List<Trigger> triggers = new ArrayList<>();
		List<NPC> NPCs = new ArrayList<>();
		int width = layoutImage.getWidth();
		int height = layoutImage.getHeight();

		for (int xx = 0; xx < width; xx++) {
			for (int yy = 0; yy < height; yy++) {
				int pixel = layoutImage.getRGB(xx, yy);
				int alpha = (pixel >> 24) & 0x0000FF;
				int red = (pixel >> 16) & 0x0000FF;
				int green = (pixel >> 8) & 0x0000FF;
				int blue = pixel & 0x0000FF;
				int x = xx * 32 + 57;
				int y = yy * 32 + 215;
				if (red == 255 && green == 255 && blue == 255) {
					// WHITE PIXELS
					if (alpha == 255) {
						blocks.add(new MoveableBlock(x, y, 32, 32));
					} else if (alpha == 128) {
						blocks.add(new MoveableBlock(x, y + 24, 8, 8));
						blocks.add(new MoveableBlock(x + 8, y + 16, 8, 8));
						blocks.add(new MoveableBlock(x + 16, y + 8, 8, 8));
						blocks.add(new MoveableBlock(x + 24, y, 8, 8));
					}
				}
				if (red == 255 && green == 0 && blue == 0) {
					// RED PIXELS
					if (alpha == 200) {
						triggers.add(new DamageTrigger(x, y, 32, 32, 1));
					} else if (alpha == 225) {
						triggers.add(new DamageTrigger(x, y, 32, 32, 2));
					} else if (alpha == 255) {
						triggers.add(new DamageTrigger(x, y, 32, 32, 3));
					}
					System.out.println("RED/DAMAGE");
				}
				if (red == 0 && green == 255 && blue == 0) {
					// GREEN PIXELS
					if (alpha > 127)
						alpha -= 128;
					int direction = alpha > 127 ? 0 : 1; // IF ALPHA BIGGER THAN
															// 128 IT WILL PUSH
															// RIGHT
					triggers.add(new PushTrigger(x, y, 32, 32, alpha, direction));
					System.out.println("GREEN/PUSH");
				}
				if (red == 0 && green == 0 && blue == 255) {
					// BLUE PIXELS
					triggers.add(new DisplacementTrigger(x, y, 32, 32, (alpha + 1) * 64, (alpha + 1) * 64));
					System.out.println("BLUE/DISPLACEMENT");
				}
				if (red == 0 && green == 255 && blue == 255) {
					// CYAN PIXELS
					triggers.add(new TransitionTrigger(x, y, 32, 32));
					System.out.println("CYAN/TRANSITION");
				}
				if (red == 255 && green == 106 && blue == 0) {
					System.out.println("ORANJ/PLAYER");
					// Player.getInstance();
					Player.getInstance().setX(x);
					Player.getInstance().setY(y);
				}

				// NPCS START HERE
				if (red == 255 && green == 255 && blue == 0) {
					System.out.println("TEST NPC");
					NPC tmp = new Test(NPCList.get(1));
					tmp.setX(x);
					tmp.setY(y);
					NPCs.add(tmp);
				}
				if (red == 255 && green == 0 && blue == 255) {
					System.out.println("TEST2 NPC");
					NPC tmp = new Test2(NPCList.get(2));
					tmp.setX(x);
					tmp.setY(y);
					NPCs.add(tmp);
				}
			}
		}

		Block[] blocksArray = blocks.toArray(new Block[blocks.size()]);
		Trigger[] triggersArray = triggers.toArray(new Trigger[triggers.size()]);
		NPC[] NPCsArray = NPCs.toArray(new NPC[NPCs.size()]);
		List<GameObject[]> allEntitiesInLevel = new ArrayList<>();
		allEntitiesInLevel.add(0, blocksArray);
		allEntitiesInLevel.add(1, triggersArray);
		allEntitiesInLevel.add(2, NPCsArray);
		return allEntitiesInLevel;
	}

	public static HashMap<Integer, NPC> buildNPCList(File NPCStorage) {
		try {
			Scanner scan = new Scanner(NPCStorage);
			Map<Integer, NPC> NPCList = new HashMap<>();
			int i = 1;
			while (scan.hasNextLine()) {
				NPCList.put(i, NPC.parseStringToNPC(scan.nextLine()));
				i++;
			}
			scan.close();
			return (HashMap<Integer, NPC>) NPCList;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Fuck up at level NPCStorage");
			return null;
		}
	}
}
