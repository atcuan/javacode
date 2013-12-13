package com.itheima.newtech;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 元注解(注解的注解)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD, ElementType.TYPE})
public @interface AnnotationClass
{
	// 注解的属性
	String color() default "blue";
	String value();
	int[] arrayAttr() default {1, 2, 3};
	TrafficLamp lamp() default TrafficLamp.RED;
}
