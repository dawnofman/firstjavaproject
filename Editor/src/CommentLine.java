//package editor.mcm.fhooe.at;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;

public class CommentLine extends Line {
	private static final long serialVersionUID = 1L;

	public CommentLine(Point point, Point point2) {
		super(point, point2);
	}

	@Override
	public void draw(Graphics2D g) {
		Stroke temp = g.getStroke();
		g.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT,
				BasicStroke.JOIN_BEVEL, 0, new float[] {9}, 0));
		g.draw(this);
		g.setStroke(temp);
		if(mText != null)
			g.drawString(mText, (x1 + x2) / 2, (y1 + y2) / 2);
	}

}
