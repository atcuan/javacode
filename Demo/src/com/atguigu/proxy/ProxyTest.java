package com.atguigu.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;

import org.junit.Test;

public class ProxyTest
{
	
	@Test
	public void testCount()
	{
		Count ci = new CountImp();
		ci.add(2, 4);
		ci.dem(4, 2);
		ci.div(6, 2);
		ci.mul(1, 4);
	}
	
	@Test
	public void proxyTest()
	{
		/*	public static Object newProxyInstance(ClassLoader loader,
                                      Class<?>[] interfaces,
                                      InvocationHandler h)
                               throws IllegalArgumentException
		 * 
		 * ClassLoader loader: 由动态代理产生的对象由哪个类加载器加载,即被代理的类的类加载器
		 * Class<?>[] interfaces：由动态代理类产生的对象必须实现的接口的Class数组
		 * InvocationHandler h：当具体调用代理对象方法时，将产生什么行为
		 */
		final Count ci2 = new CountImp2();
		
		ClassLoader loader = CountImp2.class.getClassLoader();
		Class<?>[] interfaces = CountImp2.class.getInterfaces();
		InvocationHandler h = new InvocationHandler()
		{
			@Override
			public Object invoke(Object proxy, Method method, Object[] args)
					throws Throwable
			{
				System.out.println("the "+method.getName()+" method begin with "+Arrays.asList(args));
				Object returnValue = method.invoke(ci2, args);
				System.out.println("the "+method.getName()+" method ends with "+returnValue);
				return returnValue;
			}
			
		};
		Count ciProxy = (Count) Proxy.newProxyInstance(loader, interfaces, h);
		int result = ciProxy.add(2, 4);
		System.out.println(result);
		ciProxy.div(6, 2);
		ciProxy.mul(1, 4);
	}
}
