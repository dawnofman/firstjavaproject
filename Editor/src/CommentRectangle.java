//package editor.mcm.fhooe.at;

import java.awt.BasicStroke;
import java.awt.Graphics2D;
import java.awt.Stroke;

public class CommentRectangle extends Rectangle {
	private static final long serialVersionUID = 1L;
	public CommentRectangle(int i, int j, int k, int l) {
		super(i, j, k, l);
	}
	
	public CommentRectangle(int i, int j, int k, int l, String text) {
		super(i,j, k, l, text);
	}

	@Override
	public void draw(Graphics2D g) {
		Stroke temp = g.getStroke();
		g.setStroke(new BasicStroke(1, BasicStroke.CAP_BUTT,
				BasicStroke.JOIN_BEVEL, 0, new float[] {9}, 0));
		g.draw(this);
		g.setStroke(temp);
		if(mText != null)
			g.drawString(mText, x + width, y);
	}
}
