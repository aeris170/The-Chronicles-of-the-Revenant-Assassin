package objects.characters;

import java.awt.Graphics;

import objects.GameObject;

public abstract class Character extends GameObject {

	protected ID ID;
	protected String name;
	protected double xVel;
	protected double yVel;
	protected int hitPoints;
	protected double experiencePoints;
	protected double gold;
	protected boolean falling = true;
	protected boolean isInvincible = false;

	public boolean isInvincible() {
		return isInvincible;
	}

	public float getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public double getxVel() {
		return xVel;
	}

	public void setxVel(double xVel) {
		this.xVel = xVel;
	}

	public double getyVel() {
		return yVel;
	}

	public void setyVel(double yVel) {
		this.yVel = yVel;
	}

	public int getHitPoints() {
		return hitPoints;
	}

	public void setHitPoints(int hitPoints) {
		this.hitPoints = hitPoints;
	}

	public double getExperiencePoints() {
		return experiencePoints;
	}

	public void setExperiencePoints(double experiencePoints) {
		this.experiencePoints = experiencePoints;
	}

	public double getGold() {
		return gold;
	}

	public void setGold(double gold) {
		this.gold = gold;
	}

	public boolean isFalling() {
		return falling;
	}

	public void setFalling(boolean falling) {
		this.falling = falling;
	}

	public String getName() {
		return name;
	}

	public abstract void damage(int damage);

	@Override
	public abstract void tick();

	@Override
	public abstract void render(Graphics g);

	public abstract ID getID();
}