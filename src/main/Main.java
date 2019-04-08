package main;

import java.io.IOException;

import display.Window;
import io.sound.AudioPlayer;

public class Main {

	private static Window game;

	public static void main(String[] args) throws IOException {
		game = new Window();
		AudioPlayer.setGlobalVolume();
		AudioPlayer.mainMenuMusicStream.loop(0);
	}

	public static Window getWindow() {
		return game;
	}
}
