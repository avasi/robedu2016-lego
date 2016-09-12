package config;

import lejos.nxt.Motor;
import lejos.nxt.NXTRegulatedMotor;
import lejos.nxt.SensorPort;

public class Globals{
	
	public static final boolean debug = true;	
	
	//Strings
	public static final String introMsg = "Robot ready.";
	public static final String pressButton = "Press any button to start!";
	
	public static Boolean turningLeft = false;
	public static Boolean turningRight = false;

	//Sensor Ports
	public static final SensorPort lightL = SensorPort.S1;
	public static final SensorPort lightR = SensorPort.S2;
	public static final SensorPort usFront = SensorPort.S3;
	public static final SensorPort usSide = SensorPort.S4;

	//Motor Ports
	public static final NXTRegulatedMotor leftMotor =  Motor.A;
	public static final NXTRegulatedMotor rightMotor =  Motor.B;

	//Speeds
	public static final int travelSpeed = 200;

	public static final int turnSpeedMin = 200;
	public static final int turnSpeedMax = 300;

	public static final int lightLthreshold = 51;
	public static final int lightRthreshold = 51;
	public static final int turnDelay = 50;

	public static final int backoffDelay = 500;
	public static final int avoidDelay = 2500;

	public static final int minObstacleDistance = 10;
	public static final int minObstacleDistanceSide = 20;
	//Navigation
	//public static final int minForwardDistance = 20; //cm
	//public static final int maxForwardDistance = 200; //cm
	
	//public static final int backwardsDistance = 15;
	//public static final int varianceFactor = 2;
	
	
	//Delays
	//public static final int isMovingDelay = 100;
	//public static final int backDelay = 500;
	//public static final int msBetweenReadings = 100;
}