package com.itheima.exam;

import java.io.*;

/*
 * 第8题：编写程序拷贝一个文件, 尽量使用效率高的方式.
 * */
public class Test8
{
	public static void main(String[] args)
	{
		// 测试复制文件
		Copy cp = new Copy();
		if (cp.copyFile("c:\\test.txt", "c:\\Test8.txt"))
			System.out.printf("复制文件成功");
		else
			System.out.printf("复制文件失败");
	}
}

class Copy
{
	// 建立关闭资源函数
	public void close(Object obj, String msg)
	{
		try 
		{
			if (obj != null)
				Closeable.class.cast(obj).close();
		}
		catch (IOException e)
		{
			throw new RuntimeException(msg);
		}
	}
	
	// 复制文件，使用缓冲区，提高效率
	public boolean copyFile(String src, String dst )
	{
		BufferedReader bufr = null;
		BufferedWriter bufw = null;
		
		try 
		{
			bufr = new BufferedReader(new FileReader(src));
			bufw = new BufferedWriter(new FileWriter(dst));
			
			// 写入读取出来的数据，采用每次读一行的方式
			String line=null;
			while ((line=bufr.readLine()) != null)
			{
				bufw.write(line);
				bufw.newLine();
				// 刷新内存，读一行，写一行
				bufw.flush();
			}
		}
		// 异常处理
		catch (IOException e)
		{
			throw new RuntimeException("复制文件失败！");
		}
		//关闭资源
		finally
		{
			close(bufr, "关闭读资源失败！");
			close(bufw, "关闭写资源失败！");
		}
		return true;
	}
}