package com.demo.dao;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.demo.bean.Student;
import com.demo.exception.StudentNotExistException;
import com.demo.utils.XmlUtils;

public class StudentDao
{
	public void add(Student s)
	{
		try
		{
			Document doc = XmlUtils.getDocument();
			
			// 创建封装学生信息的标签
			Element stuTag = doc.createElement("student");
			stuTag.setAttribute("idcard", s.getIdcard());
			stuTag.setAttribute("examid", s.getExamid());
			
			// 创建封装学生姓名，所在地，成绩的标签
			Element name = doc.createElement("name");
			Element location = doc.createElement("location");
			Element grade = doc.createElement("grade");
			
			name.setTextContent(s.getName());
			location.setTextContent(s.getLocation());
			grade.setTextContent(String.valueOf(s.getGrade()));
			
			// 把封装好的信息挂在到dom节点
			stuTag.appendChild(name);
			stuTag.appendChild(location);
			stuTag.appendChild(grade);
			
			doc.getElementsByTagName("exam").item(0).appendChild(stuTag);
			
			// 写回到本地文档
			XmlUtils.write2Xml(doc);
			
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		} 
		
	}
	
	public Student find(String examid)
	{
		try
		{
			Document doc = XmlUtils.getDocument();
			NodeList list = doc.getElementsByTagName("student");
			for (int i=0; i<list.getLength(); i++)
			{
				Element stuTag = (Element) list.item(i);
				if (stuTag.getAttribute("examid").equals(examid))
				{
					// 找到匹配，封裝學生信息並返回
					Student s = new Student();
					s.setExamid(examid);
					s.setIdcard(stuTag.getAttribute("idcard"));
					s.setName(stuTag.getElementsByTagName("name").item(0).getTextContent());
					s.setLocation(stuTag.getElementsByTagName("location").item(0).getTextContent());
					s.setGrade(Double.parseDouble(stuTag.getElementsByTagName("grade").item(0).getTextContent()));
					
					return s;
				}
			}
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}

		return null;
	}
	
	public void delete(String name) throws StudentNotExistException
	{
		try
		{
			Document doc = XmlUtils.getDocument();
			NodeList list = doc.getElementsByTagName("name");
			
			for (int i=0; i<list.getLength(); i++)
			{
				if (list.item(i).getTextContent().equals(name))
				{
					list.item(i).getParentNode().getParentNode().removeChild(list.item(i).getParentNode());
					
					// 写回到本地文档
					XmlUtils.write2Xml(doc);
					return;
				}
			}
			
			throw new StudentNotExistException(name+"不存在");
		}
		catch (StudentNotExistException e)
		{
			throw e;
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
	}
}
