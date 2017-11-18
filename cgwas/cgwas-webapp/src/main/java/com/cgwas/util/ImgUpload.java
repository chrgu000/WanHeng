package com.cgwas.util;

import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.cgwas.common.json.entity.Result;
import com.cgwas.common.utils.FileUtils;
import com.cloopen.rest.sdk.utils.encoder.BASE64Encoder;

@Controller
@RequestMapping("cgwas/systemAction")
public class ImgUpload {

	/**
	 * 图片上传返回base64
	 * 
	 * @param files
	 * @param request
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping("previewImg")
	public Result previewImg(MultipartFile files, HttpServletRequest request,
			double toCropImgX, double toCropImgY, double toCropImgH,
			double toCropImgW, HttpServletResponse response) throws Exception {

		/*
		 * toCropImgX: 裁剪的区域距离图片的左边缘的距离 toCropImgY: 裁剪的区域距离图片的上边缘的距离 toCropImgW:
		 * 裁剪的区域的宽度 toCropImgH: 裁剪的区域的高度 maxWidth: 你期望裁剪的图片的最大宽度 maxHeight:
		 * 你期望裁剪的图片的最大高度
		 */
		// String imgName= request.getAttribute("")
		// 转换成multi
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		// 获取文件表单
		CommonsMultipartFile file = (CommonsMultipartFile) multipartRequest
				.getFile("files");
		String base64Data = "";
		if (!files.isEmpty()) {
			try {
				BASE64Encoder encoder = new BASE64Encoder();
				// 通过base64来转化图片
				base64Data = encoder.encode(file.getBytes());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if ("".equals(base64Data)) {
			return new Result("RY0018", null); // 图片上传失败

		} else {

			String filenamme = files.getOriginalFilename(); // 得到上传的文件名
			String[] fileItem = filenamme.split("\\.");
			String formName = ""; // 得到文件后缀名
			if (fileItem.length == 2) {
				formName = fileItem[1];
			} else {
				// 文件格式不正确
			}

			FileUtils fuleUtils = new FileUtils();
			String nodepath = this.getClass().getClassLoader()
					.getResource("..//..//files//").getPath();
			String picPath = nodepath + UUID.randomUUID().toString();
			fuleUtils.GenerateImage(base64Data, picPath + filenamme); // 将base64转换成图片
			// 裁剪图片
			this.cutImage(picPath + filenamme, picPath + "mid" + filenamme,
					(new Double(toCropImgX)).intValue(),
					(new Double(toCropImgY)).intValue(),
					(new Double(toCropImgW)).intValue(),
					(new Double(toCropImgH)).intValue(), formName);

			// 获取裁剪后图片base64
			String retnBase64 = encodeBase64File(picPath + "mid" + filenamme);
			// 删除预览图片
			File filemid = new File(picPath + "mid" + filenamme);
			filemid.delete();

			return new Result(Boolean.TRUE, "上传成功", retnBase64); // 返回结果;

		}

	}

	/**
	 * 本地剪裁上传图片
	 * 
	 * @param files
	 * @param request
	 * @param response
	 * @return
	 */
	@ResponseBody
	@RequestMapping("previewImgLocal")
	public Result previewImgLocal(MultipartFile files,
			HttpServletRequest request, HttpServletResponse response) {
		StringBuffer base64Data = new StringBuffer();
		if (!files.isEmpty()) {
			try {
				BASE64Encoder encoder = new BASE64Encoder();
				// 通过base64来转化图片
				base64Data = base64Data.append(encoder.encode(files.getBytes())) ;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if ("".equals(base64Data)) {
			return new Result("UT0001", null); // 图片上传失败

		} else {

			return new Result(Boolean.TRUE, "上传成功", base64Data); // 返回结果;
		}

	}

	@ResponseBody
	@RequestMapping("previewImgFile")
	public Result previewImgFile(MultipartFile userfile,
			HttpServletRequest request, HttpServletResponse response) {
		StringBuffer base64Data = new StringBuffer();
		if (!userfile.isEmpty()) {
			try {
				BASE64Encoder encoder = new BASE64Encoder();
				// 通过base64来转化图片
				base64Data = base64Data.append(encoder.encode(userfile.getBytes())) ;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if ("".equals(base64Data)) {
			return new Result("UT0001", null); // 图片上传失败

		} else {

			return new Result(Boolean.TRUE, "上传成功", base64Data); // 返回结果;
		}

	}
	/*
	 * 图片裁剪通用接口
	 */

	public void cutImage(String src, String dest, int x, int y, int w, int h,
			String formName) throws IOException {
		@SuppressWarnings("rawtypes")
		Iterator iterator = ImageIO.getImageReadersByFormatName(formName);
		ImageReader reader = (ImageReader) iterator.next();
		InputStream in = new FileInputStream(src);

		ImageInputStream iis = ImageIO.createImageInputStream(in);
		reader.setInput(iis, true);
		ImageReadParam param = reader.getDefaultReadParam();
		Rectangle rect = new Rectangle(x, y, w, h);
		param.setSourceRegion(rect);
		BufferedImage bi = reader.read(0, param);
		ImageIO.write(bi, formName, new File(dest));
		in.close();
		File fileBig = new File(src);// 删除大图
		fileBig.delete();

	}

	/**
	 * 将文件转成base64 字符串
	 * 
	 * @param path文件路径
	 * @return *
	 * @throws Exception
	 */
	public String encodeBase64File(String path) throws Exception {
		File file = new File(path);
		FileInputStream inputFile = new FileInputStream(file);
		byte[] buffer = new byte[(int) file.length()];
		inputFile.read(buffer);
		inputFile.close();
		return new BASE64Encoder().encode(buffer);
	}

	/**
	 * 删除文件
	 * 
	 * @param file
	 * @return
	 */
	public boolean forceDelete(File file) {
		boolean result = file.delete();
		int tryCount = 0;
		while (!result && tryCount++ < 10) {
			System.gc(); // 回收资源
			result = file.delete();
		}
		return result;
	}

}
