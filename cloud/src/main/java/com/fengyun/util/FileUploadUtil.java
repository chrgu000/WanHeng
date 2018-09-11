package com.fengyun.util;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.multipart.MultipartFile;
  class PrintStream extends Thread   
{  
     java.io.InputStream __is = null;  
     public PrintStream(java.io.InputStream is)   
     {  
         __is = is;  
     }   
   
     public void run()   
     {  
         try   
         {  
             while(this != null)   
             {  
                 int _ch = __is.read();  
                 if(_ch != -1)   
                     System.out.print((char)_ch);   
                 else break;  
             }  
         }   
         catch (Exception e)   
         {  
             e.printStackTrace();  
         }   
     }  
 }  
class ExecuteCodecs {  
	
    /**  
     * 视频转码 (PC端MP4) 
     * @param ffmpegPath    转码工具的存放路径 
     * @param upFilePath    用于指定要转换格式的文件,要截图的视频源文件 
     * @param codcFilePath    格式转换后的的文件保存路径 
     * @return  
     * @throws Exception  
     */    
    public static  boolean exchangeToMp4(String ffmpegPath, String upFilePath, String codcFilePath) throws Exception {    
        // 创建List集合来保存转换视频文件为flv格式的命令   
        List<String> convert = new ArrayList<String>();    
        convert.add(ffmpegPath); // 添加转换工具路径    
        convert.add("-y"); // 该参数指定将覆盖已存在的文件    
        convert.add("-i");  
        convert.add(upFilePath);  
        convert.add("-c:v");  
        convert.add("libx264");  
        convert.add("-c:a");  
        convert.add("aac");  
        convert.add("-strict");  
        convert.add("-2");  
        convert.add("-pix_fmt");  
        convert.add("yuv420p");  
        convert.add("-movflags");  
        convert.add("faststart");  
        //convert.add("-vf");   // 添加水印  
        //convert.add("movie=watermark.gif[wm];[in][wm]overlay=20:20[out]");  
        convert.add(codcFilePath);    
    
        boolean mark = true;    
                  
        try {    
            Process videoProcess = new ProcessBuilder(convert).redirectErrorStream(true).start();              
            new PrintStream(videoProcess.getInputStream()).start();                          
            //videoProcess.waitFor();  // 加上这句，系统会等待转换完成。不加，就会在服务器后台自行转换。  
              
        } catch (Exception e) {    
            mark = false;    
            System.out.println(e);    
            e.printStackTrace();    
        }    
        return mark;    
    }   
}  
public class FileUploadUtil {
	public static void main(String[] args) {
		uploadSingleVideoFile();
	}
	private static final Logger logger = LoggerFactory  
            .getLogger(FileUploadUtil.class);  
      
  
    public static final String UPLOAD_ROOT_PATH = "upload";  
      
    public static final String UPLOAD_VIDEO_PATH="videos";  
          
    
      
    public static File mkDir(String path){  
        File dir = new File(path);  
        if(!dir.exists())  
            dir.mkdirs();  
        return dir;  
    }  
      
      
    /** 
     *  
     * @param originalFileName 
     * @return 返回原文件的后缀  
     *  
     */  
    public static String getOriginalFileSuffix(String originalFileName){  
        int index=originalFileName.lastIndexOf(".");  
        if(index!=-1){  
            return originalFileName.substring(index);  
        }else  
            return originalFileName;  
    }  
      
    public static String uploadSingleVideoFile() {  
            try {  
                String ffmpegPath = "C:/Users/yangjun/Downloads/ffmpeg/ffmpeg.rev11143/ffmpeg.exe";   
                    ExecuteCodecs executeCodecs = new ExecuteCodecs();  
                    executeCodecs.exchangeToMp4(ffmpegPath,"C:/Users/yangjun/Desktop/target/4.mp4","C:/Users/yangjun/Desktop/video/1.avi");  
            } catch (IOException e) {  
                e.printStackTrace();  
                System.out.println(e.getMessage());  
            } catch (Exception e) {  
                e.printStackTrace();  
                System.out.println(e.getMessage());  
            }  
          
       
        return null;      
    }  
      
}
