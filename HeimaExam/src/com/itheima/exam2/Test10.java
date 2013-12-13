package com.itheima.exam2;

public class Test10 {

	/**
	 * 	10、  金额转换，阿拉伯数字转换成中国传统形式。
 				例如：101000001010   转换为    
 				壹仟零壹拾亿零壹仟零壹拾圆整
	 */
	public static void main(String[] args)
	{
		// demo1
		String str = "101000001010"; 
		MoneyConvertToChinese(str);  // 壹仟零壹拾亿零壹仟零壹拾圆整
		
		// demo2 
		String str2 = "101000001010.00"; 
		MoneyConvertToChinese(str2);  // 壹仟零壹拾亿零壹仟零壹拾圆整
		
		// demo3
		String str3 = "101000001010.01"; 
		MoneyConvertToChinese(str3);  // 壹仟零壹拾亿零壹仟零壹拾圆零壹分
		
		// demo4
		String str4 = "101000001010.10"; 
		MoneyConvertToChinese(str4);  // 壹仟零壹拾亿零壹仟零壹拾圆壹角
		
		// demo5
		String str5 = "1234567891234"; 
		MoneyConvertToChinese(str5); // 壹万贰仟叁佰肆拾伍亿陆仟柒佰捌拾玖万壹仟贰佰叁拾肆圆整
		
		// demo6
		String str6= "1002"; 
		MoneyConvertToChinese(str6); // 壹仟零贰圆整
		
		
		
		
	}

	private static void MoneyConvertToChinese(String str)
	{

		// 转换为中文形式
		String strChinese = convertMoney(str);
		
		// 格式化转换后的字符串，去除重复零，加上缺少的零，去除不该存在的单位
		strChinese = formatStringMoney(strChinese);
		
		System.out.println(str+" 中文形式为："+strChinese);
	}

	private static String convertMoney(String str)
	{
		
		// 是否存在小数
		boolean flag = str.contains(".");
		String strInt = "0";
		String strDec = "0";
		if (!flag)
		{
			strInt = str;
		}
		else
		{
			str = str.replaceAll("\\.+", ",");
			String[] strArr = str.split(",");
			if (strArr.length >2)
				throw new RuntimeException("请检查格式时候正确！");
			strInt = strArr[0]; // 整数部分
			strDec = strArr[1]; // 小数部分
		}
		
		// 测传入字符串可是时候合格
		checkString(strInt);
		checkString(strDec);
		
		
		return dealMoney(strInt, strDec);
	}

	// 检测传入的字符是否符合要求
	private static void checkString(String str)
	{
		char[] chs = str.toCharArray();
		for (int i=0; i< chs.length; i++)
		{
			if (chs[i] < '0' && chs[i]> '9')
				throw new RuntimeException("哥们，不要乱来,字符必须为数字");
		}
		
	}
	
	// 根据钱的位数来转换为中文形式
	private static String dealMoney(String strInt, String strDec)
	{
		// 先处理小数部分，只处理小数位数最多两位
		int lenDec = strDec.length();
		if (lenDec > 2)
			throw new RuntimeException("小数位数超过两位，请修改后重新运行！");
		
		int moneyDec = Integer.parseInt(strDec);
		if (moneyDec == 0)
			strDec = "";
		else
			strDec = convertDec(moneyDec);	
		
		// 处理整数部分
		int len = strInt.length();
		if (len<=8 && len>=1) //处理亿元以下的钱
		{
			int money = Integer.parseInt(strInt);
			String con_str1 = convert(money, '圆');
			
			return con_str1+strDec;
		}
		else if (len >8 && len<14) // 处理亿元以上的钱
		{
			// 先处理上亿的钱
			String sub_hight = strInt.substring(0, len-8);
			int moeny_high = Integer.parseInt(sub_hight);
			String con_str2 = convert(moeny_high, '亿');
			
			// 然后处理亿元以下的钱
			String sub_low = strInt.substring(len-8 ,len);
			sub_low = convertMoney(sub_low);
			
			// 结合在一起返回值
			return con_str2+sub_low+strDec;
		}
		else //超过万亿不考虑了
		{
			System.out.println("兄弟，有这么多钱吗？？？");
			return null;
		}
	}

	// 按单位转换现有的钱小数部分
	private static String convertDec(int moneyDec)
	{	
		StringBuilder sb = new StringBuilder();
		int mod = moneyDec%10;
		sb.append(converNumber(mod)+"分");
		moneyDec /= 10;
		sb.insert(0, converNumber(moneyDec)+"角");
		return sb.toString();
	}

	// 按单位转换现有的钱整数部分(单位为：圆拾佰仟万拾佰仟亿拾佰仟万)
	private static String convert(int money, char ch)
	{
		StringBuilder unit = new StringBuilder("拾佰仟万拾佰仟亿");
		unit.insert(0, ch);
		char[] units = unit.toString().toCharArray();
		
		StringBuilder sb = new StringBuilder();
		int i = 0;
		while(money != 0)
		{
			sb.insert(0, units[i++]); //插入单位
			int mod = money%10;
			sb.insert(0, converNumber(mod)); // 插入对应单位的数字
			money /= 10; //去除最后一位
		}
		
		return sb.toString();
	}
	
	// 格式化转换好的钱。
	private static String formatStringMoney(String str)
	{
		StringBuilder sb = new StringBuilder(str);
		
		// 去除不该存在的零或者单位
		int index = sb.indexOf("零", 0); 
		while  (index != -1)
		{
			char ch = sb.charAt(index+1);
			
			if (ch != '亿' && ch!= '万' && ch != '圆')
				sb.deleteCharAt(index+1);
			else
				sb.deleteCharAt(index);
			
			index = sb.indexOf("零", index+1);
		}
		
		// 去重零
		sb = DeleteRepetZero(sb);
		
		// 加零
		if (sb.indexOf("万") == -1) //万为0
		{
			int index_yi = sb.indexOf("亿", 0);
			if (index_yi != -1)
				sb = sb.insert(index_yi+1, "零");
		}
		
		String retStr = sb.toString();
		if (retStr.contains("角") || retStr.contains("分"))
			return retStr;
		else
			return retStr+"整";
	}

	// 去除重复的零和最后一个零
	private static StringBuilder DeleteRepetZero(StringBuilder sb)
	{
		StringBuilder sbb = new StringBuilder();
		String str;
		str = sb.toString();
		
		// 多个连着的零变为一个零
		str = str.replaceAll("(零)\\1+", "$1");
		
		// 去除万，亿，圆前面的零
		str = str.replaceAll("(零)(万)", "$2");
		str = str.replaceAll("(零)(亿)", "$2");
		str = str.replaceAll("(零)(圆)", "$2");
		sbb.append(str);
		
		if (str.endsWith("零"))
			sbb.delete(sbb.length()-1, sbb.length());
		return sbb;
	}
	
	 // 阿拉伯数字换成中文数字
	private static char converNumber(int temp)
	{
		switch (temp)
		{
			case 0: return '零';
			case 1: return '壹';
			case 2: return '贰';
			case 3: return '叁';
			case 4: return '肆';
			case 5: return '伍';
			case 6: return '陆';
			case 7: return '柒';
			case 8: return '捌';
			case 9: return '玖';
		}
		return 0;
	}
}