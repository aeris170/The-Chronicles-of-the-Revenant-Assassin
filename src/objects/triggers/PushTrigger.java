package objects.triggers;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Timer;

import objects.characters.player.Player;

public class PushTrigger extends Trigger {

	private int pushForce;
	private int pushDirection; // IF 1 DIRECTION IS RIGHT-WAYS, ELSE
								// DIRECTION IS LEFT

	public PushTrigger(int x, int y, int width, int height, int pushForce, int pushDirection) {
		super(x, y, width, height);
		super.id = ID.PUSH;
		this.pushForce = pushForce;
		this.pushDirection = pushDirection;
	}

	@Override
	public void tick() {}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.GREEN);
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
		Timer timer = new Timer(100, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				Player player = Player.getInstance();
				if (PushTrigger.this.getBounds().intersects(player.getBounds())) {
					if (pushDirection == 1)
						player.setxVel(pushForce);
					else
						player.setxVel(-pushForce);
				} else {
					if (pushDirection == 1)
						player.setX((int) (player.getX() + 2));
					else
						player.setX((int) (player.getX() - 2));
					player.setxVel(0);
					((Timer) e.getSource()).stop();
				}
			}
		});
		timer.start();
	}
}
