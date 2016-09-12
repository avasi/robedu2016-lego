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

public class AvoidObstacle implements Behavior{

	private int[] distances;
	private UltrasonicSensor usFront;
	private LightSensor lightL;
	private boolean suppressed = false;

	public AvoidObstacle(SensorPort usPortFront, SensorPort lightLport) {

		this.lightL = new LightSensor(lightLport);
		this.usFront = new UltrasonicSensor(usPortFront);
		distances = new int[8];
		
	}
	
	@Override
	public boolean takeControl() {
		return iSeeObstacles(usFront) || Globals.turningLeft || Globals.turningRight;
	}
	
	@Override
	public void action() {
		suppressed = false;
		//si leo obstaculo en frente empiezo a girar
		
		if (iSeeObstacles(usFront)){
			Globals.turningLeft = true;
			Globals.leftMotor.backward();
			Globals.rightMotor.backward();
			Delay.msDelay(Globals.backoffDelay);
		}
		
		//giro para encontrar obstaculo con sensor del costado
		if (Globals.turningLeft) {
			//giro a la izq sobre el eje
			Globals.leftMotor.backward();
			Globals.rightMotor.forward();
			Delay.msDelay(Globals.backoffDelay);
		}

		if (Globals.turningRight) {
				//giro a la izq sobre el eje
				Globals.leftMotor.forward();
				Globals.rightMotor.backward();
				Delay.msDelay(Globals.backoffDelay/2);
		}		
			
	}

	@Override
	public void suppress() {
		suppressed = true;
	}

	
	private boolean iSeeObstacles (UltrasonicSensor usFront) {

		return usFront.getDistance() < Globals.minObstacleDistance; 
	}
}
