package com.itheima.trafficlamp;


public enum Lamp 
{
	// 四个主要的灯
	S2N("N2S", "S2W", false),
	S2W("N2E", "E2W", false),
	E2W("W2E", "E2s", false),
	E2S("W2N", "N2S", false),
	
	// 与四个主要灯相对应的灯(方向相反)
	N2S(null ,null, false),
	N2E(null, null, false),
	W2E(null, null, false),
	W2N(null, null, false),
	
	// 右转向灯
	S2E(null, null, true),
	E2N(null, null, true),
	N2W(null, null, true),
	W2S(null, null, true);

	private boolean lighted;
	private String opposite;
	private String next;
	
	private Lamp(String opposite, String next, boolean lighted)
	{
		this.opposite = opposite;
		this.next = next;
		this.lighted = lighted;
		
	} 
	
	public boolean isLighted()
	{
		return lighted;
	}
	
	public void turnOn()
	{
		this.lighted = true;
		if (opposite != null)
		{	
			Lamp.valueOf(opposite).turnOn();
		}
		System.out.println(name()+" lmap is green, 下面总共应该有六个方向能看到汽车");
	}
	
	public Lamp turnOff()
	{
		this.lighted = false;
		if (opposite != null)
		{
			Lamp.valueOf(opposite).turnOff();
		}
		
		Lamp nextLamp = null;
		if (next != null)
		{
			nextLamp = Lamp.valueOf(next);
			System.out.println("绿灯从"+name()+ "-------->切换为"+ next);
			nextLamp.turnOn();
		}
		
		return nextLamp;
	}
	
}