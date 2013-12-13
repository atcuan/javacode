package com.itheima.exam2;

import java.lang.reflect.Method;

public class Test1 {

	/**
	 * 1、 方法中的内部类能不能访问方法中的局部变量，为什么？
	 * @throws Exception 
	 * @throws NoSuchMethodException 
	 */
	
	/*
	 * 答案：方法中的内部类不能访问方法中的局部变量。
	 * 如下面的例子所示。直接在方法的内部类中的方法中调用方法的局部变量会报错
	 * 报错信息为" Cannot refer to a non-final variable age inside an inner class defined in a different 
	 	method"
	 	由此我们可以在知道。如果要在方法的内部类中访问方法中的局部变量，该局部变量必须是final的才行。
	 	原因是：内部类对象的生命周期比局部变量要长。方法调用结束就销毁局部变量。而内部类和其他普通类一样。只有在
	 	虚拟机认为不再调用的时候才销毁它，完全有可能方法调用结束，然而内部类还需要接着调用的情况。只要内部类还活着
	 	它调用的局部变量就不能被销毁，所以必须加上final防止这种情况发生。
	 */
	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		
		// 直接调用
		Outer out = new Outer();
		Object obj = out.func(); // 返回内部类
		
		// 反射方式调用，方法已经调用完毕，局部变量已经销毁。接着调用内部类中的方法。
		Class clazz = obj.getClass();
		Method method = clazz.getMethod("printInfo", null);  // 为了演示方便，异常不处理，直接抛出
		method.invoke(obj, null);
	}
}

class Outer
{
	public Object func()
	{
		String name = "Lucy";
		final int age = 22;
		
		class Inner
		{
			public void printInfo()
			{
				//System.out.println("name="+name);// 编译报错
				System.out.println("age="+age);    // 编译通过
			}
		}
		
		Inner in = new Inner();
		in.printInfo();
		return in;
	}
}