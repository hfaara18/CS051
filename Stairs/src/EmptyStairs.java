import objectdraw.Location;

/**
 * the "null" stairs that cap off the recursion. 
 * <br>
 * Because they are empty, they do not have to be rendered or moved.
 */
public class EmptyStairs implements StairsInterface {
	
	
	/**
	 * move
	 *
	 * @param	dx ... x movement (in pixels)
	 * @param	dy ... y movement (in pixels)
	 */
	public void move(double x, double y) {}
	
	/**
	 * Checks whether mouse in within designated area
	 * 
	 * @param loc
	 * 			Coordinates of mouse pointer
	 * @return
	 * 			returns false initially
	 */
	public Boolean contains(Location pt) {
		return false;
	}
	
	// empty stairs
	public EmptyStairs() {}

}
