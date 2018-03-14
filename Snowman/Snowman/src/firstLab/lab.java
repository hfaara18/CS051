package firstLab;

import objectdraw.*;
import java.awt.*;



public class lab extends WindowController {
	private Location lastPoint;
	private SmileyFace smiley;
	private boolean dragging;
	private Snowman kurt;

	/**
	 * Initialization method, called when program begins to execute
	 */
	public void begin() {
		smiley = new SmileyFace(20, 20, canvas);
		kurt = new Snowman(150, 150, canvas);

	}

	public void onMousePress(Location point) {
		dragging = smiley.contains(point);
		lastPoint = point;
	}

	public void onMouseDrag(Location point) {
		if (dragging) {
			smiley.move(point.getX() - lastPoint.getX(), point.getY() - lastPoint.getY());
			lastPoint = point;
		}
	}
}
