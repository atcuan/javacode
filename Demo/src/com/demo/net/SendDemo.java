package com.demo.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SendDemo
{

	public static void main(String[] args) throws IOException
	{
		/* 通过udp协议发送一段数据给指定主机 */
		
		// 建立udp的socket服务
		DatagramSocket ds = new DatagramSocket(10040);
		
		// 确定发送具体数据
		String str = "this is send by udp socket";
		byte[] bbuf = str.getBytes();
		
		// 创建数据包对象
		DatagramPacket dp = new DatagramPacket(bbuf, bbuf.length, InetAddress.getByName("127.0.0.1"), 10000);
		
		// 使用upd socket服务send方法将数据包发出
		ds.send(dp);
		
		// 光资源
		ds.close();
	}

}
