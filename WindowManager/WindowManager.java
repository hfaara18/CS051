/**
 * Applet that creates a window and allows user to resize and drag it.
 *
 *	@author Hussein Faara, CS051J
 */

import objectdraw.*;

public class WindowManager extends WindowController {
	// Window starting location
	private static final Location WINDOW_LOCN = new Location(50, 80);

	private static final double WINDOW_WIDTH = 120; // window starting width
	private static final double WINDOW_HEIGHT = 120; // window starting height

	private Window testWindow; // window to be displayed
	private Location lastPoint; // where mouse was before a drag
	private boolean resizing, // whether user dragging in resize box
			dragging; // whether user dragging in title bar

	// Create window on canvas
	public void begin() {
		testWindow = new Window(WINDOW_LOCN, WINDOW_WIDTH, WINDOW_HEIGHT,
				canvas);
	}

	// Remember whether user pressed mouse in resize box or title bar
	public void onMousePress(Location point) {
		if (testWindow.inGoAwayCircle(point)) {
			testWindow.removeFromCanvas();
		} else {
			resizing = testWindow.inResizeBox(point);
			dragging = testWindow.inTitleBar(point);
			lastPoint = point;
		}
		
		// zooms window or returns window to initial size
		// depending on when user clicks in zoom circle
		if (testWindow.inZoomCircle(point)) {
			if (!testWindow.zooming()) {
				testWindow.zoom();
			} else if(testWindow.zooming()) {
				testWindow.sendToInitialPosition();
			}
		}
		// minimizes window or returns it to its initial position 
		// depending on when user clicks in minimize circle 
		if (testWindow.inMinimizeCircle(point)) {
			if(!testWindow.minimized()) {
				testWindow.minimize();
			} else if (testWindow.minimized()) {
				testWindow.restoreWindow();
			}
		}
	}

	/*
	 * If mouse pressed in resize box then resize it. If mouse pressed in title
	 * bar then drag it.
	 */
	public void onMouseDrag(Location point) {
		if (resizing) {
			testWindow.resizeBy(point.getX() - lastPoint.getX(), point.getY()
					- lastPoint.getY());
		} else if (dragging) {
			testWindow.move(point.getX() - lastPoint.getX(), point.getY()
					- lastPoint.getY());
		}
		lastPoint = point;
	}

	public static void main(String[] args) {
		new WindowManager().startController(400, 400);
	}

}
