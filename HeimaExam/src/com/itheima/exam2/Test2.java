package com.itheima.exam2;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Test2 {

	/**
	 * 一个ArrayList对象aList中存有若干个字符串元素，
	 * 现欲遍历该ArrayList对象，删除其中所有值为"abc"的字符串元素，请用代码实现
	 */
	public static void main(String[] args) {
		// 测试代码
		List<String> aList = new ArrayList<String>();
		aList.add("abc");
		aList.add("lucy");
		aList.add("mike");
		aList.add("abc");
		aList.add("bad");
		aList.add("lajgoa");
		aList.add("abc");
		
		printStringArrayList(aList);
		aList = deleteElement(aList, "abc");
		printStringArrayList(aList);
	}

	// 打印ArrayList
	private static void printStringArrayList(List<String> aList) {
		Iterator<String> it = aList.iterator();
		for (; it.hasNext(); )
			System.out.println(it.next().toString());
		System.out.println("------------------------");
	}

	// 删除指定元素
	private static List<String> deleteElement(List<String> aList, String str) {
		List<String> list = new ArrayList<String>();
		Iterator<String> it = aList.iterator();
		for (; it.hasNext(); )
		{
			String temp = it.next();
			if (!temp.equals(str))
				list.add(temp);
		}
		return list;
	}

}
