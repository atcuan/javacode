package com.itheima.bank;

import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class MianClass
{
	public static void main(String[] args)
	{
		// 产生4个普通窗口
		for (int i=1; i<5; i++)
		{
			ServiceWindow commonWindow  = new ServiceWindow();
			commonWindow.setWindowId(i);
			commonWindow.start();
		}
		
		// 产生1个快速窗口
		ServiceWindow expressWindow  = new ServiceWindow();
		expressWindow.setType(CustomerType.EXPRESS);
		expressWindow.start();
		
		// 产生1个VIP窗口
		ServiceWindow vipWindow  = new ServiceWindow();
		vipWindow.setType(CustomerType.VIP);
		vipWindow.start();
		
		// 模拟普通客户取号
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable()
		{
			@Override
			public void run()
			{
				Integer commonNumber = NumberMachine.getInstance().getCommonManager().generateNewNumber();
				System.out.println("@!第"+commonNumber+"号普通客户等待服务");
			}
		}, 0, Constants.COMMON_CUSTOMER_INTERVAL_TIME, TimeUnit.SECONDS);
		
		// 模拟快速客户取号
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable()
		{
			@Override
			public void run()
			{
				Integer commonNumber = NumberMachine.getInstance().getExpressManager().generateNewNumber();
				System.out.println("@!第"+commonNumber+"号快速客户等待服务");
			}
		}, 0, Constants.COMMON_CUSTOMER_INTERVAL_TIME * 2, TimeUnit.SECONDS);
		
		// 模拟VIP客户取号
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable()
		{
			@Override
			public void run()
			{
				Integer commonNumber = NumberMachine.getInstance().getVipManager().generateNewNumber();
				System.out.println("@!第"+commonNumber+"号VIP户客等待服务");
			}
		}, 0, Constants.COMMON_CUSTOMER_INTERVAL_TIME * 6, TimeUnit.SECONDS);

	}

}

