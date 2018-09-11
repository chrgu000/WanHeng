package com.cgwas.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.zip.Adler32;
import java.util.zip.CheckedOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;

import junit.framework.Assert;

import org.apache.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import net.coobird.thumbnailator.Thumbnails;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.HttpMethod;
import com.aliyun.oss.OSSClient;
import com.aliyun.oss.common.utils.BinaryUtil;
import com.aliyun.oss.model.CompleteMultipartUploadRequest;
import com.aliyun.oss.model.CopyObjectRequest;
import com.aliyun.oss.model.CopyObjectResult;
import com.aliyun.oss.model.DeleteObjectsRequest;
import com.aliyun.oss.model.DeleteObjectsResult;
import com.aliyun.oss.model.GeneratePresignedUrlRequest;
import com.aliyun.oss.model.InitiateMultipartUploadRequest;
import com.aliyun.oss.model.InitiateMultipartUploadResult;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.MatchMode;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PartETag;
import com.aliyun.oss.model.PolicyConditions;
import com.aliyun.oss.model.UploadFileRequest;
import com.aliyun.oss.model.UploadPartCopyRequest;
import com.aliyun.oss.model.UploadPartCopyResult;
import com.aliyun.oss.model.UploadPartRequest;
import com.aliyun.oss.model.UploadPartResult;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.mts.model.v20140618.QueryMediaListByURLRequest;
import com.aliyuncs.mts.model.v20140618.QueryMediaListByURLResponse;
import com.aliyuncs.mts.model.v20140618.SubmitJobsRequest;
import com.aliyuncs.mts.model.v20140618.SubmitJobsResponse;
import com.aliyuncs.profile.DefaultProfile;

public class OSSFilesUtil {
	private static Logger log = Logger.getLogger(OSSFilesUtil.class);
	public static String endpoint;
	public static String accessKeyId ;
	public static String accessKeySecret;
	public static String bucketName;
	private static String mpsRegionId = "cn-hangzhou";
    private static String pipelineId;
    private static String templateId = "S00000001-200010";
    private static String ossLocation = "oss-cn-hangzhou";
    
	// 您的回调服务器地址，如http://oss-demo.aliyuncs.com:23450或http://0.0.0.0:9090
	// private static final String callbackUrl = "http:/127.0.0.1:80";
	/**
	 * 调用OSS初始化加载
	 */
	static{
		Properties props = new Properties();
		try { // 读取配置文件中的信息
			Resource resource = new ClassPathResource("base.properties");
			InputStream in = resource.getInputStream();
			props.load(in);
			in.close();
			accessKeyId = (String) props.get("accessKeyId");
			accessKeySecret = (String) props.get("accessKeySecret");
			bucketName = (String) props.get("bucketName");
			endpoint = (String) props.get("endpoint");
			pipelineId = (String) props.get("pipelineId");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
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
	 * 上传文件 断点续传
	 * 
	 * @return
	 */
	public static Boolean UploadFileRequest(String path,String localFile) {
		// 创建OSSClient实例
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		// 设置断点续传请求
		UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName, path);
		// 指定上传的本地文件
		uploadFileRequest.setUploadFile(localFile);
		// 指定上传并发线程数
		uploadFileRequest.setTaskNum(5);
		// 指定上传的分片大小
		uploadFileRequest.setPartSize(1 * 1024 * 1024);
		// 开启断点续传
		uploadFileRequest.setEnableCheckpoint(true);
		// 断点续传上传
		try {
			ossClient.uploadFile(uploadFileRequest);
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// 关闭client
		ossClient.shutdown();
		return true;

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
	 * 断点续传下载
	 */
	public static String DownloadFileRequest(String path) throws Exception {
		// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
		// 创建OSSClient实例。
		/*OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		// 下载请求，10个任务并发下载，启动断点续传。
		DownloadFileRequest downloadFileRequest = new DownloadFileRequest(bucketName, path);
		downloadFileRequest.setDownloadFile("<yourdownloadFile>");
		downloadFileRequest.setPartSize(1 * 1024 * 1024);
		downloadFileRequest.setTaskNum(10);
		downloadFileRequest.setEnableCheckpoint(true);
		downloadFileRequest.setCheckpointFile("<yourCheckpointFile>");
		// 下载文件。
		DownloadFileResult downloadRes = ossClient.downloadFile(downloadFileRequest);
		// 下载成功时，会返回文件的元信息。
		downloadRes.getObjectMeta();
		// 关闭Client。
		ossClient.shutdown();*/
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
			}
		}
		return list;
	}

	/**
	 * 删除单个文件(夹)
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
	 * 删除多个文件(夹)
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
	 * 删除当前文件夹下所有文件
	 * @return
	 */
	public static List<String> deleteFilesAll(String path) {
		List<String> list=getFiles(path);
		deleteFiles(list);
		return list;
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
				String thumbnailsName = file.getParent() + "/thumbnails/" + thumbnails;
				//Thumbnails.of(localFile).scale(1d).keepAspectRatio(false).toFile(thumbnailsName);
				Thumbnails.of(localFile).scale(0.1d).toFile(thumbnailsName);
				System.out.println(thumbnailsName);
				//uploadDocumentByFile(path + "thumbnails/"+thumbnails, thumbnailsName);
				/*File localF = new File(thumbnailsName);
				if (localF.exists()) {
					localF.delete();
				}*/
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
		 // 分片大小，1MB
		 long partSize = 1024  * 1024 * 1;
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
	 
	 
	 public static void completeMultipartUpload(String sourceKey,String targetKey){
		String key = "yourKey";
		// 创建OSSClient实例
		OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
		InitiateMultipartUploadRequest request = new InitiateMultipartUploadRequest(bucketName, key);
		InitiateMultipartUploadResult result = ossClient.initiateMultipartUpload(request);
		String uploadId = result.getUploadId();
		
		List<PartETag> partETags = new ArrayList<PartETag>();
		InputStream instream = null;
		try {
			instream = new FileInputStream(new File("<localFile>"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UploadPartRequest uploadPartRequest = new UploadPartRequest();
		uploadPartRequest.setBucketName(bucketName);
		uploadPartRequest.setKey(key);
		uploadPartRequest.setUploadId(uploadId);
		uploadPartRequest.setInputStream(instream);
		// 设置分片大小，除最后一个分片外，其它分片要大于100KB
		uploadPartRequest.setPartSize(100 * 1024);
		// 设置分片号，范围是1~10000，
		uploadPartRequest.setPartNumber(1);
		UploadPartResult uploadPartResult = ossClient.uploadPart(uploadPartRequest);
		partETags.add(uploadPartResult.getPartETag());
		
		Collections.sort(partETags, new Comparator<PartETag>() {
			@Override
			public int compare(PartETag p1, PartETag p2) {
				return p1.getPartNumber() - p2.getPartNumber();
			}
		});
		
		CompleteMultipartUploadRequest completeMultipartUploadRequest = 
				new CompleteMultipartUploadRequest(bucketName, key, uploadId, partETags);
		ossClient.completeMultipartUpload(completeMultipartUploadRequest);
	 }
	 
	 /**
	  * 根据路径生成 policy和signature
	  * @param ossUrl 上传到指定的oss目录位置
	  * @throws ServletException
	  * @throws IOException
	  */
	 public static net.sf.json.JSONObject postObjectPolicy(String ossUrl) { 
		String endpoint = "oss-cn-hangzhou.aliyuncs.com";
        String host = "https://" + bucketName + "." + endpoint;
        net.sf.json.JSONObject json =null;
        OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        try { 	
        	/**
        	 * 上传有效期 设为 30分钟，因为相差8个小时的时差，所以加8
        	 */
        	long expireTime = 60 * 60 * 8 + 60 * 30;
        	long expireEndTime = System.currentTimeMillis() + expireTime * 1000;
            Date expiration = new Date(expireEndTime);
            PolicyConditions policyConds = new PolicyConditions();
            policyConds.addConditionItem(PolicyConditions.COND_CONTENT_LENGTH_RANGE, 0, 1073741823 * 2);
            policyConds.addConditionItem(MatchMode.StartWith, PolicyConditions.COND_KEY, ossUrl);
            String postPolicy = client.generatePostPolicy(expiration, policyConds);
            System.out.println("postPolicy:"+JSON.toJSON(postPolicy));
            byte[] binaryData = postPolicy.getBytes("utf-8");
            String encodedPolicy = BinaryUtil.toBase64String(binaryData);
            String postSignature = client.calculatePostSignature(postPolicy);
            Map<String, String> respMap = new LinkedHashMap<String, String>();
            respMap.put("accessid", accessKeyId);
            respMap.put("policy", encodedPolicy);
            respMap.put("signature", postSignature);
            respMap.put("dir", ossUrl);
            respMap.put("host", host);
            respMap.put("expire", String.valueOf(expireEndTime / 1000));
            json = net.sf.json.JSONObject.fromObject(respMap);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        return json;
    }
	 
	 /**
	  * 视频转码
	  * @param ossInputObject 输入路径
	  * @param ossOutputObject 输出路径
	  * @throws IOException
	  */
	 public static Boolean SimpleTranscode(String ossInputObject,String ossOutputObject) { 
		if(!isFile(ossInputObject)){
			return false;
		}
        // 创建DefaultAcsClient实例并初始化
        DefaultProfile profile = DefaultProfile.getProfile(
                mpsRegionId,      // 地域ID
                accessKeyId,      // RAM账号的AccessKey ID
                accessKeySecret); // RAM账号Access Key Secret
        IAcsClient client = new DefaultAcsClient(profile);
        // 创建API请求并设置参数
        SubmitJobsRequest request = new SubmitJobsRequest();
        // Input
        JSONObject input = new JSONObject();
        input.put("Location", ossLocation);
        input.put("Bucket", bucketName);
        try {
            input.put("Object", URLEncoder.encode(ossInputObject, "utf-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("input URL encode failed");
        }
        request.setInput(input.toJSONString());
        // Output
        String outputOSSObject;
        try {
            outputOSSObject = URLEncoder.encode(ossOutputObject, "utf-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("output URL encode failed");
        }
        JSONObject output = new JSONObject();
        output.put("OutputObject", outputOSSObject);
        // Ouput->Container
        JSONObject container = new JSONObject();
        container.put("Format", "mp4");
        output.put("Container", container.toJSONString());
        // Ouput->Video
        JSONObject video = new JSONObject();
        video.put("Codec", "H.264");
        video.put("Bitrate", "1500");
        video.put("Width", "1280");
        //video.put("Fps", "25");
        output.put("Video", video.toJSONString());
        // Ouput->Audio
        JSONObject audio = new JSONObject();
        audio.put("Codec", "AAC");
        audio.put("Bitrate", "128");
        audio.put("Channels", "2");
        audio.put("Samplerate", "44100");
        output.put("Audio", audio.toJSONString());
        // Ouput->TemplateId
        output.put("TemplateId", templateId);
        JSONArray outputs = new JSONArray();
        outputs.add(output);
        request.setOutputs(outputs.toJSONString());
        request.setOutputBucket(bucketName);
        request.setOutputLocation(ossLocation);
        // PipelineId
        request.setPipelineId(pipelineId);
        // 发起请求并处理应答或异常
        SubmitJobsResponse response;
        try {
            response = client.getAcsResponse(request);
            System.out.println("response is:"+JSON.toJSONString(response));
            if (response.getJobResultList().get(0).getSuccess()) {
               return true;
            } else {
            	log.info("视频转码出错：" + response.getJobResultList().get(0).getCode() + " message:" + response.getJobResultList().get(0).getMessage());
            	return false;
            }
        } catch (ServerException e) {
            e.printStackTrace();
        } catch (ClientException e) {
            e.printStackTrace();
        }
		return null;
	}
	 
	 
	/**
	 * 根据视频源OSS地址查询媒体信息
	 * @param path 视频路径
	 * @return
	 * @throws ClientException
	 * @throws UnsupportedEncodingException
	 */
    public static JSONObject queryMediaListByURL(String path) throws ClientException, UnsupportedEncodingException {
        QueryMediaListByURLRequest request = new QueryMediaListByURLRequest();
        String endpoint = "oss-cn-hangzhou.aliyuncs.com/";
        String ossHost = "http://" + bucketName + "." + endpoint;
        //ossObject需要符合rfc3986标准
        String rfc3986Object = encodeByRFC3986(path);
        request.setFileURLs(ossHost + rfc3986Object);
        DefaultAcsClient client = new DefaultAcsClient(DefaultProfile.getProfile(mpsRegionId, accessKeyId, accessKeySecret));
        QueryMediaListByURLResponse response = client.getAcsResponse(request);
        JSONArray array = JSON.parseArray(JSONObject.toJSONString(response.getMediaList()));
        System.out.println("array:"+array.toJSONString());
        return (JSONObject) array.get(0);
    }
    
    private static String encodeByRFC3986(String object) throws UnsupportedEncodingException {
        StringBuilder builder = new StringBuilder();
        String[] segments = object.split("/");
        for (int i = 0; i < segments.length; i++) {
            builder.append(percentEncode(segments[i]));
            if (i != segments.length - 1) {
                builder.append("/");
            }
        }
        return builder.toString();
    }
    private static String percentEncode(String value) throws UnsupportedEncodingException {
        if (value == null)
            return null;
        return URLEncoder.encode(value, "UTF-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
    }
    
    
    public static String getUrl(String objectName) {
    	// Endpoint以杭州为例，其它Region请按实际情况填写。
    	// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
    	// 创建OSSClient实例。
    	OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    	// 设置URL过期时间为1小时
    	Date expiration = new Date(new Date().getTime() + 3600 * 1000 * 9);
    	// 生成URL。
    	URL url = ossClient.generatePresignedUrl(bucketName, objectName, expiration);
    	// 关闭Client。
    	ossClient.shutdown();
    	System.out.println(JSON.toJSONString(url));
        return "";
    }
    
    public static String getUrl1(String objectName) {
    	// Endpoint以杭州为例，其它Region请按实际情况填写。
    	// 阿里云主账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建RAM账号。
    	// 创建OSSClient实例。
    	OSSClient ossClient  = new OSSClient(endpoint, accessKeyId, accessKeySecret);
    	// 设置URL过期时间为1小时
    	Date expiration = new Date(new Date().getTime() + 3600 * 1000 * 9);
    	GeneratePresignedUrlRequest request = new GeneratePresignedUrlRequest(bucketName, objectName, HttpMethod.GET);
    	//设置过期时间。
    	request.setExpiration(expiration);
    	// 生成URL签名(HTTP GET请求)。
    	URL signedUrl = ossClient .generatePresignedUrl(request);
    	System.out.println("signed url for getObject: " + signedUrl);
    	//使用url签名字串发送请求。
    	Map<String, String> customHeaders = new HashMap<String, String>();
    	// 添加GetObject请求头。
    	customHeaders.put("Range", "bytes=100-1000");
    	OSSObject object = ossClient.getObject(signedUrl,customHeaders);
    	// 关闭Client。
    	ossClient.shutdown();
        return object.toString();
    }
    
    /**
     * 对多个文件进行压缩生成压缩文件
     * @param keylist
     * @param zipUrl
     */
    public static void createZipFiles(String[] keylist,String zipUrl){
        try {
            // 初始化
            OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
            // 创建临时文件
            File zipFile = new File(zipUrl);
            FileOutputStream f = new FileOutputStream(zipFile);
            /**
             * 作用是为任何OutputStream产生校验和
             * 第一个参数是制定产生校验和的输出流，第二个参数是指定Checksum的类型 （Adler32（较快）和CRC32两种）
             */
            CheckedOutputStream csum = new CheckedOutputStream(f, new Adler32());
            // 用于将数据压缩成Zip文件格式
            ZipOutputStream zos = new ZipOutputStream(csum);
            for (String ossfile : keylist) {
                // 获取Object，返回结果为OSSObject对象
                OSSObject ossObject = ossClient.getObject(bucketName, ossfile);
                // 读取Object内容  返回
                InputStream inputStream = ossObject.getObjectContent();
                // 对于每一个要被存放到压缩包的文件，都必须调用ZipOutputStream对象的putNextEntry()方法，确保压缩包里面文件不同名
                zos.putNextEntry(new ZipEntry(ossfile.substring(ossfile.lastIndexOf("/")+1)));
                int bytesRead = 0;
                // 向压缩文件中输出数据
                while((bytesRead=inputStream.read())!=-1){
                    zos.write(bytesRead);
                }
                inputStream.close();
                zos.closeEntry(); // 当前文件写完，定位为写入下一条项目
            }
            zos.close();
            ossClient.shutdown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
