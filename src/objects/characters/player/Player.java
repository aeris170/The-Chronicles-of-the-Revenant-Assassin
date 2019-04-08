package objects.characters.player;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.Timer;

import handler.Handler;
import io.image.Animation;
import io.image.Assets;
import io.image.BlinkableAnimation;
import level.Level;
import main.Main;
import objects.characters.ID;
import objects.characters.npcs.NPC;
import objects.triggers.Trigger;
import state.GameState;
import objects.blocks.Block;
import objects.blocks.MoveableBlock;
import objects.characters.Character;

public class Player extends Character {

	private static Player player = new Player();
	private double shield;
	private int special;
	private int level;
	private boolean jumping = false;
	private boolean moving = false;
	private boolean cameraShouldFollow = true;
	private Handler handler;
	private boolean facingRight;
	private BlinkableAnimation animation;

	private Player() {
		this("Player", 100, 0, 100, 0, 1, 0);
	}

	private Player(String name, int hitPoints, int special, double shield, int gold, int level, int experiencePoints) {
		super();
		super.ID = objects.characters.ID.PLAYER;
		super.name = name;
		super.hitPoints = hitPoints;
		super.x = 700;
		super.y = 700;
		super.yVel = 1;
		super.width = 90;
		super.height = 144;
		this.special = special;
		this.shield = shield;
		this.gold = gold;
		this.level = level;
		super.experiencePoints = experiencePoints;
		this.setFacingRight(true);
		this.animation = new BlinkableAnimation(Assets.playerIdleR, 200, this);
		this.animation.start();
		this.hitPoints = 3;
		this.shield = 1;
		this.special = 0;
		this.handler = Handler.getInstance();
	}

	@Override
	public void tick() {
		double gravityConstant = ((GameState) (Main.getWindow().getGame())).getCurrentLevel().getGravityConstant();
		double terminalVelocity = ((GameState) (Main.getWindow().getGame())).getCurrentLevel().getTerminalVelocity();
		x += xVel;
		y += yVel;

		if ((falling || jumping)) {
			yVel += gravityConstant;
			if (yVel > terminalVelocity) {
				yVel = terminalVelocity;
			}
		}

		collisionCheck();
	}

	public void collisionCheck() {
		for (int i = 0; i < handler.size(); i++) {
			if (handler.get(i) instanceof Block) {
				Block block = (Block) handler.get(i);
				if (block instanceof MoveableBlock) {
					Rectangle blockBounds = block.getBounds();
					if (player.getDownCollisionBox().intersects(blockBounds)) {
						player.y = block.getY() - player.height;
						player.yVel = 0;
						player.falling = false;
						player.jumping = false;
					} else if (player.getRightCollisionBox().intersects(blockBounds)) {
						player.x = block.getX() - 30; // random values
					} else if (player.getLeftCollisionBox().intersects(blockBounds)) {
						player.x = block.getX() + 30; // random values
					} else if (player.getUpCollisionBox().intersects(blockBounds)) {
						player.y = block.getY() + 32;
						player.yVel = 0;
					}
				} else {}
			} else if (handler.get(i) instanceof NPC) {
				// player.yVel = 0;
				/*
				 * switch (((NPC) (handler.get(i))).getID()) { case NPC_BOSS:
				 * ((NPC) (handler.get(i))).onHitEffect(); break; case
				 * NPC_COMPANION: break; case NPC_ENEMY: ((NPC)
				 * (handler.get(i))).onHitEffect(); break; case NPC_FRIENDLY:
				 * ((NPC) (handler.get(i))).openDialog(); break; case
				 * NPC_VENDOR: ((NPC) (handler.get(i))).openShop(); break; }
				 */
			} else if (handler.get(i) instanceof Trigger) {
				Trigger trigger = (Trigger) handler.get(i);
				if (player.getBounds().intersects(trigger.getBounds()))
					trigger.onTouch();
			} else {
				player.falling = true;
			}
		}
	}

	@Override
	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		g.setColor(Color.BLUE);
		g2d.draw(getBounds());

		g.setColor(Color.GREEN);
		g2d.draw(getRightCollisionBox());
		g2d.draw(getLeftCollisionBox());
		g2d.draw(getUpCollisionBox());
		g2d.draw(getDownCollisionBox());
		g.setColor(Color.ORANGE);
		g2d.draw(castRayParallelToYAxis());
		if (facingRight) {
			g.drawImage(animation.getCurrent(), (int) x, (int) y, (int) width, (int) height, null);
		} else {
			g.drawImage(animation.getCurrent(), (int) x, (int) y, (int) width, (int) height, null);
		}
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle((int) x, (int) y, (int) width, (int) height);
	}

	public Rectangle getRightCollisionBox() {
		return new Rectangle((int) (x + width - 5), (int) y + 40, (int) 5, (int) height - 80);
	}

	public Rectangle getLeftCollisionBox() {
		return new Rectangle((int) x, (int) y + 40, (int) 5, (int) height - 80);
	}

	public Rectangle getUpCollisionBox() {
		return new Rectangle((int) (x + (width / 2) - ((width / 2) / 2)) + 5, (int) y, (int) width / 2 - 10, (int) height / 2);
	}

	public Rectangle getDownCollisionBox() {
		return new Rectangle((int) (x + (width / 2) - ((width / 2) / 2)) + 5, (int) (y + (height / 2)), (int) width / 2 - 10, (int) height / 2);
	}

	public Rectangle castRayParallelToYAxis() {
		return new Rectangle((int) (x), (int) (y + height / 2), width, 725);
	}

	public void jump() {
		if (!jumping) {
			if(!moving) {
				animation.overrideAndEnd(Assets.playerJumpR, Assets.playerIdleR, 95);
			} else if(moving) {
				animation.overrideAndEndMidway(Assets.playerJumpMoveR, Assets.playerMoveR, 1500, 60, 4);
			}
			setyVel(-10);
			// jumping = true;
		}
	}

	public boolean isJumping() {
		return jumping;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public boolean isFacingRight() {
		return facingRight;
	}

	public void setFacingRight(boolean facingRight) {
		this.facingRight = facingRight;
	}

	public void slamAttack() {
		if (facingRight) {
			animation.overrideAndEnd(Assets.playerAttackR1, Assets.playerIdleR, 100);
		} else {
			animation.overrideAndEnd(Assets.playerAttackL1, Assets.playerIdleL, 100);
		}
		setyVel(20);
		jumping = false;
		falling = false;
	}

	public void dashAttack() {
		if (facingRight) {
			setxVel(20);
			animation.overrideAndEnd(Assets.playerAttackR2, Assets.playerIdleR, 100);
		} else {
			setxVel(-20);
			animation.overrideAndEnd(Assets.playerAttackL2, Assets.playerIdleL, 100);
		}
		Timer timer = new Timer(500, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				setxVel(0);
				((Timer) e.getSource()).stop();
			}
		});
		timer.start();
	}

	public static Player getInstance() {
		if (player == null) {
			try {
				Scanner scanner = new Scanner(new File("levels/save.txt"));
				player = new Player(scanner.nextLine(), Integer.parseInt(scanner.nextLine()), Integer.parseInt(scanner.nextLine()), Integer.parseInt(scanner.nextLine()), Integer.parseInt(scanner.nextLine()), Integer.parseInt(scanner.nextLine()), Integer.parseInt(scanner.nextLine()));
				scanner.close();
				return player;
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				System.out.println("Fucked up during load!");
				return player = new Player();
			}
		} else {
			return player;
		}
	}

	public void savePlayer() {
		try {
			PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter("levels/save.txt", false)));
			writer.write(name + "\r\n");
			writer.write(hitPoints + "\r\n");
			writer.write(special + "\r\n");
			writer.write(shield + "\r\n");
			writer.write(gold + "\r\n");
			writer.write(level + "\r\n");
			writer.write(experiencePoints + "");
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Fucked up during save!");
		}
	}

	@Override
	public ID getID() {
		return super.ID;
	}

	public double getShield() {
		return shield;
	}

	public void setShield(int shield) {
		this.shield = shield;
	}

	public int getSpecial() {
		return special;
	}

	public void setSpecial(int special) {
		this.special = special;
	}

	public void setSpecial() {
		this.special++;
	}

	public Animation getAnimation() {
		return this.animation;
	}

	public boolean getCameraBehaviour() {
		cameraShouldFollow = true;
		for (int i = 0; i < handler.size(); i++) {
			if (handler.get(i) instanceof Block) {
				if (handler.get(i) instanceof MoveableBlock) {
					if (handler.get(i).getY() == 1015.0) {
						if (player.castRayParallelToYAxis().intersects(handler.get(i).getBounds())) {
							cameraShouldFollow = false;
						}
					}
				}
			}
		}
		return cameraShouldFollow;
	}

	@Override
	public void damage(int damage) {
		Thread blinker = new Thread(new Runnable() {

			@Override
			public void run() {
				isInvincible = true;
				try {
					Thread.sleep(2000);
				} catch (InterruptedException ex) {
					ex.printStackTrace();
				}
				isInvincible = false;
			}
		});
		blinker.start();
		if (shield >= 1) {
			shield = 0;
			damage--;
			Thread shieldRecharger = new Thread(new Runnable() {

				@Override
				public void run() {
					while (shield < 1) {
						shield += 0.1;
						try {
							Thread.sleep(1000);
						} catch (InterruptedException ex) {
							ex.printStackTrace();
						}
					}
				}
			});
			shieldRecharger.start();
		}
		hitPoints -= damage;
		if (hitPoints <= 0) {
			hitPoints = 3;
			shield = 1;
			isInvincible = false;
			GameState game = (GameState) (Main.getWindow().getGame());
			Level currentLevel = game.getCurrentLevel();
			String currentLevelName = currentLevel.getLevelName();
			try {
				game.loadLevel(currentLevelName);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}
}