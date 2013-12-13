package com.demo.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FilterWriterDemo
{

	public static void main(String[] args)
	{
		/*
		 *  将指定目录下的指定后缀名文件列表写入到指定文件
		 */
		File dir 		= new File("D:\\Course\\itheima.com\\step1-java基础\\Code");
		String ends 	= "java";
		File destFile	= new File("d:\\list");
		
		listFiles2Doc(dir, ends, destFile);
	}

	private static void listFiles2Doc(File dir, String ends, File destFile)
	{
		FilenameFilter filte = getFilenameFilter(dir, ends);
		
		List<File> list = new ArrayList<File>();
		listFile(dir, filte, list);
		write2Doc(list, destFile);
		System.out.println("ok");
	}

	private static void write2Doc(List<File> list, File destFile)
	{
		if (!destFile.exists())
			try
			{
				destFile.createNewFile();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		
			BufferedWriter bufw = null;
		Iterator<File> it = list.iterator();
		try
		{
			bufw = new BufferedWriter(new FileWriter(destFile));
			for (; it.hasNext();)
			{
				bufw.write(it.next().getAbsolutePath());
				bufw.newLine();
				bufw.flush();
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	private static FilenameFilter getFilenameFilter(File dir, final String ends)
	{
		FilenameFilter filter = new FilenameFilter()
		{
			@Override
			public boolean accept(File dir, String name)
			{
				return name.endsWith("."+ends);
			}
		};
		
		return filter;
	}

	private static void listFile(File dir, FilenameFilter filter, List<File> list)
	{
		File[] files = dir.listFiles();
		if (files == null)
			return;
		
		for (File file : files)
		{
			if (file.isDirectory())
			{
				listFile(file, filter, list);
			}
			else if (filter.accept(dir, file.getName()))
				list.add(file);
		}
	}

}
