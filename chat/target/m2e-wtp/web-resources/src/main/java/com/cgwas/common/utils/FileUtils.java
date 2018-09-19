package com.cgwas.common.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.coobird.thumbnailator.Thumbnails;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.cloopen.rest.sdk.utils.encoder.BASE64Decoder;

@SuppressWarnings({"resource","unused"})
public class FileUtils {
	// 验证字符串是否为正确路径名的正则表达式
	private static String matches = "[A-Za-z]:\\\\[^:?\"><*]*";
	private static final Logger logger = LoggerFactory
			.getLogger(FileUtils.class);
	// 通过 sPath.matches(matches) 方法的返回值判断是否正确
	// sPath 为路径字符串
	static boolean flag = false;
	static File file;
	static int k = 1; // 定义递归次数变量
	static int documentNum = 0; // 定义递归次数变量
	public static long getFileSizes(File f) throws Exception {// 取得文件大小
		long s = 0;
		if (f.exists()) {
			FileInputStream fis = null;
			fis = new FileInputStream(f);
			s = fis.available();        //返回字节数
		} else {
			f.createNewFile();
			System.out.println("文件不存在");
		}
		return s;
	}

	// 递归
	public static long getFileSize(File f) throws Exception// 取得文件夹大小
	{
		long size = 0;
		File flist[] = f.listFiles();
		for (int i = 0; i < flist.length; i++) {
			if (flist[i].isDirectory()) {   // 如果是目录
				size = size + getFileSize(flist[i]);    //递归  
			} else {
				size = size + flist[i].length();
			}
		}
		return size;
	}

	public static String FormetFileSize(long fileS) {// 转换文件大小
		DecimalFormat df = new DecimalFormat("#.00");
		String fileSizeString = "";
		if (fileS ==0) {
			fileSizeString = "0字节";
		}else if (fileS < 1024) {
			fileSizeString = df.format((double) fileS) + "字节";
		} else if (fileS < 1048576) {
			fileSizeString = df.format((double) fileS / 1024) + "KB";
		} else if (fileS < 1073741824) {
			fileSizeString = df.format((double) fileS / 1048576) + "MB";
		} else {
			fileSizeString = df.format((double) fileS / 1073741824) + "GB";
		}
		return fileSizeString;
	}

	public static long getFileSizesAll(File f) {// 递归取得目录占用空间大小
		File flist[] = f.listFiles();
		long total = 0;
		try {
			for (int i = 0; i < flist.length; i++) {
				if (flist[i].isDirectory()) {
					total = total + getFileSizesAll(flist[i]);
				} else {
					FileInputStream fis = new FileInputStream(flist[i]);
					long s = fis.available();
					if (s != 0) {
						if (s / 1024 == 0 || s / 1024 < 4) {
							s = 1024 * 4;
						} else {
							s = s / (1024 * 4) * (1024 * 4) + (1024 * 4);
						}
					}
					total += s;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return total;

	}
	
	public static long getlist(File f) {// 递归求取目录文件个数
		long size = 0;
		File flist[] = f.listFiles();
		size = flist.length;
		try {
			for (int i = 0; i < flist.length; i++) {
				if (flist[i].isDirectory()) {
					size = size + getlist(flist[i]);
					size--;
					documentNum++;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return size;

	}

	public static long getlistFile(File f) {// 递归求取目录文件夹以及文件总和
		long size = 0;
		File flist[] = f.listFiles();
		size = flist.length;
		for (int i = 0; i < flist.length; i++) {
			if (flist[i].isDirectory()) {
				size = size + getlistFile(flist[i]);
				System.out.println(size);
			}
		}
		return size;

	}

	public static boolean DeleteFolder(String deletePath) {// 根据路径删除指定的目录或文件，无论存在与否
		flag = false;
		if (deletePath.matches(matches)) {
			file = new File(deletePath);
			if (!file.exists()) {// 判断目录或文件是否存在
				return flag; // 不存在返回 false
			} else {

				if (file.isFile()) {// 判断是否为文件
					return deleteFile(deletePath);// 为文件时调用删除文件方法
				} else {
					return deleteDirectory(deletePath);// 为目录时调用删除目录方法
				}
			}
		} else {
			System.out.println("要传入正确路径！");
			return false;
		}
	}

	/**
	 * 删除文件夹
	 * @param filePath
	 * @return
	 */
	public static boolean deleteFile(String filePath) {// 删除单个文件
		flag = false;
		file = new File(filePath);
		if (file.isFile() && file.exists()) {// 路径为文件且不为空则进行删除
			file.delete();// 文件删除
			flag = true;
		}
		return flag;
	}
   
   /**
    * 使用文件通道的方式复制文件
    * @param s
    *            源文件
    * @param t
    *       复制到的新文件
    */
    public static void fileChannelCopy(File s, File t) {
    	if(s.exists()){
    		FileInputStream fi = null;
    		FileOutputStream fo = null;
    		FileChannel in = null;
    		FileChannel out = null;
    		try {
    			fi = new FileInputStream(s);
    			fo = new FileOutputStream(t);
    			in = fi.getChannel();//得到对应的文件通道
    			out = fo.getChannel();//得到对应的文件通道
    			in.transferTo(0, in.size(), out);//连接两个通道，并且从in通道读取，然后写入out通道
    		} catch (IOException e) {
    			e.printStackTrace();
    		} finally {
    			try {
    				fi.close();
    				in.close();
    				fo.close();
    				out.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
    		}
    	}
    }
   
    /**
     * 删除目录（文件夹）以及目录下的文件
     * @param dirPath
     * @return
     */
	public static boolean deleteDirectory(String dirPath) {
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!dirPath.endsWith(File.separator)) {
			dirPath = dirPath + File.separator;
		}
		File dirFile = new File(dirPath);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		flag = true;
		File[] files = dirFile.listFiles();// 获得传入路径下的所有文件
		for (int i = 0; i < files.length; i++) {// 循环遍历删除文件夹下的所有文件(包括子目录)
			if (files[i].isFile()) {// 删除子文件
				flag = deleteFile(files[i].getAbsolutePath());
				System.out.println(files[i].getAbsolutePath() + " 删除成功");
				if (!flag)
					break;// 如果删除失败，则跳出
			} else {// 运用递归，删除子目录
				flag = deleteDirectory(files[i].getAbsolutePath());
				System.out.println(files[i].getAbsolutePath() + " 删除成功");
				if (!flag)
					break;// 如果删除失败，则跳出
			}
		}
		if (!flag)
			return false;
		if (dirFile.delete()) {// 删除当前目录
			return true;
		} else {
			return false;
		}
	}
	
	/**
     * 删除目录下的文件
     * @param dirPath
     * @return
     */
	public static boolean deleteFileDocument(String dirPath) {
		// 如果sPath不以文件分隔符结尾，自动添加文件分隔符
		if (!dirPath.endsWith(File.separator)) {
			dirPath = dirPath + File.separator;
		}
		File dirFile = new File(dirPath);
		// 如果dir对应的文件不存在，或者不是一个目录，则退出
		if (!dirFile.exists() || !dirFile.isDirectory()) {
			return false;
		}
		flag = true;
		File[] files = dirFile.listFiles();// 获得传入路径下的所有文件
		for (int i = 0; i < files.length; i++) {// 循环遍历删除文件夹下的所有文件(包括子目录)
			if (files[i].isFile()) {// 删除子文件
				flag = deleteFile(files[i].getAbsolutePath());
				System.out.println(files[i].getAbsolutePath() + " 删除成功");
				if (!flag)
					break;// 如果删除失败，则跳出
			} else {// 运用递归，删除子目录
				flag = deleteDirectory(files[i].getAbsolutePath());
				System.out.println(files[i].getAbsolutePath() + " 删除成功");
				if (!flag)
					break;// 如果删除失败，则跳出
			}
		}
		return true;
	}

	// 创建单个文件
	public static boolean createFile(String filePath) {
		File file = new File(filePath);
		if (file.exists()) {// 判断文件是否存在
			System.out.println("目标文件已存在" + filePath);
			return false;
		}
		if (filePath.endsWith(File.separator)) {// 判断文件是否为目录
			System.out.println("目标文件不能为目录！");
			return false;
		}
		if (!file.getParentFile().exists()) {// 判断目标文件所在的目录是否存在
			// 如果目标文件所在的文件夹不存在，则创建父文件夹
			System.out.println("目标文件所在目录不存在，准备创建它！");
			if (!file.getParentFile().mkdirs()) {// 判断创建目录是否成功
				System.out.println("创建目标文件所在的目录失败！");
				return false;
			}
		}
		try {
			if (file.createNewFile()) {// 创建目标文件
				System.out.println("创建文件成功:" + filePath);
				return true;
			} else {
				System.out.println("创建文件失败！");
				return false;
			}
		} catch (IOException e) {// 捕获异常
			e.printStackTrace();
			System.out.println("创建文件失败！" + e.getMessage());
			return false;
		}
	}

	// 创建目录
	public static boolean createDir(String destDirName) {
		File dir = new File(destDirName);
		if (dir.exists()) {// 判断目录是否存在
			System.out.println("创建目录失败，目标目录已存在！");
			return false;
		}
		if (!destDirName.endsWith(File.separator)) {// 结尾是否以"/"结束
			destDirName = destDirName + File.separator;
		}
		if (dir.mkdirs()) {// 创建目标目录
			System.out.println("创建目录成功！" + destDirName);
			return true;
		} else {
			System.out.println("创建目录失败！");
			return false;
		}
	}

	/**
	 * 目录不存在时，先创建目录
	 * 
	 * @param zipFileName
	 */
	private static void createDirs(String zipFileName) {
		String filePath = StringUtils.substringBeforeLast(zipFileName, "/");
		File targetFile = new File(filePath);
		if (!targetFile.exists()) {// 目录不存在时，先创建目录
			targetFile.mkdirs();
		}
	}

	// 创建临时文件
	public static String createTempFile(String prefix, String suffix,
			String dirName) {
		File tempFile = null;
		if (dirName == null) {// 目录如果为空
			try {
				tempFile = File.createTempFile(prefix, suffix);// 在默认文件夹下创建临时文件
				return tempFile.getCanonicalPath();// 返回临时文件的路径
			} catch (IOException e) {// 捕获异常
				e.printStackTrace();
				System.out.println("创建临时文件失败：" + e.getMessage());
				return null;
			}
		} else {
			// 指定目录存在
			File dir = new File(dirName);// 创建目录
			if (!dir.exists()) {
				// 如果目录不存在则创建目录
				if (FileUtils.createDir(dirName)) {
					System.out.println("创建临时文件失败，不能创建临时文件所在的目录！");
					return null;
				}
			}
			try {
				tempFile = File.createTempFile(prefix, suffix, dir);// 在指定目录下创建临时文件
				return tempFile.getCanonicalPath();// 返回临时文件的路径
			} catch (IOException e) {// 捕获异常
				e.printStackTrace();
				System.out.println("创建临时文件失败!" + e.getMessage());
				return null;
			}
		}
	}

	/**
	 * 对字节数组字符串进行Base64解码并生成图片
	 * 
	 * @param imgStr
	 *            base64编码
	 * @param imgFilePath
	 *            图片上传路径
	 * @return
	 */
	public static String GenerateImage(String imgStr, String imgFilePath) {
		if (imgStr == null) // 图像数据为空
			return null;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] bytes = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < bytes.length; ++i) {
				if (bytes[i] < 0) {// 调整异常数据
					bytes[i] += 256;
				}
			}
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(bytes);
			out.flush();
			out.close();
			return imgFilePath;
		} catch (Exception e) {
			return null;
		}
	}

	public static HttpServletResponse downloadZip(File file,
			HttpServletResponse response, HttpServletRequest request) {
		try {
			// 以流的形式下载文件。
			InputStream fis = new BufferedInputStream(new FileInputStream(
					file.getPath()));
			// 清空response
			response.reset();
			response.setCharacterEncoding("UTF-8");
			String filename = null;
			OutputStream toClient = new BufferedOutputStream(
					response.getOutputStream());
			response.setContentType("application/octet-stream");// 设置为下载application/x-download
			if ("FF".equals(getBrowser(request))) {
				// 针对火狐浏览器处理方式不一样了
				filename = new String(file.getName().getBytes("UTF-8"),
						"ISO-8859-1");
			} else {
				filename = URLEncoder.encode(file.getName(), "UTF-8");
			}
			response.setHeader("Content-Disposition", "attachment;filename="
					+ filename);
			byte[] buffer = new byte[1024 * 1024 * 4];
			int i = -1;
			while ((i = fis.read(buffer)) != -1) {
				toClient.write(buffer, 0, i);
			}
			fis.read(buffer);
			fis.close();
			// response.setContentType("application/octet-stream");
			// 如果输出的是中文名的文件，在此处就要用URLEncoder.encode方法进行处理
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			try {
				File f = new File(file.getPath());
				f.delete();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return response;
	}

	/**
	 * 压缩指定的单个或多个文件，如果是目录，则遍历目录下所有文件进行压缩
	 * 
	 * @param zipFileName
	 *            ZIP文件名包含全路径
	 * @param files
	 *            文件列表
	 */
	public static boolean zip(String zipFileName, File... files) {
		System.out.println("压缩: " + zipFileName);
		ZipOutputStream out = null;
		BufferedOutputStream bo = null;
		try {
			createDirs(zipFileName);
			out = new ZipOutputStream(new FileOutputStream(zipFileName));
			for (int i = 0; i < files.length; i++) {
				if (null != files[i]) {
					zip(out, files[i], files[i].getName());
				}
			}
			out.close(); // 输出流关闭
			System.out.println("压缩完成");
			return true;
		} catch (Exception e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 执行压缩
	 * 
	 * @param out
	 *            ZIP输入流
	 * @param f
	 *            被压缩的文件
	 * @param base
	 *            被压缩的文件名
	 */
	private static void zip(ZipOutputStream out, File f, String base) { // 方法重载
		try {
			if (f.isDirectory()) {// 压缩目录
				try {
					System.out.println("压缩中。。。");
					File[] fl = f.listFiles();
					if (fl.length == 0) {
						out.putNextEntry(new ZipEntry(base + "/")); // 创建zip实体
						logger.info(base + "/");
					}
					for (int i = 0; i < fl.length; i++) {
						zip(out, fl[i], base + "/" + fl[i].getName()); // 递归遍历子文件夹
					}
					// System.out.println("第" + k + "次递归");
					k++;
				} catch (IOException e) {
					logger.error(e.getMessage());
					e.printStackTrace();
				}
			} else { // 压缩单个文件
				logger.info(base);
				out.putNextEntry(new ZipEntry(base)); // 创建zip实体
				FileInputStream in = new FileInputStream(f);
				BufferedInputStream bi = new BufferedInputStream(in);
				int b;
				while ((b = bi.read()) != -1) {
					out.write(b); // 将字节流写入当前zip目录
				}
				out.closeEntry(); // 关闭zip实体
				in.close(); // 输入流关闭
			}

		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		}
	}


	// 以下为服务器端判断客户端浏览器类型的方法
	public static String getBrowser(HttpServletRequest request) {
		String UserAgent = request.getHeader("USER-AGENT").toLowerCase();
		if (UserAgent != null) {
			if (UserAgent.indexOf("msie") >= 0)
				return "IE";
			if (UserAgent.indexOf("firefox") >= 0)
				return "FF";
			if (UserAgent.indexOf("safari") >= 0)
				return "SF";
		}
		return null;
	}
	/**
	 * base64转byte
	 * 
	 * @param base64
	 * @return
	 */
	public static byte[] base64ToByte(String base64) {
		int headIndex = base64.indexOf(",", 1);

		if (headIndex != -1) {// 去头
			String base = base64;
			String head = base.substring(0, headIndex + 1);
			base64 = base64.replace(head, "");
		}

		byte[] fileArr = Base64.decodeBase64(base64);
		return fileArr;

	}

	/**
	 * 路径生成工具类(用户)
	 * 
	 * @param type
	 * @param uuid
	 * @param fileName
	 * @return
	 */
	public static String getSaveUserPath(String uuid, String fileName) {
		int date = (int) ((Math.random() * 9 + 1) * 100000);// new Date()为获取当前系统时间，也可使用当前时间戳
		String path = ConstantUtil.USER_PATH+uuid+"/images/avater/user/"+fileName+"_"+date+".jpg";
		return path;
	};
	/**
	 * 路径生成工具类(公司)
	 * @param uuid
	 * @param fileName
	 * @return
	 */
	public static String getSaveCompanyPath(String uuid, String fileName,Long companyId) {
		int date = (int) ((Math.random() * 9 + 1) * 100000);// new Date()为获取当前系统时间，也可使用当前时间戳
		String path = ConstantUtil.USER_PATH+uuid+"/images/avater/company/"+String.valueOf(companyId)+"/"+fileName+"_"+date+".jpg";
		return path;
	};
	
	/**
	 * 路径生成工具类(项目)
	 * @param uuid
	 * @param fileName
	 * @return
	 */
	public static String getSaveProjectPath(String uuid, String fileName) {
		String path = ConstantUtil.USER_PATH+uuid+"/data/"+fileName+"/";
		return path;
	}

	public static String getSaveProjectCoverPath(String uuid, String fileName) {

		String path = ConstantUtil.USER_PATH+uuid+"/images/avater/user/"+fileName+".jpg";
		return path;
	};
	public static String getSaveArbitrateUserInfoIMGPath(String uuid, String fileName){
		String path = ConstantUtil.ARB_PATH +uuid+"/images/"+fileName+".jpg";
		return path;
	}
	
	/**
	 * 文件上传
	 * @param file
	 * @param path
	 * @return
	 */
	public static Boolean uploadFile(MultipartFile file, String path) {
		// 保存
		try {
			System.out.println("开始");
			FileOutputStream os = new FileOutputStream(path);
			InputStream in = file.getInputStream();
			byte[] buffer = new byte[1024 * 1024 * 4];
			int i = -1;
			while ((i = in.read(buffer)) != -1) {
				os.write(buffer, 0, i);
			}
			os.flush(); // 关闭流
			in.close();
			os.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}
	
	/**
	 * 根据图片路径生成指定路径的缩略图 
	 * @param localFile 图片路径
	 * @param path 缩略图路径
	 * 
	 */
	public static void uploadThumbnailsImg(String localFile,String path) {
		try {
			System.out.println("path:"+path);
			System.out.println("localFile:"+localFile);
			Thumbnails.of(localFile).scale(0.2f).outputQuality(0.5f).toFile(path);
			System.out.println("成功");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/** 
     * 采用指定宽度、高度或压缩比例 的方式对图片进行压缩 
     * @param imgsrc 源图片地址 
     * @param imgdist 目标图片地址 
     * @param widthdist 压缩后图片宽度（当rate==null时，必传） 
     * @param heightdist 压缩后图片高度（当rate==null时，必传） 
     * @param rate 压缩比例  
     */  
	public static void reduceImg(String imgsrc, String imgdist) {  
        try {  
            File srcfile = new File(imgsrc);  
            // 检查文件是否存在  
            if (!srcfile.exists()) {  
                return;  
            }  
            int[] results = getImgWidth(srcfile);  
            int widthdist=400;   
            int heightdist=0;
            if(results[0] == results[1]){
            	heightdist=400;  
            }else{
            	heightdist=(int) (results[1] /(results[0]/400));
            }
            System.out.println(widthdist+":"+heightdist);
            // 开始读取文件并进行压缩  
            Image src = javax.imageio.ImageIO.read(srcfile);  
            BufferedImage tag = new BufferedImage((int) widthdist,  
                    (int) heightdist, BufferedImage.TYPE_INT_RGB);  
  
            tag.getGraphics().drawImage(  
                    src.getScaledInstance(widthdist, heightdist,  
                            Image.SCALE_SMOOTH), 0, 0, null);  
            String formatName = imgdist.substring(imgdist.lastIndexOf(".") + 1);  
            ImageIO.write(tag, /*"GIF"*/ formatName /* format desired */ , new File(imgdist) /* target */ );  
  
        } catch (IOException ex) {  
            ex.printStackTrace();  
        }  
    }  
    
    public static void main(String[] args) {  
        /** 
         * d://3.jpg 源图片 
         * d://31.jpg 目标图片 
         * 压缩宽度和高度都是1000 
         *  
         */  
    	/*long startTime = System.currentTimeMillis();
    	fileChannelCopy(new File("D:\\开发软件\\eclipse_64bit.zip"), new File("E:\\eclipse_64bit.zip"));
    	long endTime = System.currentTimeMillis();
        System.out.println("耗时:" + (endTime-startTime));  */
    	File tempFile= new File ("E:/f1114432423423.zip/");
    	tempFile.delete();
    	System.out.println(tempFile.length());
      
    }  
    
    /** 
     * 获取图片宽度 
     *  
     * @param file 
     *            图片文件 
     * @return 宽度 
     */  
    public static int[] getImgWidth(File file) {  
        InputStream is = null;  
        BufferedImage src = null;  
        int result[] = { 0, 0 };  
        try {  
            is = new FileInputStream(file);  
            src = javax.imageio.ImageIO.read(is);  
            result[0] = src.getWidth(null); // 得到源图宽  
            result[1] = src.getHeight(null); // 得到源图高  
            is.close();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return result;  
    }  
	/**
	 * 
	 * @param filePath 图片路径
	 * @param thumbPath 缩略图路径
	 * @throws Exception
	 */
	@SuppressWarnings("restriction")
	public static void toSmaillImg(String filePath,String thumbPath) throws Exception{   
		Long startTime =System.currentTimeMillis();
        String newurl =thumbPath;    
        java.awt.Image bigJpg = javax.imageio.ImageIO.read(new java.io.File(filePath));    
        float tagsize = 100;    
        int old_w = bigJpg.getWidth(null);    
        int old_h = bigJpg.getHeight(null);       
        int new_w = 0;    
        int new_h = 0;    
        float tempdouble;     
        tempdouble = old_w > old_h ? old_w/tagsize : old_h/tagsize;    
        new_w = Math.round(old_w/tempdouble);    
        new_h = Math.round(old_h/tempdouble);    
        java.awt.image.BufferedImage tag = new java.awt.image.BufferedImage(new_w,new_h,java.awt.image.BufferedImage.TYPE_INT_RGB);    
        tag.getGraphics().drawImage(bigJpg,0,0,new_w,new_h,null);    
        FileOutputStream newimage = new FileOutputStream(newurl);    
        com.sun.image.codec.jpeg.JPEGImageEncoder encoder = com.sun.image.codec.jpeg.JPEGCodec.createJPEGEncoder(newimage);           
        encoder.encode(tag);    
        newimage.close(); 
        Long endTime =System.currentTimeMillis();
        System.out.println("转换缩略图成功,耗时:"+(endTime-startTime));
	}
	
	/**
	 * 根据文件后缀匹配所属文件类型
	 * @param suffix
	 * @return
	 */
	public static String matchType(String suffix) {
		//视频格式
		String[] videoSuffix={"mp4","mov","wmv","avi"};
		//图片格式
		String[] imgSuffix={"png","jpg","gif","bmp","jpge","tga","tif"};
		if(Arrays.asList(videoSuffix).contains(suffix)){
			return "video";
		}else if(Arrays.asList(imgSuffix).contains(suffix)){
			return "image";
		}
		return "other";
	}
}
