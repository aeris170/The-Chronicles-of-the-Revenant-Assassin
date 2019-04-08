package components;

import javax.swing.JButton;

public class TransparentButton extends JButton {

	private static final long serialVersionUID = -7783750970566290651L;

	public TransparentButton() {
		super();
		super.setOpaque(false);
		super.setContentAreaFilled(false);
		super.setBorderPainted(false);
	}

	public TransparentButton(String title) {
		super(title);
		super.setOpaque(false);
		super.setContentAreaFilled(false);
		super.setBorderPainted(false);
	}
}
