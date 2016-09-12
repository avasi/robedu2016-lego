package behaviours;

import config.Globals;
import lejos.nxt.Motor;
import lejos.robotics.subsumption.Behavior;

public class Forward implements Behavior{

	private boolean suppressed = false;

	
	public Forward() {
	}
	
	@Override
	public boolean takeControl() {
		return true;
	}
	
	@Override
	public void action() {
		suppressed = false;
		
		//List<Integer> leftMeasures = new ArrayList<>();
		//List<Integer> rightMeasures = new ArrayList<>();
		Globals.leftMotor.forward();
		Globals.rightMotor.forward();
		while (!suppressed)
			Thread.yield();	
		Globals.leftMotor.stop();
		Globals.rightMotor.stop();
	}

	@Override
	public void suppress() {
		suppressed = true;
	}
}