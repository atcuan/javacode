package com.itheima.thread;

public class TicketDemo
{

	public static void main(String[] args)
	{
		Ticket t = new Ticket();
		
		new Thread(t).start();
		new Thread(t).start();
		new Thread(t).start();
		new Thread(t).start();
		new Thread(t).start();

		
	}

}

class Ticket implements Runnable
{
	private int count = 100;
	@Override
	public void run()
	{
			while (count > 0)
			{
				System.out.println(Thread.currentThread().getName()+" sale: "+count--);
			}
	
	}
}