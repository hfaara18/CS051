import objectdraw.*;
import java.awt.*;

/**
 * Lab - Box Ball
 * <p>
 * a game in which players attempt to drop balls into a small box
 * <p>
 *
 * Suggested window size: 600x600
 *
 * @author HUSSEIN FAARA, JAVA
 */
public class Boxball extends WindowController {
	// constants for the play area and button positions and sizes
	private static final int FIELD_WIDTH = 400;
	private static final int FIELD_HEIGHT = 400;
	private static final int EASY_X = 150;
	private static final int MEDIUM_X = EASY_X + 100;
	private static final int HARD_X = MEDIUM_X + 100;
	private static final int SETTING_Y = 500;
	private static final int DIFFICULTY_WIDTH = 75;
	private static final int DIFFICIULTY_HEIGHT = 50;

	// constant for ball size
	private static final int BALL_WIDTH = 20;

	// constants for field, line, and heading location and sizes
	private static final double FIELD_X = 100;
	private static final double FIELD_Y = 50;
	private static final double LINE_Y = FIELD_Y + 280;
	private static final double WHITE_SHIELD_Y = FIELD_Y + FIELD_HEIGHT + 1;
	private static final double WHITE_SHIELD_HEIGHT = SETTING_Y - WHITE_SHIELD_Y;
	private static final double HEADER_X = 250;
	private static final double HEADER_Y = 10;

	// Variables for button, box and text locations
	private Location easyTextLocation = new Location(EASY_X + 25, SETTING_Y + 20);
	private Location mediumTextLocation = new Location(MEDIUM_X + 15, SETTING_Y + 20);
	private Location hardTextLocation = new Location(HARD_X + 25, SETTING_Y + 20);
	private Location boxLocation = new Location(50, 50);
	private Location messageLocation = new Location(260, 470);
	private Location hitsLocation = new Location(150, 470);
	private Location missesLocation = new Location(400, 470);

	// variables for difficulty buttons, playing field, text, and box
	private FilledRect easyBox;
	private FilledRect mediumBox;
	private FilledRect hardBox, whiteShield;
	private FramedRect playingField, correctBox;
	private Text easyText, mediumText, hardText;
	private Text message, hits, misses;
	private Line line;
	private Box box;

	/**
	 * When program starts, draws buttons, box and playing field
	 */
	public void begin() {
		// Creates a playing field for the game
		playingField = new FramedRect(FIELD_X, FIELD_Y, FIELD_WIDTH, FIELD_HEIGHT, canvas);

		// Creates buttons with difficulties written on each
		easyBox = new FilledRect(EASY_X, SETTING_Y, DIFFICULTY_WIDTH, DIFFICIULTY_HEIGHT, canvas);
		easyText = new Text("Easy", easyTextLocation, canvas);
		mediumBox = new FilledRect(MEDIUM_X, SETTING_Y, DIFFICULTY_WIDTH, DIFFICIULTY_HEIGHT, canvas);
		mediumText = new Text("Medium", mediumTextLocation, canvas);
		hardBox = new FilledRect(HARD_X, SETTING_Y, DIFFICULTY_WIDTH, DIFFICIULTY_HEIGHT, canvas);
		hardText = new Text("Hard", hardTextLocation, canvas);

		// Creates a line to restrict playing area
		line = new Line(FIELD_X, LINE_Y, FIELD_X + FIELD_WIDTH, LINE_Y, canvas);

		// Creates box
		box = new Box(boxLocation.getX(), boxLocation.getY(), playingField);

		// Creates shield to hide falling ball below bottom of field
		whiteShield = new FilledRect(0, WHITE_SHIELD_Y, canvas.getWidth(), WHITE_SHIELD_HEIGHT, canvas);

		// A correct area to click for ball and a heading
		correctBox = new FramedRect(FIELD_X, FIELD_Y, FIELD_WIDTH, LINE_Y - FIELD_Y, canvas);
		new Text("Boxball", HEADER_X, HEADER_Y, canvas);

		// Creates messages which change to reflect player's performance
		message = new Text("Let's play!", messageLocation, canvas);
		hits = new Text("Hits: 0", hitsLocation, canvas);
		misses = new Text("Misses: 0 ", missesLocation, canvas);

		// Sets colors for buttons and shield.
		easyBox.setColor(Color.GREEN);
		mediumBox.setColor(Color.YELLOW);
		hardBox.setColor(Color.RED);
		whiteShield.setColor(Color.WHITE);
	}

	/**
	 * Event handler, called when mouse button is "clicked"
	 * <P>
	 * If click is on a difficulty button, change the box size, and the height from
	 * which balls drop.
	 * <P>
	 * If click was in arena, create a new ball, starting at that X position, from
	 * the difficulty-height.
	 *
	 * @param point
	 *            mouse coordinates at time of click
	 */
	public void onMouseClick(Location point) {
		// constants for allowed playing field border
		double easyY = LINE_Y;
		double mediumY = LINE_Y - 100;
		double hardY = LINE_Y - 200;

		// Sets field restrictions selected difficulty and assigns designated
		// area to "correctBox"
		if (easyBox.contains(point) || easyText.contains(point)) {
			line.setStart(FIELD_X, easyY);
			line.setEnd(FIELD_X + FIELD_WIDTH, easyY);
			box.setSize(50);
			correctBox.setHeight(easyY - FIELD_X / 2);
		} else if (mediumBox.contains(point) || mediumText.contains(point)) {
			line.setStart(FIELD_X, mediumY);
			line.setEnd(FIELD_X + FIELD_WIDTH, mediumY);
			box.setSize(40);
			correctBox.setHeight(mediumY - FIELD_X / 2);
		} else if (hardBox.contains(point) || hardText.contains(point)) {
			line.setStart(FIELD_X, hardY);
			line.setEnd(FIELD_X + FIELD_WIDTH, hardY);
			box.setSize(30);
			correctBox.setHeight(hardY - FIELD_X / 2);
		}
		// Creates a ball of player clicks within designated area and
		// brings test and messages to front
		if (correctBox.contains(point)) {
			new Ball(point, BALL_WIDTH, box, message, hits, misses, canvas);
			whiteShield.sendToFront();
			message.sendToFront();
			hits.sendToFront();
			misses.sendToFront();
		}
	}
}
