package com.demo.net;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class IPDemo
{

	public static void main(String[] args)
	{
		/* 将IP地址封装成对象 */
		InetAddress ip;
		try
		{
			ip = InetAddress.getLocalHost();
			String str_ip = ip.getHostAddress();
			String str_name = ip.getHostName();
			
			System.out.println(str_ip);
			System.out.println(str_name);
		}
		catch (UnknownHostException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
