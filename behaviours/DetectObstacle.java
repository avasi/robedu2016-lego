package behaviours;

import java.util.ArrayList;
import java.util.List;

import config.Globals;
import lejos.nxt.SensorPort;
import lejos.nxt.Motor;
import lejos.nxt.LightSensor;
import lejos.nxt.UltrasonicSensor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.subsumption.Behavior;
import lejos.util.Delay;

public class DetectObstacle implements Behavior{

	private int[] distances;
	private UltrasonicSensor usSide;
	private LightSensor lightL;
	private boolean suppressed = false;
	
	public DetectObstacle(SensorPort usPortSide, SensorPort lightLport) {

		this.lightL = new LightSensor(lightLport);
		this.usSide = new UltrasonicSensor(usPortSide);
		distances = new int[8];
		
	}
	
	@Override
	public boolean takeControl() {
		return iSeeObstacles(usSide);
	}
	
	@Override
	public void action() {
		suppressed = false;
		Globals.turningLeft = false;
		Globals.turningRight = true;
		//avanzo
		Globals.leftMotor.forward();
		Globals.rightMotor.forward();
		Delay.msDelay(Globals.backoffDelay/2);
		if (Globals.turningRight){
			if (lightL.getLightValue()<Globals.lightLthreshold){
				Globals.turningLeft = false;
				Globals.turningRight = false;
				Globals.leftMotor.backward();
				Globals.rightMotor.forward();
				Delay.msDelay(Globals.backoffDelay);
			}
		}
			
	}

	@Override
	public void suppress() {
		suppressed = true;
	}

	
	private boolean iSeeObstacles (UltrasonicSensor usSide) {

		return usSide.getDistance() < Globals.minObstacleDistanceSide; 
	}
}
