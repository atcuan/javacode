package com.demo.string;

public class Demo
{

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		String str = "abd";
		String str2 = String.valueOf(true);
		Object obj = str2.getClass().getName();
		System.out.println(obj);
		System.out.println(str2);
		
		int a = 9;
		String b = "1121";
		Object val = Integer.valueOf(b); // ���ص���Integer
		Object obj2 = val.getClass().getName();
		System.out.println(obj2);
		
		Object val2 = Integer.parseInt(b);  //���ص���int
		System.out.println(val2.getClass().getName());
	}

}
