import behaviours.Forward;
import behaviours.Turn;
import behaviours.AvoidObstacle;
import behaviours.DetectObstacle;

import lejos.nxt.LCD;
import lejos.nxt.Button;
import lejos.robotics.subsumption.Arbitrator;
import lejos.robotics.subsumption.Behavior;
import config.Globals;

public class Robot {
	public static void main(String argv[]) {

		
		LCD.drawString(Globals.introMsg, 0, 0);
		LCD.drawString(Globals.pressButton, 0, 1);
		
		Button.waitForAnyPress();
		LCD.clear();

	    Globals.leftMotor.setSpeed(Globals.travelSpeed);
	    Globals.rightMotor.setSpeed(Globals.travelSpeed);

		Behavior f = new Forward();
		Behavior t = new Turn(Globals.lightL, Globals.lightR);
		Behavior o = new AvoidObstacle(Globals.usFront, Globals.lightL);
		Behavior d = new DetectObstacle(Globals.usSide, Globals.lightL);

		Behavior [] hierarchy = {f, t, o, d};
		Arbitrator arbitrator = new Arbitrator(hierarchy);
		arbitrator.start();
	}
}