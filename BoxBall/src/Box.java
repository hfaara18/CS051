import objectdraw.*;
import java.awt.*;

/**
 * a box at a randomly chosen position (somewhere at the bottom of the playing
 * area) into which the player attempts to drop balls.
 */
public class Box {
	// Variable for box
	private FilledRect box;

	// variables for acceptable X and Y positions
	private double minX;
	private double maxX;
	private double locY;

	// size of the box
	private double width;

	// a random integer generator
	private RandomIntGenerator boxPosition = new RandomIntGenerator(0, 600);

	/**
	 * create a box at the bottom of the game
	 *
	 * @param 
	 * 			initial width
	 * @param 
	 * 			height
	 * @param 
	 * 			 playing field
	 */
	public Box(double width, double height, FramedRect playingArea) {
		this.width = width;

		// Retrieves and stores max and minimum X positions and Y position
		minX = playingArea.getX();
		maxX = playingArea.getX() + playingArea.getWidth();
		locY = playingArea.getY() + playingArea.getHeight() - height;

		// creates box
		box = new FilledRect(maxX - minX, locY, width, height, playingArea.getCanvas());
	}

	/**
	 * Choose a new (random) location for the box, and move it there.
	 *
	 * But ... we must make sure the chosen location is legal: the left edge of the
	 * box must be within the playing area the right edge of the box must be within
	 * the playing area
	 */
	public void moveBox() {
		double boxLocation = boxPosition.nextValue();
		if (boxLocation > maxX - box.getWidth()) {
			boxLocation = Math.min(boxLocation, maxX - box.getWidth());
		} else if (boxLocation < minX) {
			boxLocation = Math.max(boxLocation, minX);
		}
		box.moveTo(boxLocation, locY);
	}

	/**
	 * set the width of the box to change the difficulty (smaller boxes are harder
	 * to hit).
	 *
	 * @param new
	 *            desired box width
	 */
	public void setSize(double width) {
		// changes box width with difficulty and moves it to random location
		this.width = width;
		box.setWidth(width);
		moveBox();
	}

	/**
	 * X position of the left edge of the box (used to determine whether or not ball
	 * fell in box)
	 *
	 * @return x position of the left edge of the box
	 */
	public double getLeft() {
		return box.getX();
	}

	/**
	 * X position of the right edge of the box (used to determine whether or not
	 * ball fell in box)
	 *
	 * @return x position of the right edge of the box
	 */
	public double getRight() {
		return box.getX() + width;
	}

	// returns Y position of left edge of the box
	public double getY() {
		return box.getY();
	}

	// returns box height
	public double getHeight() {
		return box.getHeight();
	}
}
