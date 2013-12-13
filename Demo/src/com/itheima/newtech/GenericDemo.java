package com.itheima.newtech;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Date;
import java.util.Vector;

public class GenericDemo
{
	public static void main(String[] args) throws Exception
	{
		Method applyVectorMethod = GenericDemo.class.getMethod("applyVector", Vector.class);
		Type[] types = applyVectorMethod.getGenericParameterTypes();
		ParameterizedType pType = (ParameterizedType) types[0];
		System.out.println(pType.getRawType());
		System.out.println(pType.getActualTypeArguments()[0]);
	}
	
	public static void applyVector (Vector<Date> v){}
	
	public static void printCollection( Collection<?> coll) //上限
	{
		for (Object obj : coll)
			System.out.println(obj);
	}

}

