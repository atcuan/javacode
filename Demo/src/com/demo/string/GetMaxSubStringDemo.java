package com.demo.string;

public class GetMaxSubStringDemo
{

	public static void main(String[] args)
	{
		/* 获取最大子串nbaabn */
		String str = "akganbaabnoaoejlanbaabdnba";
		String str2 = "nbaabnnbaabkdjlnbaailo";
		String max_sub = getMaxSubString(str, str2);
		System.out.println("the max sub string is :"+max_sub);
	}

	private static String getMaxSubString(String str1, String str2)
	{
		String max = str1.length() > str2.length() ? str1 : str2;
		String min = (max.equals(str1)) ? str2: str1;
		
		for (int i=0; i<min.length(); i++)
		{
			for (int j=0,k=min.length()-i; k!=min.length()+1; j++, k++)
			{
				if (max.contains(min.subSequence(j, k)))
					return min.substring(j, k);
			}
		}
		return null;
	}

}
