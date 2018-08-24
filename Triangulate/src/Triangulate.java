import java.awt.Color;

import objectdraw.*;

/**
 * Lab 7 (part I) - Triangulate
 * <p>
 * Recursively construct a triangle, which is made up of
 * sub-triangles, which are in turn ...
 * </p>
 * <p>
 *    Class Structure:
 *    <ul>
 *	<li>Triangulate is the controller class, that instantiates
 *	    a complex triangle and moves it in response to mouse events.</li>
 *	<li>TriangleDoodle is the interface for all triangles (simple or complex.</li>
 *	<li>ComplexTriangleDoodle recursively creates and moves a complex structure
 *	    made out of many nested sub-triangles.</li>
 *	<li>EmptyTriangleDoodle is the null class that ends the recursive creation
 *	    and moving.  It draws nothing, and has nothing to move.</li>
 *    </ul>
 * </p>
 * <br>
 * Suggested window size: 400x400
 * <br>
 * @author HUSSEIN FAARA, CS051J
 */
public class Triangulate extends WindowController {
	// the three vertices of the doodled triangle
	private static final int START = 32;
	private static final int WIDTH = 256;
	private static final int HEIGHT = (128+32);
	private static final Location vertex1 = new Location(START,
													START+HEIGHT);
	private static final Location vertex2 = new Location(START+WIDTH,
													START+HEIGHT);
	private static final Location vertex3 = new Location(START+WIDTH/2,
													START);

	/** where the mouse was at last report	*/
	private Location lastPoint;

	/** doodle being drawn and dragged		*/
	private TriangleDoodle doodle;

	/**
	 * Initialization method, called when applet starts.
	 * <p>
	 * 	Create a (recursive) complex triangle.
	 * </p>
	 */
	public void begin() {
		doodle = new ComplexTriangleDoodle(vertex1, 
					vertex2, vertex3, Color.BLUE, canvas);
	}

	/**
	 * Event handler, called when mouse button is pressed
	 * <p>
	 * Note location of press in preparation for dragging the triangle.
	 * </p>
	 *
	 * @param point	... mouse coordinates at time of press
	 */
	public void onMousePress(Location point) {
		lastPoint = point;
	}

	/**
	 * Event handler, called (periodically) when mouse is moved 
	 * w/button held
	 * <p>
	 * Drag the the triangle using its move method.
	 * </p>
	 *
	 * @param point	mouse coordinates after recent motion
	 */
	public void onMouseDrag(Location point) {
		doodle.move(point.getX() - lastPoint.getX(), 
				point.getY() - lastPoint.getY());
		lastPoint = point;
		
	}
}
