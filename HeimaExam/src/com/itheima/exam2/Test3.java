package com.itheima.exam2;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Test3 {

	/**
	 * 3、 	 定义一个交通灯枚举，包含红灯、绿灯、黄灯，需要有获得下一个灯的方法，
	 * 		例如：红灯获取下一个灯是绿灯，绿灯获取下一个灯是黄灯。
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new LampCtrl();
	}
}

enum Lamp{
	 GREEN("YELLOW", false), YELLOW("RED", false), RED("GREEN", false);
	
	private Lamp(String next, boolean lighted){
		this.next = next;
		this.lighted = lighted;
	}
	
	private String next;
	private boolean lighted;
	
	// 点亮灯
	public void ligth()
	{
		this.lighted = true;
		System.out.println("点亮"+name());
	}
	
	// 判断是否亮着
	public boolean isLighted()
	{
		return lighted;
	}
	
	// 当前灯熄灭，获取下一灯并点亮
	public Lamp getNext()
	{
		System.out.println(this+"状态为"+this.isLighted()+"马上熄灭");
		this.lighted = false;
		Lamp nextLamp = null;
		if (next != null)
		{
			nextLamp = Lamp.valueOf(next);
			nextLamp.ligth();
		}
		return nextLamp;
	}
}

class LampCtrl
{
	private Lamp currentLamp;
	public LampCtrl()
	{
		// 最开始让绿灯亮
		currentLamp = Lamp.GREEN;
		currentLamp.ligth();
		
		// 每隔一秒钟灭当前灯，亮下一个灯
		ScheduledExecutorService timer =  Executors.newScheduledThreadPool(1);
		timer.scheduleAtFixedRate(new Runnable()
		{
			@Override
			public void run() {
				currentLamp = currentLamp.getNext();
			}
			
		}, 1, 1, TimeUnit.SECONDS);
	}
}