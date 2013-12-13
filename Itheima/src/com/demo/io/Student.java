package com.demo.io;

public class Student implements Comparable<Student>
{
	private String name;
	private int ma, cn, en;
	private int sum;

	public Student(String name, int ma, int cn, int en)
	{
		super();
		this.name = name;
		this.ma = ma;
		this.cn = cn;
		this.en = en;
		this.sum = ma+cn+en;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public int getMa()
	{
		return ma;
	}

	public void setMa(int ma)
	{
		this.ma = ma;
	}

	public int getCn()
	{
		return cn;
	}

	public void setCn(int cn)
	{
		this.cn = cn;
	}

	public int getEn()
	{
		return en;
	}

	public void setEn(int en)
	{
		this.en = en;
	}

	public int getSum()
	{
		return sum;
	}

	public void setSum(int sum)
	{
		this.sum = sum;
	}

	@Override
	public int compareTo(Student o)
	{
		int temp = this.getSum() - o.getSum();
		return temp==0 ? this.name.compareTo(o.getName()) : temp;
	}

	@Override
	public String toString()
	{
		return "Student [name=" + name + ", ma=" + ma + ", cn=" + cn + ", en="
				+ en + ", sum=" + sum + "]";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + cn;
		result = prime * result + en;
		result = prime * result + ma;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + sum;
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (cn != other.cn)
			return false;
		if (en != other.en)
			return false;
		if (ma != other.ma)
			return false;
		if (name == null)
		{
			if (other.name != null)
				return false;
		}
		else if (!name.equals(other.name))
			return false;
		if (sum != other.sum)
			return false;
		return true;
	}

}
