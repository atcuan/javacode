package com.itheima.exam;

import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;

/*	第10题：
 * 定义一个学生类, 需要有姓名, 年龄, 考试成绩三个成员属性，创建5个对象, 
 * 属性可为任意值. 编程对这5个对象按成绩排序，并将结果输出。
 * （提示，用TreeSet和Comparator实现）
 * */
public class Test10
{
	public static void main(String[] args)
	{
		TreeSet<Student> ts = new TreeSet<Student>(new ComparatorByScore());
		ts.add(new Student("mike", 22, 77));
		ts.add(new Student("matt", 25, 87));
		ts.add(new Student("lucy", 23, 82));
		ts.add(new Student("rose", 26, 77));
		ts.add(new Student("shane", 24, 92));
		
		// 遍历并打印结果
		Iterator<Student> it = ts.iterator();
		while (it.hasNext())
		{
			Student stu = it.next();
			System.out.println(stu.getName()+":"+stu.getScore());
		}
	}
}

class Student 
{
	String name;
	int age;
	int score;

	public String getName()
	{
		return name;
	}
	
	public int getScore()
	{
		return score;
	}
	
	Student(String name, int age, int score)
	{
		this.name = name;
		this.age = age;
		this.score = score;
	}
}

/*class ComparatorByScore implements Comparator<Object>
{
	public int compare(Object o1, Object o2)
	{
		Student stu1 = (Student)o1;
		Student stu2 = (Student)o2;
		
		int tmp = stu1.getScore() - stu2.getScore();
		if (tmp>0)
			return -1;
		else if (tmp<0)
			return 1;
		// 不考虑同成绩同名同岁的情况
		return stu1.getName().compareTo(stu2.getName());
	}
}
*/

// 使用成绩来排序
class ComparatorByScore implements Comparator<Student>
{
	public int compare(Student stu1, Student stu2)
	{
		int tmp = stu1.getScore() - stu2.getScore();
		if (tmp>0)
			return -1;
		else if (tmp<0)
			return 1;
		// 不考虑同成绩同名同岁的情况，成绩相同时使用名字自然数排序
		return stu1.getName().compareTo(stu2.getName());
	}
}












