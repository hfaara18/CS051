import java.awt.*;

import objectdraw.*;

/**
 * Create a rendition of the hangman game that makes apples fall for wrong letter
 * guesses in an unknown word
 */
public class AppleGame {
	// the location of the win and game over texts
	private static final Location TEXT_LOCATION = new Location(90, 90);
	
	// the location of the current word
	private static final Location CURRENT_WORD_LOCATION = new Location(50, 350);
	
	// the location of the tried letters
	private static final Location LETTERS_TRIED_LOCATION = new Location(50, 400);
	
	// the font size of the texts
	private static final int FONT_SIZE = 25;

	// the number of tries
	private int tries;
	
	// the word dictionary
	private Dictionary dictionary;
	
	// the game display
	private AppleGameDisplay display;
	
	// the canvas
	private DrawingCanvas canvas;
	
	// the texts to be displayed
	private Text winText, gameOverText, 
						word, lettersTried;
	
	// the current word
	private String currentWord = "";
	
	// the tried letters
	private String triedLetters = "";
	
	// whether or not the user can play
	public Boolean play;
	
	// the new random word
	public String newWord = "";
	
	
	/***
	 * 
	 * Initializes apple game
	 * 
	 * @param numApples
	 * 				the number of apples
	 * @param wordfile
	 * 				the word dictionary
	 * @param canvs
	 * 				the drawing canvas
	 */
	public AppleGame(int numApples, String wordfile, DrawingCanvas canvs){
		// number of tries and the canvas
		tries = numApples;
		canvas = canvs;
		
		// the dictionary and the display
		dictionary = new Dictionary(wordfile);
		display = new AppleGameDisplay(numApples, canvas);
		
		// creating and hiding the win message
		winText = new Text("You won!", TEXT_LOCATION, canvas);
		winText.setFontSize(FONT_SIZE);
		winText.setColor(Color.RED);
		winText.hide();
		
		// creating and hiding the game over message
		gameOverText = new Text("Game Over", TEXT_LOCATION, canvas);
		gameOverText.setFontSize(FONT_SIZE);
		gameOverText.setColor(Color.RED);
		gameOverText.hide();
		
		// the word and the tried letters
		word = new Text(currentWord, CURRENT_WORD_LOCATION, canvas);
		lettersTried = new Text(triedLetters, LETTERS_TRIED_LOCATION, canvas);
		
		// whether the user can play the game
		play = true;
		
		// making a new game
		newGame();
		
	}
	
	/**
	 * Evaluates the guessed letters
	 */
	public void guessLetter(char c){
		
		// if the user is allowed to play
		if(play) {
			// if the letter is in the word
			if(newWord.indexOf(c) >=0) {
				String displayString = "";
				
				// updates the game/display for a correct guess
				for (int i=0; i < newWord.length(); i++) {
					if(newWord.charAt(i)== c) {
						displayString += c;
					}
					else {
						displayString += currentWord.charAt(i);
					}
				}
				currentWord = displayString;
				word.setText(currentWord);
				
				// display a "win message" after user wins game
				if(!currentWord.contains("-")) {
					winText.show();
					winText.sendToFront();
					play = false;
				}
			}
			
			// updates the game/display for an incorrect guess
			else if (triedLetters.indexOf(c) < 0) {
				triedLetters += c;
				lettersTried.setText(triedLetters);
				lettersTried.setFontSize(FONT_SIZE);
				display.removeApple();
				tries -=1;
				
				// displays game over if user runs out of tries
				if(tries ==0) {
					gameOverText.show();
					gameOverText.sendToFront();
					word.setText(currentWord);
					play = false; 
				}
			}
		}
	}
	
	// generates a new game
	public void newGame(){
		
		// removes previous apples from the screen
		for(int i = 0; i < tries; i++) {
			display.removeApple();
		}
		
		// creates a new game display
		display = new AppleGameDisplay(tries, canvas);
		
		// the new random word
		currentWord = "";
		newWord = dictionary.getRandomWord();
		
		// replaces letters in the word with dashes
		for( int i=0; i< newWord.length(); i++) {
			currentWord +="-";	
		}
		
		// resets the random generated word
		word.setText(currentWord);
		word.setFontSize(FONT_SIZE);
		play = true;
		
		// resets the tried letters
		triedLetters = "";
		lettersTried.setText(triedLetters);
	}
	
	// sets game to a varying level of difficulty
		public void setTries(int tris) {
			tries = tris;
	}
}	


