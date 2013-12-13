package com.demo.test;

public class Demo6
{
	public static void main(String[] args)
	{
		new Zi();
		final StringBuilder s = new StringBuilder();
		s.append("aaaa");
		
		String s1="a";
        String s2=s1+"b";
        String s3="a"+"b";
        String s4="ab";
        System.out.println(s2.equals(s3));
        System.out.println(s4==s3);
	}
}
class Fu
{
	public Fu()
	{
		System.out.println("fu run.");
	}
}

class Zi extends Fu
{

}
