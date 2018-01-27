import objectdraw.*;
import java.awt.*;

/**
 * Lab - Dirty Laundry (rectangles, colors, and drag/drop)
 * <p>
 * A game in which the player must decide if an item is "white", "dark" or
 * "colored", and drag it to the appropriate basket.
 * <p>
 * Suggested window size: 400x200
 *
 * @author HUSSEIN FAARA, JAVA
 */
public class LaundrySorterApplet extends WindowController {
	// TODO DESIGN - constants for where objects will be in window
	private static final int BASKETORIGIN_Y = 40;
	private static final int WHITEBASKET_X = 40;
	private static final int DARKBASKET_X = 150;
	private static final int COLORBASKET_X = 260;
	private static final int BASKETWIDTH = 75;
	private static final int BASKETHEIGHT = 50;
	private static final int LAUNDRYBASKET_X = 150;
	private static final int LAUNDRYBASKET_Y = BASKETORIGIN_Y + BASKETHEIGHT + 25;

	// TODO DESIGN - declarations for instance variables
	private FramedRect whiteBasket;
	private FramedRect darkBasket;
	private FramedRect colorBasket;
	private FramedRect laundryBasket;
	private FilledRect laundryColor;
	private FramedRect correctBasket;
	private RandomIntGenerator generator = new RandomIntGenerator(0, 255);
	private int colorIndex;
	private Location lastPoint;
	private Text correct;
	private Text incorrect;
	private int correctScore;
	private int incorrectScore;
	private int redNum;
	private int greenNum;
	private int blueNum;
	private Color newColor;
	private double movingBasket_X;
	private double movingBasket_Y;

	/**
	 * Initialization method, called when applet starts
	 */
	public void begin() {
		// TODO DESIGN - what objects you will create here
		whiteBasket = new FramedRect(WHITEBASKET_X, BASKETORIGIN_Y, BASKETWIDTH, BASKETHEIGHT, canvas);
		darkBasket = new FramedRect(DARKBASKET_X, BASKETORIGIN_Y, BASKETWIDTH, BASKETHEIGHT, canvas);
		colorBasket = new FramedRect(COLORBASKET_X, BASKETORIGIN_Y, BASKETWIDTH, BASKETHEIGHT, canvas);
		new Text("White", WHITEBASKET_X + 20, BASKETORIGIN_Y + 18, canvas);
		new Text("Dark", DARKBASKET_X + 22, BASKETORIGIN_Y + 18, canvas);
		new Text("Color", COLORBASKET_X + 22, BASKETORIGIN_Y + 18, canvas);
		laundryBasket = new FramedRect(LAUNDRYBASKET_X, LAUNDRYBASKET_Y, BASKETWIDTH, BASKETHEIGHT, canvas);
		laundryColor = new FilledRect(LAUNDRYBASKET_X + 1, LAUNDRYBASKET_Y + 1, BASKETWIDTH - 1, BASKETHEIGHT - 1,
				canvas);
		new Text("Laundry", DARKBASKET_X + 10, 10, canvas);
		correctBasket = darkBasket;
		correct = new Text(" Correct = 0 ", WHITEBASKET_X, LAUNDRYBASKET_Y + 20, canvas);
		incorrect = new Text("Incorrect = 0 ", COLORBASKET_X, LAUNDRYBASKET_Y + 20, canvas);
		correctScore = 0;
		incorrectScore = 0;
	}

	/**
	 * Event handler, called when mouse button is "clicked" (pressed and released)
	 *
	 * In part 1 of the assignment, check to see if the chosen box is the right one
	 * for the current color. If correct, choose a new color.
	 *
	 * @param point
	 *            mouse coordinates at time of click
	 */
	public void onMouseClick(Location point) {
		// TODO DESIGN - what you will do in this method
	}

	/**
	 * Event handler, called when mouse button is pressed (and held)
	 * 
	 * In part 2 of the assignment, see if the user has picked up the current color
	 * swatch.
	 *
	 * @param point
	 *            mouse coordinates at time of press
	 */
	public void onMousePress(Location point) {
		// TODO DESIGN-2 - what will you do in this method
		lastPoint = point;
	}

	/**
	 * Event handler, called (periodically) when mouse is moved w/button held
	 * 
	 * In part 2 of the assignment, if the user has picked up the color swatch, drag
	 * it.
	 *
	 * @param point
	 *            mouse coordinates after recent motion
	 */
	public void onMouseDrag(Location point) {
		if (laundryBasket.contains(lastPoint)) {
			movingBasket_X = (point.getX() - lastPoint.getX());
			movingBasket_Y = (point.getY() - lastPoint.getY());
			laundryBasket.move(movingBasket_X, movingBasket_Y);
			laundryColor.move(movingBasket_X, movingBasket_Y);
			lastPoint = point;
		}
	}
	// TODO DESIGN-2 - what will you do in this method

	/**
	 * Event handler, called when mouse button is released
	 * 
	 * In part 2 of the assignment, see if the color swatch was dragged to the right
	 * basket, and update the score. If correct, choose a new color.
	 *
	 * @param point
	 *            mouse coordinates at time of release
	 */
	public void onMouseRelease(Location point) {
		if (laundryBasket.contains(lastPoint)) {
			if (correctBasket.contains(point)) {
				redNum = generator.nextValue();
				greenNum = generator.nextValue();
				blueNum = generator.nextValue();
				newColor = new Color(redNum, greenNum, blueNum);
				colorIndex = redNum + greenNum + blueNum;
				if (colorIndex <= 230) {
					correctBasket = darkBasket;
					laundryColor.setColor(newColor);
				} else if (colorIndex >= 600) {
					correctBasket = whiteBasket;
					laundryColor.setColor(newColor);
				} else if (colorIndex > 230 && colorIndex < 600) {
					correctBasket = colorBasket;
					laundryColor.setColor(newColor);
				}

				correctScore = correctScore + 1;
				correct.setText("Correct = " + correctScore);

			} else if (whiteBasket.contains(point) || darkBasket.contains(point) || colorBasket.contains(point)) {
				incorrectScore = incorrectScore + 1;
				incorrect.setText(" Incorrect = " + incorrectScore);
			}
		}
		laundryBasket.moveTo(LAUNDRYBASKET_X, LAUNDRYBASKET_Y);
		laundryColor.moveTo(LAUNDRYBASKET_X + 1, LAUNDRYBASKET_Y + 1);

		// TODO DESIGN-2 - what will you do in this method
	}
}
