package com.itheima.exam;

/*
 *  第6题：编写程序获取已知文件的扩展名. 注意: abc.txt的扩展名是txt, abc.java.txt的扩展名也是txt
 * */
public class Test6
{

	public static void main(String[] args)
	{
		String ext = getExtension("abc.java.txt");
		System.out.println("abc.txt的扩展名为：" +ext);
		System.out.println("abc.java.txt的扩展名为：" +ext);
	}
	
	public static String getExtension(String str)
	{
		// 由于"."的正则匹配为任意字符，所以先替换为","，然后拆分
		str = str.replace('.',',');
		String arr[] = str.split(",");
		int len = arr.length;
		return arr[len-1];
	}
}
