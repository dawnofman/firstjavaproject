//package editor.mcm.fhooe.at;

import java.awt.Graphics2D;

public class Rectangle extends java.awt.Rectangle implements Shape {
	private static final long serialVersionUID = 1L;
	public String mText = null;
	public String mType = null;
	public Rectangle(int i, int j, int k, int l) {
		x = i;y = j;width = k;height = l;
	}
	public Rectangle(int i, int j, int k, int l, String text) {
		x = i;
		y = j;
		width = k;
		height = l;
		mText = text;
	}
	public Rectangle(int i, int j, int k, int l, String text, String type) {
		x = i;
		y = j;
		width = k;
		height = l;
		mText = text;
		mType = type;
	}
	public String getText() {
		return mText;
	}
	@Override
	public void draw(Graphics2D g) {
		g.draw(this);
		if(mText != null)
			g.drawString(mText, x + width, y);
	}
}
