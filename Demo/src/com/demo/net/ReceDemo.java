package com.demo.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReceDemo
{

	public static void main(String[] args) throws IOException
	{
		/* 接受发过来的数据 */
		
		// 创建upd socket服务对象
		DatagramSocket ds = new DatagramSocket(10039);
		
		//创建数据包，用于存储接收到的数据
		byte[] bbuf = new byte[1024];
		DatagramPacket dp = new DatagramPacket(bbuf, bbuf.length);
		
		// 用socket对象的receive方法将接收到的数据存储到数据包中
		ds.receive(dp);
		
		// 获取ip
		String ip = dp.getAddress().getHostAddress();
		int port = dp.getPort();
		
		// 获取数据内容
		byte[] data = dp.getData();
		String text = new String(data, 0, dp.getLength());
		
		System.out.println(ip+":"+port+":"+text);
		
		ds.close();
	}

}
