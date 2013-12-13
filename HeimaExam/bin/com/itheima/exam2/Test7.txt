package com.itheima.exam2;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Test7 {

	private static final String FS = System.getProperty("file.separator");

	/**
		7、 把当前文件中的所有文本拷贝，存入一个txt文件，统计每个字符出现的次数并输出，例如：
		a：  21 次 
		b：  15 次
		c:： 15 次
		把：  7 次
		当：  9 次
		前：  3 次
		，：30 次
	 */
	public static void main(String[] args) {
		FileReader fr = null;
		FileWriter fw = null;
		try 
		{
			fr = new FileReader("src"+FS+"com"+FS+"itheima"+FS+"exam2"+FS+"Test7.java");
			fw = new FileWriter("src"+FS+"com"+FS+"itheima"+FS+"exam2"+FS+"Test7.txt");
			
			Map<Character, Integer> map = new HashMap<Character, Integer>();
			
			// 因为要统计字符个数，所以采用一次读取一个字符的方式
			int ch=0;
			for (; (ch=fr.read()) != -1;)
			{
				fw.write(ch);
				Integer value = map.get((char)ch);
				int count=0;
				if (value != null)
					count = value;
				count++;
				map.put((char) ch, count);
			}
			
			Set<Character> keySet = map.keySet(); 
			Iterator<Character> it = keySet.iterator();
			for (; it.hasNext(); )
			{
				Character key = it.next();
				Integer value = map.get(key);
				System.out.println(key+":"+value+"次");
			}
	
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch (IOException e1) 
		{
			e1.printStackTrace();
		}
		finally
		{
			if (fr != null)
				try
				{
					fr.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			
			if (fw != null)
				try
				{
					fw.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
		}
	}
}
