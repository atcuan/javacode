package com.demo.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ReceDemo
{

	public static void main(String[] args) throws IOException
	{
		/* ���ܷ����������� */
		
		// ����upd socket�������
		DatagramSocket ds = new DatagramSocket(10039);
		
		//�������ݰ������ڴ洢���յ�������
		byte[] bbuf = new byte[1024];
		DatagramPacket dp = new DatagramPacket(bbuf, bbuf.length);
		
		// ��socket�����receive���������յ������ݴ洢�����ݰ���
		ds.receive(dp);
		
		// ��ȡip
		String ip = dp.getAddress().getHostAddress();
		int port = dp.getPort();
		
		// ��ȡ��������
		byte[] data = dp.getData();
		String text = new String(data, 0, dp.getLength());
		
		System.out.println(ip+":"+port+":"+text);
		
		ds.close();
	}

}
