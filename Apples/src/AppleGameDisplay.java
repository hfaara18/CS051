import java.awt.Color;

import objectdraw.*;

/**
 * Class that displays a tree with some number of apples and
 * provides methods for<br>
 * (1) removing apples one at a time.<br>
 * (2) restoring the tree to fully populated<br>
 */
public class AppleGameDisplay {
	// Constants related to the appearance of the tree
	private static final int TREE_WIDTH = 200;
	private static final int TREE_HEIGHT = 150;
	private static final int TRUNK_WIDTH = 20;
	private static final int TRUNK_HEIGHT = 160;
	private static final Location TREE_LOCATION = new Location(50,25);
	private static final Location TRUNK_LOCATION = 
		new Location(TREE_WIDTH/2-TRUNK_WIDTH/2 + 50,TREE_HEIGHT);
	private static final int APPLE_SIZE = 10;
	
	// apples are placed randomly on the tree
	private RandomIntGenerator widthGen =  new RandomIntGenerator(1,TREE_WIDTH);
	private RandomIntGenerator heightGen =  new RandomIntGenerator(1,TREE_HEIGHT);
	
	/** the tree				*/
	private FilledOval tree;
	/** the individual apples	*/
	private FilledOval[] apples;
	/** the number of apples remaining on the tree	*/
	private int applesLeft;

	/**
	 * constructor for a tree with a specified number of 
	 * (randomly placed) apples.
	 * 
	 * @param	numApples	number of apples for the tree
	 * @param	canvas		canvas on which tree should be drawn
	 */
	public AppleGameDisplay(int numApples, DrawingCanvas canvas) {
		tree = new FilledOval(TREE_LOCATION, TREE_WIDTH, TREE_HEIGHT, canvas);
		tree.setColor(Color.GREEN);
		
		FilledRect trunk = new FilledRect(TRUNK_LOCATION, TRUNK_WIDTH, TRUNK_HEIGHT, canvas);
		trunk.setColor(Color.GRAY);
		
		apples = new FilledOval[numApples];
		
		for (int i = 0; i < numApples; i++) {
			apples[i] = new FilledOval(getRandomLocation(), APPLE_SIZE, APPLE_SIZE, canvas);
			apples[i].setColor(Color.RED);
		}
		
		applesLeft = numApples;
	}
	
	/**
	 * repopulate the tree, restoring all of the apples
	 */
	public void fillTree() {
		for (int i = 0; i < apples.length; i++) {
			apples[i].show();
		}
		
		applesLeft = apples.length;
	}
	
	/**
	 * report on the number of apples remaining in the tree
	 * 
	 * @return	number of apples remaining in the tree
	 */
	public int getNumApplesLeft() {
		return applesLeft;
	}
	
	/**
	 * remove one more apple from the tree<br>
	 * 
	 * NOTE: all we do to remove an apple is hide() it
	 */
	public void removeApple() {
		if (applesLeft > 0) {
			apples[applesLeft-1].hide();
			applesLeft--;
		}
	}
	
	/**
	 * choose a (random) location for a new apple<br>
	 * (which is guaranteed not to be currently occupied)
	 * 
	 * @return	location where a new apple can be placed
	 */
	private Location getRandomLocation() {
		int x = widthGen.nextValue();
		int y = heightGen.nextValue();
		
		while (!tree.contains(new Location(x,y))) {
			x = widthGen.nextValue();
			y = heightGen.nextValue();
		}
		
		return new Location(x,y);
	}
}
