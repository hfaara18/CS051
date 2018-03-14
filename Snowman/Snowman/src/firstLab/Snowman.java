package firstLab;

import objectdraw.*;
import java.awt.*;

public class Snowman {
	private FramedOval snowHead ;
	private FramedOval snowTorso;
	private FramedOval snowDown;
	private FilledOval snowFace;
	private FilledOval snowBelly;
	private FilledOval leftEye;
	private FilledOval rightEye;
	private FilledOval firstButton;
	private FilledOval secondButton;
	private FilledOval thirdButton;
	
	
	private static final double HEAD_DIM = 50;
	private static final double TORSO_DIM = 75;
	private static final double DOWN_DIM = 100;
	private static final double EYE_DIM = 10;
	
	
	public Snowman(double x, double y, DrawingCanvas canvas) {
		Location faceLoc = new Location(x + 1, y + 1);
		Location torsoLoc = new Location(x - 13, y + 40);
		Location bellyLoc = new Location(x - 12, y + 41);
		Location downLoc = new Location(x - 26, y + 85);
		Location leftLoc = new Location(x + 10, y + 15);
		Location rightLoc = new Location(x + 30, y + 15);
		Location firstButton = new Location(x + 17, y + 60);
		Location secondButton = new Location( x + 17, y + 80);
		Location thirdButton = new Location( x + 17, y + 100);
		double faceWidth = HEAD_DIM - 2; 
		double bellyWidth = TORSO_DIM - 2;
		
		snowHead = new FramedOval(x, y, HEAD_DIM, HEAD_DIM, canvas);
		snowFace = new FilledOval(faceLoc, faceWidth, faceWidth, canvas);
		snowTorso = new FramedOval(torsoLoc,TORSO_DIM, TORSO_DIM, canvas );
		snowBelly = new FilledOval(bellyLoc,bellyWidth, bellyWidth, canvas );
		snowDown = new FramedOval(downLoc, DOWN_DIM, DOWN_DIM, canvas);
		leftEye = new FilledOval(leftLoc, EYE_DIM, EYE_DIM, canvas);
		rightEye = new FilledOval(rightLoc, EYE_DIM, EYE_DIM, canvas);
		this.firstButton = new FilledOval(firstButton, EYE_DIM, EYE_DIM,canvas);
		this.secondButton = new FilledOval(secondButton, EYE_DIM,EYE_DIM, canvas);
		this.thirdButton = new FilledOval(thirdButton, EYE_DIM, EYE_DIM, canvas);
		new Line(x - 7, y + 58, x - 59, y + 18, canvas);
		new Line(x - 59, y + 18, x - 61, y + 5, canvas);
		new Line(x - 59, y + 18, x - 71, y + 13, canvas);
		new Line(x - 59, y + 18, x - 73, y + 25, canvas);
		new Line(x + 56, y + 58 , x + 108, y + 18, canvas);
		new Line(x + 108, y + 18, x + 115, y + 3, canvas);
		new Line(x + 108, y + 18, x + 123, y + 12, canvas);
		new Line(x + 108, y + 18, x + 121, y + 24, canvas);
		
		
		snowFace.setColor(Color.WHITE);
		snowBelly.setColor(Color.WHITE);
		snowBelly.sendToFront();
		snowFace.sendToFront();
		snowHead.sendToFront();
		leftEye.sendToFront();
		rightEye.sendToFront();
		this.firstButton.sendToFront();
		this.secondButton.sendToFront();
		this.thirdButton.sendToFront();
		
	}

}
