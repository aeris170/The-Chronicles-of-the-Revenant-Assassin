package io.sound;

import java.util.ArrayList;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;

import main.Main;
import state.MainMenuState;

public class AudioPlayer {

	private static ArrayList<AudioPlayer> allClips = new ArrayList<AudioPlayer>();

	public static AudioPlayer menuButtonHoverStream = load("/sounds/menuButtonHover.wav");
	public static AudioPlayer mainMenuMusicStream = load("/sounds/op-1.wav");
	public static AudioPlayer tutorialMusicStream = load("/sounds/op0.wav");
	public static AudioPlayer act1MusicStream = load("/sounds/op1.wav");
	public static AudioPlayer act2MusicStream = load("/sounds/op2.wav");
	public static AudioPlayer act3MusicStream = load("/sounds/op3.wav");
	public static AudioPlayer act4MusicStream = load("/sounds/op4.wav");
	public static AudioPlayer act5MusicStream = load("/sounds/op5.wav");

	private Clip clip;
	private long microsecondPosition;

	private static AudioPlayer load(String filePath) {
		AudioPlayer sound = new AudioPlayer();
		try {
			AudioInputStream stream = AudioSystem.getAudioInputStream(AudioPlayer.class.getResource(filePath));
			Clip clip = AudioSystem.getClip();
			clip.open(stream);
			sound.clip = clip;
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		allClips.add(sound);
		return sound;
	}

	public static void setGlobalVolume() {
		float volume = (float) ((MainMenuState) (Main.getWindow().getMainMenu())).getVolumeSlider().getVolume();
		System.out.println(volume);
		float dB = (float) (Math.log(volume) / Math.log(10) * 20) + 6;
		for (AudioPlayer sound : allClips) {
			FloatControl gainControl = (FloatControl) sound.clip.getControl(FloatControl.Type.MASTER_GAIN);
			gainControl.setValue(dB);
			System.out.println("Vol : " + volume + " gayin: " + dB);
		}
	}

	public void loop(final int count) {
		if (clip != null)
			new Thread() {

				@Override
				public void run() {
					synchronized (clip) {
						clip.stop();
						clip.setFramePosition(0);
						clip.loop(count);
					}
				}
			}.start();
	}

	public void pause() {
		if (clip != null)
			new Thread() {

				@Override
				public void run() {
					synchronized (clip) {
						microsecondPosition = clip.getMicrosecondPosition();
						clip.stop();
					}
				}
			}.start();
	}

	public void resume() {
		if (clip != null)
			new Thread() {

				@Override
				public void run() {
					synchronized (clip) {
						clip.setMicrosecondPosition(microsecondPosition);
						clip.start();
					}
				}
			}.start();
	}
}