package io.image;

import java.awt.image.BufferedImage;
import objects.characters.Character;

public class BlinkableAnimation extends Animation {

	private Character character;
	private int blinkDecider;

	public BlinkableAnimation(BufferedImage[] frames, int delay, Character character) {
		super(frames, delay);
		this.character = character;
	}

	@Override
	public BufferedImage getCurrent() {
		if (character.isInvincible()) {
			blinkDecider++;
			if (blinkDecider % 2 == 0) {
				return null;
			} else {
				return super.frames[current % frames.length];
			}
		} else {
			return super.frames[current % frames.length];
		}
	}
}
