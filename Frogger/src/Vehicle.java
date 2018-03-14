import objectdraw.*;
import java.awt.*;

/**
 * A Vehicle is an ActiveObject that moves along a designated lane at a constant
 * speed, until it reaches the end.
 */
public class Vehicle extends ActiveObject {

	// constants for the vehicles
	private static final int CAR_WIDTH = 139;
	private static final int PAUSE = 30;

	// instance variables for the game
	private VisibleImage car;
	private FilledRect highway;
	private double laneLoc;
	private double spd;
	private double carX;
	private Frog frog;
	private DrawingCanvas canvas;

	/**
	 * Instantiate a new vehicle, with a specified position and speed
	 * 
	 * TODO DESIGN: describe all Vehicle constructor variables
	 * 
	 * @param ...
	 *            vehicle image
	 * @param ...
	 *            highway
	 * @param ...
	 *            starting location
	 * @param ...
	 *            frog (so we can check for overlap)
	 * @param ...
	 *            travel speed
	 * @param ...
	 *            canvas
	 */
	public Vehicle(Image image, FilledRect road, double laneLocation, Frog frog, double speed, DrawingCanvas aCanvas) {
		// constructing the vehicle class
		highway = road;
		laneLoc = laneLocation;
		this.frog = frog;
		this.spd = speed;
		canvas = aCanvas;

		if (speed > 0) {
			car = new VisibleImage(image, highway.getX() - CAR_WIDTH, laneLoc, canvas);
		} else {
			car = new VisibleImage(image, highway.getX() + highway.getWidth(), laneLoc, canvas);
		}
		carX = car.getX();

		start();
	}

	/**
	 * Active Object (thread) routine for a vehicle
	 * <p>
	 * Move vehicle along the lane until it reaches the end, checking to see if we
	 * have hit the frog.
	 * <UL>
	 * <LI>after each iteration of the loop, pause for at least 30ms.
	 * <LI>we may pause longer than the requested time. To achieve smooth movement
	 * we should check the time when we resume, determine how long we actually
	 * paused, and compute the distance the vehicle should have moved during that
	 * time.
	 * <LI>after we move the vehicle to its new position, see if we hit the frog. If
	 * so, call his kill method.
	 * <LI>after the vehicle reaches the end of the lane, we should remove the
	 * vehicle from the canvas and exit.
	 * </UL>
	 */
	public void run() {

		// Vehicle moves steadily until it reaches end of lane
		while ((car.getX() >= highway.getX() - CAR_WIDTH) && (car.getX() <= highway.getX() + highway.getWidth())) {
			carX = carX + spd;
			car.move(spd, 0);
			if (frog.overlaps(car)) {
				frog.kill();
			}
			pause(PAUSE);

		}
		car.removeFromCanvas();

	}
}
