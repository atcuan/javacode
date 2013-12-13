package com.itheima.thread;

public class SyncDemo
{
	public static void main(String[] args)
	{
		new SyncDemo().init();
	}
	
	private void init()
	{
		final Outputer out = new Outputer();
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				while (true)
				{
					try
					{
						Thread.sleep(1000);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
					out.output("LucyandMary");
				}
			}
		}).start();
		
		new Thread(new Runnable()
		{
			@Override
			public void run()
			{
				while (true)
				{
					try
					{
						Thread.sleep(1000);
					}
					catch (InterruptedException e)
					{
						e.printStackTrace();
					}
					out.output("MikeandJerry");
				}
			}
		}).start();
	}
	
	class Outputer
	{
		public void output(String name)
		{
			int len = name.length();
			synchronized (this)
			{
				for (int i=0; i<len; i++)
				{
					System.out.print(name.charAt(i));
				}
				System.out.println();
			}
		}
	}
}


