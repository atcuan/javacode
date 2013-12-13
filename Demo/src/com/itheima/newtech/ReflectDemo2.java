package com.itheima.newtech;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ReflectDemo2
{

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		List list = new ArrayList();
		list.add("la");
		list.add("dad");
		list.add("gagd");
		list.add("adagd");
		printObj(list);

	}
	
	public static void printObj(Object obj)
	{
		Class clazz = obj.getClass();
		if (clazz.isArray())
		{
			int len = Array.getLength(obj);
			for (int i=0; i<len; i++)
				System.out.println(Array.get(obj ,i));
		}
		else
		{
			System.out.println(obj);
		}
	}

}
