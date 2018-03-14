import objectdraw.*;
import java.awt.*;

/**
 * Light-Balloon
 * <P>
 * Simulate the coloring effect of two colored lights, which can be dragged
 * around with the mouse, on a pair of balloons.
 * </P>
 * Extra Credit: TODO (Implementation): Please list all features that you think
 * deserve extra credit here before submitting your code.
 * 
 * @author HUSSEIN FAARA, D'ANGELO DAVIS, JAVA
 */

public class LightBalloon extends WindowController {
	/* location of the two lights and balloons when program starts */
	// The 3 bulb locations
	private Location bulb1_Loc = new Location(130, 85);
	private Location bulb2_Loc = new Location(280, 230);
	private Location bulb3_Loc = new Location(430, 370);
	// The 2 balloon locations
	private Location balloon1_Loc = new Location(180, 130);
	private Location balloon2_Loc = new Location(330, 270);
	// Effective distances of the 3 lights
	private static final int redEffective = 300;
	private static final int greenEffective = 200;
	private static final int blueEffective = 250;
	// The 3 lights
	private Light bulb1;
	private Light bulb2;
	private Light bulb3;
	// The two balloons
	private Balloon balloon1;
	private Balloon balloon2;
	// A correct bulb value for when a bulb is being dragged
	private Light correctBulb;
	// Whether or not a bulb is being dragged
	private boolean dragging;
	// Last known location of the mouse
	private Location lastPoint;

	/**
	 * when the program starts, draw the balloons and the lights.
	 */
	public void begin() {
		// Creates 3 bulbs with 3 different colors
		bulb1 = new Light(bulb1_Loc, Color.RED, redEffective, canvas);
		bulb2 = new Light(bulb2_Loc, Color.GREEN, greenEffective, canvas);
		bulb3 = new Light(bulb3_Loc, Color.BLUE, blueEffective, canvas);
		// Creates 2 balloons with a tail
		balloon1 = new Balloon(balloon2_Loc, bulb1, bulb2, bulb3, canvas);
		balloon2 = new Balloon(balloon1_Loc, bulb1, bulb2, bulb3, canvas);
		// Sets the color of the balloons with respect to bulb locations
		balloon1.setColor();
		balloon2.setColor();
	}

	/**
	 * handle mouse action PRESS.
	 * 
	 * if the mouse is pressed within one of the light, setup the values of
	 * corresponding instance variables to facilitate dragging.
	 * 
	 * @param loc
	 *            location of the mouse
	 */
	public void onMousePress(Location loc) {
		// Logic to determine which light is selected
		// and what's need to be done if it is selected
		// and makes it the "correct bulb"
		if (bulb1.contains(loc)) {
			correctBulb = bulb1;
			dragging = true;
		} else if (bulb2.contains(loc)) {
			correctBulb = bulb2;
			dragging = true;
		} else if (bulb3.contains(loc)) {
			correctBulb = bulb3;
			dragging = true;
		} else {
			dragging = false;
		}
		// Stores last known location of mouse
		lastPoint = loc;
	}

	/**
	 * handle mouse action RELEASE.
	 * 
	 * @param loc
	 *            location of the mouse
	 */
	public void onMouseRelease(Location loc) {
		// Sets value of dragging to false when mouse is released
		dragging = false;
	}

	/**
	 * handle mouse action DRAG.
	 * 
	 * if a light has been selected, then, that light is moved with the mouse.
	 * whenever a light is moved, the color of the balloon(s) will change
	 * accordingly.
	 * 
	 * * @param loc location of the mouse
	 */
	public void onMouseDrag(Location loc) {
		// Moves the correct/ selected bulb when it is dragged
		if (dragging) {
			correctBulb.move(loc.getX() - lastPoint.getX(), loc.getY() - lastPoint.getY());
			// Stores the last known location of the mouse
			lastPoint = loc;
			// Puts the selected bulb over all other objects
			correctBulb.sendToFront();
			// Sets balloon colors with respect to effective
			//  distances and bulb locations
			balloon1.setColor();
			balloon2.setColor();
		}
	}
}
