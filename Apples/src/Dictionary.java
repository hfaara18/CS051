import objectdraw.*;
import java.io.*;

/**
 * a list of words, read from a file, from which we choose
 * random words for the player to guess.
 */
public class Dictionary {
	
	/** The maximum number of words that will be loaded in	*/
	private static final int MAX_WORDS = 100;

	/** the words								*/
	private String[] words;
	/** number of words in our dictionary		*/
	private int numWords;
	/** random number generator to choose words	*/
	private RandomIntGenerator numGen;

	/**
	 * Create a dictionary full of the words from a specified file.<br>
	 *
	 * @param name of dictionary file to be opened
	 */
	public Dictionary(String filename) {
		
		try {
			// the array of words and it's word count
			words = new String[MAX_WORDS];
			numWords = 0;
			
			// reads the word dictionary
			FileReader file;
			file = new FileReader(filename);
			
			// retrieves words from the dictionary
			BufferedReader wordText = new BufferedReader(file);
			words[numWords]= wordText.readLine();  
			
			// adds each word from the dictionary to the words array
			while((numWords< MAX_WORDS) && (words[numWords] != null)) {
				numWords +=1;
				words[numWords] = wordText.readLine();
				
			}
			
			// closes the dictionary
			wordText.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			 
		} catch (IOException e) {
			e.printStackTrace();
		}
		// generates random integers
		numGen = new RandomIntGenerator(0, numWords);
		
	}
	

	/**
	 * choose a random word from the dictionary
	 * 
	 * @return	a word from the dictionary
	 */
	public String getRandomWord() {
		return words[numGen.nextValue()];
		
	}
}
