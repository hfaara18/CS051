import objectdraw.*;
import java.applet.*;
/**
 * Test Program 1b - SKiBall
 *
 * A game that awards player points depending 
 * on how small the circle in which player's ball lands
 *
 * @author Hussein Faara, CS051J
 */

public class SkiBall extends WindowController {

	// Location of the center of the concentric circles
	private static final Location CENTER = new Location(200, 200);

	// Diameters of different scoring areas
	private static final double BIGGEST_DIAMETER = 150;
	private static final double BIG_DIAMETER = 100;
	private static final double MIDDLE_DIAMETER = 50;
	private static final double TOP_DIAMETER = 25;
	
	// alloted scores for each correct shot
	private static final int SMALL_SCORE = 10;
	private static final int MEDIUM_SCORE = 20;
	private static final int GOOD_SCORE = 30;
	private static final int GREAT_SCORE = 50;
	
	// increment factor for ball movement
	private static final double POWER = 4;

	// y-coordinate of horizontal line
	private static final double LINE_Y = 500;

	// radius of ball
	private static final double BALL_RADIUS = 10;

	// the circles
	private FramedOval middleOval, bigOval, biggestOval, topOval;
	
	// the ball and its line
	private Line line;
	private FilledOval ball;
	
	// messages and the total score
	private Text displayText, totalText;
	private int total;
	
	// the start coordinates of the line
	private Location lineStart;
	
	// the location of the display and total texts
	private Location displayTextLocation, totalTextLocation;
	
	// audio to be played
	private AudioClip bloop, ding, punch, splat;

	/**
	 * Initialization method, called when applet starts.
	 * <ul>
	 * <li>generates the circles and foul line
	 * <li> Initializes texts and total score
	 * <li> retrieves audio from file
	 * </ul>
	 */
	public void begin() {
		// creates circles when game starts
		middleOval = new FramedOval(CENTER.getX() - MIDDLE_DIAMETER / 2,
				CENTER.getY() - MIDDLE_DIAMETER / 2, MIDDLE_DIAMETER, 
				MIDDLE_DIAMETER, canvas);
		bigOval = new FramedOval(CENTER.getX() - BIG_DIAMETER / 2, 
				CENTER.getY() - BIG_DIAMETER / 2, BIG_DIAMETER,
				BIG_DIAMETER, canvas);
		biggestOval = new FramedOval(CENTER.getX() - BIGGEST_DIAMETER / 2,
				CENTER.getY() - BIGGEST_DIAMETER / 2,
				BIGGEST_DIAMETER, BIGGEST_DIAMETER, canvas);
		topOval = new FramedOval(CENTER.getX() - TOP_DIAMETER / 2, 
				CENTER.getY() - (TOP_DIAMETER + BIG_DIAMETER / 2),
				TOP_DIAMETER, TOP_DIAMETER, canvas);
		
		// creates a foul line
		new Line(0, LINE_Y, canvas.getWidth(), LINE_Y, canvas);
		
		// Locations of texts to be displayed
		displayTextLocation = new Location(170, 450);
		totalTextLocation = new Location(170, 470);
		
		// message to player and total score texts
		displayText = new Text("Drag To Shoot",displayTextLocation, canvas);
		totalText = new Text("Total Score : " + 0 , totalTextLocation, canvas);
		
		// initializing total score and retrieving audio from file
		total = 0;
		ding = getAudio("ding.wav");
		bloop = getAudio("bloop.wav");
		punch = getAudio("punch.wav");
		splat = getAudio("splat.wav");
	}

	/**
	 * Event handler, called when mouse is pressed
	 * 
	 * Creates a ball on foul line and a line behind it
	 * 
	 * @param point 
	 * 			Mouse coordinates at time of press 
	 */
	public void onMousePress(Location point) {
		
		// creates ball on foul line and line behind it
		ball = new FilledOval(point.getX() - BALL_RADIUS / 2,
				LINE_Y - BALL_RADIUS / 2, BALL_RADIUS, BALL_RADIUS,
				canvas);
		line = new Line(point.getX(), LINE_Y, point.getX(),
				point.getY(), canvas);
	}

	/**
	 *Event handler, called when mouse is dragged
	 * 
	 * Changes the end of ball's the line
	 * 
	 * @param point 
	 * 			Mouse coordinates at time of drag 
	 */
	public void onMouseDrag(Location point) {
		// sets mouse end coordinates to that of the mouse pointer
		line.setEnd(point);
	}
	
	/**
	 * Event handler, called when mouse press is released
	 * 
	 * Moves ball to a new location and removes its line 
	 * from canvas
	 * Updates display message and total scores
	 * 
	 * @param point 
	 * 			Mouse coordinates at time of release 
	 */
	public void onMouseRelease(Location point) {
		// each shot's score and coordinates of line 
		//start position
		int score = 0;
		lineStart = line.getStart();
		
		// moves ball and removes line from canvas
		ball.move(POWER * (lineStart.getX() - point.getX()), 
				POWER * (lineStart.getY() - point.getY()));
		line.removeFromCanvas();
		
		// location of ball's center and the different scores
		Location ballCenter = new Location(ball.getX() 
				+ BALL_RADIUS / 2, ball.getY() + BALL_RADIUS / 2);		
		
		// sets score and plays a sound depending on ball's final location
		if (biggestOval.contains(ballCenter)) {
			if (bigOval.contains(ballCenter)) {
				if (middleOval.contains(ballCenter)) {
					score = GOOD_SCORE;
					bloop.play();
				} else {
					score = MEDIUM_SCORE;
					punch.play();
				}
			} else if (topOval.contains(ballCenter)) {
				score = GREAT_SCORE;
				ding.play();
			} else {
				score = SMALL_SCORE;
				splat.play();
			}
		}
		
		// updates display text and total score after each shoot
		displayText.setText("You scored " + score + " points!");
		total = total + score;
		totalText.setText("Total Score : " + total + " points!");
		
	}
}
