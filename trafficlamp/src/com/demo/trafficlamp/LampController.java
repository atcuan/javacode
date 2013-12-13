package com.demo.trafficlamp;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class LampController
{
	private Lamp currentLamp;
	
	// 没隔10秒钟切换绿灯
	public LampController()
	{
		currentLamp = Lamp.W2E;
		currentLamp.turnGreen();
		
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(
				new Runnable()
				{
					@Override
					public void run()
					{
						currentLamp = currentLamp.turnRed(); 
					}
				}, 
				10, 10, TimeUnit.SECONDS);
	}
}
