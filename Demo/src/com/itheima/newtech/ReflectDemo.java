package com.itheima.newtech;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectDemo
{
	public static void main(String[] args) throws SecurityException, Exception
	{
		Class clazz = Class.forName("com.itheima.newtech.Person");
		
		// Constructor
		Constructor cons = clazz.getConstructor(null);  
		cons.newInstance(null);
		
		Constructor cons2 = clazz.getConstructor(String.class, String.class);  
		Person p = (Person) cons2.newInstance("Lucy", "famale");
		System.out.println(p.getName()+":"+p.getSex());
		
		// Field 
		Field nameField = clazz.getField("name");
		System.out.println(nameField.getType());
		Object obj = nameField.get(p);
		System.out.println(obj);
		
		Field sexField = clazz.getDeclaredField("sex");
		sexField.setAccessible(true);
		sexField.set(p, "male");
		Object obj2 = sexField.get(p);
		System.out.println(obj2);
		
		// Method
		Method method = clazz.getMethod("setName", String.class);
		method.invoke(p, "Mike");
		System.out.println(p.getName());
		
		Method mainMethod = clazz.getMethod("main", String[].class); //main method reflect
		mainMethod.invoke(null, (Object)new String[]{"balabala", "bashbash", "call me maybe"});
	}
}

class Person
{
	public String name;
	private String sex;
	
	public static void main(String[] args)
	{
		System.out.println("Person main function run");
		for (String arg : args)
			System.out.println(arg);
	}
	
	public Person()
	{
		super();
		System.out.println("Person run");
	}
	
	public Person(String name, String sex)
	{
		super();
		this.name = name;
		this.sex = sex;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getSex()
	{
		return sex;
	}

	public void setSex(String sex)
	{
		this.sex = sex;
	}
}
