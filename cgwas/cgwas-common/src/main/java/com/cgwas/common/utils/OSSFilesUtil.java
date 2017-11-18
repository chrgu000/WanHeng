package com.cgwas.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.coobird.thumbnailator.Thumbnails;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.CompleteMultipartUploadRequest;
import com.aliyun.oss.model.CopyObjectRequest;
import com.aliyun.oss.model.CopyObjectResult;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import com.aliyun.oss.model.InitiateMultipartUploadRequest;
import com.aliyun.oss.model.InitiateMultipartUploadResult;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PartETag;
import com.aliyun.oss.model.UploadPartCopyRequest;
import com.aliyun.oss.model.UploadPartCopyResult;

public class OSSFilesUtil {
	private static String endpoint = "http://oss-cn-hangzhou.aliyuncs.com";
	private static String accessKeyId = "LTAIlf5LdEtpa7C1";
	private static String accessKeySecret = "ITnQbEhpJ3aI7dIxnpre2r1FdVoA8d";
	private static String bucketName = "zhangyuefeng";

	// 您的回调服务器地址，如http://oss-demo.aliyuncs.com:23450或http://0.0.0.0:9090
	// private static final String callbackUrl = "http:/127.0.0.1:80";

	/**
	 * 新建文件夹
	 * 
	 * @return
	 */
	public static Boolean addFile(String path) {
		// 创建OSSClient实例
		if (!path.substring(path.length() - 1, path.length()).equals("/")) {
			path += "/";
		}
		try {
			OSSClient ossClient = new OSSClient(endpoint, accessKeyId,
					accessKeySecret);
			// final String path = "cgwas/resource/杭州万恒会计服务有限公司/";
			ossClient.putObject(bucketName, path, new ByteArrayInputStream(
					new byte[0]));
			// 关闭client
			ossClient.shutdown();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	/**
	 * 判断文件夹是否存在
	 * 
	 * @return
	 */
	public static Boolean isFile(String path) {
		// 创建OSSClient实例
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		// Object是否存在
		boolean found = ossClient.doesObjectExist(bucketName, path);
		// 关闭client
		ossClient.shutdown();
		return found;
	}
	
	/**
	 * 上传文件 流形式
	 * 
	 * @return
	 */
	public static Boolean uploadDocumentByFile(String path, String localFile) {
		// path = "cgwas/resource/杭州网络有限公司/XCM/身份证.jpg";
		// localFile="C:/Users/John/Desktop/最新众包平台数据库表设计.pdm";
		// 创建OSSClient实例
		try {
			OSSClient ossClient = new OSSClient(endpoint, accessKeyId,
					accessKeySecret);
			// 上传文件
			ossClient.putObject(bucketName, path, new File(localFile));
			// 关闭client
			ossClient.shutdown();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;

	}

	/**
	 * 上传文件 字节形式
	 * 
	 * @return
	 */
	public static Boolean uploadDocumentByString(String path, byte[] bytes) {
		// 创建OSSClient实例
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId,
				accessKeySecret);
		// 上传字符串
		ossClient.putObject(bucketName, path, new ByteArrayInputStream(bytes));
		// 关闭client
		ossClient.shutdown();
		return true;

	}

	/**
	 * 计算当前目录下的文件或文件夹个数和占用的总空间和总大小
	 * 
	 * @return
	 */
	public static Map<String, Object> getFileSize(String path) {
		Map<String, Object> map = new HashMap<String, Object>();
		// // 创建OSSClient实例
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId,
				accessKeySecret);
		ListObjectsRequest listObjectsRequest = new ListObjectsRequest(
				bucketName);
		listObjectsRequest.setPrefix(path);
		// 递归列出fun目录下的所有文件
		ObjectListing listing = ossClient.listObjects(listObjectsRequest);
		// 遍历所有Object
		long spaceSize = 0l;// 占用空间大小
		long totalSize = 0l;// 总文件大小
		// 文件个数
		long documentNum = 0l;
		// 文件夹个数
		long filesNum = 0l;
		int i = 0;
		for (OSSObjectSummary objectSummary : listing.getObjectSummaries()) {
			String fileKey = objectSummary.getKey();
			if (i == 0) {
				map.put("lastModified", objectSummary.getLastModified());
			}
			i++;
			if (!fileKey.substring(fileKey.length() - 1, fileKey.length())
					.equals("/")) {
				long size = objectSummary.getSize();
				totalSize += size;
				if (size / 1024 == 0 || size / 1024 < 4) {
					size = 1024 * 4;
				} else {
					size = size / (1024 * 4) * (1024 * 4) + (1024 * 4);
				}
				spaceSize += size;
				documentNum++;
				continue;
			}
			filesNum++;
		}
		if (filesNum > 0) {
			--filesNum;
		}
		map.put("totalSize", totalSize);
		map.put("spaceSize", spaceSize);
		map.put("documentNum", documentNum);
		map.put("filesNum", filesNum);
		return map;
	}
	
	/**
	 * 下载
	 * @throws IOException 
	 */
	
	public static BufferedInputStream downloadgetObject(String path) throws IOException {
		// 创建OSSClient实例
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		OSSObject ossObject = ossClient.getObject(bucketName, path);
		BufferedInputStream input=new BufferedInputStream(ossObject.getObjectContent());
		 byte[] buf = new byte[1024];  
         input.read(buf);
		// 关闭client
		ossClient.shutdown();
		return input;

	}
	
	/**
	 * 流式下载
	 */
	
	public static String downloadgetString(String path) throws Exception {
		// endpoint以杭州为例，其它region请按实际情况填写
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		OSSObject ossObject = ossClient.getObject(bucketName, path);
		// 读Object内容
		System.out.println("Object content:");
		String line ="";
		BufferedReader reader = new BufferedReader(new InputStreamReader(ossObject.getObjectContent()));
		while (true) {
		    line = reader.readLine();
		    if (line == null) 
		    	break;
		    System.out.println("\n" + line);
		}
		reader.close();
		// 关闭client
		ossClient.shutdown();
		return "";

	}
	
	/**
	 * 递归获取当前文件夹下所有文件
	 * 
	 * @return
	 */
	@SuppressWarnings("unused")
	public static List<String> getFiles(String path) {
		Map<String, Object> map = new HashMap<String, Object>();
		// // 创建OSSClient实例
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId,
				accessKeySecret);
		ListObjectsRequest listObjectsRequest = new ListObjectsRequest(
				bucketName);
		listObjectsRequest.setPrefix(path);
		// 递归列出fun目录下的所有文件
		ObjectListing listing = ossClient.listObjects(listObjectsRequest);
		List<String> list= new ArrayList<String>();
		// 遍历所有Object
		for (OSSObjectSummary objectSummary : listing.getObjectSummaries()) {
			if(!objectSummary.getKey().contains("cgwas/images/sc/")){
				list.add(objectSummary.getKey());
				System.out.println("url:"+objectSummary.getKey());
			}
		}
		return list;
	}

	/**
	 * 删除单个文件
	 * 
	 * @return
	 */
	public static Boolean deleteFile(String path) {
		// final String path = "cgwas/resource/杭州万恒会计服务有限公司/";
		// 创建OSSClient实例
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId,
				accessKeySecret);
		// 删除Object
		ossClient.deleteObject(bucketName, path);
		// 关闭client
		ossClient.shutdown();
		return true;
	}

	/**
	 * 删除多个文件
	 * 
	 * @return
	 */
	public static List<String> deleteFiles(List<String> path) {
		// 创建OSSClient实例
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId,
				accessKeySecret);
		DeleteObjectsResult deleteObjectsResult = ossClient.deleteObjects(new DeleteObjectsRequest(bucketName).withKeys(path));
		List<String> deletedObjects = deleteObjectsResult.getDeletedObjects();
		// 关闭client
		ossClient.shutdown();
		return deletedObjects;
	}

	/**
	 * 拷贝文件
	 * 
	 * @return
	 */
	public static void copyObject(List<String> path) {
		String srcBucketName = "<sourceBucketName>";
		String srcKey = "<sourceKey>";
		String destBucketName = "<targetBucketName>";
		String destKey = "<targetKey>";
		// 创建OSSClient实例
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId,
				accessKeySecret);
		// 创建CopyObjectRequest对象
		CopyObjectRequest copyObjectRequest = new CopyObjectRequest(
				srcBucketName, srcKey, destBucketName, destKey);

		// 设置新的Metadata
		ObjectMetadata meta = new ObjectMetadata();

		meta.setContentType("text/html");
		copyObjectRequest.setNewObjectMetadata(meta);
		// 复制Object
		CopyObjectResult result = ossClient.copyObject(copyObjectRequest);
		System.out.println("ETag: " + result.getETag() + " LastModified: "
				+ result.getLastModified());
		// 关闭client
		ossClient.shutdown();
	}
	
	/**
	 * 获取当前文件夹所有文件list
	 * 
	 * @return
	 */
	public static List<String> list(String path) {
		// 创建OSSClient实例
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId,
				accessKeySecret);
		// 列举Object
		ObjectListing objectListing = ossClient.listObjects(bucketName,path);
		List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
		List<String> list = new ArrayList<String>();
		for (OSSObjectSummary s : sums) {
			if (s.getKey().contains("")
					&& (s.getKey().contains(".png")
							|| s.getKey().contains(".tga")
							|| s.getKey().contains(".tif")
							|| s.getKey().contains(".bmp") || s.getKey()
							.contains(".jpg"))) {
				list.add(s.getKey());
			}
		}
		return list;
	}

	/**
	 * 上传文件若为图片会生成缩略图
	 * 
	 * @param path
	 *            oss url
	 * @param localFile
	 *            本地 url
	 */
	public static void uploadThumbnailsImg(String path, String localFile) {
		try {
			File file = new File(localFile);
			String fileName = file.getName();
			// 文件名称
			String name = fileName.substring(0, fileName.lastIndexOf("."));
			// 后缀名
			String suffix = fileName.substring(fileName.lastIndexOf("."));
			uploadDocumentByFile(path + fileName, localFile);
			if (suffix.equals(".png") || suffix.equals(".tga")
					|| suffix.equals(".tif") || suffix.equals(".bmp")
					|| suffix.equals(".jpg")) {
				/**
				 * 缩略图命名规则
				 */
				String thumbnails = name + "-thumbnails" + suffix;
				String thumbnailsName = file.getParent() + "\\" + thumbnails;
				Thumbnails.of(localFile).size(150, 150).keepAspectRatio(false).toFile(thumbnailsName);
				uploadDocumentByFile(path + "thumbnails/"+thumbnails, thumbnailsName);
				File localF = new File(thumbnailsName);
				if (localF.exists()) {
					localF.delete();
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 public static void copyObject(String sourceKey,String targetKey){
		 // 得到被拷贝object大小
		 OSSClient ossClient = new OSSClient(endpoint, accessKeyId,accessKeySecret);
		 ObjectMetadata objectMetadata = ossClient.getObjectMetadata(bucketName, sourceKey);
		 long contentLength = objectMetadata.getContentLength();
		 // 分片大小，10MB
		 long partSize = 1024 * 1024 * 10;
		 // 计算分块数目
		 int partCount = (int) (contentLength / partSize);
		 if (contentLength % partSize != 0) {
		     partCount++;
		 }
		 System.out.println("total part count:" + partCount);
		 // 初始化拷贝任务
		 InitiateMultipartUploadRequest initiateMultipartUploadRequest = new InitiateMultipartUploadRequest(bucketName, targetKey);
		 InitiateMultipartUploadResult initiateMultipartUploadResult = ossClient.initiateMultipartUpload(initiateMultipartUploadRequest);
		 String uploadId = initiateMultipartUploadResult.getUploadId();
		 // 分片拷贝
		 List<PartETag> partETags = new ArrayList<PartETag>();
		 for (int i = 0; i < partCount; i++) {
		      // 计算每个分块的大小
		     long skipBytes = partSize * i;
		     long size = partSize < contentLength - skipBytes ? partSize : contentLength - skipBytes;
		     // 创建UploadPartCopyRequest
		     UploadPartCopyRequest uploadPartCopyRequest = 
		         new UploadPartCopyRequest(bucketName, sourceKey, bucketName, targetKey);
		     uploadPartCopyRequest.setUploadId(uploadId);
		     uploadPartCopyRequest.setPartSize(size);
		     uploadPartCopyRequest.setBeginIndex(skipBytes);
		     uploadPartCopyRequest.setPartNumber(i + 1);
		     UploadPartCopyResult uploadPartCopyResult = ossClient.uploadPartCopy(uploadPartCopyRequest);
		     // 将返回的PartETag保存到List中
		     partETags.add(uploadPartCopyResult.getPartETag());
		 }
		 // 提交分片拷贝任务
		 CompleteMultipartUploadRequest completeMultipartUploadRequest = new CompleteMultipartUploadRequest(
				 bucketName, targetKey, uploadId, partETags);
		 ossClient.completeMultipartUpload(completeMultipartUploadRequest);
	 }

	public static void main(String[] args) {
		//System.out.println(isFile("cgwas/"));
		//deleteFiles(getFiles("cgwas/material/"));
	    //addFile("cgwas/user/7f219025-614f-462d-bdb9-c11fff873444/data/ZYLGZS18/XX/task/a/b/");
	    //System.out.println("boolean:"+isFile("ZYLGZS18/XX15/ep010/task/Light/"));
		try {
			long start=System.currentTimeMillis();
			copyObject("cgwas/user/7f219025-614f-462d-bdb9-c11fff873444/data/ZYLGZS18/11/cgwas.rar","cgwas/user/7f219025-614f-462d-bdb9-c11fff873444/data/ZYLGZS18/FF/HTML.rar");
			//uploadDocumentByFile("cgwas/1.jpg", "C:/Users/John/Desktop/1234.png");
			System.out.println("耗时:"+(System.currentTimeMillis()-start));
			//uploadThumbnailsImg("", "C:/Users/John/Desktop/众包/资产管理");
			//deleteFile("cgwas/user/7f219025-614f-462d-bdb9-c11fff873444/data/ZYLGZS18/LW/");
			//downloadgetObject("cgwas/user/7f219025-614f-462d-bdb9-c11fff873444/data/ZYLGZS18/XX15/ep010/task/Model/YANGJUNTWO/sucai/1.png");
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// // 创建OSSClient实例
		// OSSClient ossClient = new OSSClient(endpoint, accessKeyId,
		// accessKeySecret);
		// // 列举Object
		// ObjectListing objectListing = ossClient.listObjects(bucketName,
		// "cgwas/material/");
		// List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
		// List<OSSObjectSummary> list = new ArrayList<OSSObjectSummary>();
		// for (OSSObjectSummary s : sums) {
		// if(s.getKey().contains("") &&
		// (s.getKey().contains(".png")||s.getKey().contains(".tga")
		// ||s.getKey().contains(".tif")||s.getKey().contains(".bmp")||s.getKey().contains(".jpg"))){
		// list.add(s);
		// System.out.println(s.getKey());
		// }
		// }
		try {
			/*List<String> list=list("cgwas/material/thumbnails/");
			for (String string : list) {
				System.out.println(string);
			}*/
			//uploadThumbnailsImg("cgwas/material/", "C:/Users/John/Desktop/众包/资产管理/6.jpg");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 关闭client
		// ossClient.shutdown();
	}
}
