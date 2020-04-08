//package editor.mcm.fhooe.at;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.Stroke;
import java.awt.geom.Line2D;
import java.awt.image.BufferedImage;
import java.util.Vector;

public class Model extends DataObservable {

//	Matrix mTransformationsMatrix = null;
//	Vector<GeoObject> mGeoObjects = new Vector<GeoObject>();
	
	Vector<Shape> mComponents = new Vector<Shape>();
	
	int mImgWidth = 0;
	int mImgHeight = 0;

	public Model() {
//		mTransformationsMatrix = new Matrix();
	}

	public void setSize(int _width, int _height) {
		mImgWidth = _width;
		mImgHeight = _height;
		paint();
	}

	private void update(BufferedImage _data) {
		mImgWidth = _data.getWidth();
		mImgHeight = _data.getHeight();
		for (DataObserver _do : observers) {
			_do.update(_data);
		}
	}

	public void zoomToFit() {
//		if (mGeoObjects.size() != 0) {
//			Rectangle boundingBox = getMapBounds(mGeoObjects);
//			mTransformationsMatrix = Matrix.zoomToFit(boundingBox,
//					new Rectangle(0, 0, mImgWidth - 1, mImgHeight - 1));
//			paint();
//		}
	}

//	public void zoom(double _factor) {
//		zoom(new Point(mImgWidth / 2, mImgHeight / 2), _factor);
//		paint();
//	}

//	public void zoom(Point _pt, double _factor) {
//		mTransformationsMatrix = Matrix.zoomPoint(mTransformationsMatrix, _pt,
//				_factor);
//		paint();
//	}


//	public Rectangle getMapBounds(Vector<GeoObject> geoObjects) {
//		Rectangle boundingBox = new Rectangle();
//		if (geoObjects.size() != 0) {
//			boundingBox = geoObjects.elementAt(0).mPolygon.getBounds();
//			for (GeoObject go : geoObjects) {
//				boundingBox = boundingBox.union(go.mPolygon.getBounds());
//			}
//		}
//		return boundingBox;
//	}

	void paint() {
		BufferedImage bi = new BufferedImage(mImgWidth, mImgHeight,
				BufferedImage.TYPE_INT_ARGB);
		Graphics2D g = bi.createGraphics();
		g.setColor(Color.black);
//		for (GeoObject go : mGeoObjects) {
//			Polygon temp = mTransformationsMatrix.multiply(go.mPolygon);
//			g.drawPolygon(temp);
//		}
		Rectangle rect;
		Line line;
		Stroke temp = g.getStroke();
		for (Shape shape : mComponents) {
			if (shape instanceof CommentRectangle){
				((CommentRectangle) shape).draw(g);
			} else if(shape instanceof Rectangle) {
				rect = (Rectangle) shape;
				rect.draw(g);
			} else if(shape instanceof CommentLine) {
				((CommentLine) shape).draw(g);
			} else if(shape instanceof Line) {
				((Line) shape).draw(g);
			}
		}
		g.dispose();
		update(bi);
	}

//	public void scrollHorizontal(int _delta) {
//		mTransformationsMatrix = Matrix.translate(_delta, 0).multiply(
//				mTransformationsMatrix);
//		paint();
//	}
//
//	/**
//	 * Veraendert die interne Transformationsmatrix so, dass die zu zeichnenden
//	 * Objekte vertikal verschoben werden.
//	 * 
//	 * @param _delta
//	 *            Die Strecke, um die verschoben werden soll
//	 */
//	public void scrollVertical(int _delta) {
//		mTransformationsMatrix = Matrix.translate(0, _delta).multiply(
//				mTransformationsMatrix);
//		paint();
//	}
//
//	/**
//	 * Veraendert die interne Transformationmatrix so, dass die zu zeichnenden
//	 * Objekte gedreht werden
//	 * 
//	 * @param _angle
//	 */
//	public void rotate(double _angle) {
//		mTransformationsMatrix = Matrix.rotatePoint(mTransformationsMatrix,
//				new Point(mImgWidth / 2, mImgHeight / 2), _angle);
//		paint();
//	}

	void loadData() {
		boolean init = false;
		
//		if (init) {
//			String sql_stmt = "select * from data where type in (233, 931, 932, 933, 934, 1101)";
//			boolean success = false;
//			Vector<GeoObject> objectContainer = null;
//			try {
//				CgStatement stmt = m_geoInterface.Execute(sql_stmt);
//				CgResultSet cursor = stmt.getCursor();
//				objectContainer = new Vector<GeoObject>();
//				while (cursor.next()) {
//					CgIGeoObject obj = cursor.getObject();
//					System.out.println("NAME --> " + obj.getName());
//					System.out.println("TYP  --> " + obj.getCategory());
//					CgIGeoPart[] parts = obj.getParts();
//					for (int i = 0; i < parts.length; i++) {
//						System.out.println("PART " + i);
//						int pointCount = parts[i].getPointCount();
//						int[] xArray = parts[i].getX();
//						int[] yArray = parts[i].getY();
//						Polygon poly = new Polygon(xArray, yArray, pointCount);
//						for (int j = 0; j < pointCount; j++) {
//							System.out.println("[" + xArray[j] + " ; "
//									+ yArray[j] + "]");
//						} // for j
//						objectContainer.addElement(new GeoObject(obj.getName(),
//								obj.getCategory(), poly));
//					} // for i
//					System.out.println();
//				} // while cursor
//				success = true;
//
//			} catch (Exception _e) {
//				_e.printStackTrace();
//			}
//			if (success) {
//				mGeoObjects = objectContainer;
//				paint();
//			}
//		}
	}
}
