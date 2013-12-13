package com.demo.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

public class UDPChatDemo
{

	public static void main(String[] args) throws SocketException
	{
		/* ÁÄÌì³ÌÐò */
		DatagramSocket send = new DatagramSocket(11111);
		DatagramSocket rece = new DatagramSocket(11211);
		
		new Thread(new Send(send)).start();
		new Thread(new Rece(rece)).start();
	}

}

class Send implements Runnable
{


	private DatagramSocket ds;
	public Send(DatagramSocket ds)
	{
		super();
		this.ds = ds;
	}
	
	@Override
	public void run()
	{
		try
		{
			BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
			String line = null;
			for (; (line=bufr.readLine()) != null;)
			{
				byte[] bbuf = line.getBytes();
				DatagramPacket dp = new DatagramPacket(bbuf, bbuf.length, InetAddress.getByName("127.0.0.1"), 11211);
				ds.send(dp);
				if ("done".equals(line))
					break;
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			ds.close();
		}
	}
	
}

class Rece implements Runnable
{
	private DatagramSocket ds;	
	
	public Rece(DatagramSocket ds)
	{
		super();
		this.ds = ds;
	}

	@Override
	public void run()
	{
		byte[] bbuf = new byte[1024];
		for (;;)
		{
			try
			{
				DatagramPacket dp = new DatagramPacket(bbuf, bbuf.length);
				ds.receive(dp);
				
				String ip = dp.getAddress().getHostAddress();
				String text = new String(dp.getData(), 0, dp.getLength());
				System.out.println(ip+":"+text);
				if ("done".equals(text))
					System.out.println("chat is over!");
			}
			catch (IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}