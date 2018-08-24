/**
 * This class represents a <row,col> position. Its only purpose is to group
 * together a row and a column into a pair so that we can treat them as a single
 * object.
 * 
 * Originally created by A Danyluk, April 2000, modified by Tom Murtagh, Nov.
 * 2001
 */
public class Position {
	/** the individual row/col components of a position */
	private int row;
	private int col;

	/**
	 * Create a new position
	 * 
	 * @param row
	 *            y-component of position
	 * @param col
	 *            x-component of position
	 */
	public Position(int row, int col) {
		this.row = row;
		this.col = col;
	}

	/**
	 * @return the row/y component of a position
	 */
	public int getRow() {
		return row;
	}

	/**
	 * @return the column/x component of a position
	 */
	public int getCol() {
		return col;
	}

	/**
	 * determine if another positiojn is the same as this
	 * 
	 * @param pos
	 *            position to be compared with this one
	 * @return true if specified position is the same as this
	 */
	public boolean equals(Position pos) {
		return row == pos.getRow() && col == pos.getCol();
	}

	/**
	 * determine the position that would be reached if we move in a specified
	 * direction.
	 * 
	 * @param dir
	 *            direction of desired motion
	 * @return new position resulting from that motion
	 */
	public Position translate(Direction dir) {
		return new Position(row + dir.getYchange(), col + dir.getXchange());
	}

	/**
	 * render a position as a printable string (for diagnostic output)
	 * 
	 * @return string of the form "(row,col)"
	 */
	public String toString() {
		return "(" + row + ", " + col + ")";
	}

}