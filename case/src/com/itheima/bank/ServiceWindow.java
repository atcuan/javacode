package com.itheima.bank;

import java.util.Random;
import java.util.concurrent.Executors;

public class ServiceWindow
{
	private  CustomerType type = CustomerType.COMMON;
	private int windowId = 1;
	
	public void setType(CustomerType type)
	{
		this.type = type;
	}

	public void setWindowId(int windowId)
	{
		this.windowId = windowId;
	}

	
	public void start()
	{
		Executors.newSingleThreadScheduledExecutor().execute(new Runnable()
		{
			@Override
			public void run()
			{
				while (true)
				{
					service(type);
				}
			}
		});
	}
	
	private void service(CustomerType taskType) // 参数：获取任务的类型
	{
		String windowName = "第"+windowId+"号"+type.toString()+"窗口";
		System.out.println(windowName+"正在获取"+taskType.toString()+"任务");
		
		Integer number = getNumber(taskType);
		
		if (number != null) //获取到任务
		{
			long beginTime  = System.currentTimeMillis(); //计时器开始标记
			int serviceTime = getServiceTime(taskType);
			System.out.println("取到任务！！！"+windowName+"开始为第"+number+"号"+taskType.toString()+"客户服务");
			try
			{
				Thread.sleep(serviceTime); //服务
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
			long timeDelay = (System.currentTimeMillis() - beginTime) / 1000; //耗时，秒
			//System.out.println("___________________________________________________________________________________________________________________________");
			System.out.println(windowName+"为第"+number+"个"+taskType.toString()+"客户完成服务"+"耗时"+timeDelay+"秒钟");
		}
		else // 没有获取到任务
		{
			if (taskType == CustomerType.COMMON) // 如果本来就是获取普通任务而没有获取到，空闲一秒
			{
				System.out.println(windowName+"没有获取到"+taskType.toString()+"任务!"+"正在空闲");   
				try
				{
					Thread.sleep(500);
				}
				catch (InterruptedException e)
				{
					e.printStackTrace();
				}
			}
			else // 若本来是快速或者VIP窗口，不空闲，获取普通任务
			{
				System.out.println(windowName+"没有获取到"+taskType.toString()+"任务!"); 
				service(CustomerType.COMMON); 
			}
		}
	}

	// 取号
	private Integer getNumber(CustomerType taskType)
	{
		Integer number = 0;
		switch (taskType)
		{
			case COMMON:
				number = NumberMachine.getInstance().getCommonManager().fetchNumber();
				break;
			case EXPRESS:
				number = NumberMachine.getInstance().getExpressManager().fetchNumber();
				break;
			case VIP:
				number = NumberMachine.getInstance().getVipManager().fetchNumber();
				break;
		}
		return number;
	}

	// 每种客户类型服务所需要的时间
	private int getServiceTime(CustomerType taskType)
	{
		int commonTime  = new Random().nextInt(Constants.MAX_SERVICE_TIME - Constants.MIN_SERVICE_TIME)+1+Constants.MIN_SERVICE_TIME;
		int expressTime = Constants.MIN_SERVICE_TIME;  
		int vipTime		=  commonTime;
		
		int time = 0;
		switch (taskType)
		{
			case COMMON: 
				time = commonTime;
				break;
			case EXPRESS:
				time = expressTime;
				break;
			case VIP:
				time = vipTime;
		}
		return time;
	}
}