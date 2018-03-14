import objectdraw.*;
import java.awt.*;

/**
 * this class represents a balloon
 * <P>
 * Each Balloon is aware of its environment, e.g. the lights. A balloon's color
 * is determined by the color and distance of the lights.
 * </P>
 * 
 * @author HUSSEIN FAARA, D'ANGELO DAVIS, JAVA
 */

public class Balloon {
	/* constants for the size of different components of a balloon. */
	// Sets dimensions for balloon
	private static final double BALLOON_WIDTH = 80;
	private static final double BALLOON_HEIGHT = 140;
	// Sets the length of the tail
	private static final double TAIL_LENGTH = 180;
	// Balloon's body
	private FilledOval balloon;
	// Center of the balloon
	private Location centerLocation;
	// The 3 lights
	private Light redLight;
	private Light greenLight;
	private Light blueLight;

	/**
	 * construct an instance of a balloon
	 * 
	 * @param location
	 *            ... where balloon should be placed
	 * @param canvas
	 *            ... canvas on which it is drawn
	 */
	public Balloon(Location loc, Light light1, Light light2, Light light3, DrawingCanvas canvas) {
		// Location of the balloon's tail
		Location tailLoc = new Location(loc.getX() + 0.5 * BALLOON_WIDTH, loc.getY() + BALLOON_HEIGHT);
		// Creates balloon
		balloon = new FilledOval(loc, BALLOON_WIDTH, BALLOON_HEIGHT, canvas);
		// Creates balloon's tail
		new Line(tailLoc.getX(), tailLoc.getY(), tailLoc.getX(), tailLoc.getY() + TAIL_LENGTH, canvas);
		// Calculates balloon's center location and stores it
		centerLocation = new Location(loc.getX() + 0.5 * BALLOON_WIDTH, loc.getY() + 0.5 * BALLOON_HEIGHT);
		// Stores all 3 light parameters
		redLight = light1;
		greenLight = light2;
		blueLight = light3;
	}

	/**
	 * returns the location of the center of the balloon.
	 * 
	 * @return the center location.
	 */
	public Location getCenter() {
		// Returns correct center location of balloon
		return centerLocation;
	}

	/**
	 * set the color of the balloon based on the color and distance of the lights.
	 *
	 * the color of the balloon depends on the how far each light is. If a light is
	 * in range, its impact is determined by the distance.
	 */
	public void setColor() {
		// Local variables to store R,G,B light components
		int R = 0;
		int G = 0;
		int B = 0;
		// Computes distance between the balloon and red light
		double distance = getCenter().distanceTo(redLight.getCenter());
		// Gets effective distance of light
		double effective = redLight.getEffectiveDist();
		// Gets and stores color of light
		Color color = redLight.getColor();
		// Computes effective brightness of red light bulb if
		// its distance is less than its effective distance
		if (distance < effective) {
			R = (int) ((1 - (distance / effective)) * color.getRed());
			G = (int) ((1 - (distance / effective)) * color.getGreen());
			B = (int) ((1 - (distance / effective)) * color.getBlue());
		}
		// Computes distance between balloon and green light
		distance = getCenter().distanceTo(greenLight.getCenter());
		// Gets effective distance of light
		effective = greenLight.getEffectiveDist();
		// Gets and stores color of light
		color = greenLight.getColor();
		// Computes effective brightness of green light bulb if
		// its distance is less than its effective distance
		if (distance < effective) {
			R = R + (int) ((1 - (distance / effective)) * color.getRed());
			G = G + (int) ((1 - (distance / effective)) * color.getGreen());
			B = B + (int) ((1 - (distance / effective)) * color.getBlue());
		}
		// Computes distance between balloon and blue light
		distance = getCenter().distanceTo(blueLight.getCenter());
		// Gets effective distance of light
		effective = blueLight.getEffectiveDist();
		// Gets and stores color of light
		color = blueLight.getColor();
		// Computes effective brightness of blue light bulb if
		// its distance is less than its effective distance
		if (distance < effective) {
			R = R + (int) ((1 - (distance / effective)) * color.getRed());
			G = G + (int) ((1 - (distance / effective)) * color.getGreen());
			B = B + (int) ((1 - (distance / effective)) * color.getBlue());
		}
		// construct the color and set the body of the balloon to this color.
		color = new Color(Math.min(R, 255), Math.min(G, 255), Math.min(B, 255));
		balloon.setColor(color);
	}
}
