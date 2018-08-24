import java.awt.Color;
import objectdraw.*;

/* An ActiveObject that creates 3 colored circles
 */

public class Pow extends ActiveObject {
	// canvas size
	private static final int CANVAS_SIZE = 400;

	// oval sizes
	private double redSize = 40;
	private double magentaSize = 20;
	private double yellowSize = 0;

	// pause time
	private int PAUSE = 30;

	// the ovals
	private FilledOval redOval, magentaOval, yellowOval;
	private Boolean exploding;
	
	// canvas
	private DrawingCanvas canvas;

	// save message
	private Text saveMessage;

	/*
	 * Draws three exploding ovals
	 * 
	 * @param ... canvas canvas
	 */
	public Pow(DrawingCanvas aCanvas) {
		// oval locations
		Location redLocation = new Location(180, 180);
		Location magentaLocation = new Location(190, 190);
		Location yellowLocation = new Location(200, 200);
		Location messageLocation = new Location(100, 220);
		canvas = aCanvas;
		
		// creating ovals
		redOval = new FilledOval(redLocation, redSize, redSize, canvas);
		magentaOval = new FilledOval(magentaLocation, magentaSize, magentaSize, canvas);
		yellowOval = new FilledOval(yellowLocation, yellowSize, yellowSize, canvas);
		
		// creating and hiding save message
		saveMessage = new Text("Whew! You saved the world!", messageLocation, canvas);
		saveMessage.hide();
		
		// setting oval colors
		redOval.setColor(Color.RED);
		magentaOval.setColor(Color.MAGENTA);
		yellowOval.setColor(Color.YELLOW);
		
		// boolean for exploding
		exploding = true;
		start();
	}

	/*
	 * Active Object routine for an oval Increases oval size until it reaches bottom
	 */
	public void run() {
		// setting sizeOffset
		double sizeOffset = 2;

		// runs while largest oval location is within canvas
		while ((redOval.getX() > 0) && exploding) {
			// increases each oval size by sizeOffset
			redSize = redSize + sizeOffset;
			yellowSize = yellowSize + sizeOffset;
			magentaSize = magentaSize + sizeOffset;

			// sets new oval size for each
			redOval.setWidth(redSize);
			redOval.setHeight(redSize);
			yellowOval.setWidth(yellowSize);
			yellowOval.setHeight(yellowSize);
			magentaOval.setWidth(magentaSize);
			magentaOval.setHeight(magentaSize);

			// makes ovals centered
			redOval.move(-1, -1);
			magentaOval.move(-1, -1);
			yellowOval.move(-1, -1);
			pause(PAUSE);
		}

		if (!exploding) {
			// loops until red oval reaches window bottom
			while (redSize > 0) {
				// decreases each oval size by sizeOffset
				redSize = redSize - sizeOffset;
				yellowSize = yellowSize - sizeOffset;
				magentaSize = magentaSize - sizeOffset;

				// sets new oval size for each
				redOval.setWidth(redSize);
				redOval.setHeight(redSize);
				yellowOval.setWidth(yellowSize);
				yellowOval.setHeight(yellowSize);
				magentaOval.setWidth(magentaSize);
				magentaOval.setHeight(magentaSize);

				// makes ovals centered
				redOval.move(1, 1);
				magentaOval.move(1, 1);
				yellowOval.move(1, 1);
				pause(PAUSE);
			}
			saveMessage.show();
			
		// makes canvas black after explosion
		} else {
			FilledRect dead = new FilledRect(0, 0, CANVAS_SIZE, CANVAS_SIZE, canvas);
			dead.setColor(Color.BLACK);
		}
		redOval.removeFromCanvas();
		magentaOval.removeFromCanvas();
		yellowOval.removeFromCanvas();
	}

	// returns not exploding
	public boolean reverseExplosion() {
		return exploding = !exploding;
	}
}
