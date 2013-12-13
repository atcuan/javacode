package com.itheima.bank;

public class NumberMachine
{
	private static NumberMachine instance = new NumberMachine();
	
	
	private NumberManager commonManager  = new NumberManager();
	private NumberManager expressManager = new NumberManager();
	private NumberManager vipManager     = new NumberManager();

	private NumberMachine(){}
	public static NumberMachine getInstance()
	{
		return instance;
	}

	public NumberManager getCommonManager()
	{
		return commonManager;
	}

	public NumberManager getExpressManager() 
	{
		return expressManager;
	}


	public NumberManager getVipManager()
	{
		return vipManager;
	}
}
