package io.image;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import javax.swing.Timer;

public class Animation {

	protected int current;
	private int currentDelay;
	private Timer timer;
	protected BufferedImage[] frames;

	public Animation(BufferedImage[] frames, int delay) {
		super();
		this.current = 0;
		this.currentDelay = delay;
		this.frames = frames;
		this.timer = new Timer(delay, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				current++;
			}
		});
	}

	public void start() {
		timer.start();
	}

	public void stop() {
		timer.stop();
	}

	public void override(BufferedImage[] frames, int delay) {
		if (this.frames == frames)
			return;
		this.frames = frames;
		this.current = 0;
		this.currentDelay = delay;
		this.stop();
		this.timer = new Timer(delay, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				current++;
			}
		});
		this.start();
	}

	public void override(BufferedImage[] frames) {
		if (this.frames == frames)
			return;
		this.stop();
		this.frames = frames;
		this.current = 0;
		this.timer = new Timer(currentDelay, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				current++;
			}
		});
		this.start();
	}

	public void overrideAndEnd(BufferedImage[] newFrames, BufferedImage[] oldFrames, int delay) {
		this.frames = newFrames;
		this.current = 0;
		this.stop();
		this.timer = new Timer(delay, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (current < newFrames.length - 1) {
					current++;
				} else {
					Animation.this.override(oldFrames);
				}
			}
		});
		this.start();
	}
	
	public void overrideAndEnd(BufferedImage[] newFrames, BufferedImage[] oldFrames, int delay, int newDelay) {
		this.frames = newFrames;
		this.current = 0;
		this.stop();
		this.timer = new Timer(delay, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (current < newFrames.length - 1) {
					current++;
				} else {
					Animation.this.override(oldFrames, newDelay);
				}
			}
		});
		this.start();
	}
	
	public void overrideAndEndMidway(BufferedImage[] newFrames, BufferedImage[] oldFrames, int delay, int newDelay, int midway) {
		this.frames = newFrames;
		this.current = 0;
		this.stop();
		this.timer = new Timer(delay, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (current < newFrames.length - 1) {
					current++;
					System.out.println(newFrames.toString());
				} else {
					Animation.this.overrideMidway(oldFrames, newDelay, midway);
				}
			}
		});
		this.start();
	}
	
	public void overrideMidway(BufferedImage[] newFrames, int delay, int midway) {
		this.frames = newFrames;
		this.current = 0;
		this.stop();
		this.timer = new Timer(delay, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (current < newFrames.length - 1) {
					current++;
				} else {
					current = midway;
				}
			}
		});
		this.start();
	}

	public BufferedImage getCurrent() {
		return frames[current % frames.length];
	}
	
	public void wait(int waitDelay) {
		this.stop();
		Timer waitTimer = new Timer(waitDelay, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				Animation.this.start();
			}
		});
		waitTimer.start();
	}

}