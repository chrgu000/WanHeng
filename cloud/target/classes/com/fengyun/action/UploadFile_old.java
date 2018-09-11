package com.fengyun.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fengyun.service.IChapterService;

public class UploadFile_old extends HttpServlet {

	// 保存文件的目录
	private static String PATH_IMAGE_FOLDER = "/";
	// 存放临时文件的目录
	private static String TEMP_VEDIO_FOLDER = "/";

	private IChapterService chapterService;

	public void init(ServletConfig config) throws ServletException {
		ServletContext servletCtx = config.getServletContext();
		// 初始化路径
		// 保存文件的目录
		PATH_IMAGE_FOLDER = "/usr/memory/cgwas/cloud/images";
		// 存放临时文件的目录,存放xxx.tmp文件的目录
		TEMP_VEDIO_FOLDER = "/usr/memory/cgwas/cloud/videos";
		WebApplicationContext wac = WebApplicationContextUtils
				.getRequiredWebApplicationContext(config.getServletContext());
		chapterService = (IChapterService) wac.getBean("chapterService");

	}

	public UploadFile_old() {
		super();
	}

	public void destroy() {
		super.destroy();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().print("error");
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); // 设置编码
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File(request.getSession().getServletContext()
				.getRealPath("/")
				+ "uploadstart/vedios"));
		factory.setSizeThreshold(5000 * 5000);
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			Long t1 = System.currentTimeMillis();
			List<FileItem> list = (List<FileItem>) upload.parseRequest(request);
			String filenames = "";
			String flag = request.getParameter("type");
			if ("images".equals(flag)) {
				String srcUrl = "";
				Map<String, String> data = new HashMap<String, String>();
				Map<String, Object> cache = new HashMap<String, Object>();
				PrintWriter out = response.getWriter();
				for (FileItem item : list) {
					String name = item.getFieldName();
					if (item.isFormField()) {
						String value = item.getString();
					} else {
						String value = item.getName();
						int start = value.lastIndexOf("\\");
						String filename = value.substring(start + 1);
						String type = filename.substring(filename
								.lastIndexOf("."));
						String type1 = type.toLowerCase();
						filename = new Date().getTime() + ""
								+ (new Random().nextInt(900) + 100) + type1;
						final File src = new File(
								"/usr/memory/cgwas/cloud/images", filename);
						item.write(src);// 第三方提供的
						srcUrl += new File("cgwas/cloud/images", src.getName())
								.getPath() + "&";
						data.put("title",filename);
					}
				}
				data.put("src", srcUrl);
				cache.put("code", 0);
				cache.put("msg", "后台接收数据异常");
				cache.put("data", JSONObject.fromObject(data)
						.toString());
				JSONObject object = JSONObject.fromObject(cache);
				out.print(object.toString());
				out.flush();
				out.close();
			} else {
				for (FileItem item : list) {
					String name = item.getFieldName();
					if (item.isFormField()) {
						String value = item.getString();
					} else {
						String value = item.getName();
						int start = value.lastIndexOf("\\");
						String filename = value.substring(start + 1);
						String type = filename.substring(filename
								.lastIndexOf("."));
						String type1 = type.toLowerCase();
						filename = new Date().getTime() + ""
								+ (new Random().nextInt(900) + 100) + type1;
						PrintWriter out = response.getWriter();
						final String filename1 = filename.substring(1,
								filename.lastIndexOf("."))
								+ ".mp4";
						final File source = new File(request.getSession()
								.getServletContext().getRealPath("/")
								+ "uploadstart/vedios", filename);
						final File target = new File(TEMP_VEDIO_FOLDER,
								filename1);
						Long begin = System.currentTimeMillis();
						item.write(source);// 第三方提供的
						Map<String, Object> cache = new HashMap<String, Object>();
						cache.put("sourceFile", source.getAbsolutePath());
						cache.put("targetFile", target.getAbsolutePath());
						cache.put("videoTimeLength",
								"/usr/memory/cgwas/cloud/videos/" + filename1);
						cache.put("filePath", "cgwas/cloud/videos/" + filename1);
						JSONObject object = JSONObject.fromObject(cache);
						out.print(object.toString());
						out.flush();
						out.close();
					}
				}
			}
			

		} catch (FileUploadException e) {
			e.printStackTrace();
			response.getWriter().print("error");
		} catch (Exception e) {
			e.printStackTrace();
			response.getWriter().print("error");
		}
	}
}
