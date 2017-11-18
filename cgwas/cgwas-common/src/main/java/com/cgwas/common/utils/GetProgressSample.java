package com.cgwas.common.utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import net.coobird.thumbnailator.Thumbnails;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.event.ProgressEvent;
import com.aliyun.oss.event.ProgressEventType;
import com.aliyun.oss.event.ProgressListener;
import com.aliyun.oss.model.GetObjectRequest;
import com.aliyun.oss.model.PutObjectRequest;

/**
 * 上传下载进度示例
 *
 */
public class GetProgressSample {
	static String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
	// accessKey请登录https://ak-console.aliyun.com/#/查看
	static String accessKeyId = "LTAIlf5LdEtpa7C1";
	static String accessKeySecret = "ITnQbEhpJ3aI7dIxnpre2r1FdVoA8d";
	static String bucketName = "zhangyuefeng";
    
    /**
     * 获取上传进度回调
     */
    static class PutObjectProgressListener implements ProgressListener {

        public static long bytesWritten = 0;
        public static long totalBytes = -1;
        public static boolean succeed = false;
        
        @Override
        public void progressChanged(ProgressEvent progressEvent) {
            long bytes = progressEvent.getBytes();
            ProgressEventType eventType = progressEvent.getEventType();
            switch (eventType) {
            case TRANSFER_STARTED_EVENT:
                System.out.println("Start to upload......");
                break;
            
            case REQUEST_CONTENT_LENGTH_EVENT:
                totalBytes = bytes;
                System.out.println(totalBytes + " bytes in total will be uploaded to OSS");
                break;
            
            case REQUEST_BYTE_TRANSFER_EVENT:
                bytesWritten += bytes;
                if (totalBytes != -1) {
                    int percent = (int)(bytesWritten * 100.0 / totalBytes);
                    System.out.println(bytes + " bytes have been written at this time, upload progress: " +
                            percent + "%(" + bytesWritten + "/" + totalBytes + ")");
                } else {
                    System.out.println(bytes + " bytes have been written at this time, upload ratio: unknown" +
                            "(" + bytesWritten + "/...)");
                }
                break;
                
            case TRANSFER_COMPLETED_EVENT:
                succeed = true;
                System.out.println("Succeed to upload, " + bytesWritten + " bytes have been transferred in total");
                break;
                
            case TRANSFER_FAILED_EVENT:
                System.out.println("Failed to upload, " + bytesWritten + " bytes have been transferred");
                break;
                
            default:
                break;
            }
        }

        public boolean isSucceed() {
            return succeed;
        }
    }
    
    /**
     * 获取下载进度回调
     */
    static class GetObjectProgressListener implements ProgressListener {
        
        public static long bytesRead = 0;
        public static long totalBytes = -1;
        public static boolean succeed = false;
        
        @Override
        public void progressChanged(ProgressEvent progressEvent) {
            long bytes = progressEvent.getBytes();
            ProgressEventType eventType = progressEvent.getEventType();
            switch (eventType) {
            case TRANSFER_STARTED_EVENT:
                System.out.println("Start to download......");
                break;
            
            case RESPONSE_CONTENT_LENGTH_EVENT:
                totalBytes = bytes;
                System.out.println(totalBytes + " bytes in total will be downloaded to a local file");
                break;
            
            case RESPONSE_BYTE_TRANSFER_EVENT:
                bytesRead += bytes;
                if (totalBytes != -1) {
                    int percent = (int)(bytesRead * 100.0 / totalBytes);
                    System.out.println(bytes + " bytes have been read at this time, download progress: " +
                            percent + "%(" + bytesRead + "/" + totalBytes + ")");
                    
                } else {
                    System.out.println(bytes + " bytes have been read at this time, download ratio: unknown" +
                            "(" + bytesRead + "/...)");
                }
                break;
                
            case TRANSFER_COMPLETED_EVENT:
                succeed = true;
                System.out.println("Succeed to download, " + bytesRead + " bytes have been transferred in total");
                break;
                
            case TRANSFER_FAILED_EVENT:
                System.out.println("Failed to download, " + bytesRead + " bytes have been transferred");
                break;
                
            default:
                break;
            }
        }
        
        public boolean isSucceed() {
            return succeed;
        }
    }
    
    /**
     * 带进度条的上传
     * @param path
     * @param localFile
     */
    public static void upload(String path,File file) { 
        try {
        	OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            // 带进度条的上传 
            String fileName = file.getName();
            client.putObject(new PutObjectRequest(bucketName, path+fileName, file).<PutObjectRequest>withProgressListener(new PutObjectProgressListener()));
            /**
             * 初始化校验参数
             */
            PutObjectProgressListener.bytesWritten = 0l;
            PutObjectProgressListener.totalBytes = -1;
            PutObjectProgressListener.succeed = false;
            // 关闭client
            client.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 带进度条的下载
     * @param path
     * @param localFile
     */
    public static void download(String path,String localFile) { 
    	// endpoint以杭州为例，其它region请按实际情况填写
        //String key = "最新众包平台数据库表设计.pdm";
        OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try {
            File fh = new File(localFile);
            client.getObject(new GetObjectRequest(bucketName, path).
                    <GetObjectRequest>withProgressListener(new GetObjectProgressListener()), fh);
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    /**
     * 获取文件上传进度
     */
    public static Map<String,Object> getUploadbytes() {
    	Map<String,Object> map= new HashMap<String, Object>();
    	map.put("bytesWritten", PutObjectProgressListener.bytesWritten);
    	map.put("totalBytes", PutObjectProgressListener.totalBytes);
    	map.put("succeed", PutObjectProgressListener.succeed);
		return map; 
    	
    }
    
    /**
     * 获取文件下载进度
     */
    public static Map<String,Object> getdownloadBytes() {
    	Map<String,Object> map= new HashMap<String, Object>();
    	map.put("bytesWritten", GetObjectProgressListener.bytesRead);
    	map.put("totalBytes", GetObjectProgressListener.totalBytes);
    	map.put("succeed", GetObjectProgressListener.succeed);
		return map; 
    	
    }
    
    public static void main(String[] args) { 
    	//upload("cgwas/material/","C:/Users/John/Desktop/众包/");
    	//upload("cgwas/material/","C:/Users/John/Desktop/众包/资产管理/6.jpg");
    	String localFile="C:/Users/John/Desktop/众包/资产管理/";
    	File file = new File(localFile);
    	//判断是否为文件夹
    	if(file.isDirectory()){
    		File flist[] = file.listFiles();
    		for (int i = 0; i < flist.length; i++){
                if (!flist[i].isDirectory()){
                	upload("cgwas/img/",flist[i]);
                }
            }
    	}else{
    		upload("cgwas/img/",file);
    	}
    }
}


