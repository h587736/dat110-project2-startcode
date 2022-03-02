package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;
import no.hvl.dat110.common.TODO;

public class TemperatureDevice {

	private static final int COUNT = 10;

	public static void main(String[] args) {

		// simulated / virtual temperature sensor
		TemperatureSensor sn = new TemperatureSensor();

		// Done - start
		//Create a client object
		Client sensorClient = new Client("TemperatureSensor", Common.BROKERHOST, Common.BROKERPORT);

		//Connect to the broker 
		sensorClient.connect();
		
		int i = 0;
		int temperatureInt;
		String temperatureString;
		
		
		//Publish the temperature 
		while(i < COUNT) {
			temperatureInt = sn.read();
			try {
				Thread.sleep(1000);
			} catch(InterruptedException e) {
				e.printStackTrace();
			}
			temperatureString = Integer.toString(temperatureInt);
			sensorClient.publish(Common.TEMPTOPIC, temperatureString);
			i++;
		}
		
		//Disconnect from the broker 
		sensorClient.disconnect();
		// Done - end

		System.out.println("Temperature device stopping ... ");


	}
}
