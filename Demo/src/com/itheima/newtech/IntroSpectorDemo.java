package com.itheima.newtech;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;

public class IntroSpectorDemo
{

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception
	{
		// 内省的方法操作属性
		
		IntroSpectorDemoBean isdb = new IntroSpectorDemoBean(22, "Mike");
		
		String propertyName = "age"; 
		PropertyDescriptor pd = new PropertyDescriptor(propertyName, isdb.getClass());
		
		Method methodGetAge = pd.getReadMethod();
		Object returnValue = methodGetAge.invoke(isdb);
		System.out.println(returnValue);
		
		Method methodSetAge = pd.getWriteMethod();
		methodSetAge.invoke(isdb, 18);
		System.out.println(isdb.getAge());
		
		// 复杂一点的get方法
		BeanInfo beanInfo = Introspector.getBeanInfo(isdb.getClass());
		PropertyDescriptor[] props = beanInfo.getPropertyDescriptors();
		Object returnVal = null;
		for (PropertyDescriptor pd1: props)
		{
			if (pd1.getName().equals(propertyName))
			{
				Method methodGetAge1 = pd1.getReadMethod();
				returnVal = methodGetAge1.invoke(isdb);
				break;
			}
		}
		System.out.println(returnVal);
		
		System.out.println(isdb);
		BeanUtils.getProperty(isdb, propertyName);
	}

}

class IntroSpectorDemoBean
{
	private int age;
	public String name;
	
	public IntroSpectorDemoBean()
	{
		super();
	}

	public IntroSpectorDemoBean(int age, String name)
	{
		super();
		this.age = age;
		this.name = name;
	}
	public int getAge()
	{
		return age;
	}
	public void setAge(int age)
	{
		this.age = age;
	}
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	
}