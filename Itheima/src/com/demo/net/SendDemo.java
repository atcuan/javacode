package com.demo.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class SendDemo
{

	public static void main(String[] args) throws IOException
	{
		/* ͨ��udpЭ�鷢��һ�����ݸ�ָ������ */
		
		// ����udp��socket����
		DatagramSocket ds = new DatagramSocket(10040);
		
		// ȷ�����;�������
		String str = "this is send by udp socket";
		byte[] bbuf = str.getBytes();
		
		// �������ݰ�����
		DatagramPacket dp = new DatagramPacket(bbuf, bbuf.length, InetAddress.getByName("127.0.0.1"), 10000);
		
		// ʹ��upd socket����send���������ݰ�����
		ds.send(dp);
		
		// ����Դ
		ds.close();
	}

}
