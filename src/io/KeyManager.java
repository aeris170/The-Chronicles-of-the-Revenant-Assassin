package io;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import io.image.Assets;
import main.Main;
import objects.characters.player.Player;
import state.State;

public class KeyManager extends KeyAdapter {

	private static KeyManager keyManager;
	/*
	 * private static InputMap actions; private static ActionMap reactions;
	 * private static boolean valve;
	 */

	private KeyManager(State state) {
		super();
		/*
		 * actions = state.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		 * reactions = state.getActionMap();
		 */
		defineKeys(state);
		state.addKeyListener(this);
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_A) {
			Player.getInstance().setxVel(-10);
			Player.getInstance().setFacingRight(false);
			if(!Player.getInstance().isMoving()){
				Player.getInstance().getAnimation().override(Assets.playerMoveL);
				Player.getInstance().setMoving(true);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			Player.getInstance().setxVel(10);
			Player.getInstance().setFacingRight(true);
			if(!Player.getInstance().isMoving()){
				Player.getInstance().getAnimation().overrideMidway(Assets.playerMoveR, 60, 4);
				Player.getInstance().setMoving(true);
			}
		}
		if (e.getKeyCode() == KeyEvent.VK_SPACE) {
			Player.getInstance().setyVel(5);
			Player.getInstance().jump();
		}
		if (e.getKeyCode() == KeyEvent.VK_SHIFT) {
			Player.getInstance().setSpecial();
		}
		if (e.getKeyCode() == KeyEvent.VK_CONTROL) {
			Player.getInstance().setSpecial(0);
		}
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_A) {
			Player.getInstance().setxVel(0);
			Player.getInstance().getAnimation().override(Assets.playerIdleL);
			Player.getInstance().setMoving(false);
		}
		if (e.getKeyCode() == KeyEvent.VK_D) {
			Player.getInstance().setxVel(0);
			Player.getInstance().getAnimation().overrideAndEnd(Assets.playerSpringR, Assets.playerIdleR, 80);
			Player.getInstance().setMoving(false);
		}
	}

	private void defineKeys(State state) {

		state.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getButton() == MouseEvent.BUTTON1) {
					Player.getInstance().dashAttack();
				} else if (e.getButton() == MouseEvent.BUTTON3 && Player.getInstance().isFalling()) {
					Player.getInstance().slamAttack();
				}
			}
		});
		/*
		 * actions.put(KeyStroke.getKeyStroke("SPACE"), "Jump");
		 * actions.put(KeyStroke.getKeyStroke("A"), "Move/Look Left");
		 * actions.put(KeyStroke.getKeyStroke("released A"),
		 * "released Move/Look Left"); actions.put(KeyStroke.getKeyStroke("S"),
		 * "Crouch"); actions.put(KeyStroke.getKeyStroke("released S"),
		 * "released Crouch"); actions.put(KeyStroke.getKeyStroke("D"),
		 * "Move/Look Right"); actions.put(KeyStroke.getKeyStroke("released D"),
		 * "released Move/Look Right"); reactions.put("Move/Look Left", new
		 * AbstractAction() {
		 * @Override public void actionPerformed(ActionEvent e) {
		 * Player.getInstance().setxVel(0); Player.getInstance().setxVel(-10);
		 * Player.getInstance().setFacingRight(false);
		 * Player.getInstance().getAnimation().override(Assets.playerMoveL); }
		 * }); reactions.put("released Move/Look Left", new AbstractAction() {
		 * @Override public void actionPerformed(ActionEvent e) {
		 * Player.getInstance().setxVel(0);
		 * Player.getInstance().getAnimation().override(Assets.playerIdleL); }
		 * }); reactions.put("Crouch", new AbstractAction() {
		 * @Override public void actionPerformed(ActionEvent e) { // CROUCH IF
		 * FEET ON GROUND // LOOK DOWN IF JUMPING/FALLING } });
		 * reactions.put("released Crouch", new AbstractAction() {
		 * @Override public void actionPerformed(ActionEvent e) { // REMOVE
		 * CROUCH IF CROUCHED // LOOK AT CURRENT DIRECTION } });
		 * reactions.put("Move/Look Right", new AbstractAction() {
		 * @Override public void actionPerformed( ActionEvent e) {/* if (!valve)
		 * { Thread t = new Thread(new Runnable() {
		 * @Override public void run() { for (int i = 0; i < 10; i++) {
		 * Player.getInstance().setxVel(Player.getInstance().getxVel() + 1); try
		 * { Thread.sleep(60); } catch (InterruptedException e) {
		 * e.printStackTrace(); } } try { Thread.currentThread().join(); } catch
		 * (InterruptedException e) { e.printStackTrace(); } } }); t.start();
		 * valve = true; } Player.getInstance().setxVel(0);
		 * Player.getInstance().setxVel(10);
		 * Player.getInstance().setFacingRight(true);
		 * Player.getInstance().getAnimation().override(Assets.playerMoveR);
		 * System.out.println("P " + Player.getInstance().getX());
		 * System.out.println("CAM " + (Player.getInstance().getX() -
		 * (Camera.playSpaceWidth / 2) + Player.getInstance().getxVel())); } });
		 * reactions.put("released Move/Look Right", new AbstractAction() {
		 * @Override public void actionPerformed(ActionEvent e) {
		 * Player.getInstance().setxVel(0);
		 * Player.getInstance().getAnimation().override(Assets.playerIdleR);
		 * valve = false; } }); reactions.put("Jump", new AbstractAction() {
		 * @Override public void actionPerformed(ActionEvent e) {
		 * Player.getInstance().jump(); } });
		 */
	}

	public static KeyManager getInstance() {
		if (keyManager == null)
			keyManager = new KeyManager(Main.getWindow().getGame());
		return keyManager;
	}
}
