import objectdraw.*;

/**
 * Interface for stairs
 * <br>
 * Defines the method(s) to be implemented by all Stairs
 */
public interface StairsInterface {


	/**
	 * move
	 *
	 * @param	dx ... x movement (in pixels)
	 * @param	dy ... y movement (in pixels)
	 */
	public void move(double x, double y);	
	
	/**
	 * Checks whether mouse in within designated area
	 * 
	 * @param loc
	 * 			Coordinates of mouse pointer
	 */
	public Boolean contains(Location loc);
}
