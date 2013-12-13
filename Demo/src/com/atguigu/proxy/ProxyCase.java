package com.atguigu.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import org.junit.Test;

class Person
{
	private int id;
	private String name;

	public Person()
	{
		super();
	}
	
	public Person(int id, String name)
	{
		this.id = id;
		this.name = name;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	@Override
	public String toString()
	{
		return "Person [id=" + id + ", name=" + name + "]";
	}
}

interface Service
{
	void add(Person p);
	void delete(int id);
	void update(Person p);
}

class ServiceImp implements Service
{
	private Map<Integer, Person> persons = new HashMap<Integer, Person>();
	
	public ServiceImp()
	{
		persons.put(1001, new Person(1001, "Lily"));
		persons.put(1002, new Person(1002, "Mike"));
	}
	
	public Map<Integer, Person> getPersons()
	{
		return persons;
	}
	
	@Override
	public void add(Person p)
	{
		persons.put(p.getId(), p);
	}

	@Override
	public void delete(int id)
	{
		if (id == 1001)
			throw new RuntimeException("1001不能删除");
		persons.remove(id);
	}

	@Override
	public void update(Person p)
	{
		persons.put(p.getId(), p);
	}
}

class PersonServiceProxy
{
	private Service target;

	public PersonServiceProxy(Service target)
	{
		this.target = target;
	}
	
	public Service getPersonServiceProxy()
	{
		Service proxy = (Service) Proxy.newProxyInstance(target.getClass().getClassLoader(), 
				target.getClass().getInterfaces(), 
				new InvocationHandler()
				{
					@Override
					public Object invoke(Object proxy, Method method, Object[] args)
							throws Throwable
					{
						System.out.println("开启事务");
						try
						{
							Object result = method.invoke(target, args);
							System.out.println("提交事务");
							return result;
						}
						catch (Exception e)
						{
							e.printStackTrace();
							System.out.println("回滚事务");
						}
						return null;
					}
				});
		return proxy;
	}
}

public class ProxyCase
{
	@Test
	public void testPersonSerice()
	{
		Service target = new ServiceImp();
		Service proxy = new PersonServiceProxy(target).getPersonServiceProxy();
		
		System.out.println(((ServiceImp) target).getPersons());
		proxy.add(new Person(1003, "Matt"));
		System.out.println(((ServiceImp) target).getPersons());
		
		System.out.println(((ServiceImp) target).getPersons());
		proxy.update(new Person(1003, "Mary"));
		System.out.println(((ServiceImp) target).getPersons());
		
		System.out.println(((ServiceImp) target).getPersons());
		proxy.delete(1002);
		System.out.println(((ServiceImp) target).getPersons());
		
		System.out.println(((ServiceImp) target).getPersons());
		proxy.delete(1001);
		System.out.println(((ServiceImp) target).getPersons());
	}
}
