package com.cgwas.common.utils;
public class ThreadTransCode implements Runnable { 

	  //原始文件 
	  private String videoPath; 
	  //目标文件 
	  private String targetPath; 
	  
	  public ThreadTransCode(String videoPath, String targetPath) { 
	    this.videoPath = videoPath; 
	    this.targetPath = targetPath; 
	  } 

	  @Override 
	  public void run() { 
	    synchronized (this) { 
	      System.out.println("转码开始.............."); 
	      boolean flag=ConvertVideo.convertVideoToMp4(videoPath, targetPath); 
	      if(flag){
	    	  System.out.println("转码成功");
	      }
	    } 
	  } 
	  
	} 
