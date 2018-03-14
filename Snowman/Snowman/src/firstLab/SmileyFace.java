package firstLab;

import objectdraw.*;
import java.awt.*;

public class SmileyFace {

	private static final double SIZE = 100;
	private static final double faceHeight = SIZE;
	private static final double faceWidth = SIZE;

	private static final double eyeWidth = 0.2 * SIZE;
	private static final double eyePatchWidth =0.2 * SIZE;
	private static final double eyeHeight = 0.2 * SIZE;
	private static final double eyePatchHeight = 0.2 * SIZE;
	private static final double eyeBall = 10;

	private static final double mouthWidth = 0.5 * SIZE;
	private static final double mouthHeight = 0.1 * SIZE;

	private FramedOval face, eyeLine1, eyeLine2, mouth;
	private FilledOval eye1, eye2;
	private Line rope;

	public SmileyFace(double x, double y, DrawingCanvas aCanvas) {

		// Just testing intro to classes
		face = new FramedOval(x, y,  faceWidth, faceHeight, aCanvas);
		eyeLine1 = new FramedOval(x + 20, y + 30, eyeWidth, eyeHeight, aCanvas);
		eyeLine2 = new FramedOval(x + 60, y + 30, eyeWidth, eyeHeight, aCanvas);
		mouth = new FramedOval(x + 25, y + 65, mouthWidth, mouthHeight, aCanvas);
		eye1 = new FilledOval(x + 20, y + 30, eyePatchWidth, eyePatchHeight, aCanvas);
		eye2 = new FilledOval(x + 65, y + 35, eyeBall, eyeBall, aCanvas);
		rope = new Line(x + 1, y + 55, faceWidth , faceHeight * 0.3, aCanvas);

		eye1.setColor(Color.BLACK);
		eye2.setColor(Color.BLACK);
	}

	public void move(double x, double y) {
		face.move(x, y);
		eyeLine1.move(x, y);
		eyeLine2.move(x, y);
		eye1.move(x, y);
		eye2.move(x, y);
		mouth.move(x, y);
		rope.move(x, y);
	}

	public boolean contains(Location point) {
		return face.contains(point);
	}

	public void moveTo(double x, double y) {
		this.move(x - face.getX(), y - face.getY());
	}
}
