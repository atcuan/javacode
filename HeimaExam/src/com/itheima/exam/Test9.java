package com.itheima.exam;
/*	第9题：
 * 编程实现：猫和狗都会叫，但猫是喵喵的叫，狗是汪汪的叫？
 * 定义一个动物类，在动物类(animal)中有一个叫的抽象方法。 
 * 写两个子类，一个猫一个狗，继承自动物类，并实现相应的抽象方法。
 * */
public class Test9
{
	public static void main(String[] args)
	{
		Cat cat = new Cat();
		cat.speak();
		Dog dog = new Dog();
		dog.speak();
	}


}

abstract class Animal
{
	public abstract void speak();
}

class Cat extends Animal
{
	public void speak()
	{
		System.out.println("miao,miao,miao");
	}
}

class Dog extends Animal
{
	public void speak()
	{
		System.out.println("wang,wang,wang");
	}
}