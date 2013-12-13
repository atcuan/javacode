package com.itheima.thread;

public class ThreadCommunicationDemo
{
/*
 * 子线程循环 10 次， 接着主线程循环 20， 接着又回到子
 * 线程循环 10 次，接着再回到主线程又循环 100，如此循环50 次
 */
	public static void main(String[] args)
	{
//		demo1();
		runDemo();
	}
	
	private static void runDemo()
	{
		final Business b = new Business();
		
		//子线程
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				for (int i=0; i<50; i++)
					b.sub(i);
				
			}
		}).start();
		
		// 主线程
		for (int i=0; i<50; i++)
			b.main(i);
	}
	
	private static void demo1()
	{
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				for (int j=0; j<50; j++)
					synchronized (Thread.class)
					{
						for (int i=0; i<10; i++)
						{
							System.out.println("sub thread run "+i+" loop of " +j);
						}
					}
				
			}
		}).start();
		
		
		for (int j=0; j<50; j++)
			synchronized (Thread.class)
			{
				for (int i=0; i<10; i++)
				{
					System.out.println("main thread run "+i+" loop of " +j);
				}
			}
	}
}

class Business
{
	private boolean flag = true;
	
	public synchronized void sub(int j)
	{
		while (!flag)
		{
			try
			{
				this.wait();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
			
		for (int i=0; i<10; i++)
			System.out.println("sub thread run "+i+" time" +" of loop "+j);
		flag = false;
		this.notifyAll();
	}
	
	public synchronized void main(int j)
	{
		while (flag)
		{
			try
			{
				this.wait();
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
			
			for (int i=0; i<20; i++)
				System.out.println("main thread run "+i+" time" +" of loop "+j);
		flag = true;
		this.notifyAll();
	}
}



