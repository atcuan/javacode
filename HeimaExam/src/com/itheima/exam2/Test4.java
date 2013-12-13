package com.itheima.exam2;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Test4 {

	/**
	 * 编写一个类，在main方法中定义一个Map对象（采用泛型），加入若干个对象，然后遍历并打印出各元素的key和value。
	 */
	public static void main(String[] args) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(24, "Kobe");  
        map.put(23, "Jordan");  
        map.put(3, "Paul");  
        map.put(16, "Paul");  
        
        printMap(map); 
	}

	private static void printMap(Map<Integer, String> map)  
    {  
        // 方法1：通过Map集合的Set<K> keySet()方法实现  
        printMapByKeySet(map);  
  
        // 方法2：通过Map集合的Set<Map.Entry<K,V>> entrySet()方法实现  
        // Entry其实就是Map接口中的内部接口  
        prinMapByEntrySet(map);  
 
    }  
  
    private static void prinMapByEntrySet(Map<Integer, String> map)  
    {  
        // 通过entrySet获取Map集合中的键值关系，存到set集合  
        Set<Map.Entry<Integer, String>> entrySet = map.entrySet();  
        // 通过set集合的迭代器遍历元素  
        for (Iterator<Map.Entry<Integer, String>> it = entrySet.iterator(); it  
                .hasNext();)  
        {  
            Map.Entry<Integer, String> me = it.next();  
            System.out.println("key="+me.getKey() + "|| value=" + me.getValue());  
        }  
        System.out.println("--------------------------------------");
    }  
  
    private static void printMapByKeySet(Map<Integer, String> map)  
    {  
        // 通过keySet获取map集合中的键集  
        Set<Integer> keySet = map.keySet();  
        // 通过迭代器取值  
        for (Iterator<Integer> it = keySet.iterator(); it.hasNext();)  
        {  
            Integer key = it.next();  
            String value = map.get(key);  
            System.out.println("key="+key + "| value=" + value);  
        }  
        System.out.println("--------------------------------------");
    }  
	

}
