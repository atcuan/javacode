package com.demo.test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Demo2 {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {

		/*
		思考题：
		1，
		对指定目录中的所有(包含子目录)的Java文件的绝对路径写入到一个文本文件中。
		这样查找某个java文件会比较便捷。建立指定文件的清单。
		思路：
		1，递归。
		2，在递归过程需要过滤。
		3，满足条件很多，需要容器存储。 
		4，将存储的文件绝对路径写入到一个文件中。
		*/
		
		File dir = new File("D:\\Course\\itheima.com\\step1-java基础\\Code");
		
		FilenameFilter filter = new FilenameFilter(){

			@Override
			public boolean accept(File dir, String name) {
				
				return name.endsWith(".java");
			}
			
		};
		
		List<File> list = new ArrayList<File>();
		
		//使用递归，过滤并存储。
		listAllJavaFile(dir, filter, list);
		
		
		//创建目的地文件。
		File file = new File(dir,"javaavilist.txt");
		
		write2File(list, file);
	
	}
	
	/*
	 * 定义功能对指定的目录进行递归。
	 * 在递归过程中需要过滤。
	 * 而且对过滤条件满足需要进行存储。
	 * 
	 * 这个功能，对哪个目录递归不确定，目的是参数。
	 * 过滤条件是什么不确定，是参数。
	 * 存储到哪里不确定，是参数。 
	 * 
	 * 
	 */
	
	public static void listAllJavaFile(File dir, FilenameFilter filter,List<File> list){
		
		File[] files = dir.listFiles();
		
		if(files!=null){
			for(File file : files){
//			for(int x=0; x<files.length; x++){
				if(file.isDirectory()){//如果是目录就递归。 
					listAllJavaFile(file, filter, list);
				}else{
					if(filter.accept(dir, file.getName())){//使用过滤器对指定的目的和文件名进行过滤。符合条件进行存储。
						list.add(file);
					}
				}
			}
		}
		
	}
	
	/*
	 * 对集合中存储的内容的信息写入到文件中。
	 * 参数：集合，文件。
	 */

	public static void write2File(List<File> list,File dest) throws IOException{
		
		BufferedWriter bufw = null;
		
		try {
			bufw = new BufferedWriter(new FileWriter(dest));

			for(Iterator<File> it = list.iterator(); it.hasNext() ; ){
				bufw.write(it.next().getAbsolutePath());
				bufw.newLine();
				bufw.flush();
			}
			
//			//遍历集合。 
////			for (File file : list) {
//			for(int x=0; x<list.size(); x++){
//				bufw.write(list.get(x).getAbsolutePath());
//				bufw.newLine();
//				bufw.flush();
//			}
		} finally{
			if(bufw!=null)
				try {
					bufw.close();
				} catch (IOException e) {
					
					throw new RuntimeException("关闭失败");
				}
		}
	}

}









