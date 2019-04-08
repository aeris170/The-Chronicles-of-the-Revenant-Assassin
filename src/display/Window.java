package display;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.io.IOException;

import javax.swing.JFrame;

import components.AnimatedCursor;
import state.CreditsState;
import state.ExitMenuState;
import state.GameState;
import state.MainMenuState;
import state.State;

public class Window extends JFrame {

	private static final long serialVersionUID = 4549866233088016478L;

	private static final Dimension SCREEN_SIZE = Toolkit.getDefaultToolkit().getScreenSize();
	public static final int HEIGHT = SCREEN_SIZE.height;
	public static final int WIDTH = SCREEN_SIZE.width;

	private State mainMenu;
	private State exitMenu;
	private State credits;
	private State game;
	private State current;
	private AnimatedCursor cursor;

	public Window() throws IOException {
		super();
		super.setLayout(new BorderLayout());

		this.mainMenu = new MainMenuState();
		this.exitMenu = new ExitMenuState();
		this.credits = new CreditsState();
		this.game = new GameState();
		this.cursor = new AnimatedCursor();

		super.add(mainMenu);
		this.current = mainMenu;
		super.setLocation(0, 0);
		super.setIconImage(null); // SET ICON IMAGE SOON
		super.setUndecorated(true);
		super.setTitle("To Live Is To Die");
		// super.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(ImageIO.read(getClass().getResource("/mcursor/defmcursor.png")),
		// new Point(0, 0), "defCursor"));
		Thread cursorChanger = new Thread(new Runnable() {

			@Override
			public void run() {
				while (true) {
					try {
						Thread.sleep(200);
					} catch (InterruptedException ex) {
						ex.printStackTrace();
					}
					Window.this.setCursor(cursor.getCursor());
				}
			}
		});
		cursorChanger.start();
		new Thread(cursor).start();
		super.setMinimumSize(Window.SCREEN_SIZE);
		super.setPreferredSize(Window.SCREEN_SIZE);
		super.setMaximumSize(Window.SCREEN_SIZE);
		super.setResizable(false);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		super.setVisible(true);
	}

	@Override
	public Component add(Component newState) {
		if (current instanceof GameState) {
			((GameState) current).pause();
		}
		super.remove(current);
		super.getContentPane().add(newState, BorderLayout.CENTER);
		super.validate();
		super.repaint();
		super.revalidate();
		this.current = (State) newState;
		if (current instanceof GameState) {
			Thread game = new Thread((Runnable) current);
			game.start();
		}
		return newState;
	}

	public State getMainMenu() {
		return mainMenu;
	}

	public State getExitMenu() {
		return exitMenu;
	}

	public State getCredits() {
		return credits;
	}

	public State getGame() {
		return game;
	}

	public State getCurrent() {
		return current;
	}

	public void printCurrent() {
		System.out.println(current.toString());
	}
}