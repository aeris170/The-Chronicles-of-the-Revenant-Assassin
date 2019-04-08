package state;

import javax.swing.JPanel;

public abstract class State extends JPanel {

	private static final long serialVersionUID = -6036849467313467968L;

	/*
	 * @Override public boolean equals(Object state) { if(!(state instanceof
	 * State)){ return false; } if(this.identifier == ((State)state).identifier)
	 * { return true; } return false; }
	 */
}