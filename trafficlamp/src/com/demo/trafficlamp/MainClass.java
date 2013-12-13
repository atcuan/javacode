package com.demo.trafficlamp;

public class MainClass
{
	public static void main(String[] args)
	{
		String[] dirs = new String[]{"W2E", "W2S", "N2S", "N2W", "E2W", "E2N", "S2N", "S2E", "W2N", "N2E", "E2S", "S2W"};
		for (int i=0; i<dirs.length; i++)
		{
			new Road(dirs[i]);
		}
		
		new LampController();
	}
}
