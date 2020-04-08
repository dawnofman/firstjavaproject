//package editor.mcm.fhooe.at;

import java.awt.Graphics2D;
import java.awt.Point;

public class Line extends java.awt.geom.Line2D.Float implements Shape {
	private static final long serialVersionUID = 1L;
	String mText = null;
	
	public Line(Point point, Point point2) {
		this.setLine(point, point2);
	}
	public Line(Point point, Point point2, String text) {
		this.setLine(point, point2);
		mText = text;
	}
	
	public String getText() {
		return mText;
	}
	@Override
	public void draw(Graphics2D g) {
		g.draw(this);
		if(mText != null)
			g.drawString(mText, (x1 + x2) / 2, (y1 + y2) / 2);
	}
}
