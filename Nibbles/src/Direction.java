/**
 * Class to represent snake's direction. This is a very simple class. It's only
 * purpose is to group together a horizontal and vertical speed into a pair so
 * that we can treat them as a single object.
 * 
 * Written by Tom Murtagh, Nov. 2001
 */
public class Direction {

	/** constants, representing the four directions	*/
	public static final Direction UP = new Direction(0, -1),
								DOWN = new Direction(0, 1), 
								LEFT = new Direction(-1, 0),
								RIGHT = new Direction(1, 0);

	/** the individual delta-x and delta-y components of a direction */
	private int xchange;
	private int ychange;

	/**
	 * Create a new direction
	 * 
	 * @param xchange
	 *            delta-x component of new direction
	 * @param ychange
	 *            delta-y component of new direction
	 */
	private Direction(int xchange, int ychange) {
		this.xchange = xchange;
		this.ychange = ychange;
	}

	/**
	 * @return delta-x component of direction
	 */
	public int getXchange() {
		return xchange;
	}

	/**
	 * @return delta-y component of direction
	 */
	public int getYchange() {
		return ychange;
	}

	/**
	 * determine whether or not a new direction is opposite this one
	 * 
	 * @return true if new direction is opposite of this
	 */
	public boolean isOpposite(Direction newDirection) {
		return xchange == -newDirection.getXchange()
				&& ychange == -newDirection.getYchange();
	}

	/**
	 * render a direction as a printable string (for diagnostic output)
	 * 
	 * @return string of the form "(x,y)"
	 */
	public String toString() {
		return "(" + xchange + ", " + ychange + ")";
	}
}