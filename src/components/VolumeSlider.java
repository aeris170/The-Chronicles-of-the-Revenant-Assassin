package components;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicSliderUI;
import javax.swing.plaf.metal.MetalSliderUI;

import io.sound.AudioPlayer;

public class VolumeSlider extends JSlider {

	private static final long serialVersionUID = -3652721082651923527L;

	private Hashtable<Integer, JLabel> labels;

	public VolumeSlider(int min, int max, int start) {
		super(min, max, 0);
		super.setMajorTickSpacing(25);
		super.setMinorTickSpacing(5);
		super.setPaintTicks(true);
		super.setPaintLabels(true);
		super.setUI(new coloredThumbSliderUI(Color.RED));
		this.labels = new Hashtable<Integer, JLabel>();
		this.labels.put(0, new JLabel("SOUND MUTE"));
		this.labels.put(100, new JLabel("SOUND FULL"));
		super.setLabelTable(labels);
		super.setOpaque(false);
		super.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				VolumeSlider.this.setValue(((BasicSliderUI) VolumeSlider.this.getUI()).valueForXPosition(e.getX()));
				AudioPlayer.setGlobalVolume();
			}
		});
		super.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider source = (JSlider) e.getSource();
				if (!source.getValueIsAdjusting()) {
					AudioPlayer.setGlobalVolume();
				}
			}
		});
	}

	public float getVolume() {
		return (float) super.getValue() / 100f;
	}

	private class coloredThumbSliderUI extends MetalSliderUI {

		Icon icon = new ImageIcon(getClass().getResource("/components/volumeSliderThumb.png"));

		coloredThumbSliderUI(Color tColor) {
			thumbColor = tColor;
		}

		@Override
		public void paintThumb(Graphics g) {
			Rectangle knobBounds = thumbRect;

			g.translate(knobBounds.x, knobBounds.y);
			icon.paintIcon(slider, g, 0, 5);
			g.translate(-knobBounds.x, -knobBounds.y);
		}

		@Override
		protected Dimension getThumbSize() {
			Dimension size = new Dimension();
			size.width = icon.getIconWidth();
			size.height = icon.getIconHeight();
			return size;
		}

		@Override
		public void paint(Graphics g, JComponent c) {
			recalculateIfInsetsChanged();
			recalculateIfOrientationChanged();
			Rectangle clip = g.getClipBounds();

			if (slider.getPaintTrack() && clip.intersects(trackRect)) {
				int arc = 10;
				int trackHeight = 8;
				int trackWidth = 230;
				int fillTop = 33;
				int fillLeft = 35;

				((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
				((Graphics2D) g).setStroke(new BasicStroke(1.5f));
				g.setColor(Color.GRAY);
				g.fillRoundRect(fillLeft, fillTop, trackWidth, trackHeight, arc, arc);

				int fillBottom = fillTop + trackHeight;
				int fillRight = auxilarry(((JSlider) c).getValue(), c, new Rectangle(fillLeft, fillTop, trackWidth, fillBottom - fillTop));

				g.setColor(Color.MAGENTA);
				g.fillRect(fillLeft + 1, fillTop + 1, fillRight - fillLeft, fillBottom - fillTop);

				g.setColor(Color.WHITE);
				g.drawRoundRect(fillLeft, fillTop, trackWidth, trackHeight, arc, arc);
			}
			if (slider.getPaintTicks() && clip.intersects(tickRect)) {
				g.setColor(Color.WHITE);
				paintTicks(g);
			}
			if (slider.getPaintLabels() && clip.intersects(labelRect)) {
				@SuppressWarnings("unchecked")
				Dictionary<Integer, JLabel> dictionary = slider.getLabelTable();
				if (dictionary != null) {
					Enumeration<Integer> keys = dictionary.keys();
					while (keys.hasMoreElements()) {
						JLabel label = dictionary.get(keys.nextElement());
						label.setForeground(Color.WHITE);
					}
				}
				paintLabels(g);
			}
			if (slider.hasFocus() && clip.intersects(focusRect)) {
				paintFocus(g);
			}
			if (clip.intersects(thumbRect)) {

				paintThumb(g);

			}
		}

		private int auxilarry(int value, JComponent c, Rectangle trackRect) {
			int min = ((JSlider) c).getMinimum();
			int max = ((JSlider) c).getMaximum();
			int trackLength = trackRect.width;
			double valueRange = (double) max - (double) min;
			double pixelsPerValue = (double) trackLength / valueRange;
			int trackLeft = trackRect.x;
			int trackRight = trackRect.x + (trackRect.width - 1);
			int xPosition;

			xPosition = trackLeft;
			xPosition += Math.round(pixelsPerValue * ((double) value - min));

			xPosition = Math.max(trackLeft, xPosition);
			xPosition = Math.min(trackRight, xPosition);

			return xPosition;
		}

		@Override
		public void paintMajorTickForHorizSlider(Graphics g, Rectangle tickBounds, int x) {
			int coordinateX = x;
			if (slider.getOrientation() == JSlider.HORIZONTAL) {
				g.setColor(new Color(255, 255, 255));
				g.drawLine(coordinateX - 1, 0, coordinateX - 1, tickBounds.height);
				g.drawLine(coordinateX, 0, coordinateX, tickBounds.height);
				g.drawLine(coordinateX + 1, 0, coordinateX + 1, tickBounds.height);
			}
		}

		@Override
		public void paintMinorTickForHorizSlider(Graphics g, Rectangle tickBounds, int x) {
			int coordinateX = x;
			if (slider.getOrientation() == JSlider.HORIZONTAL) {
				g.setColor(new Color(255, 255, 255));
				g.drawLine(coordinateX - 1, 0, coordinateX - 1, tickBounds.height / 2);
				g.drawLine(coordinateX, 0, coordinateX, tickBounds.height / 2);
				g.drawLine(coordinateX + 1, 0, coordinateX + 1, tickBounds.height / 2);
			}
		}
	}
}