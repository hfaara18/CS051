import objectdraw.*;
import java.awt.*;

/**
 * An ActiveObject that periodically creates new Vehicles.
 */
public class Lane extends ActiveObject {
	/**
	 * Distance from front bumper to back bumper of the longest vehicle (in pixels)
	 */
	private static final int MAX_VEHICLE_SIZE = 139;

	// constants for the shields
	private static final int SHIELD_WIDTH = 50;
	private static final int SHIELD_HEIGHT = 700;
	private static final int LSHIELD_X = 0;
	private static final int RSHIELD_X = 750;
	private static final int SHIELD_Y = 100;

	// instance variables for the lane
	private Image car;
	private FilledRect road, leftShield, rightShield;
	private double laneLoc;
	private double spd;
	private Frog frog;
	private DrawingCanvas canvas;

	/**
	 * Instantiate a new Lane, and determine inter-vehicle pause
	 * 
	 * TODO DESIGN: identify/define Lane constructor parameters
	 * 
	 * @param ...
	 *            Vehicle image
	 * @param ...
	 *            highway
	 * @param ...
	 *            location of this lane
	 * @param ...
	 *            Frog (to be passed to Vehicles)
	 * @param ...
	 *            direction for this lane
	 * @param ...
	 *            canvas
	 * 
	 * 
	 */
	public Lane(Image image, FilledRect highway, double lane, Frog frog, int direction, DrawingCanvas aCanvas) {
		// remembering parameters
		car = image;
		road = highway;
		laneLoc = lane;
		this.frog = frog;
		canvas = aCanvas;
		int speedMin = 2;
		int speedMax = 4;

		// draw the left and right shields
		leftShield = new FilledRect(LSHIELD_X, SHIELD_Y, SHIELD_WIDTH, SHIELD_HEIGHT, canvas);
		rightShield = new FilledRect(RSHIELD_X, SHIELD_Y, SHIELD_WIDTH, SHIELD_HEIGHT, canvas);
		leftShield.setColor(Color.WHITE);
		rightShield.setColor(Color.WHITE);

		// randomizing the speed of the cars
		RandomDoubleGenerator speed = new RandomDoubleGenerator(speedMin, speedMax);
		spd = direction * speed.nextValue();

		start();
	}

	/**
	 * Active Object (thread) routine (main loop) for a Lane
	 * <p>
	 * <UL>
	 * <li>instantiate a new vehicle
	 * <li>decide how long to wait before creating next one (1-4 car lengths)
	 * <li>pause for that period
	 * </ul>
	 */
	public void run() {
		int doubleCarLength = 2 * MAX_VEHICLE_SIZE;
		int quinCarLength = 4 * MAX_VEHICLE_SIZE;
		int factor = 25;
		// Loop until the program stops.
		RandomIntGenerator spacing = new RandomIntGenerator(doubleCarLength, quinCarLength);
		while (true) {
			// creates and moves the cars
			new Vehicle(car, road, laneLoc, frog, spd, canvas);
			leftShield.sendToFront();
			rightShield.sendToFront();
			pause((int) (spacing.nextValue() / Math.abs(spd)) * factor);
		}
	}

}
