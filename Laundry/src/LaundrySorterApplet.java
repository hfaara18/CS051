import objectdraw.*;
import java.awt.*;

/**
 * <p>
 * A game in which the player must decide if an item is "white", "dark" or
 * "colored", and drag it to the appropriate basket.
 * <p>
 *
 * @author HUSSEIN FAARA, JAVA
 */
public class LaundrySorterApplet extends WindowController {
	/**
	 * Constant variables to contain location of objects for easy reference and to
	 * position other objects relative to them.
	 */
	// A common Y-location for the 3 baskets.
	private static final int BASKETORIGIN_Y = 40;
	// Other locations for baskets.
	private static final int WHITEBASKET_X = 40;
	private static final int DARKBASKET_X = 150;
	private static final int COLORBASKET_X = 260;
	// a common height and width for size uniformity.
	private static final int BASKETWIDTH = 75;
	private static final int BASKETHEIGHT = 50;
	// Initial location of laundry basket.
	private static final int LAUNDRYBASKET_X = 150;
	private static final int LAUNDRYBASKET_Y = BASKETORIGIN_Y + BASKETHEIGHT + 25;
	// Figures that will represent baskets.
	private FramedRect whiteBasket;
	private FramedRect darkBasket;
	private FramedRect colorBasket;
	// The laundry box/ basket frame so that colors can be
	// easily seen even when on a similar background.
	private FramedRect laundryBasket;
	// A solid rectangle for easy manipulation of laundry
	// coloring.
	private FilledRect laundryColor;
	// A random integer generator, which produces a random
	// number from 0 to 255.
	private RandomIntGenerator generator = new RandomIntGenerator(0, 255);
	// Last known location of the mouse.
	private Location lastPoint;
	// Randomly generated integers from 0-255.
	private int redNum;
	private int greenNum;
	private int blueNum;
	// A changing correct value for laundry.
	private FramedRect correctBasket;
	// Text to show score.
	private Text correct;
	private Text incorrect;
	// Scores.
	private int correctScore;
	private int incorrectScore;
	// Sum of generated random numbers.
	private int colorIndex;
	private double movingBasket_X;
	private double movingBasket_Y;
	private Color newColor;

	/**
	 * Initialization method, called when applet starts
	 */
	public void begin() {
		// Initializes objects and texts.
		whiteBasket = new FramedRect(WHITEBASKET_X, BASKETORIGIN_Y, BASKETWIDTH, BASKETHEIGHT, canvas);
		darkBasket = new FramedRect(DARKBASKET_X, BASKETORIGIN_Y, BASKETWIDTH, BASKETHEIGHT, canvas);
		colorBasket = new FramedRect(COLORBASKET_X, BASKETORIGIN_Y, BASKETWIDTH, BASKETHEIGHT, canvas);
		laundryBasket = new FramedRect(LAUNDRYBASKET_X, LAUNDRYBASKET_Y, BASKETWIDTH, BASKETHEIGHT, canvas);
		laundryColor = new FilledRect(LAUNDRYBASKET_X + 1, LAUNDRYBASKET_Y + 1, BASKETWIDTH - 1, BASKETHEIGHT - 1,
				canvas);
		new Text("White", WHITEBASKET_X + 20, BASKETORIGIN_Y + 18, canvas);
		new Text("Dark", DARKBASKET_X + 22, BASKETORIGIN_Y + 18, canvas);
		new Text("Color", COLORBASKET_X + 22, BASKETORIGIN_Y + 18, canvas);
		new Text("Laundry", DARKBASKET_X + 10, 10, canvas);
		correct = new Text(" Correct = 0 ", WHITEBASKET_X, LAUNDRYBASKET_Y + 20, canvas);
		incorrect = new Text("Incorrect = 0 ", COLORBASKET_X, LAUNDRYBASKET_Y + 20, canvas);
		// Initializes "correctBasket" so that it can be changed afterwards.
		correctBasket = darkBasket;
		// Initializes scores.
		correctScore = 0;
		incorrectScore = 0;
	}

	/**
	 * Event handler, called when mouse button is pressed (and held) and notes mouse
	 * coordinates at time of press
	 */
	public void onMousePress(Location point) {
		// Stores the location of the mouse pointer when mouse button is pressed.
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
		// Moves the laundry when it is dragged.
		if (laundryBasket.contains(lastPoint)) {
			movingBasket_X = (point.getX() - lastPoint.getX());
			movingBasket_Y = (point.getY() - lastPoint.getY());
			laundryBasket.move(movingBasket_X, movingBasket_Y);
			laundryColor.move(movingBasket_X, movingBasket_Y);
			lastPoint = point;
		}
	}

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
				// RandomIntGenerator generates a random number every time it is called with the
				// ".nextValue()" method, hence generator.nextValue(). The generated numbers are
				// then assigned to variables so that they are not lost.
				redNum = generator.nextValue();
				greenNum = generator.nextValue();
				blueNum = generator.nextValue();
				// Sets different colors for laundry and a correct box for each color
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
				// Increases correct score and updates text if player drops laundry in correct
				// basket.
				correctScore = correctScore + 1;
				correct.setText("Correct = " + correctScore);
				// Specifies incorrect locations so that no scores are updated if
				// player drops laundry any other place in the window.
			} else if (whiteBasket.contains(point) || darkBasket.contains(point) || colorBasket.contains(point)) {
				// Increases incorrect score and updates text if player drops in incorrect
				// basket
				incorrectScore = incorrectScore + 1;
				incorrect.setText(" Incorrect = " + incorrectScore);
			}
		}
		// Moves laundry basket to its initial position after the mouse has been
		// released.
		laundryBasket.moveTo(LAUNDRYBASKET_X, LAUNDRYBASKET_Y);
		laundryColor.moveTo(LAUNDRYBASKET_X + 1, LAUNDRYBASKET_Y + 1);
	}
}
