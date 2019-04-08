package objects.characters.npcs;

import java.awt.Color;
import java.awt.Graphics;

public class Test extends NPC{

	public Test(String name, int id, int hitPoints, int gold, int experiencePoints) {
		super(name, id, hitPoints, gold, experiencePoints);		
		super.width = 30;
		super.height = 90;
	}
	
	public Test(NPC npc) {
		super(npc.getName(), 1, npc.getHitPoints(), (int) npc.getGold(), (int) npc.getExperiencePoints());		
		width = npc.getWidth();
		height = npc.getHeight();
	}

	@Override
	public void tick() {
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.YELLOW);
		g.fillRect((int) x, (int) y, (int) width, (int) height);
	}

	@Override
	public void damage(int damage) {
		
	}

}
