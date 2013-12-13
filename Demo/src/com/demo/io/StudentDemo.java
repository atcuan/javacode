package com.demo.io;

import java.io.File;
import java.util.Comparator;
import java.util.Set;

public class StudentDemo
{
	private static final String FS = System.getProperty("file.separator");
	
	public static void main(String[] args)
	{
		/*
lili,25,56,78
Lusy,45,65,78
Matt,54,25,78
Micheal,45,85,36
Alex,54,65,25
done
		 * */
		
		/* 五个学生，三门成绩，键盘录入，安总成绩排序，存入文件中 */
		
		StudenTools st = new StudenTools();
		
		Set<Student> stuSet = st.getStudent(null);
		File file = new File("tempfiles"+FS+"stu.info");
		st.write2File(stuSet, file);
		
		/* 按第一门成绩排序， 比较器 */
		StudenTools st2 = new StudenTools();
		Set<Student> stuSetbyComp = st.getStudent(new ComparatorByMath());
		File file2 = new File("tempfiles"+FS+"stu.info");
		st2.write2File(stuSetbyComp, file2);
	}

}
