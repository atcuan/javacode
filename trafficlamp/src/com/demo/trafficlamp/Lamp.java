package com.demo.trafficlamp;


/*
W2E, W2S, N2S, N2W,
E2W, E2N, S2N, S2E,
W2N, N2E, E2S, S2W
*/
public enum Lamp
{
	/* 四个主要方向 */
	W2E("E2W", "W2S", false), W2S("E2N", "N2S", false), N2S("S2N", "N2W", false), N2W("S2E", "W2E", false),
	
	/* 四个主要方向对应的相反方向 */ 
	E2W(null, null, false), E2N(null, null, false), S2N(null, null, false), S2E(null, null, false),
	
	/* 右转向 */
	W2N(null, null, true), N2E(null, null, true), E2S(null, null, true), S2W(null, null, true);
	
	private boolean lighted;
	private String opposite;
	private String next;
	
	private Lamp(){}
	private Lamp(String opposite, String next, boolean lighted)
	{
		this.opposite 	= opposite;
		this.next 		= next;
		this.lighted 	= lighted;
	}
	
	public boolean isLighted()
	{
		return lighted;
	}
	
	public void light()
	{
		this.lighted = true;
	}
	
	// 点绿当前方向灯，相反方向灯也点绿
	public void turnGreen()
	{
		this.lighted = true;
		if (opposite != null)
			Lamp.valueOf(opposite).turnGreen();
		System.out.println(name()+"灯已经变绿");
	}
	
	// 点红当前灯，相反方向灯也点红，下一个方向灯点绿
	public Lamp turnRed()
	{
		this.lighted = false;
		if (opposite != null)
			Lamp.valueOf(opposite).turnRed();
		Lamp nextLamp = null;
		if (next != null)
		{
			nextLamp = Lamp.valueOf(next);
			System.out.println("绿灯从"+name()+"切换为"+next);
			nextLamp.turnGreen();
		}
		return nextLamp;
	}
}
