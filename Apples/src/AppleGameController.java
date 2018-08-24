import java.awt.*;

import objectdraw.*;
import java.awt.event.*;

import javax.swing.*;

/**
 * Lab: Apples<br>
 * 
 * A friendlier version of the "hangman" word guessing game.
 * 
 * We choose a word and put up 
 * <UL>
 *	<li>an empty box for each letter of the word</li>
 *      <li>an image of a tree with several apples in it</li>
 * </UL>
 * Each time the player types a candidate letter into the input box:
 * <UL>
 *	<li> if the letter appears in the word, we fill in that
 *	     letter in the appropriate letter position boxes.</li>
 	<li> if the letter does not appear in the word, we remove
	     one apple from the tree.</li>
 * </UL>
 * The game ends when the user has completed the word, or when no apples
 * remain in the tree.
 *
 * Class Structure
 * <ul>
 *	<li>AppleGameController ... create interface and receive keyboard input</li>
 *	<li>AppleGame ... process guesses and maintain the game state</li>
 *	<li>AppleGameDisplay ... maintain the displayed tree/apples image</li>
 *	<li>Dictionary ... read in a word list, and select random words from it</li>
 * </ul>
 *
 * Suggested window size: 300x525
 * 
 * @author Hussein Faara, CS051J
 */
public class AppleGameController extends WindowController implements ActionListener {
	
	/** number of apples on tree/guesses allowed	*/
	private static final int NUM_APPLES = 8;
	
	/** name of dictionary file						*/
	private static final String FILENAME="words.txt";

	// the game buttons
	private JButton newGame, hard, medium, easy;
	
	// the text field
	private JTextField field;
	
	// the apple game
	private AppleGame game;
	
	// the initial apple count
	private int initialAppleCount = 10;

	/**
	 * this method is invoked when the applet is started
	 *
	 * <ol type="1">
	 *   <li>create the GUI (text entry and new game button)</li>
	 *   <li>register action listeners for text input and button pushes</li>
	 *   <li>instantiate an AppleGame instance</li>
	 * </ol>
	 * Initializes GUI widgetry and creates a new AppleGame
	 */
	public void begin() {
		// a panel to hold all south GUI components
		JPanel panel = new JPanel(new GridLayout(3, 1));
		
		// the text field
		field = new JTextField("Enter a letter");
		field.addActionListener(this);
		
		// the new game button
		newGame = new JButton("New Game");
		newGame.addActionListener(this);
		
		// a difficulty panel
		JPanel difficultyPanel = new JPanel(new GridLayout(1, 3));
		
		// a hard difficulty button
		hard = new JButton("Hard");
		hard.addActionListener(this);
		
		// a medium difficulty button
		medium = new JButton("Medium");
		medium.addActionListener(this);
		
		// an easy difficulty button
		easy = new JButton("Easy");
		easy.addActionListener(this);
		
		// adding difficulty button to panel
		difficultyPanel.add(hard);
		difficultyPanel.add(medium);
		difficultyPanel.add(easy);
		
		// initializing the game
		game = new AppleGame(initialAppleCount, "words.txt", canvas);
		
		// adding the GUI components to the containing panel
		panel.add(field);
		panel.add(newGame);
		panel.add(difficultyPanel);
		
		// adding the containing panel to the south of the window
		add(panel, BorderLayout.SOUTH);
		validate();
	}
	
	/**
	 * required keyboard event listener (for ActionListener interface)<br>
	 * handles letter guesses and "new game" button
	 * <ul>
	 *   <li>if event was from reset button, start new game</li>
	 *   <li>if event was from keyboard, pass it to AppleGame.guess</li>
	 * </ul>
	 *
	 * @param	e
	 * 				key press event
	 */
	public void actionPerformed(ActionEvent e) {
		
		// restarts the game
		if(e.getSource() == newGame) {
			game.newGame();
		}
		// resets the game to a hard difficulty
		if(e.getSource() == hard) {
			game.setTries(5);
			game.newGame();
		}
		// resets the game to a medium difficulty
		if(e.getSource() == medium) {
			game.setTries(8);
			game.newGame();
		}
		// rests the game to an easy difficulty
		if(e.getSource() == easy) {
			game.setTries(10);
			game.newGame();
		}
		// takes input from field and updates the display
		if(e.getSource() == field) {
			String text = field.getText();	
			
			// updates display if input is a letter
			if ((text.length() ==1) && (Character.isLetter(text.charAt(0)))) {
					game.guessLetter(text.charAt(0));
					field.setText("");
			}
			// asks user to type in a letter otherwise
			else {
				field.setText("Please enter a letter");
			}
		}
	}
}
