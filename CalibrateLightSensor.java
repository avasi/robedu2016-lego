import lejos.nxt.Button;
import lejos.nxt.LightSensor;
import lejos.nxt.LCD;
import lejos.nxt.SensorPort;
import lejos.util.Delay;

public class CalibrateLightSensor {

	public static final int lightSensorSamples = 25;
	public static final int msBetweenReadings = 50;


	public static final SensorPort lightLport = SensorPort.S1;
	public static final SensorPort lightRport = SensorPort.S2;

	public static void main(String[] args) {
		
		LightSensor lightL = new LightSensor(lightLport);
		LightSensor lightR = new LightSensor(lightRport);

		while (true) {
			int button = Button.waitForAnyPress();
			
			LCD.clear();
			
			int lightLWhiteAvg = 0;
			int lightLBlackAvg = 0;
			int lightRWhiteAvg = 0;
			int lightRBlackAvg = 0;		

			for (int i = 0; i < lightSensorSamples; i++){

				int l = lightL.getLightValue();
				int r = lightR.getLightValue();
				lightLWhiteAvg += l;
				lightRWhiteAvg += r;
				LCD.drawString("L:" + l + ", " + "R:" + r, 0, i);
				

				
				if (i < lightSensorSamples - 1) Delay.msDelay(msBetweenReadings);
			}
			
			LCD.drawString("La:" + lightLWhiteAvg / lightSensorSamples + "," + "Ra:" + lightRWhiteAvg / lightSensorSamples, 0, 0);
			
		}
	}
}