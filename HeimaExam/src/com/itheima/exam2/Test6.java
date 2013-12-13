package com.itheima.exam2;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Test6 {

	/**
	 * 定义一个标准的JavaBean，名叫Person，包含属性name、age。使用反射的方式创建一个实例、
	 * 调用构造函数初始化name、age，
	 * 使用反射方式调用setName方法对名称进行设置，
	 * 不使用setAge方法直接使用反射方式对age赋值。 
	 */
	public static void main(String[] args) throws Exception{
		
		// 调用构造函数初始化name、age
		String className = "com.itheima.exam2.Person";
		@SuppressWarnings("unchecked")
		Class<Person> clazz = (Class<Person>) Class.forName(className);
		Constructor<Person> constructor =  clazz.getConstructor(String.class, int.class);
		Object p = constructor.newInstance("Mike", 22);
		System.out.println(p);
		
		
		// 使用反射方式调用setName方法对名称进行设置
		@SuppressWarnings("rawtypes")
		Class clazz2 = Person.class;
		@SuppressWarnings("unchecked")
		Method method = clazz2.getMethod("setName", String.class);
		Object obj2 = clazz2.newInstance();
		method.invoke(obj2, "Kimi");
		System.out.println(obj2);
		
		// 不使用setAge方法直接使用反射方式对age赋值
		Person p2 = new Person();
		@SuppressWarnings("unchecked")
		Class<Person> clazz3 = (Class<Person>) p2.getClass();
		Field ageField = clazz3.getDeclaredField("age");
		Object obj = clazz3.newInstance();
		ageField.setAccessible(true);
		ageField.set(obj, 22);
		System.out.println(obj);
	}

}

class Person
{
	private String name;
	private int age;
	
	public Person()
	{
		super();
	}
	
	public Person(String name, int age)
	{
		super();
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [name=" + name + ", age=" + age + "]";
	}
	
}