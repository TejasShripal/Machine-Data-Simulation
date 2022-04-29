package com.velmdart.sim;

import java.io.FileNotFoundException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import com.velmdart.sim.ChannelSetup.MyGateway;

@SpringBootApplication
public class PayloadSimulation {
	public static void main(String[] args) throws FileNotFoundException {
		ReadXL read = new ReadXL();	
		MqttServerSim sim = new MqttServerSim();
		//ExecutorService quickService = Executors.newFixedThreadPool(200);
		ConfigurableApplicationContext context = new SpringApplicationBuilder(PayloadSimulation.class).run(args);
		MyGateway gateway = context.getBean(MyGateway.class);
		read.ReadFile();
		System.out.println("File read");
		while(true) {
			for(int i = 0; i<200; i++){
				try {
					System.out.println("Machine: "+ Integer.toString(i));
					TimeUnit.MILLISECONDS.sleep(85);
					gateway.sendToMqtt(sim.Simulate(read.mac.get(i), read.cyct.get(i), read.injt.get(i)));
					if(i == 199) {
						i = 0;
						continue;
					}
				}
				catch(Exception e) {
					System.err.println(e.getMessage());
				}
				
			}
			
		}
	}
}
