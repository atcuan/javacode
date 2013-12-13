package com.itheima.thread;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

public class ListDemo
{

	@Test
	public void itrTest()
	{
		List<String> list = new ArrayList<String>();
		list.add("2222");
		list.add("1111");
		list.add("3333");
		list.add("5555");
		
		Iterator it = list.iterator();
		for (; it.hasNext();)
		{
			String next = (String) it.next();
			if (next.equals("2222"))
				it.remove();
			else
			{
				System.out.println(next);
			}
		}
		
		System.out.println(list);
	}
}
