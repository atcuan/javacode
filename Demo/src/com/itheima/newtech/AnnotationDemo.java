package com.itheima.newtech;

@AnnotationClass(color = "red", value = "bash", arrayAttr={1, 2})
public class AnnotationDemo
{
	@AnnotationClass("bash")
	public static void main(String[] args)
	{
		if (AnnotationDemo.class.isAnnotationPresent(AnnotationClass.class))
		{
			AnnotationClass ac = (AnnotationClass)AnnotationDemo.class.getAnnotation(AnnotationClass.class);
			System.out.println(ac.color());
			System.out.println(ac.value());
			System.out.println(ac.arrayAttr().length);
			// 还可以是枚举，Class,8种基本类型
		}
	}
}
