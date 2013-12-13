package com.itheima.exam;

/*
 * 第7题：JAVA语言如何进行异常处理，关键字：throws,throw,try,catch,finally分别代表什么意义？在try块中可以抛出异常吗？
 * */
public class Test7
{
/*
 * java语言通过thaw catch语句处理异常或者直接采用throws抛出异常，但不建议直接使用抛出异常方式
 * throws:	在函数上使用，后面跟的是异常类型，如IOExpception.
 * throw:	在函数里面使用，跟的是异常对象,一般为提示性语句。
 * try：		try语句块里面的内容是需要检测异常的代码
 * catch：	异常处理代码
 * finally：不管try,catch语句如何执行，该语句一定会执行，常用于关闭资源。
 * 在try语句块里面可以抛出异常。如果不想处理，可以直接抛出。例如，try{throw new Exception("不想处理了！");}
 * */
}
