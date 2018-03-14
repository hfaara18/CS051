import objectdraw.*;
import java.awt.*;

/**
 * Frogger (loops and active objects)
 * <p>
 * A game in which the player controls a frog that is trying to cross a busy
 * 4-lane highway.
 * <p>
 * High Level Design
 * <UL>
 * <LI>Frog ... a frog image that can hop in a specified direction, determine
 * whether or not it has been hit by a car, die, and be reincarnated to start a
 * new game.
 * <LI>Vehicle ... an active object (vehicle image) that moves along a lane at a
 * constant speed, and can determine whether or not it has hit the frog.
 * <LI>Lane ... an active object that periodically creates new Vehicles, at
 * random intervals, all traveling with the same speed/direction.
 * <LI>Frogger ... the control class which
 * <UL>
 * <LI>decides where each lane should be and how it should move
 * <LI>displays the lane images
 * <LI>instantiates a Frog
 * <LI>instantiates the Lanes (which instantiate Vehicles)
 * <LI>receives mouse press events, passes them to Frog
 * </UL>
 * </UL>
 *
 * Class Interactions:
 * <UL>
 * <LI>images can only be loaded from the WindowController class, so the Frogger
 * class loads these, and then passes them to the Frog and (through the Lanes)
 * the Vehicles
 * <LI>each Vehicle has a reference to the Frog so that it can:
 * <UL>
 * <LI>call Frog.overlap() method (afer each movement) to see if the vehicle has
 * hit the Frog
 * <LI>call Frog.kill() method to tell the Frog to die
 * </UL>
 * <LI>since Vehicles are created by Lanes, each Lane must also have a reference
 * to the Frog.
 * </UL>
 * 
 * Design Tricks:
 * <UL>
 * <LI>Rather than removing the car from the screen when it reaches the end of a
 * lane, it is more attractive to put a white rectangle at either end of, and on
 * top of, each lane. The Vehicles will appear/disappear from/to behind these
 * rectangles.
 * <LI>Rather than creating and destroying the "Ouch" message each time the Frog
 * dies and reincarnates, it is simpler to have the message always be present,
 * but sometimes be hidden.
 * </UL>
 * 
 * Suggested window size: 800x600
 *
 * @author Hussein Faara & Jorge Rodriguez, CS051J
 */
public class Frogger extends WindowController {

	// Constants defining the sizes of the background components.
	private static final double HIGHWAY_LENGTH = 700;
	private static final double LANE_WIDTH = 100;
	private static final int NUM_LANES = 4;
	private static final double HIGHWAY_WIDTH = LANE_WIDTH * NUM_LANES;
	private static final double LINE_WIDTH = LANE_WIDTH / 10;

	// Constants defining the locations of the background components
	private static final double HIGHWAY_LEFT = 50;
	private static final double HIGHWAY_RIGHT = HIGHWAY_LEFT + HIGHWAY_LENGTH;
	private static final double HIGHWAY_TOP = 100;
	private static final double HIGHWAY_BOTTOM = HIGHWAY_TOP + HIGHWAY_WIDTH;

	// Constants describing the lines on the highway
	private static final double LINE_SPACING = LINE_WIDTH / 2;
	private static final double DASH_LENGTH = LANE_WIDTH / 3;
	private static final double DASH_SPACING = DASH_LENGTH / 2;

	// instance variable for frog
	private Frog frog;

	/**
	 * Initialization method, called when applet starts.
	 * <ul>
	 * <li>load vehicle and frog images
	 * <li>instantiate the frog
	 * <li>instantiate the lanes
	 * </ul>
	 */

	public void begin() {

		// Draw the highway background
		FilledRect highway;
		highway = new FilledRect(HIGHWAY_LEFT, HIGHWAY_TOP, HIGHWAY_LENGTH, HIGHWAY_WIDTH, canvas);

		// Draw the lane dividers
		int whichLine = 1;
		while (whichLine < NUM_LANES) {
			if (whichLine == NUM_LANES / 2) {
				// The middle line is a no passing line
				drawNoPassingLine(HIGHWAY_TOP + (whichLine * LANE_WIDTH) - (LINE_SPACING / 2 + LINE_WIDTH));
			} else {
				drawPassingLine(HIGHWAY_TOP + (whichLine * LANE_WIDTH) - (LINE_WIDTH / 2));
			}
			whichLine = whichLine + 1;
		}

		// extracting the image files
		Image frogPic = getImage("froggy.gif");
		Image car1Left = getImage("jeep_left.gif");
		Image car2Left = getImage("oldcar_left.gif");
		Image car3Right = getImage("taxi_right.gif");
		Image car4Right = getImage("van_right.gif");

		// local variables for cars
		int yOffset = 30;
		double oldCarY = HIGHWAY_TOP + LANE_WIDTH;
		double taxiY = HIGHWAY_TOP + 2 * LANE_WIDTH;
		double vanY = HIGHWAY_TOP + 3 * LANE_WIDTH;
		
		// draw the frog and the lanes
		frog = new Frog(frogPic, LANE_WIDTH, highway, canvas);
		new Lane(car1Left, highway, HIGHWAY_TOP + yOffset, frog, -1, canvas);
		new Lane(car2Left, highway, oldCarY + yOffset, frog, -1, canvas);
		new Lane(car3Right, highway,  taxiY + yOffset, frog, 1, canvas);
		new Lane(car4Right, highway, vanY + yOffset, frog, 1, canvas);
	}

	/**
	 * Draw a pair of solid yellow lines to represent a no-passing divider between
	 * lanes.
	 * <P>
	 * NOTE: YOU SHOULD NOT NEED TO MODIFY THIS METHOD
	 * 
	 * @param y
	 *            coordinate of the top of the line
	 * 
	 */
	public void drawNoPassingLine(double y) {
		// Draw the solid dividing lines
		FilledRect topLine = new FilledRect(HIGHWAY_LEFT, y, HIGHWAY_LENGTH, LINE_WIDTH, canvas);
		topLine.setColor(Color.yellow);

		FilledRect bottomLine = new FilledRect(HIGHWAY_LEFT, y + LINE_WIDTH + LINE_SPACING, HIGHWAY_LENGTH, LINE_WIDTH,
				canvas);
		bottomLine.setColor(Color.yellow);
	}

	/**
	 * Draw a dashed white line to represent a passing line dividing two lanes of
	 * traffic.
	 * <P>
	 * NOTE: YOU SHOULD NOT NEED TO MODIFY THIS METHOD
	 * 
	 * @param y
	 *            coordinate of the top of the line
	 */
	public void drawPassingLine(double y) {
		double x = HIGHWAY_LEFT;
		FilledRect dash;

		while (x < HIGHWAY_RIGHT) {
			// Draw the next dash.
			dash = new FilledRect(x, y, DASH_LENGTH, LINE_WIDTH, canvas);
			dash.setColor(Color.white);
			x = x + DASH_LENGTH + DASH_SPACING;
		}

	}

	/**
	 * Event handler, called when mouse button is pressed
	 * <p>
	 * Make the frog jump towards the new cursor position. <br>
	 * If the frog is dead, a click below the highway should reincarnate it.
	 *
	 * @param point
	 *            mouse coordinates at time of mouse press
	 */
	public void onMousePress(Location point) {
		// move the frog towards the mouse press
		if (frog.isAlive()) {
			frog.hopToward(point);
		}
		// reincarnate dead frog
		else if (point.getY() > HIGHWAY_BOTTOM) {
			frog.reincarnate();
		}
	}

}
