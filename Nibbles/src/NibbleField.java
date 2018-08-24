import objectdraw.*;
import java.awt.*;

/**
 * The 2D grid that the snake roams around on
 * 
 * Written by A. Danyluk, B. Lerner April 2000 Modified by Tom Murtagh, Nov
 * 2001.
 */
public class NibbleField {
	/** Size of a single cell in the field */
	private static final int CELLSIZE = 14;

	/** Minimum amount of blank space between field and canvas */
	private static final int FIELDINSET = 10;
	
	private static final int BUTTON_OFFSET = 10;

	/** Colors to use when drawing food or pieces of the snake */
	private static final Color FOODCOLOR = Color.red;
	private static final Color SNAKECOLOR = Color.green;

	/** Dimensions of the field measured in cells */
	private int fieldWidth;
	private int fieldHeight;

	/**
	 * The grid contains rectangles.<br>
	 * Null indicates an empty spot. If a rectangle is present the color tells
	 * whether it is snake or food.
	 */
	private FilledRect[][] theField;

	/** upper left corner of the field on the display */
	private int cornerX, cornerY;

	/** the canvas on which the field is drawn */
	private DrawingCanvas canvas;

	/** Current location of the food */
	private Position foodPos;

	/** Random number generators used to place food */
	RandomIntGenerator rowGenerator, colGenerator;

	/**
	 * Create a new field<br>
	 * - the rectangular on-screen arena<br>
	 * - the 2D array that stores what is in each square<br>
	 * - RandomIntGeerators for food placement<br>
	 * 
	 * @param canvas
	 *            where to display the field-grid
	 */
	public NibbleField(DrawingCanvas canvas) {
		this.canvas = canvas;

		cornerX = FIELDINSET;
		cornerY = FIELDINSET;

		// Automatically size ourselves to the available canvas
		fieldWidth = (int) (canvas.getWidth() - 2 * FIELDINSET)  / CELLSIZE;
		fieldHeight = (int) ((canvas.getHeight()- BUTTON_OFFSET) - 2 * FIELDINSET) / CELLSIZE;

		// Create the array use to record contents of the field.
		theField = new FilledRect[fieldHeight][fieldWidth];

		// draws the borders of the field
		new FramedRect(cornerX - 1, cornerY - 1, CELLSIZE * fieldWidth + 1,
				CELLSIZE * fieldHeight + 1, canvas);

		// Create random number generators used to position food
		rowGenerator = new RandomIntGenerator(0, fieldHeight - 1);
		colGenerator = new RandomIntGenerator(0, fieldWidth - 1);

		// draws the first initial food to "eat"
		placeFood();
	}

	/**
	 * determine whether or not a position contains food
	 * 
	 * @param pos
	 *            position to be checked
	 * @return true if specified position contains food
	 */
	public boolean containsFood(Position pos) {
		return pos.equals(foodPos);
	}

	/**
	 * determine whether or not a position contains snake
	 * 
	 * @param pos
	 *            position to be checked
	 * @return true if specified position contains snake
	 */
	public boolean containsSnake(Position pos) {
		return isOccupied(pos) && !containsFood(pos);
	}

	/**
	 * determine whether or not a position is out of bounds (outside the edges
	 * of the field)
	 * 
	 * @param pos
	 *            position to be checked
	 * @return true if specified position is outside of the field
	 */
	public boolean outOfBounds(Position pos) {
		// if the snake is out of the "legal" rows
		Boolean outOfRows = (pos.getRow()<0) || 
				(pos.getRow() >= fieldHeight);
		
		// if the snake is out of the "legal" columns
		Boolean outOfColumns = pos.getCol() < 0 || 
				pos.getCol() >= fieldWidth;
		
		// returns true if the snake is out of bounds
		return outOfRows || outOfColumns;
	}

	/**
	 * find the center of the field<br>
	 * (useful for initial snake positioning)
	 * 
	 * @return position of the center of the field
	 */
	public Position getCenter() {
		return new Position(fieldHeight / 2, fieldWidth / 2);
	}

	/**
	 * consume the food at its current position, and re-place new food in a
	 * newly chosen position
	 */
	public void consumeFood() {
		Position oldPos = foodPos;
		placeFood();
		removeContents(oldPos);
	}

	/**
	 * Add a piece of snake to the field as a rectangle at the specified
	 * position.<br>
	 * (complains if position is out off the playing field)
	 * 
	 * @param pos
	 *            position where new piece of snake is to be placed
	 */
	public void addItem(Position pos) {
		if (!outOfBounds(pos)) {
			if (isOccupied(pos)) {
				System.out.println("WARNING:  Adding item to " + pos
						+ " but it is not empty!");
				Thread.currentThread().dumpStack();
			}
			showContents(pos, SNAKECOLOR);
		}
	}

	// remove a piece of the snake from the field. Complains if the position is
	// already
	// empty.
	// Parameters:
	// pos - row and column of the cell to remove the item from
	/**
	 * remove a piece of snake from the field.<br>
	 * (complains if the position is already empty)
	 * 
	 * @param pos
	 *            position from which snake segment is to be removed
	 */
	public void removeItem(Position pos) {
		if (!isOccupied(pos)) {
			System.out.println("WARNING:  Removing item from " + pos
					+ " but it is empty!");
			Thread.currentThread().dumpStack();
		}
		removeContents(pos);
	}

	/***********************************************************************/
	/* P R I V A T E M E T H O D S */
	/***********************************************************************/

	/**
	 * Place a new piece of food on the screen, somewhere away from the snake
	 */
	private void placeFood() {

		// Randomly generate locations until we find an empty one.
		do {
			foodPos = new Position(rowGenerator.nextValue(),
					colGenerator.nextValue());
		} while (isOccupied(foodPos));

		showContents(foodPos, FOODCOLOR);

	}

	/**
	 * determine whether or not a specified position is currently occupied.
	 * 
	 * @param pos
	 *            position to be checked
	 * @return true if position is occupied (by snake or food)
	 */
	private boolean isOccupied(Position pos) {
		// returns true if a position is occupied		
		return (!outOfBounds(pos)) && 
				(theField[pos.getRow()][pos.getCol()] !=null);
	}

	/**
	 * update the display to remove the rectangle representing food or a piece
	 * of snake.
	 * 
	 * @param pos
	 *            position from which item is to be removed.
	 */
	private void removeContents(Position pos) {
		// removes contents of given position
		if(!outOfBounds(pos)) {
			theField[pos.getRow()][pos.getCol()].removeFromCanvas();
			theField[pos.getRow()][pos.getCol()] = null;
		}
	}

	// Add a rectangle representing the food or a piece of the snake
	// Parameters:
	// pos - row and column of the cell in which to place the item
	/**
	 * update the display to add a rectangle representing food or a piece of
	 * snake.
	 * 
	 * @param pos
	 *            position where item is to be added
	 * @param hue
	 *            color of item to be placed
	 */
	private void showContents(Position pos, Color hue) {
		// adds a colored rectangle to the field
		if(!outOfBounds(pos)) {
			
			// the row and column of the given position
			int column = pos.getCol();
			int row = pos.getRow();
			
			// creates the colored rectangle
			theField[row][column] = new FilledRect(FIELDINSET + column * CELLSIZE,
					FIELDINSET + row * CELLSIZE, CELLSIZE, CELLSIZE,canvas);
			theField[row][column].setColor(hue);
			
		}
	}
	
	// returns the field's canvas
	public DrawingCanvas getCanvas() {
		return canvas;
	}
}
