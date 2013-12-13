package com.demo.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class StudenTools
{
	public Set<Student> getStudent()
	{
		return getStudent(null);
	}
	
	/*获取学生对象*/
	public Set<Student> getStudent(Comparator<Student> comp)
	{
		BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
		Set<Student> stu = null;
		if (comp == null)
			stu = new TreeSet<Student>();
		else
			stu = new TreeSet<Student>(comp);
		
		String line = null;
		try
		{
			for (; (line=bufr.readLine()) != null;)
			{
				if ("done".equals(line))
					break;
				
				String[] infoArr = line.split(",");
				if (infoArr.length !=4)
					throw new RuntimeException("输入格式错误！");
				Student tempStu = new Student(infoArr[0],
						Integer.parseInt(infoArr[1]),
						Integer.parseInt(infoArr[2]),
						Integer.parseInt(infoArr[3]));
				stu.add(tempStu);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (bufr != null)
				try
				{
					bufr.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
		}
		
		return stu;
	}
	
	/* 写入文件中 */
	public void write2File(Set<Student> stu, File file)
	{
		BufferedWriter bufw = null;
		try
		{
			bufw = new BufferedWriter(new FileWriter(file, true));
			for (Student s : stu)
			{
				bufw.append(s.toString());
				bufw.newLine();
				bufw.flush();
			}
			bufw.append("---------\n".toString());
			bufw.flush();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (bufw != null)
				try
				{
					bufw.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
		}
		System.out.println("写入成功");
	}
}
