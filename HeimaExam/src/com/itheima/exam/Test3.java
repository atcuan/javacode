package com.itheima.exam;

/**
 * 第3题：环境变量path和classpath的作用是什么？
 */
public class Test3 
{
	public static void main(String[] args) 
	{
		/*	path:系统环境变量，它会帮我我们找到我们使用的命令的路径，然后执行，如果有多个路径，
		 * 		 会挨个搜索。比如，windows下的cmd命令，直接在命令提示符中输入path就会显示当前path路径，
		 *		 也可也到计算机属性的环境变量里面配置，该配置为永久可靠的，下次开机仍然有效。
		 *		 linux下的terminal。输入echo $PATH便会打印出环境变量路径，原理与windows一样。
		 *
		 *classpath:classpath是专门针对java的环境变量，java类文件的目录，用法同path。
		 *		需要注意的是，直接在cmd命令行中加入java环境变量是临时的。
		 */
	}

}
