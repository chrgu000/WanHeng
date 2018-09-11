package com.fengyun.util;

import java.io.File;
import java.io.FileFilter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class A { 
	public A()throws Exception{
		
	}
	private static final SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
	public static void main(String[] args) throws Exception {
	 System.out.println(ReadVideo.getVideoTimeLength("F:/咕泡学院/2017期ev加密版/1工程化专题/01工程化专题之maven/第一次课/vip_工程化专题之maven.ev4"));
//		File file=new File("F:/咕泡学院");
//		long times=0;
//		  List<File> list=getEVVideoNums(file);
//		  for (File f : list) {
//			  System.out.println(f.getAbsolutePath());
//			times+=getFileLength(f.getAbsolutePath());
//		}
//		  System.out.println(times/60/60.0);
	}

	private static long getFileLength(String absolutePath) throws Exception {
		long datetime=sdf.parse("00:00:00").getTime();
		String date=ReadVideo.getVideoTimeLength(absolutePath);
		long t=sdf.parse(date).getTime();
		
		return (t-datetime)/1000;
	}

	private static List<File> getEVVideoNums(File file) {
		List<File> list=new ArrayList<File>();
		File[] fs=file.listFiles(new FileFilter() {
			@Override
			public boolean accept(File pathname) {
				return pathname.getAbsolutePath().indexOf("python")==-1&& (pathname.getName().endsWith("mp4")||pathname.getPath().endsWith(".ev4")||pathname.isDirectory());
			}
		});
		for (File f : fs) {
			if(f.isDirectory()){
				list.addAll(getEVVideoNums(f));
			}else{
				list.add(f);
			}
		}
		return list;
	}
	 
}
