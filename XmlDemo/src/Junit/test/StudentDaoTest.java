package Junit.test;

import org.junit.Test;

import com.demo.bean.Student;
import com.demo.dao.StudentDao;
import com.demo.exception.StudentNotExistException;

public class StudentDaoTest
{
	@Test
	public void testAdd()
	{
		StudentDao dao = new StudentDao();
		Student s = new Student();
		s.setExamid("1121");
		s.setGrade(99);
		s.setIdcard("1234");
		s.setLocation("成都");
		s.setName("Lesile");
		dao.add(s);
	}
	
	@Test
	public void testFind()
	{
		StudentDao dao = new StudentDao();
		Student s = dao.find("1121");
		System.out.println(s.getName());
	}
	
	@Test
	public void testDelete() throws StudentNotExistException
	{
		StudentDao dao = new StudentDao();
		dao.delete("Lesile");
	}
}
