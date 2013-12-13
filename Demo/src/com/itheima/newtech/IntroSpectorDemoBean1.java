package com.itheima.newtech;

import java.util.Date;


 public class IntroSpectorDemoBean1
{
	private Date birth = new Date();
	private int age;
	public String name;
	

	public Date getBirth()
	{
		return birth;
	}

	public void setBirth(Date birth)
	{
		this.birth = birth;
	}

	public IntroSpectorDemoBean1()
	{
		super();
	}

	public IntroSpectorDemoBean1(int age, String name)
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