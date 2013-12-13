package com.itheima.bank;

import java.util.ArrayList;
import java.util.List;

public class NumberManager
{
	private int lastNumber = 0; 
	private List<Integer> queueNumber = new ArrayList<Integer>();
	
	public synchronized Integer generateNewNumber()
	{
		queueNumber.add(++lastNumber);
		return lastNumber;
	}
	
	public synchronized Integer fetchNumber()
	{
		if (queueNumber.size() > 0)
			return (Integer) queueNumber.remove(0);
		else
			return null;
	}
}
