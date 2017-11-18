package com.cgwas.common.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.channels.FileChannel;
import java.text.DecimalFormat;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cloopen.rest.sdk.utils.encoder.BASE64Decoder;

@SuppressWarnings({"resource","unused"})
public class FileUtils {
	// 验证字符串是否为正确路径名的正则表达式
	private static String matches = "[A-Za-z]:\\\\[^:?\"><*]*";
	private static final Logger logger = LoggerFactory
			.getLogger(FileUtils.class);
	// 图片路径
	private static final String IMG_PATH = "http://zhangyuefeng.oss-cn-hangzhou.aliyuncs.com/cgwas/images/";
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
	
	 public static void main(String[] args) {
	    	long start=System.currentTimeMillis();
	    	long size=getlist(new File("D:/dist"));
	    	long end=System.currentTimeMillis();
	    	System.out.println("文件夹大小："+documentNum);
	    	System.out.println("文件大小："+size);
	    	System.out.println("时间："+(end-start));
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
   
	public static boolean deleteDirectory(String dirPath) {// 删除目录（文件夹）以及目录下的文件
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
	public String GenerateImage(String imgStr, String imgFilePath) {
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
			// 生成jpeg图片
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
			file.getName().getBytes("UTF-8");
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

	/**
	 * 获取远程内容并返回
	 * 
	 * @param path
	 * @return
	 */
	public static String getFileContent(String path) {
		StringBuilder result = new StringBuilder();
		try {
			URL url = new URL(IMG_PATH + path);
			URLConnection connection = url.openConnection();
			InputStream is = connection.getInputStream();
			// 构造一个BufferedReader类来读取文件
			BufferedReader br = new BufferedReader(new InputStreamReader(is,
					"gbk"));
			String s = null;
			// 使用readLine方法，一次读一行
			while ((s = br.readLine()) != null) {
				result.append(System.lineSeparator() + s);
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString();
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
}
