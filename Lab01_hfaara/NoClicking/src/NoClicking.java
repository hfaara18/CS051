import objectdraw.*;
import java.awt.*;

/**
 * Lab 1 - NoClicking
 *
 * This is an exercise in basic shape drawing and mouse event handling.
 * <UL>
 *	<LI> At startup, it puts up a sign that says "CLICKING" 
 *           with a circle and slash in front of it.</LI>
 *	<LI> The message turns red when the mouse enters the applet
 *	     window, black when the mouse leaves the window.</LI>
 *	<LI> The circle and slash should disappear when the mouse 
 *	     button is pressed, and reappear when it is released.</LI>
 * </UL>
 *
 * Suggested window size 700x450
 *
 * @author Hussein Faara, Java
 */
public class NoClicking extends WindowController{

    /** Declare variables so that you can assign drawn/ created figures 
	*to them for easy manipulation of figure. Changing colors and other 
	*responsive methods depend on declaring these variables and assigning 
	*figures to them.
	*/ 
	private FilledRect innerBox;
	private FilledOval innerOval;
	private FilledRect signPost;
	private Text visibleMessage;
	private FramedOval ovalFrame;
	private Line line;
	  

   /**
    * Initialization method, called when program begins to execute
    */
   public void begin()
   {
   	/** Create figures( an oval and a rectangle) and their frames 
   	 * and assigns them to declared variables so that their colors 
   	 * and other properties can be easily manipulated.
   	 * The colors of figures are also changed in the latter part
   	 */
	   	new FramedRect(330, 75, 100, 100, canvas);
	   	innerBox = new FilledRect(331, 76, 98.5, 98.5, canvas);
	   	ovalFrame = new FramedOval(335, 80, 85, 85, canvas);
	   	innerOval= new FilledOval(336, 81, 83, 83, canvas);
	   	visibleMessage = new Text("CLICKING", 345, 118,canvas);
	   	line = new Line(344,95,406,153, canvas);
	   	signPost= new FilledRect(374, 176, 5, 180, canvas);
	   	new Line (330, 356, 430, 356, canvas);
	   	
	   	innerBox.setColor(Color.yellow);
	   	innerOval.setColor(Color.yellow);
	   	signPost.setColor(Color.black);   
   }

   /**
    * event handler: called when mouse enters the program window
    *
    * @param	point	Location of mouse at entry
    */
   public void onMouseEnter(Location point)
   {
   	// When mouse pointer enters program window, the text " CLICKING" turns red
	   visibleMessage.setColor(Color.red);   
   }

   /**
    * event handler: called when mouse exits the program window
    *
    * @param	point	Location of mouse at exit
    */
   public void onMouseExit(Location point)
   {
   	// When mouse pointer leaves the program window, the text "CLICKING" returns to its black color
	   
	   visibleMessage.setColor(Color.black);
   }

   /**
    * event handler: called when mouse button is pressed
    *
    * @param	point	Location of mouse at time of press
    */
   public void onMousePress(Location point)
   {
   	// When mouse button is pressed, oval frame and line disappear
	   ovalFrame.hide();
	   line.hide();
   }
   		
   		
   /**
    * event handler: called when mouse button is released
    *
    * @param	point	Location of mouse at time of release
    */
   public void onMouseRelease(Location point)
   {
   	// When mouse button is released oval frame and line re-appear
	   ovalFrame.show();
	   line.show();
   }
}
