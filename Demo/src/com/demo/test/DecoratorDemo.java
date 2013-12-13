package com.demo.test;

import org.junit.Test;

interface Human
{
	 void eat();
	 void sleep();
}

// 普通类
class Man implements Human
{
	@Override
	public void eat()
	{
		System.out.println("狼吞虎咽！");
	}

	@Override
	public void sleep()
	{
		System.out.println("爬倒就睡！");
	}
	public void work()
	{
		System.out.println("上班");
	}
}

// 普通类
class Woman implements Human
{
	@Override
	public void eat()
	{
		System.out.println("小口吃！");
	}

	@Override
	public void sleep()
	{
		System.out.println("优雅的睡！");
	}
	
	public void cook()
	{
		System.out.println("做饭");
	}
}

class Manager implements Human
{
	private Human h;

	public Manager()
	{
	}
	
	public Manager(Human h)
	{
		this.h = h;
	}
	
	@Override
	public void eat()
	{
		h.eat();
	}

	@Override
	public void sleep()
	{
		h.sleep();
	}
}

// 装饰类
// 对谁装饰，装饰类就持有谁的对象，通过构造器传递(单个类装饰)。
// 对一组对象装饰，装饰类就应该持有该组类的父接口的对象(实现该接口)。只能对接口中方法进行装饰，各子类特有的方法无法装饰。
// 也可以在装饰类中新增方法对被装饰类进行功能的扩展
class Nobody extends Manager
{
	public Nobody(Human h)
	{
		super(h);
	}
	
	@Override
	public void eat()
	{
		this.drink();
		System.out.println("吃饭了----");
		super.eat();
	}
	
	@Override
	public void sleep()
	{
		System.out.println("睡觉了-----");
		super.sleep();
	}
	
	// 扩展功能
	public void read()
	{
		System.out.println("看书");
	}
	
	public void drink()
	{
		System.out.println("喝茶");
	}
}

/*
	Human
	  |----Man
	  |----Woman
	  |----Manager
*/

public class DecoratorDemo 
{
	@Test
	public void testUsual()
	{
		// 普通对象
		Man m = new Man();
		m.eat();
		m.sleep();
		m.work(); //子类特有方法
		System.out.println("--------------------------");
	}
	
	@Test
	public void testDecorator()
	{
		// 装饰对象
		Nobody n = new Nobody(new Man());
		n.eat();
		n.sleep();
		n.read();
		// 装饰类，只能对装饰类和被装饰类共有的方法(父接口方法)进行装饰。装饰对象不能调用被装饰类特有方法。
		System.out.println("--------------------------");
		
		Nobody n1 = new Nobody(new Woman());
		n1.eat();
		n1.sleep();
		n1.read();
	}
}