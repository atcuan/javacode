package com.itheima.newtech;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Collection;

public class ProxyDemo
{
	public static void main(String[] args)
	{
		Class clazzProxy = Proxy.getProxyClass(Collection.class.getClassLoader(), Collection.class);
		System.out.println(clazzProxy.getName());
	
		printProxyConstructors(clazzProxy);

		printProxyMethods(clazzProxy);
	
	}

	private static void printProxyMethods(Class clazzProxy)
	{
		System.out.println("----------------begain constructors list----------------");
		Method[] methods = clazzProxy.getMethods();
		for (Method method : methods)
		{
			String name = method.getName();
			StringBuilder sb = new StringBuilder(name+'(');
			Class[] clazzParams =method.getParameterTypes();
			for (Class clazzParm : clazzParams)
			{
				sb.append(clazzParm.getName()).append(',');
			}
			if (clazzParams != null && clazzParams.length != 0)
				sb.deleteCharAt(sb.length()-1);
			sb.append(')');
			System.out.println(sb.toString());
		}
	}

	private static void printProxyConstructors(Class clazzProxy)
	{
		System.out.println("----------------begain Methods list----------------:");
		Constructor[] cons = clazzProxy.getConstructors();
		for (Constructor con : cons)
		{
			String name = con.getName();
			StringBuilder sb = new StringBuilder(name+'(');
			Class[] clazzParams =con.getParameterTypes();
			for (Class clazzParm : clazzParams)
			{
				sb.append(clazzParm.getName()).append(',');
			}
			if (clazzParams != null && clazzParams.length != 0)
				sb.deleteCharAt(sb.length()-1);
			sb.append(')');
			System.out.println(sb.toString());
		}
	}

}


/*

// 实现一个动态代理类的过程：
// 1. 提供一个抽象角色即被代理类的接口：
public interface Subject {
    abstract public void request();
}

// 2. 通过抽象角色实现一个真实角色，即被代理类
public class RealSubject implements Subject {

    public RealSubject() {}

    public void request() {
        System.out.println("真实的请求.");
    }
}
// 3. 创建一个实现了接口InvocationHandler的类，并实现其invoke方法：
public class DynamicSubject implements InvocationHandler {
    private Object sub;

    public DynamicSubject() {}

    public DynamicSubject(Object obj) {
        sub = obj;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        Object returnValue = method.invoke(sub, args);
        return returnValue;
    }
}
// 4. 在客户端通过Proxy的静态方法newProxyInstance()创建一个代理，并通过代理调用方法：
// 创建被代理类
RealSubject rs = new RealSubject();
// 通过被代理类初始化一个代理处理器，在newProxyInstance()中作为参数生成代理
InvocationHandler ds = new DynamicSubject(rs);
Class<?> cls = rs.getClass();
//生成代理对象
Subject subject = (Subject) Proxy.newProxyInstance(
        cls.getClassLoader(), cls.getInterfaces(), ds);
//通过代理对象调用真实方法
subject.request();



*/






