import objectdraw.*;
import java.awt.*;

/**
 * this class represents a light
 * <P>
 * Each light is represented by an oval and a square. Each light has a color and
 * an effective distance. Each light can be moved around by the mouse.
 * </P>
 * 
 * @author HUSSEIN FAARA, D'ANGELO DAVIS, JAVA
 */

public class Light {
	// Dimensions of light bulb
	private static final double SPHERE_WIDTH = 40;
	private static final double SPHERE_HEIGHT = 70;
	// Dimensions of bulb head
	private static final double HEAD_WIDTH = 24;
	private static final double HEAD_HEIGHT = 16;
	// Bulb frame
	private FramedOval sphereFrame;
	// Light bulb
	private FilledOval sphere;
	// Bulb head
	private FilledRect head;
	// Bulb's effective distance: how far the light can reach
	private int effectiveDistance;

	/**
	 * 
	 * constructor for Light
	 * 
	 * @param loc
	 *            ... starting location
	 * @param color1
	 *            ... color of light
	 * @param canvas
	 *            ... on which light is drawn
	 * @param effectiveDistance
	 */
	public Light(Location loc, Color color1, int effectiveDistance, DrawingCanvas canvas) {
		// Local variables for bulb and head locations
		Location headLoc = new Location(loc.getX() + 1, loc.getY());
		Location sphereLoc = new Location(loc.getX() - 8, loc.getY() + 0.5 * HEAD_HEIGHT);
		// Creates a head for the bulb
		head = new FilledRect(headLoc, HEAD_WIDTH, HEAD_HEIGHT, canvas);
		// Creates a bulb
		sphere = new FilledOval(sphereLoc, SPHERE_WIDTH, SPHERE_HEIGHT, canvas);
		// Creates bulb frame
		sphereFrame = new FramedOval(sphereLoc, SPHERE_WIDTH, SPHERE_HEIGHT, canvas);
		// Sets color of head to black
		head.setColor(Color.BLACK);
		// Sets color of sphere to provided color
		sphere.setColor(color1);
		// Stores the effectiveDistance parameter
		this.effectiveDistance = effectiveDistance;
		// Sets the head and the frame on top of the bulb
		head.sendToFront();
		sphereFrame.sendToFront();
	}

	/**
	 * retrieves the color of the light
	 */
	public Color getColor() {
		// return the actual color of this light
		return sphere.getColor();
	}

	/**
	 * retrieves the location of the center the bulb.
	 */
	public Location getCenter() {
		// Calculates the x and y components of the bulb's center
		double x = sphere.getX() + 0.5 * SPHERE_WIDTH;
		double y = sphere.getY() + 0.5 * SPHERE_HEIGHT;

		// Returns correct center location of bulb
		return new Location(x, y);
	}

	/**
	 * retrieves the effective distance of the light
	 */
	public double getEffectiveDist() {
		// Returns the effective distance of the light
		return effectiveDistance;
	}

	/**
	 * moves the light object by a certain offset
	 * 
	 * @param x
	 *            offset in x axis
	 * @param y
	 *            offset in y axis
	 */
	public void move(double x, double y) {
		// Moves the head
		head.move(x, y);
		// Moves the bulb
		sphere.move(x, y);
		// Moves the frame
		sphereFrame.move(x, y);
	}

	/**
	 * determines whether a (click) location is within a light
	 * 
	 * @param loc
	 *            the point to check
	 */
	public boolean contains(Location point) {
		// Returns true if light contains mouse click
		if (sphere.contains(point) || head.contains(point)) {
			return true;
		}
		// Returns false otherwise
		return false;
	}

	/**
	 * bring the light to the front, against all other objects on screen.
	 */
	public void sendToFront() {
		// Sends light components to the front.
		sphere.sendToFront();
		sphereFrame.sendToFront();
		head.sendToFront();
	}
}
