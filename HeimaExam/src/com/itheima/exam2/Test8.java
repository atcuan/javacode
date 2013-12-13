package com.itheima.exam2;


public class Test8 {

	/**
	 * 	8、 有一个类为ClassA,有一个类为ClassB，在ClassB中有一个方法b，此方法抛出异常，在ClassA类中有一个方法a，
	 * 请在这个方法中调用b,然后抛出异常。
	 * 在客户端有一个类为TestC,有一个方法为c ,请在这个方法中捕捉异常的信息。完成这个例子，请说出java中针对异常的处理机制。
	 */
	
	
	/*	java中针对异常的处理机制:
	 *  分两种方式处理异常：抛出异常或者捕捉并处理异常
	 *  
	 *  抛出异常方式：
	 *  用throw和throws来实现。throw和throws会主动抛出一个异常，并且终止程序的运行,不处理异常。一般不建议这么做
	 *  throw使用在函数内，后跟的是异常对象。throws使用在函数上，后面跟的异常类，可以跟多个，用逗号隔开。
	 *  当前方法不知道如何处理这种类型的异常，该异常应该由上一级调用者处理，使用throws抛出异常。
	 *  如果main方法也不知道应该如何处理这种类型的异常，也可以使用使用throws声明抛出异常，该异常将交给JVM来处理。
	 *  当程序出现错误时，系统会自动抛出异常，java也允许程序自行抛出异常，可以抛出自定义的异常，可以增加丰富的提示信息
	 *  throw语句可以单独使用，throw语句抛出的不是异常类，而是一个异常实例，而且每次只能抛出一个异常实例。
	 *  throw常用于抛出运行时异常
	 *  
	 *  捕捉并处理异常方式：
	 *  主要使用try...catch语句来实现，其格式如下
	 *  try
	 *  {
	 *  	// 需要检测异常的代码
	 *  }
	 *  catch(Exception e) //Exception可以写为具体的异常类型
	 *  {
	 *  	// 异常处理代码
	 *  }
	 *  finally
	 *  {
	 *  	// 无论try和catch中是否出现异常，final中的语句都会执行。常用于关闭资源
	 *  }
	 *  finally 语句可以不写，但是try...catch是必须要写的
	 *  当某个程序块可能出现错多个异常时，可以用多个catch语句，每个catch语句捕获一种异常
	 *  需要注意的是，如果父类异常在子类异常前面的话，首先执行的是父类异常，子类异常将永远不会执行，
	 *  这样在程序运行的时候就会出现错误。所以子类异常应该放在父类前
	 */
	
	
	public static void main(String[] args){
		
//		ClassA ca = new ClassA();
//		ca.a();
		
		ClassC cc = new ClassC();
		cc.c();
	}

}

class ClassA
{
	// 抛出异常,不处理
	void a() throws ArithmeticException
	{
		ClassB cb = new ClassB();
		cb.b(5, 0);
	}
}

class ClassB
{
	// 声明异常，可能出现错误
	int  b(int a, int b) throws ArithmeticException
	{
		return a/b;
	}
}

class ClassC
{
	void c()
	{
		ClassB cb = new ClassB();
		// 捕捉异常
		try
		{
			cb.b(5, 0);
		}
		catch(ArithmeticException e)
		{
			e.printStackTrace();
			System.out.println("除数不能为零");
		}
	}
}