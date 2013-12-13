package com.itheima.exam;

/**
 * 第5题： 创建一个包含有private的属性和private方法的类。
 * 		然后创建一个内部类，它有一个方法可用来修改外部类的属性，并调用外部类的方法。
 * 		在外部类的另一个方法中，创建此内部类的对象，并且调用它的方法。
 */
class Outer
{
	// 创建含有private的属性
	private int age;
	private String name;
	
	// 创建含有private的方法
	private void talk()
	{
		System.out.println("Hello,there! I am "+name+", my age is "+age);
	}
	
	void sayHello()
	{
		talk();
	}
	
	// 构造函数，初始化年龄和姓名
	Outer()
	{
		this.age = 21;
		this.name = "mskv";
	}

	// 创建内部类
	class Inner
	{
		void speak()
		{
			// 修改外部类(private)属性(姓名)
			name = "XiaoChuan";
			
			// 调用外部类的(private)方法
			talk();
		}
	}
	
	// 创建内部类对象并调用其speak方法
	void demo()
	{
		Inner in = new Inner();
		in.speak();
	}
}

public class Test5
{
	public static void main(String[] args)
	{
		// 验证以上类写法的正确性
		Outer t5 = new Outer();
		t5.sayHello();
		t5.demo();		
	}
}
