//package editor.mcm.fhooe.at;

import java.awt.Checkbox;
import java.awt.Component;
import java.awt.Point;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class Controller implements WindowListener, ActionListener,
		ComponentListener, MouseListener, ItemListener, KeyListener {

	Model mModel = null;
	
	boolean mInsideBox = false;

	int xCoordinate = -1;
	int yCoordinate = -1;

	Rectangle mCurrentRect = null;
	
	String drawMode = null;
	String text = null;
	
	public Controller(Model _m) {
		mModel = _m;
	}

	@Override
	public void actionPerformed(ActionEvent _arg0) {
		String actionCommand = _arg0.getActionCommand();
		switch (actionCommand) {
		case "Load": {
			System.out.println(actionCommand);
			mModel.loadData();
			break;
		}
		case "ZTF": {
			System.out.println(actionCommand);
			mModel.zoomToFit();
			break;
		}
		case "+": {
			System.out.println(actionCommand);
//			mGISModel.zoom(1.3d);
			break;
		}
		case "-": {
			System.out.println(actionCommand);
//			mGISModel.zoom(1d / 1.3d);
			break;
		}
		case "U/N": {
			System.out.println(actionCommand);
//			mGISModel.scrollVertical(20);
			break;
		}
		case "D/S": {
			System.out.println(actionCommand);
//			mGISModel.scrollVertical(-20);
			break;
		}
		case "L/W": {
			System.out.println(actionCommand);
//			mGISModel.scrollHorizontal(-20);
			break;
		}
		case "R/E": {
			System.out.println(actionCommand);
//			mGISModel.scrollHorizontal(20);
			break;
		}
		case "Rot L": {
			System.out.println(actionCommand);
//			mGISModel.rotate(-Math.PI / 12);
			break;
		}
		case "Rot R": {
			System.out.println(actionCommand);
//			mGISModel.rotate(Math.PI / 12);
			break;
		}
		default: {
			System.out.println(actionCommand);
		}
		}
	}

	@Override
	public void windowActivated(WindowEvent _arg0) {
	}

	@Override
	public void windowClosed(WindowEvent _arg0) {
	}

	@Override
	public void windowClosing(WindowEvent _arg0) {
		System.exit(0);
	}

	@Override
	public void windowDeactivated(WindowEvent _arg0) {
	}

	@Override
	public void windowDeiconified(WindowEvent _arg0) {
	}

	@Override
	public void windowIconified(WindowEvent _arg0) {
	}

	@Override
	public void windowOpened(WindowEvent _arg0) {
	}

	@Override
	public void componentHidden(ComponentEvent _arg0) {
	}

	@Override
	public void componentMoved(ComponentEvent _arg0) {
	}

	@Override
	public void componentResized(ComponentEvent _arg0) {
		Component c = _arg0.getComponent();
		mModel.setSize(c.getWidth(), c.getHeight());
	}

	@Override
	public void componentShown(ComponentEvent _arg0) {
	}

	@Override
	public void mouseClicked(MouseEvent _arg0) {
	}

	@Override
	public void mouseEntered(MouseEvent _arg0) {
	}

	@Override
	public void mouseExited(MouseEvent _arg0) {
	}

	@Override
	public void mousePressed(MouseEvent _arg0) {
		DrawingPanel panel = (DrawingPanel) _arg0.getComponent();
		if (panel != null) {
			boolean commentBox = false;
			Shape cur;
			for(int i = 0; i < mModel.mComponents.size(); i++) {
				cur = mModel.mComponents.get(i);
				if(cur instanceof Rectangle) {
					Rectangle rect = (Rectangle) cur;
					if(rect.contains(new Point(_arg0.getX(), _arg0.getY()))) {
						mInsideBox = true;
						mCurrentRect = rect;
						break;
					}
				}
			}
			xCoordinate = _arg0.getX();
			yCoordinate = _arg0.getY();
			
		}
	}

	@Override
	public void mouseReleased(MouseEvent _arg0) {
		DrawingPanel panel = (DrawingPanel) _arg0.getComponent();
		if (panel != null) {
			if (xCoordinate != -1 && yCoordinate != -1) {
				int xDelta = _arg0.getX() - xCoordinate;
				int yDelta = _arg0.getY() - yCoordinate;
				//same position
				if(xCoordinate == _arg0.getX() && yCoordinate == _arg0.getY() && drawMode.equals("Rectangle")) {
					Rectangle rect = new Rectangle(xCoordinate-20,
							yCoordinate-20, 40, 40, null);
					mModel.mComponents.add(0, rect);
				}
				else if(xCoordinate == _arg0.getX() && yCoordinate == _arg0.getY() && drawMode.equals("Comment")) {
					CommentRectangle rect = new CommentRectangle(xCoordinate-20,
							yCoordinate-20, 40, 40, null);
					mModel.mComponents.add(0, rect);
				}
				else if(drawMode.equals("Text")) {
					Shape cur;
					for(int i = 0; i < mModel.mComponents.size(); i++) {
						cur = mModel.mComponents.get(i);
						if(cur instanceof Rectangle) {
							Rectangle rect = (Rectangle) cur;
							if(rect.contains(new Point(_arg0.getX(), _arg0.getY()))) {
								rect.mText = text;
								break;
							}
						} else if(cur instanceof Line) {
							Line line = (Line) cur;
							Point temp = new Point((int)(line.x1 + line.x2) / 2, (int)(line.y1 + line.y2) / 2);
							int distance = (int) Math.sqrt((temp.x - _arg0.getX()) * (temp.x - _arg0.getX()) +
									(temp.y - _arg0.getY()) * (temp.y - _arg0.getY()));
							if(distance < 35) {
								line.mText = text;
								break;
							}
						}
					}
				}
				else if(drawMode.equals("Line")) {
					if(mInsideBox) {
						Shape cur;
						for(int i = 0; i < mModel.mComponents.size(); i++) {
							cur = mModel.mComponents.get(i);
							if(cur.contains(new Point(_arg0.getX(), _arg0.getY()))) {
								if(cur instanceof CommentRectangle) {
									CommentRectangle rect = (CommentRectangle) cur;
									if(!(mCurrentRect instanceof CommentRectangle)) { //normal Rectangle
										mModel.mComponents.add(0, new CommentLine(
												new Point(mCurrentRect.x + mCurrentRect.width / 2,
														mCurrentRect.y + mCurrentRect.height / 2),
												new Point(rect.x + rect.width/2,rect.y + rect.height/2)));
										break;
									} else {
										//2 CommentRects - do nothing
									}
								} else { //normal Rectangle
									Rectangle rect = (Rectangle) cur;
									if(mCurrentRect instanceof CommentRectangle) { //CommentRect
										mModel.mComponents.add(0, new CommentLine(
												new Point(mCurrentRect.x + mCurrentRect.width / 2,
														mCurrentRect.y + mCurrentRect.height / 2),
												new Point(rect.x + rect.width/2,rect.y + rect.height/2)));
										break;
									} else { //2 normal rects
										mModel.mComponents.add(0, new Line(
												new Point(mCurrentRect.x + mCurrentRect.width / 2,
														mCurrentRect.y + mCurrentRect.height / 2),
												new Point(rect.x + rect.width/2,rect.y + rect.height/2)));
										break;
									}
								}
							}
						}
					}
				}
				xCoordinate = -1;
				yCoordinate = -1;
			}
			mModel.paint();
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		Checkbox cb = (Checkbox) e.getSource();
		if(e.getStateChange() == ItemEvent.SELECTED) {
			drawMode = e.getItem().toString();
		}
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		TextField tf = (TextField) e.getSource();
		text = tf.getText();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		TextField tf = (TextField) e.getSource();
		text = tf.getText();
	}
}