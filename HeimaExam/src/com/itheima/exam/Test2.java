package com.itheima.exam;

/**
 * 第2题：已知一个int数组, 编程从数组中获取最大数.
 */
public class Test2 
{
	public static void main(String[] args) 
	{
		// 定义一个数组
		int arr[] = new int[]{10,8,20,11,19};
		
		//调用方法
		int max = getMax(arr);
		
		//打印结果
		System.out.println("the max number is:"+max);
		
	}
	
	// 获取数组中最大值的方法
	public static int getMax(int[] arr)
	{
		int max = arr[0];
		for (int i=0; i<arr.length; i++)
		{
			if (arr[i] > max)
				max = arr[i];
		}
		return max;
	}
}
