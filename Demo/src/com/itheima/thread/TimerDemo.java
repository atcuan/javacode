package com.itheima.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class TimerDemo
{

	public static void main(String[] args)
	{
		Integer i1 = 1;
		Integer i2 = 1;
		System.out.println(i1 == i2);
		
		new Timer().schedule(new TimerTask(){
			@Override
			public void run()
			{
				System.out.println("爆炸");
				
			}}, 1000, 2000);
	}
	
}