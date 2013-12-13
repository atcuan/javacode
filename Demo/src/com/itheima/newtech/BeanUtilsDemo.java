package com.itheima.newtech;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;


public class BeanUtilsDemo
{

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception
	{
		IntroSpectorDemoBean1 isdb = new IntroSpectorDemoBean1(22, "Mike");
		
		String propertyName = "age"; 
		
		// 使用BeanUtils时，JavaBean类必须具有Public属性，不然会报错找不到get/set方法
		System.out.println(BeanUtils.getProperty(isdb, propertyName));
		
		BeanUtils.setProperty(isdb, "age", 17);
		BeanUtils.setProperty(isdb, "age", "17"); //BeanUtils可以直接传字符型
		System.out.println(isdb.getAge());
		
		PropertyUtils.setProperty(isdb, "age", 19); // PropertyUtils参数类型一定要匹配
		System.out.println(isdb.getAge());
		
		BeanUtils.setProperty(isdb, "birth.time", "22458");
		System.out.println(BeanUtils.getProperty(isdb, "birth.time"));
	}

}

