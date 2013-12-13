package com.demo.test;

import java.io.*;
import java.util.*;

public class Demo4
{
	public static void main(String[] args) throws Exception
	{
		List list = new ArrayList();
		File res = new File("d:\\test");
		File des = new File("d:\\test\\dir");
		showDir(res, list);
		copyFiles(list, des);
	}

	public static void showDir(File dir, List<File> list) throws Exception
	{

		File[] files = dir.listFiles();
		for (File file : files)
		{
			if (file.isDirectory())// 判断是否为文件夹
				showDir(file, list);// 是文件夹，递归
			else
				list.add(file);
		}
	}

	private static void copyFiles(List<File> list, File dir) throws Exception
	{
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		Iterator it = list.iterator();
		for (; it.hasNext();)
		{
			File res = new File(it.next().toString());
			File des = new File(dir, res.getName());
			des.createNewFile();
			bis = new BufferedInputStream(new FileInputStream(res));
			bos = new BufferedOutputStream(new FileOutputStream(des));
			copy(bis, bos);
			System.out.println("ok");
		}
	}

	private static void copy(BufferedInputStream bis, BufferedOutputStream bos) throws Exception
	{
		
		byte[] buf = new byte[1024*8];
		int len = 0;
		while((len=bis.read(buf))!=-1){
			bos.write(buf,0,len);
		}
		bis.close();
		bos.close();
	}

}
