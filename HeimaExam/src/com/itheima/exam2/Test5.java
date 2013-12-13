package com.itheima.exam2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;


public class Test5 {
	private static final String FS = System.getProperty("file.separator");
	/**
	 * 自定义字符输入流的包装类，通过这个包装类对底层字符输入流进行包装，让程序通过这个包装类读取某个文本文件
	 * （例如，一个java源文件）时，能够在读取的每行前面都加上有行号和冒号。
	 */
	public static void main(String[] args){

		MyBufferedReaderDemo();
	
	}
	
	// 测试用例
	private static void MyBufferedReaderDemo()
	{
		File file = new File("src"+FS+"com"+FS+"itheima"+FS+"exam2"+FS+"Test5.java");
		MyBufferedReader mbr = null;
		try
		{
			mbr = new MyBufferedReader(new BufferedReader(new FileReader(file)));
			String line = null;
			// 设置行号从第一行起
			mbr.setLineNumber(0);
			for (; (line=mbr.myReadLine()) != null;)
				System.out.println(mbr.getLineNumber()+":"+line);
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (mbr != null)
			{
				try
				{
					mbr.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}
}


// 能够获取行号的装饰类(对Buffered装饰)
class MyBufferedReader extends Reader
{
	private int lineNumber;
	private BufferedReader reader;
	
	public MyBufferedReader(BufferedReader in)
	{
		this.reader = in;
	}

	public void setLineNumber(int lineNumber)
	{
		this.lineNumber = lineNumber;
	}

	public int getLineNumber()
	{
		return lineNumber;
	}
	
	public String myReadLine()
	{
		// 每读取一次，行号计数器加一
		lineNumber++;
		try
		{
			return reader.readLine();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int read(char[] cbuf, int off, int len) throws IOException
	{
		return reader.read(cbuf, off, len);
	}

	@Override
	public void close() throws IOException
	{
		reader.close();
	}
}