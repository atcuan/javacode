package com.itheima.newtech;

import java.util.Date;

public class ClassLoaderDemo
{
	public static void main(String[] args) throws Exception
	{
		//sun.misc.Launcher$AppClassLoader
		System.out.println(ClassLoaderDemo.class.getClassLoader().getClass().getName()); 
		
		//将上面语句的测试类改为System则抛NullPointerException，这两个类存放位置不同
		System.out.println(System.class.getClassLoader());	//null
		
		System.out.println("-----------------------------");
		
		// 类加载器的层次结构关系
		ClassLoader loader = ClassLoaderDemo.class.getClassLoader();
		for (; loader != null;)
		{
			System.out.println(loader.getClass().getName());
			loader = loader.getParent();
		}
		System.out.println(loader);
		
		//System.out.println(new ClassLoaderAttachment().toString());
		
		System.out.println("++++++-------------------++++++");
		Class clazz = new MyClassLoader("lib").loadClass("com.itheima.newtech.ClassLoaderAttachment");
		Date d = (Date) clazz.newInstance();
		System.out.println(d);
		
	}

}
