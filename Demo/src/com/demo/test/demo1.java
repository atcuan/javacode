package com.demo.test;

public class demo1
{

	public static void main(String[] args)
	{
		int[][] arr = new int[3][9];
		int x = 2, y = 0;
		boolean order = true;
		for (int i = 1; i <= 9; i++)
		{
			arr[x][y] = i;
			y++;
			if (!order)
				x--;
			if (order)
				x++;
			if (x < 0)
			{
				order = true;
				x = x + 2;
			}

			if (x > 2)
			{
				order = false;
				x = x - 2;
			}
		}

		for (int i = 0; i < arr.length; i++)
		{
			for (int j = 0; j < arr[i].length; j++)
			{
				if (arr[i][j] == 0)
					System.out.print(" ");
				else
				{
					System.out.print(arr[i][j]);
				}
			}
			System.out.println();
		}
	}

}
