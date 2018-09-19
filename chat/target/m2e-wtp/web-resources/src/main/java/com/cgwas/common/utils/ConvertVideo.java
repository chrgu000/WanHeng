package com.cgwas.common.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.oro.text.regex.MatchResult;
import org.apache.oro.text.regex.Pattern;
import org.apache.oro.text.regex.PatternCompiler;
import org.apache.oro.text.regex.PatternMatcher;
import org.apache.oro.text.regex.Perl5Compiler;
import org.apache.oro.text.regex.Perl5Matcher;

/**
 * 视频格式转换
 * <p>
 * Created by 13 on 2017/12/5.
 */
public class ConvertVideo {

	/**
	 * 判断路径是不是一个文件
	 * 
	 * @param path
	 * @return
	 */
	public static boolean checkFile(String path) {
		File file = new File(path);
		if (!file.isFile()) {
			return false;
		}
		return true;
	}

	/**
	 * 判断路径是不是一个文件
	 * 
	 * @param path
	 * @return
	 */
	public static void exit(String path) {
		File file = new File(path);
		if (file.exists()) {
			file.delete();
		}
	}

	/**
     * avi 转码至 mp4
     *
     * @param filePath
     * @param targetPath
     * @return
     */
    public static boolean convertVideoToMp4(String filePath, String targetPath) {
        if (!checkFile(filePath)) {
            return false;
        }
        File f1=new File(filePath);
        Long start=System.currentTimeMillis();
        System.out.println(f1.exists());
        List<String> commend = new ArrayList<String>();
        /**
         * 如果存在源文件，删除
         */
        exit(targetPath);
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
        System.out.println("转码用时:"+(System.currentTimeMillis()-start));
        return true;
    }

	// ffmpeg能解析的格式：（asx，asf，mpg，wmv，3gp，mp4，mov，avi，flv等）
	/**
	 * ffmpeg能解析视频信息
	 * 
	 * @param inputPath
	 * @return
	 */
	public static String processFLV(String inputPath) {

		List<String> commend = new java.util.ArrayList<String>();

		// commend.add("D:\\ffmpeg-20140727-git-ad91bf8-win32-static\\bin\\ffmpeg ");//可以设置环境变量从而省去这行
		commend.add("ffmpeg");
		commend.add("-i");
		commend.add(inputPath);

		try {

			ProcessBuilder builder = new ProcessBuilder();
			builder.command(commend);
			builder.redirectErrorStream(true);
			Process p = builder.start();

			// 1. start
			BufferedReader buf = null; // 保存ffmpeg的输出结果流
			String line = null;
			// read the standard output

			buf = new BufferedReader(new InputStreamReader(p.getInputStream()));

			StringBuffer sb = new StringBuffer();
			while ((line = buf.readLine()) != null) {
				System.out.println(line);
				sb.append(line);
				continue;
			}
			@SuppressWarnings("unused")
			int ret = p.waitFor();// 这里线程阻塞，将等待外部转换进程运行成功运行结束后，才往下执行
			// 1. end
			return sb.toString();
		} catch (Exception e) {
			// System.out.println(e);
			return null;
		}
	}

	// 格式:"00:00:10.68" 时间转换成秒数
	/**
	 * 格式:"00:00:10.68" 时间转换成秒数
	 * 
	 * @param timelen
	 * @return
	 */
	public static float getTimelen(String timelen) {
		float min = 0;
		String strs[] = timelen.split(":");
		if (strs[0].compareTo("0") > 0) {
			min += Integer.valueOf(strs[0]) * 60 * 60;// 秒
		}
		if (strs[1].compareTo("0") > 0) {
			min += Integer.valueOf(strs[1]) * 60;
		}
		if (strs[2].compareTo("0") > 0) {
			min += Float.valueOf(strs[2]);
		}
		return min;
	}

	/**
	 * 获取视频帧数
	 * 
	 * @param path
	 * @return
	 */
	public static Map<String, Object> getVideoInfo(String path) {
		Map<String, Object> map = new HashMap<String, Object>();
		String result = processFLV(path);
		PatternCompiler compiler = new Perl5Compiler();
		try {
			String regexDuration = "Duration: (.*?), start: (.*?), bitrate: (\\d*) kb\\/s";
			Pattern patternDuration = compiler.compile(regexDuration,
					Perl5Compiler.CASE_INSENSITIVE_MASK);
			PatternMatcher matcherDuration = new Perl5Matcher();
			float time = 0;
			if (matcherDuration.contains(result, patternDuration)) {
				MatchResult re = (MatchResult) matcherDuration.getMatch();
				time = getTimelen(re.group(1));
				map.put("time", time);
				System.out.println("总时间：===" + time);
			}

			String[] list= result.split(",");
			List<String> stringList=Arrays.asList(list);
			for (String string :stringList) {
				if(string.contains("fps")){
					System.out.println(string);
					float fps = Float.valueOf(string.trim().split(" ")[0]);
					map.put("fps", fps);
					System.out.println("fps===" + fps);
					int sum = (int) (time * fps);
					map.put("sum", sum);
					System.out.println("总帧数：===" + sum);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;

	}
}
