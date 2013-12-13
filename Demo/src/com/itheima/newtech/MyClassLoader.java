package com.itheima.newtech;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class MyClassLoader extends ClassLoader
{
	
	private String classDir;
	public MyClassLoader()
	{
		
	}
	
	public MyClassLoader(String classDir)
	{
		this.classDir = classDir;
	}
	public static void main(String[] args) throws Exception
	{
		
		String srcPath = "D:\\JavaDemoCode\\Demo\\bin\\com\\itheima\\newtech\\ClassLoaderAttachment.class";/*args[0];*/
		String destPath = "lib";/*args[1];*/
		FileInputStream fis  = new FileInputStream(srcPath);
		String desFileName = srcPath.substring(srcPath.lastIndexOf("\\")+1);
		destPath = destPath +"\\"+ desFileName;
		FileOutputStream fos = new FileOutputStream(destPath);
		cypher(fis, fos);
		System.out.println(destPath);
		fis.close();
		fos.close();
	}
	
	public static void cypher(InputStream ins, OutputStream outs)
	{
		int b = -1;
		try
		{
			for (; (b=ins.read()) != -1;)
			{
				outs.write(b ^ 0xff);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException
	{
		String classFileName = classDir + "\\" + name.substring(name.lastIndexOf('.')+1) + ".class";
		try
		{
			FileInputStream fis  = new FileInputStream(classFileName);
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			cypher(fis, bos);
			fis.close();
			byte[] bys = bos.toByteArray();
			return defineClass(bys, 0, bys.length);
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		return super.findClass(name);
	}
	
	
}
