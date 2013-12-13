package com.itheima.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/*
 * 子线程1循环 10 次，子线程2循环 15 次， 接着主线程循环 20， 接着又回到子
 * 线程1循环 10 次，接着子线程2循环 15 次，接着再回到主线程又循环 100，如此循环50 次
 */
public class LockCondition2
{
	public static void main(String[] args)
	{
		runDemo();
	}
	
	public static void runDemo()
	{
		final Business b = new Business();
		
		// sub thread 1
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				for (int i=1; i<=50; i++)
				{
					b.subOneRun(i);
				}
			}
		}).start();
		
		// sub thread 2
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				for (int i=1; i<=50; i++)
				{
					b.subTwoRun(i);
				}
			}
		}).start();
		
		// main thread
		for (int i=1; i<=50; i++)
		{
			b.mainRun(i);
		}
	}
	
	static class Business
	{
		private int whoRun = 2;
		private Lock lock   = new ReentrantLock();
		Condition conMain   = lock.newCondition();
		Condition conSubOne = lock.newCondition();
		Condition conSubTwo = lock.newCondition();
		
		public void subOneRun(int i)
		{
			lock.lock();
			try
			{
				while (whoRun != 2)
				{
					try
					{
						conSubOne.await();
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
				
				for (int j=1; j<=10; j++)
					System.out.println("sub 1 method run of "+ j +" time loop of "+i);
				whoRun = 3;
				conSubTwo.signal();
			}
			finally
			{
				lock.unlock();
			}
		}
		
		public void subTwoRun(int i)
		{
			lock.lock();
			try
			{
				while (whoRun != 3)
				{
					try
					{
						conSubTwo.await();
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
				
				for (int j=1; j<=15; j++)
					System.out.println("sub 2 method run of "+ j +" time loop of "+i);
				whoRun = 1;
				conMain.signal();
			}
			finally
			{
				lock.unlock();
			}
		}
		
		public void mainRun(int i)
		{
			lock.lock();
			try
			{
				while (whoRun != 1)
				{
					try
					{
						conMain.await();
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
				
				for (int j=1; j<=20; j++)
					System.out.println("main method run of "+ j +" time loop of "+i);
				whoRun = 2;
				conSubOne.signal();
			}
			finally
			{
				lock.unlock();
			}
		}	
	}	
}
