package com.itheima.exam2;

public class Test9 {

	/**
	 * 	9、 在一个类中编写一个方法，这个方法搜索一个字符数组中是否存在某个字符，
	 * 	如果存在，则返回这个字符在字符数组中第一次出现的位置（序号从0开始计算），
	 * 	否则，返回-1。要搜索的字符数组和字符都以参数形式传递传递给该方法，如果传入的数组为null，应抛出IllegalArgumentException异常。
	 * 	在类的main方法中以各种可能出现的情况测试验证该方法编写得是否正确，
	 * 	例如，字符不存在，字符存在，传入的数组为null等。
	 */
	public static void main(String[] args) throws IllegalArgumentException{
		char[] chs = "aebcdefabjkdialal`".toCharArray();
		char[] chsnull = null;
		
		System.out.println("第一次出现x的位置是："+searchChar(chs, 'x')); // 字符不存在
		System.out.println("第一次出现a的位置是："+searchChar(chs, 'a')); // 字符存在
		System.out.println("第一次出现b的位置是："+searchChar(chs, 'b')); // 字符存在
		System.out.println("第一次出现`的位置是："+searchChar(chs, '`'));	 // 字符存在
		System.out.println("第一次出现a的位置是："+searchChar(chsnull, 'a'));	 // 传入的数组为null
	}

	private static int searchChar(char[] chs, char ch) {
		if (chs == null)
			throw new IllegalArgumentException("数组不能为空");
		for (int i=0; i<chs.length; i++)
			if (ch == chs[i])
				return i;
		return -1;
	}
}
