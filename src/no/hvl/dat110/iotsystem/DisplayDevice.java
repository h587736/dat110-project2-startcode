package no.hvl.dat110.iotsystem;

import no.hvl.dat110.client.Client;
import no.hvl.dat110.messages.Message;
import no.hvl.dat110.messages.PublishMsg;
import no.hvl.dat110.common.TODO;

public class DisplayDevice {
	
	private static final int COUNT = 10;
		
	public static void main (String[] args) {
		
		System.out.println("Display starting ...");
		
		// Done - START
				
		Client displayDevice = new Client("Display", Common.BROKERHOST, Common.BROKERPORT); 
		
		// - connect to the broker - use "display" as the username
		displayDevice.connect();
		// - create the temperature topic on the broker
		displayDevice.createTopic(Common.TEMPTOPIC);
		// - subscribe to the topic
		displayDevice.subscribe(Common.TEMPTOPIC);
		// - receive messages on the topic
		int i = 0;
			while (i < COUNT) {
				PublishMsg displayDevicePrint = (PublishMsg) displayDevice.receive();
				System.out.println("Display:" + displayDevicePrint.getMessage());
				System.out.println("* * * * READING: " + (i + 1));
				i++;
			}
		// - unsubscribe from the topic
		displayDevice.unsubscribe(Common.TEMPTOPIC);
		// - disconnect from the broker
		displayDevice.disconnect();
		
		// Done - END
		
		System.out.println("Display stopping ... ");
		
		
	}
}
