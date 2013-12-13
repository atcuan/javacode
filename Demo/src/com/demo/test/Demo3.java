package com.demo.test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.LineNumberReader;

public class Demo3
{

	/**
	 * @param args
	 * @throws Exception 
	 */
	public static void main(String[] args) throws Exception
	{
		FileReader fr = new FileReader("Cast.java");
        LineNumberReader lnr = new LineNumberReader(fr);
        String line = null;
        lnr.setLineNumber(100);
        while((line=lnr.readLine())!=null)
        {
                System.out.println(lnr.getLineNumber()+":"+line);
        }
        lnr.close();

	}

}
