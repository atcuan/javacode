package com.itheima.trafficlamp;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LampController 
{
	private Lamp currentLamp;
	
	public LampController()
	{
		currentLamp = Lamp.S2N;
		currentLamp.turnOn();
		
		ScheduledExecutorService timer = Executors.newScheduledThreadPool(1);
		timer.scheduleAtFixedRate
		(
			new Runnable()
			{
				public void run()
				{
					System.out.println("来啊");
					currentLamp = currentLamp.turnOff();
				}
			},
			10,
			10,
			TimeUnit.SECONDS
		);
	}
}
  














