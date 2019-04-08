package components;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class SpeechBubble extends JPanel {

	private static final long serialVersionUID = 5909942236457587130L;
	
	@SuppressWarnings("unused")
	private int x;
	@SuppressWarnings("unused")
	private int y;
	@SuppressWarnings("unused")
	private int height;
	@SuppressWarnings("unused")
	private int width;
	private JLabel textContainer;

	// ADD DYNAMIC SIZE CALCULATION!!!
	public SpeechBubble(int x, int y, int width, int height, String text) {
		super();
		super.setLocation(x, y);
		super.setSize(width, height);
		this.textContainer = new JLabel(text);
		super.add(textContainer);
	}

}
