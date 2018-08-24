import objectdraw.*;


import java.awt.BorderLayout;
import java.awt.event.*;
import javax.swing.*;

/**
 * Lab 10 - Nibbles
 * 
 * A game where a snake is placed on a field with a piece of food. The user uses
 * the arrow ikeys to move the snake and try to eat the food. The snake dies if
 * it runs into the boundary or itself.
 * 
 * Classes structure:<br>
 *  - Nibbles: instantiates other classes, receives keyboard events<br>
 *  - Position: a row/column tupple<br>
 *  - Direction: an up/down/left/right direction<br>
 *  - NibbleField: two dimensional game grid on which snake moves<br>
 *  - Snake: the (active object) moving snake<br>
 * suggested screen size: 600x600 
 * 
 * Originally created by J. Yang 2000; modified by A. Danyluk 2000; modified by
 * Tom Murtagh, Nov 2001;
 * 
 * @author HUSSEIN FAARA, CS051J
 */
public class Nibbles extends WindowController implements KeyListener, ActionListener {

	// The snake that moves around.
	private Snake theSnake;
	
	// the  restart button
	private JButton restart;
	
	// the field of play
	private NibbleField field;
	

	/**
	 * Called when the applet is started:<br>
	 * 1. Create the playing field.<br>
	 * 2. Create the snake.<br>
	 * 3. Arrange to receive events when the arrow keys are pressed<br>
	 */
	public void begin() {
		// build the field
		field = new NibbleField(canvas);
		
		// create a snake with its head at a random location that is guaranteed
		// to allow the entire snake to fit in the field.
		theSnake = new Snake(field);
		
		
		restart = new JButton("Restart");
		restart.addActionListener(this);
		add(restart, BorderLayout.SOUTH);
		validate();

		// Get ready to handle the arrow keys
		requestFocus();
		addKeyListener(this);
		setFocusable(true);
		canvas.addKeyListener(this);
	}

	/**
	 * mouse event handler ... request keyboard input
	 */
	public void onMousePress(Location point) {
		requestFocus();
	}
	
	public void  playSound() {
		
	}

	/**
	 * (mandatory) KeyListener event handler for a key having been pressed and
	 * released.
	 * 
	 * @param e
	 *            event (key that was typed)
	 */
	public void keyTyped(KeyEvent e) {
	}

	/**
	 * (mandatory) KeyListener event handler for a key having been released.
	 * 
	 * @param e
	 *            event (key that was released)
	 *            
	 */
	public void actionPerformed(ActionEvent event) {
		if(event.getSource() == restart) {
			theSnake.reset();
			theSnake= new Snake(field);
			
		}
	}
	public void keyReleased(KeyEvent e) {
	}

	/**
	 * (mandatory) KeyListener event handler for a key having been pressed.
	 * <P>
	 * Handle arrow keys by telling the snake to move in the indicated
	 * direction.
	 * 
	 * @param e
	 *            event (key that was pressed)
	 */
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			theSnake.setDirection(Direction.UP);

		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			theSnake.setDirection(Direction.DOWN);

		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			theSnake.setDirection(Direction.LEFT);

		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			theSnake.setDirection(Direction.RIGHT);

		}
	}
}
