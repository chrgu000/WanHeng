package com.fengyun.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 视频格式转换测试用例
 * <p>
 * Created by 13 on 2017/12/5.
 */
public class ConvertVideo {
    public static void main(String[] args) {
    	   String filePath = "C:\\Users\\yangjun\\Desktop\\video\\1.1导学.wmv";
           //输出路径
           String targetPath = "C:\\Users\\yangjun\\Desktop\\video\\1.1导学.mp4";
    	ConvertVideoTest(filePath,targetPath);
	}
    public static void ConvertVideoTest(String filePath,String targetPath) {
        //原路径
        if (!checkFile(filePath)) {
            return;
        }
        if (convertVideoToMp4(filePath, targetPath)) {
            //执行转码任务
            System.out.println("转码完成！");
        }
    }

    /**
     * 判断路径是不是一个文件
     *
     * @param path
     * @return
     */
    private static boolean checkFile(String path) {
        File file = new File(path);
        if (!file.isFile()) {
            return false;
        }
        return true;
    }

    /**
     * avi 转码至 mp4
     *
     * @param filePath
     * @param targetPath
     * @return
     */
    private static boolean convertVideoToMp4(String filePath, String targetPath) {
        if (!checkFile(filePath)) {
            return false;
        }
        File f1=new File(filePath);
        Long start=System.currentTimeMillis();
        System.out.println(f1.exists());
        List<String> commend = new ArrayList<String>();
        /**
         * linux版本
         */
        commend.add("ffmpeg");//ffmpeg命令
        commend.add("-i");
        commend.add(filePath);
        commend.add("-c:a libvorbis -q:a 4");
        commend.add(targetPath);
        /**
         * windows版本
         */
//        commend.add("ffmpeg");//ffmpeg命令
//        commend.add("-i");
//        commend.add(filePath);
//        commend.add("-b:v");
//        commend.add("640k");
//        commend.add(targetPath);
        //使用StringBuffer拼接命令
        StringBuffer command = new StringBuffer();
        for (int i = 0; i < commend.size(); i++) {
            command.append(commend.get(i) + " ");
        }
        System.out.println(command);
        try {
            Runtime rt = Runtime.getRuntime();
            //调用系统资源执行格式转换命令
            Process proc = rt.exec(command.toString());
            InputStream stderr = proc.getErrorStream();
            InputStreamReader isr = new InputStreamReader(stderr);
            BufferedReader br = new BufferedReader(isr);
            String line = null;
            while ((line = br.readLine()) != null) ;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        File f2=new File(targetPath);
        System.out.println(f2.exists());
        System.out.println("视频转换成功");
        System.out.println("转码用时:"+(System.currentTimeMillis()-start)/1000);
        return true;
    }
}
