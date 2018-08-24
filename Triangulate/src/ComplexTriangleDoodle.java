
import objectdraw.*;

import java.awt.*;

/**
 * A recursive class that creates a triangle, and then
 * (recursively) fills it in with smaller triangles
 * (until they get too small, at which point we fill 
 * it with an empty triangle to finish the recursion)
 */
public class ComplexTriangleDoodle implements TriangleDoodle {
	/** minimum size of a triangle side for further doodling	*/
	private static final int MINSIZE = 12;

	// the edges  of the triangle
	private Line leftEdge, rightEdge, baseEdge;
	
	// the triangle doodles
	private TriangleDoodle topTriangle, leftTriangle, rightTriangle;

	/**
	 * constructor
	 *
	 *	Constructs a complex triangle doodle. 
	 *	<OL type="1">
	 *	   <LI> draw lines connecting the three vertices</li>
	 *	   <LI> if a side is longer than MINSIZE, recursiely 
	 *		construct three smaller triangles within it</li>
	 *	   <LI> once the sides get shorter than MINSIZE, 
	 *		create three EmptyTriangleDoodles as the
	 *		sub-triangles to end the recursion.</li>
	 *	</OL>
	 *
	 * @param	left vertex
	 * @param	right vertex
	 * @param	top vertex
	 * @param	color
	 * @param	canvas
	 */
	public ComplexTriangleDoodle(Location left, Location right,
					Location top, Color color, DrawingCanvas canvas) {
		// Constructs containing triangle
		leftEdge = new Line(left, top, canvas);
		rightEdge = new Line(right, top, canvas);
		baseEdge = new Line(left, right, canvas);
		leftEdge.setColor(color);
		rightEdge.setColor(color);
		baseEdge.setColor(color);
		
		// Creates three colored triangles if size is greater than minimum 
		// allowed size
		if (right.distanceTo(left) >= MINSIZE) {
			// Midpoints of the containing triangle
			Location leftMidpoint = new Location((left.getX() + top.getX())/2,
					(left.getY() + top.getY())/2);
			Location rightMidpoint = new Location((right.getX() + top.getX())/2, 
					(right.getY() + top.getY())/2);
			Location baseMidpoint = new Location((left.getX() + right.getX())/2, 
					(left.getY() + right.getY())/2);
			
			// Creates three internal triangles within the larger triangle
			topTriangle = new ComplexTriangleDoodle(leftMidpoint,
								rightMidpoint, top, Color.RED, canvas);
			rightTriangle = new ComplexTriangleDoodle(baseMidpoint, 
								right, rightMidpoint, Color.BLUE, canvas);
			leftTriangle = new ComplexTriangleDoodle(left, 
								baseMidpoint, leftMidpoint, Color.GREEN, canvas);
			
		}	
		// Stops creating colored circles if side is less than 
		// minimum allowed size				
		else {	
			topTriangle = new EmptyTriangleDoodle();
			rightTriangle = new EmptyTriangleDoodle();
			leftTriangle = new EmptyTriangleDoodle();
		}
		
			
			
		
	}
	/**
	 * A method that moves a triangle's edge
	 * 
	 * @param x
	 * 			X coordinates of mouse pointer
	 * @param y
	 * 			Y coordinates of mouse pointer
	 * @param line
	 * 			the line to move
	 */
	public void lineMove(double x, double y, Line line) {
		// moves the lines start position
		Location lineStart = line.getStart();
		line.setStart(lineStart.getX() + x, lineStart.getY() + y);
		
		// moves the line's end position
		Location lineEnd = line.getEnd();	
		line.setEnd(lineEnd.getX() + x, lineEnd.getY() + y);
	}

	/**
	 * move method
	 *
	 * @param	dx ... x movement (in pixels)
	 * @param	dy ... y movement (in pixels)
	 */
	public void move(double dx, double dy) {
		// moves the triangle doodles
		topTriangle.move(dx, dy);
		rightTriangle.move(dx, dy);
		leftTriangle.move(dx, dy);
		
		// moves each containing triangle
		lineMove(dx, dy, leftEdge);
		lineMove(dx, dy, rightEdge);
		lineMove(dx, dy, baseEdge);
		
	}
}
