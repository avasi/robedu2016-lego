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

public class Turn implements Behavior{

	private LightSensor lightL;
	private LightSensor lightR;
	private boolean suppressed = false;
	
	public Turn(SensorPort lightLport, SensorPort lightRport) {

		this.lightL = new LightSensor(lightLport);
		this.lightR = new LightSensor(lightRport);
		
	}
	
	@Override
	public boolean takeControl() {
		return (lightL.getLightValue()<Globals.lightLthreshold) || (lightR.getLightValue()<Globals.lightRthreshold);
	}
	
	@Override
	public void action() {
		suppressed = false;
		

		//List<Integer> leftMeasures = new ArrayList<>();
		//List<Integer> rightMeasures = new ArrayList<>();

		

		Globals.leftMotor.setSpeed(Globals.travelSpeed);
		if (lightL.getLightValue()<Globals.lightLthreshold){
			System.out.println("Left:" + lightL.getLightValue());

			//Giro
			Globals.leftMotor.setSpeed(Globals.turnSpeedMax);
			Globals.leftMotor.backward();

			Globals.rightMotor.setSpeed(Globals.turnSpeedMin);
			Globals.rightMotor.forward();

			Delay.msDelay(Globals.turnDelay);
			

		}
		if (lightR.getLightValue()<Globals.lightRthreshold){
			System.out.println("Right:" + lightR.getLightValue());

			Globals.rightMotor.setSpeed(Globals.turnSpeedMax);
			Globals.rightMotor.backward();

			Globals.leftMotor.setSpeed(Globals.turnSpeedMin);			
			Globals.leftMotor.forward();

			Delay.msDelay(Globals.turnDelay);

		}

		Globals.leftMotor.setSpeed(Globals.travelSpeed);
		Globals.rightMotor.setSpeed(Globals.travelSpeed);
		//Delay.msDelay(Globals.turnDelay);
	}

	@Override
	public void suppress() {
		suppressed = true;
	}
}
