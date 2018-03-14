import objectdraw.*;
import java.awt.*;

/**
 * A Ball is an active object that falls at a constant speed.
 *
 * When it reaches the bottom, it determines whether or not it fell in the box
 * and displays an appropriate message. If the ball falls into the box, we
 * instruct the box to move to a new position.
 */
public class Ball extends ActiveObject {

	// constants for smooth ball motion
	private static final int SPEED = 10;
	private static final int PAUSE = 50;

	// constants for hits and misses
	private static int hits = 0;
	private static int misses = 0;

	// ball, its initial location and size
	private FilledOval ball;
	private double ballX;
	private double ballY;
	private double diameter;

	// box and its bottom Y location
	private Box box;
	private double bottom;

	// texts to show player
	private Text displayText, hitsDisplay, missesDisplay;

	/**
	 * Creates a falling ball
	 *
	 * @param
	 * 			starting position
	 * @param 
	 * 			diameter
	 * @param 
	 * 			target box
	 * @param 
	 * 			message text
	 * @param 
	 * 			hits text
	 * @param 
	 * 			misses text
	 * @param
	 * 			 canvas
	 */
	public Ball(Location ballLocation, double diameter, Box box, Text disp, Text hits, Text misses,
			DrawingCanvas aCanvas) {
		// Ball initial location
		ballX = ballLocation.getX() - diameter / 2;
		ballY = ballLocation.getY() - diameter / 2;
		this.diameter = diameter;
		this.box = box;

		// Texts
		displayText = disp;
		hitsDisplay = hits;
		missesDisplay = misses;

		// Retrieves bottom Y location of box
		bottom = (box.getY() + box.getHeight());

		// create a ball at the specified starting position
		ball = new FilledOval(ballX, ballY, diameter, diameter, aCanvas);

		// start up the thread that moves the ball
		start();
	}

	/**
	 * thread routine for the falling ball
	 * <P>
	 * Slowly move it downwards until it reaches the bottom of the playing area, and
	 * then determine whether or not it fell within the box. Set the message text
	 * accordingly, and then instruct the box to move to a new position.
	 */

	public void run() {
		// moves ball a small distance downwards and
		// then pause milliseconds before moving it further
		while (ballY < (bottom)) {
			ballY = ballY + SPEED;
			ball.moveTo(ballX, ballY);
			pause(PAUSE);
		}
		ball.removeFromCanvas();

		// Determines if ball fell within the box and updates message,
		// hits, and misses texts. Moves the box to a new location afterwards
		if (ball.getX() >= box.getLeft() && getRight() <= box.getRight()) {
			displayText.setText("You got in!");
			hits = hits + 1;
			hitsDisplay.setText("Hits: " + hits);
			hitsDisplay.sendToFront();
			box.moveBox();
		} else {
			displayText.setText("Try again!");
			misses = misses + 1;
			missesDisplay.setText("Misses: " + misses);
			missesDisplay.sendToFront();
		}
	}

	public void sendToBack() {
		ball.sendToBack();
	}

	// gets ball's right edge's X position
	public double getRight() {
		return ball.getX() + diameter;
	}
}
