package com.itheima.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/*
 * 子线程循环 10 次， 接着主线程循环 20， 接着又回到子
 * 线程循环 10 次，接着再回到主线程又循环 100，如此循环50 次
 */
public class LockCondition
{
	
	public static void main(String[] args)
	{
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
	
	static class Business
	{
		private boolean flag = true;
		private Lock lock = new ReentrantLock();
		private Condition con = lock.newCondition();
		
		public void sub(int j)
		{
			lock.lock();
			while (!flag)
			{
				try
				{
					con.await();
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
				
			for (int i=0; i<10; i++)
				System.out.println("sub thread run "+i+" time" +" of loop "+j);
			flag = false;
			con.signalAll();
			lock.unlock();
		}
		
		public synchronized void main(int j)
		{
			lock.lock();
			try //这个try有必要吗？难道是为了释放资源用的finally才加的？
			{
				while (flag)
				{
					try
					{
						con.await();
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
				}
					
				for (int i=0; i<20; i++)
					System.out.println("main thread run "+i+" time" +" of loop "+j);
				flag = true;
				con.signalAll();
			}
			finally
			{
				lock.unlock();
			}
		}
	}
}





// 数据缓存器
class CacheData
{
	private Map<String, Object> cache = new HashMap<String, Object>();
	private ReadWriteLock rwl = new ReentrantReadWriteLock();
	
	public Object getData(String key)
	{
		Object value = null;
		
		// 一来就上读锁，大家都去读数据(不能写)
		rwl.readLock().lock();
		value = cache.get(key);
		if (value == null) //发现没有数据
		{
			rwl.readLock().unlock(); // 都不要读了
			rwl.writeLock().lock();  // 我来写数据，上写锁
			if (value == null)		 // 防止第一个线程写好后，释放写锁，后买堵在这里的线程进来就写，冲掉数据
				value = "date....";      // 一顿狂写
			
			rwl.writeLock().unlock();// 写完释放写锁，上读锁
			rwl.readLock().lock();
		}
		rwl.readLock().unlock();
		
		return value;
	}
	
	public Object getData2(String key)
	{
		Object value = null;
		
		// 一来就上读锁，大家都去读数据(不能写)
		rwl.readLock().lock();
		try
		{
			value = cache.get(key);
			if (value == null) //发现没有数据
			{
				rwl.readLock().unlock(); // 都不要读了
				rwl.writeLock().lock();  // 我来写数据，上写锁
				try
				{
					if (value == null)	 // 防止第一个线程写好后，释放写锁，后买堵在这里的线程进来就写，冲掉数据
						value = "date....";      // 一顿狂写
				}
				finally
				{
					rwl.writeLock().unlock();// 写完释放写锁，上读锁
				}
				rwl.readLock().lock();
			}
		}
		finally
		{
			rwl.readLock().unlock();
		}
		return value;
	}
}
