package com.itheima.newtech;

public class EnumDemo
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		WeekDay day = WeekDay.SUN;
		System.out.println(day.name());
		System.out.println(day.ordinal());
		System.out.println(day.values().length);
	}

}

enum WeekDay
{
	SUN(1), MON(), TUE, WEN, THI, FRI, SAT;
	private WeekDay()
	{
		System.out.println("no args");
	}
	
	private WeekDay(int day)
	{
		System.out.println("have args");
	}
}

// 交通灯枚举类，抽象方法nextLamp
enum TrafficLamp
{
	RED(30)
	{
		@Override
		public TrafficLamp nextLamp()
		{
			return GREEN;
		}
		
	}, 
	GREEN(40)
	{
		@Override
		public TrafficLamp nextLamp()
		{
			return YELLOW;
		}
	}, 
	YELLOW(3)
	{
		@Override
		public TrafficLamp nextLamp()
		{
			return RED;
		}
	};
	public abstract TrafficLamp nextLamp();
	private int time;
	private TrafficLamp(int time)
	{
		this.time = time;
	}
}