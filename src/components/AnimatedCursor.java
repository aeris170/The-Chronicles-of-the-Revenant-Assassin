package components;

import java.awt.Cursor;
import java.awt.HeadlessException;
import java.awt.Point;
import java.awt.Toolkit;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AnimatedCursor implements Runnable {

	private Cursor[] cursors;
	private Cursor currentCursor;

	public AnimatedCursor() {
		super();
		cursors = new Cursor[6];
		Toolkit toolkit = Toolkit.getDefaultToolkit();
		try {
			cursors[0] = toolkit.createCustomCursor(ImageIO.read(getClass().getResource("/mcursor/def_m_cursor_1.png")), new Point(0, 0), "1");
			cursors[1] = toolkit.createCustomCursor(ImageIO.read(getClass().getResource("/mcursor/def_m_cursor_2.png")), new Point(0, 0), "2");
			cursors[2] = toolkit.createCustomCursor(ImageIO.read(getClass().getResource("/mcursor/def_m_cursor_3.png")), new Point(0, 0), "3");
			cursors[3] = toolkit.createCustomCursor(ImageIO.read(getClass().getResource("/mcursor/def_m_cursor_4.png")), new Point(0, 0), "4");
			cursors[4] = toolkit.createCustomCursor(ImageIO.read(getClass().getResource("/mcursor/def_m_cursor_5.png")), new Point(0, 0), "5");
			cursors[5] = toolkit.createCustomCursor(ImageIO.read(getClass().getResource("/mcursor/def_m_cursor_6.png")), new Point(0, 0), "6");
		} catch (HeadlessException | IndexOutOfBoundsException | IOException ex) {
			ex.printStackTrace();
		}
	}

	public Cursor getCursor() {
		return currentCursor;
	}

	public void run() {
		int count = 0;
		while (true) {
			try {
				Thread.sleep(200);// MB TRY currentThread().sleep
			} catch (InterruptedException ex) {
				ex.printStackTrace();
			}
			currentCursor = cursors[count % cursors.length];
			count++;
		}
	}
}

/*
 * Swing Hacks Tips and Tools for Killer GUIs By Joshua Marinacci, Chris Adamson
 * First Edition June 2005 Series: Hacks ISBN: 0-596-00907-0
 * http://www.oreilly.com/catalog/swinghks/
 */