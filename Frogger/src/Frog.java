import objectdraw.*;
import java.awt.*;

/**
 * The Frog is a visible image on the screen that moves in response to user
 * input, and dies if it comes into contact with a Vehicle.
 */
public class Frog {
	// constants for frog
	private static final double FROG_HEIGHT = 48;
	private static final double FROG_WIDTH = 83;
	private static final double startX = 350;
	private static final double startY = 530;

	// frog instance variables
	private VisibleImage frogImage;
	private VisibleImage carImage;

	// the highway
	private FilledRect highway;

	// legal frog locations
	private double legalTop;
	private double legalBottom;
	private double legalLeft;
	private double legalRight;
	private double leap;

	// message, start location and mortality
	private Text ouch;
	private Location startLoc;
	private boolean alive;

	/**
	 * Instantiate a Frog to start a new game
	 * <ul>
	 * <li>create the Frog visibleImage
	 * <li>determine the initial position
	 * <li>place the image in that position
	 * </ul>
	 *
	 * TODO DESIGN: identify and describe all Frog constructor parameters
	 * 
	 * @param frog
	 *            image
	 * @param leaping
	 *            distance
	 * @param highway
	 * @param canvas
	 */
	public Frog(Image frogPic, double travelDistance, FilledRect road, DrawingCanvas aCanvas) {
		// storing parameters
		leap = travelDistance;
		highway = road;

		// legal frog positions
		legalTop = highway.getY() - leap;
		legalBottom = highway.getY() + highway.getHeight() + leap;
		legalLeft = highway.getX() - FROG_WIDTH;
		legalRight = highway.getX() + highway.getWidth() - FROG_WIDTH;

		// creating a live frog
		startLoc = new Location(startX, startY);
		frogImage = new VisibleImage(frogPic, startLoc, aCanvas);
		alive = true;

		// create and hide "OUCH"
		ouch = new Text("OUCH", startLoc, aCanvas);
		ouch.hide();
	}

	/**
	 * Determine whether or not the Frog overlaps (has come into contact with) a
	 * Vehicle.
	 * 
	 * @param vehicleImage
	 *            the Vehicle we should check against.
	 * 
	 * @return true if the Frog and Vehicle overlap.
	 */
	public boolean overlaps(VisibleImage vehicleImage) {
		// creating the overlaps method
		carImage = vehicleImage;
		return frogImage.overlaps(vehicleImage);

	}

	/**
	 * Kill the Frog. <br>
	 * Stop him from moving and display an "OUCH!" message.
	 */
	public void kill() {
		// Kill the frog if the images overlap
		if (overlaps(carImage)) {
			alive = false;
			ouch.show();
		}
	}

	/**
	 * Bring the frog back to life.
	 * <p>
	 * <ul>
	 * <li>determine whether or not the frog is currently dead
	 * <li>bring him back to life
	 * <li>move him back to the starting place
	 * <li>clear the ouch message
	 * </ul>
	 */
	public void reincarnate() {
		// reincarnate the frog and hide "OUCH"
		frogImage.moveTo(startLoc);
		ouch.hide();
		alive = true;

	}

	/**
	 * Move the Frog one "hop" towards the specified point
	 * <P>
	 * <UL>
	 * <LI>determine point he should hop to
	 * <LI>determine whether or not that hop is legal
	 * <LI>determine whether or not that place overlaps a vehical
	 * 
	 * @param point
	 *            location, indicating relative direction of hop.
	 */
	public void hopToward(Location point) {
		// move the frog forward or to the side depending on the click location
		double x = point.getX();
		double y = point.getY();
		double frogX = frogImage.getX();
		double frogY = frogImage.getY();
		double xOffset = 0;
		double yOffset = 0;
		if (alive) {

			if (x < frogX) {
				xOffset = -leap;
			}
			if (x > frogX + FROG_WIDTH) {
				xOffset = leap;
			}

			if (y < frogY) {
				yOffset = -leap;

			}
			if (y > frogY + FROG_HEIGHT) {
				yOffset = leap;
			}
			if (xOffset != 0) {
				yOffset = 0;

			}
			if ((frogX + xOffset > legalLeft) && (frogX + xOffset < legalRight)) {
				frogImage.move(xOffset, 0);
			}
			if ((frogY + yOffset < legalBottom) && (frogY + yOffset > legalTop)) {
				frogImage.move(0, yOffset);
			}

		}

	}

	/**
	 * Determine whether or not the Frog is still alive.
	 * 
	 * @return true if the Frog is still alive.
	 */
	public boolean isAlive() {
		// return alive if the frog is reincarnated
		return alive;

	}

}
