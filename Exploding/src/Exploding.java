
import objectdraw.*;
/*
 * @author Hussein Faara, CS051J
 */
public class Exploding extends WindowController {
	private Pow pow;
	
	// initializes creation of ovals
	public void begin() {
		pow = new Pow(canvas);
	} 
	
	// reverse explosion after mouse is clicked
	public void onMouseClick(Location point) {
		pow.reverseExplosion();
	}

}
