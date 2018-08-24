import objectdraw.*;
import java.awt.*;

/**
 * A VariegatedStair is a rectangle, with recursively
 * smaller rectangles above and to the right of it.
 * In the limit this approximates a triangular stairway.
 */
public class VariegatedStairs implements StairsInterface {

	/** minimum width and height for a base square in order to build around it */
	private static final int MINSIZE = 2;
	/** used to darken the color of stairs built around the base square	*/
	private static final int DARKNESS_FACTOR = 30;
	
	// The biggest base of the staircase and an outline
	private FilledRect base;
	private FramedRect outline;
	
	// the above and side stairs
	private StairsInterface aboveStair, sideStair;
	
	/**
	 * A method that creates a Staircase from colored boxes
	 * 
	 * @param lowerLeft
	 * 			the lower left location of the to-be-created box
	 * @param initialSize
	 * 			initial size of the to-be-created box
	 * @param initialColor
	 * 			initial color of the to-be-created box
	 * @param canvas
	 * 			the drawing canvas
	 */
	public VariegatedStairs(Location lowerLeft, double initialSize, Color initialColor, DrawingCanvas canvas) {
		// the start location of the to-be-created box
		double topX = lowerLeft.getX();
		double topY = lowerLeft.getY() - initialSize;
		
		// the base of the staircase and its outline
		base = new FilledRect(topX, topY, initialSize, initialSize, canvas);
		outline = new FramedRect(base.getLocation(), initialSize, initialSize, canvas);
		base.setColor(initialColor);
		
		// creates new boxes relative to the base box until the box size is less than the minimum size
		if (initialSize >= MINSIZE ) {
			// the size and color components of the box
			double size = initialSize/ 2;
			int redValue = initialColor.getRed() - DARKNESS_FACTOR;
			int blueValue = initialColor.getBlue();
			int greenValue = initialColor.getGreen() - DARKNESS_FACTOR;
			Color color = new Color(redValue, greenValue, blueValue);
			
			// Locations of the to-be-created boxes above and beside the referenced box
			Location top = new Location(topX, topY);
			Location right = new Location(topX + initialSize, lowerLeft.getY());
			
			// the above and beside boxes
			aboveStair= new VariegatedStairs(top, size, color, canvas);
			sideStair = new VariegatedStairs(right, size, color, canvas);
			
		} else {
			// doesn't create new stairs when limit is reached
			aboveStair = new EmptyStairs();
			sideStair = new EmptyStairs();
		}
	
	}
	/**
	 * A method that checks whether the mouse pointer is within the staircase
	 * 
	 * @param Location
	 * 			the current coordinates of the mouse pointer
	 */
	public Boolean contains(Location pt) {
		
		// returns true if the mouse pointer is within the staircase  
		if (base.contains(pt) || aboveStair.contains(pt) 
				|| sideStair.contains(pt) || outline.contains(pt)) {
			return true;
		}
		// return false otherwise
		else{
			return false;
		}
	}
	/**
	 * A method that moves all components of the staircase
	 * 
	 * @param x
	 * 			the x coordinates of the mouse pointer
	 * @param y
	 * 			the y coordinates of the mouse pointer
	 */
	public void move(double x, double y) {
		base.move(x, y);		
		aboveStair.move(x, y);
		sideStair.move(x, y);
		outline.move(x, y);
	}
	

}