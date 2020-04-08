//package editor.mcm.fhooe.at;

import java.awt.Graphics;
import java.awt.Panel;
import java.awt.image.BufferedImage;

public class DrawingPanel extends Panel {
	BufferedImage image = null;

	public void addFigure(BufferedImage _data) {
		image = _data;
		repaint();
	}

	public void paint(Graphics g) {
		super.paint(g);
		if (image != null) {
			int x = image.getMinX();
			int y = image.getMinY();
			g.drawImage(image, x, y, image.getWidth(), image.getHeight(), null);
		}
	}
}