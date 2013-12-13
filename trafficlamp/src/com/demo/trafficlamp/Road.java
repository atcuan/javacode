package com.demo.trafficlamp;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Road
{
	private String loadName;
	private List<String> Vehicles = new ArrayList<String>();
	
	public Road(String loadName)
	{
		this.setLoadName(loadName);
		this.init(); 
	}
	
	public String getLoadName()
	{
		return loadName;
	}
	
	public void setLoadName(String loadName)
	{
		this.loadName = loadName;
	}
	
	public void init()
	{
		createNewVehicle();
		crossing();
	}
	
	// 模拟需要过路由的车辆
	public void createNewVehicle()
	{
		ExecutorService pool =  Executors.newSingleThreadExecutor();
		pool.execute(new Runnable()
		{
			@Override
			public void run()
			{
				// 参生999辆车
				for (int i=0; i<1000; i++)
				{
					try
					{
						Thread.sleep((new Random().nextInt(10)+1) * 1000);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
					Vehicles.add(loadName+"-"+i);
				}
			}
		});
	}
	
	// 让车辆过十字路口
	public void crossing()
	{
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(
				new Runnable()
				{
					@Override
					public void run()
					{
						if (Vehicles.size() > 0)
						{
							boolean lighted = Lamp.valueOf(loadName).isLighted();
							if (lighted)
								System.out.println(Vehicles.remove(0)+"的车正在穿过路口");
						}
					}
				}, 
				1, 
				1, 
				TimeUnit.SECONDS);
	}
}
