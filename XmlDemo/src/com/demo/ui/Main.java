package com.demo.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.demo.bean.Student;
import com.demo.dao.StudentDao;
import com.demo.exception.StudentNotExistException;


public class Main
{
	public static void main(String[] args)
	{
		System.out.print("添加学生(a) 删除学生(b) 查询学生(c)");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try
		{
			String type = br.readLine();
			if (type.equals("a"))
			{
				System.out.print("请输入学生姓名：");
				String name = br.readLine();
				
				System.out.print("请输入学生准考证号：");
				String examid = br.readLine();
				
				System.out.print("请输入学生准身份证号：：");
				String idcard = br.readLine();
				
				System.out.print("请输入学生所在地：");
				String location = br.readLine();
				
				System.out.print("请输入学生成绩：");
				String grade = br.readLine();
				
				Student s = new Student();
				s.setExamid(examid);
				s.setGrade(Double.parseDouble(grade));
				s.setIdcard(idcard);
				s.setLocation(location);
				s.setName(name);
				
				StudentDao dao = new StudentDao();
				dao.add(s);
				System.out.println("噢了");
				
			}
			else if (type.equals("b"))
			{
				System.out.print("请输入要删除的学生的姓名：");
				String name = br.readLine();
				
				StudentDao dao = new StudentDao();
				try
				{
					dao.delete(name);
				}
				catch (StudentNotExistException e)
				{
					System.out.println("你删除的用户不存在！");
				}
			}
			else if (type.equals("c"))
			{
				System.out.print("请输入要查询的学生的准考证号：");
				String examid = br.readLine();
				StudentDao dao = new StudentDao();
				dao.find(examid);
			}
			else
			{
				System.out.println("不支持您的操作！");
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
			System.out.println("程序出错了！");
		}
	
	
	}

}
