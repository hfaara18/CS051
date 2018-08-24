import objectdraw.*;


/**
 * a Snake is an active object that simulates the movement of a snake that:
 * <ul>
 *  <li> slithers in a specified direction</li>
 *  <li> grows when it consumes food </li>
 *  <li> withers away when it hits itself or a boundary </li>
 * </ul>
 */
public class Snake extends ActiveObject {
	/** the snake's growth rate				*/
	private static final int AMOUNT2GROW = 4;

	/** initial length of snake				*/
	private static final int INITLENGTH = 3;

	/** pause time between movements		*/
	private static final int PAUSE_TIME = 250;
	
	private static final int MAX_SEGMENTS = 100;

	// the field of play
	private NibbleField field;
	
	// the snake
	private Position[] snake;
	
	// the direction of movement
	private Direction direction;
	
	// snake length
	private int length;
	
	// snake's growth factor
	private int growth;
	
	
	/**
	 * Create a new snake<br>
	 *   - create and initialize the array to hold the body<br>
	 *   - start the Snake, moving up, in the center of the field<br>
	 * 
	 * @param field
	 *            the Field on which he moves
	 */
	public Snake(NibbleField field) {
		// storing the field parameter
		this.field = field;
		
		// creating an array of a 100 items at the center of the field
		snake = new Position[MAX_SEGMENTS];
		snake[0] = field.getCenter();
		
		// adding the snake to the field
		field.addItem(snake[0]);
		
		// assigning the length, direction of movement and the growth
		// factor to the snake
		length = 1;
		direction = Direction.UP;
		growth = 3;
		
		start ();
	}

	/**
	 * Change the snake's direction of motion (but reversal is not allowed).
	 * 
	 * @param dir
	 *            new direction for snake to move in
	 */
	public void setDirection(Direction dir) {
		
		// changes snake direction if it's the reverse of its 
		// current direction
		if (!direction.isOpposite(dir)) {
			direction = dir;
		}
		

	}

	/**
	 * shrink ... remove the last segment from the snake
	 */
	public void shrink() {
		// removes the last item in the snake array
		Position tail = snake[length -1];
		field.removeItem(tail);
		snake[snake.length -1] = null;
		
		// reduces the length of the snake accordingly
		length-=1;
		
		
	}

	//private AudioClip getAudio(String string) {
		//return getAudio(string);
	//}
	/**
	 * stretch ... extend the snake one segment in its current direction
	 */
	public void stretch() {
		// assigns the snake head to a variable and adds it to the field
		Position head = snake[0].translate(direction);
		field.addItem(head);
		
		// updates the length accordingly
		length +=1;
		
		// moves the snake segments from head to tail
		for (int i= 0; i< length; i++) {
			Position segment = snake[i];
			snake[i] = head;
			head = segment ;	
		}
	}
	
	// restarts the game
	public void reset() {
		while(length> 0 ) {
			shrink();
		}
	}
	

	/**
	 * run ... active object main loop
	 * <ul>
	 *	<li> pause</li>
	 *	<li> move, by calling stretch() and shrink()</li>
	 *	<li> if we encounter food, consume it and grow</li>
	 *	<li> if we hit ourselves or the edge die (stop stretching)</li>
	 * </ul>
	 */
	public void run() {	
		
		while (true) {
			// assigns the snake's head to a variable
			Position head = snake[0].translate(direction);
			
			// stops the loop if the snake eats itself or moves out
			// of the field
			if((field.outOfBounds(head)) || (field.containsSnake(head))) {
				break;
			}
			// update the snake's growth factor if the snake eats the food
			else if ((field.containsFood(head))) {
				field.consumeFood();
				growth += AMOUNT2GROW;
				
			} 
			// increases the snake's length when it is supposed to grow
			else if (growth !=0) {
				stretch();
				growth-=1;
			}
			// moves the snake without any growth otherwise
			else {
				    stretch();
					shrink();
				}
			
			pause(PAUSE_TIME);
		}
		// kills the snake if it goes out of bounds or eats itself
		while(length> 0 ) {
			shrink();
			pause(PAUSE_TIME);
		}			
		
	}

}
