package com.cgwas.project.action;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.alibaba.fastjson.JSONObject;
import com.cgwas.common.utils.ConstantUtil;
import com.cgwas.common.utils.OSSFilesUtil;
import com.cgwas.companyFiles.entity.CompanyFilesVo;
import com.cgwas.companyFiles.service.api.ICompanyFilesService;
import com.cgwas.user.entity.User;
import com.cgwas.userCompany.service.api.IUserCompanyService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class UploadFile extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = -7843992473769226650L;
	@Autowired
	private ICompanyFilesService companyFilesService;
	@Autowired
	private IUserCompanyService userCompanyService;

	// 保存文件的目录
	 //private static String PATH_FOLDER = "d:/temp/uploadstart";
	// 存放临时文件的目录
	 //private static String TEMP_FOLDER = "d:/temp/uploadshow";
	public void init() throws ServletException {
		// 初始化
		WebApplicationContext wac = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
		companyFilesService = (ICompanyFilesService) wac.getBean("companyFilesService");
		userCompanyService = (IUserCompanyService) wac.getBean("userCompanyService");
	}

	public UploadFile() {
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
		System.out.println("加载上传方法");
		request.setCharacterEncoding("utf-8"); // 设置编码
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=UTF-8");
		User user = (User) request.getSession().getAttribute("loginUser");
		JSONObject json = new JSONObject();
		json.put("state", true);
		json.put("message", "上传成功！");
		if (user == null) {
			// throw new RuntimeException();
			user = new User();
			user.setId(1l);
		}
		//String contentRange = "" + "/" + "";
		response.setHeader( "Content-Range", "524288000/637163957");
		// 获得磁盘文件条目工厂
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 如果没以下两行设置的话，上传大的文件会占用很多内存，
		// 设置暂时存放的存储室 ,这个存储室,可以和最终存储文件的目录不同
		/**
		 * 原理 它是先存到 暂时存储室，然后在真正写到 对应目录的硬盘上， 按理来说 当上传一个文件时，其实是上传了两份，第一个是以 .tmp
		 * 格式的 然后再将其真正写到 对应目录的硬盘上
		 */
		factory.setRepository(new File(ConstantUtil.TEMP_FOLDER));
		// 设置 缓存的大小，当上传文件的容量超过该缓存时，直接放到 暂时存储室
		factory.setSizeThreshold(5000 * 5000);
		// 高水平的API文件上传处理
		ServletFileUpload upload = new ServletFileUpload(factory);
		try {
			// 提交上来的信息都在这个list里面
			// 这意味着可以上传多个文件
			// 请自行组织代码
			List<FileItem> list = (List<FileItem>) upload.parseRequest(request);
			// List<FileItem> list = (List<FileItem>)
			// upload.parseRequest(request);
			// 获取上传的文件
			Long parent_id = 0l;
			for (FileItem item : list) {
				// 获取表单的属性名字
				// String name = item.getFieldName();
				// 如果获取的 表单信息是普通的 文本 信息
				if (item.isFormField()) {
					// 获取用户具体输入的字符串 ，名字起得挺好，因为表单提交过来的是 字符串类型的
					if (item.getFieldName().equals("parent_id")) {
						parent_id = Long.parseLong(item.getString());
					}
					// request.setAttribute(name, value);
				}
				// 对传入的非 简单的字符串进行处理 ，比如说二进制的 图片，电影这些
				else {
					// 获取路径名
					String value = item.getName();
					//item.write(new File(PATH_FOLDER,value));// 第三方提供的
//					if(true){
//						break;
//					}
					StringBuffer fileUrls = new StringBuffer(ConstantUtil.PATH_FOLDER);
					/**
					 * 判断当前上传文件是否存在
					 */
					CompanyFilesVo companyFilesVo = new CompanyFilesVo();
					companyFilesVo.setParent_id(parent_id);
					companyFilesVo.setFile_name(value);
					List<CompanyFilesVo> filelist = companyFilesService.selectForList(companyFilesVo);
					if (filelist.size() == 0) {
						CompanyFilesVo parentFilesVo = new CompanyFilesVo();
						parentFilesVo.setId(parent_id);
						parentFilesVo = companyFilesService.selectForObject(parentFilesVo);
						String uuid = userCompanyService.getCompanyUserUUID(parentFilesVo.getCompany_id());
						// 总容量
						Long totalTolume =companyFilesService.getTotalTolume(parentFilesVo.getCompany_id());
						Map<String, Object> resouceMap = OSSFilesUtil.getFileSize(ConstantUtil.USER_PATH+ uuid+ "/data/"
								+ parentFilesVo.getFile_url().substring(0,parentFilesVo.getFile_url().indexOf("/")+1));
						// 占用空间
						Long occupySpace = (Long) resouceMap.get("spaceSize");
						if(totalTolume*1024*1024*1024<occupySpace+item.getSize()){
							json.put("state", false);
							json.put("message", "空间容量不足！");
							throw new RuntimeException();
						}
						/**
						 * 拼接文件夹路径
						 */
						fileUrls.append(uuid);
						fileUrls.append("/data/");
						fileUrls.append(parentFilesVo.getFile_url());
						fileUrls.append("/");
						fileUrls.append(value);
						companyFilesVo = new CompanyFilesVo();
						companyFilesVo.setFile_name(value);
						companyFilesVo.setParent_id(parent_id);
						companyFilesVo.setFile_url(parentFilesVo.getFile_url()+ "/" + value);
						companyFilesVo.setCompany_id(parentFilesVo.getCompany_id());
						companyFilesVo.setIs_file(ConstantUtil.NO_FILE);
						companyFilesVo.setCreator_id(user.getId());
						companyFilesVo.setFile_size(item.getSize());
						companyFilesService.save(companyFilesVo);
						item.write(new File(fileUrls.toString()));// 第三方提供的
					} else {
						json.put("state", false);
						json.put("message", "上传文件名不能相同！");
						throw new RuntimeException();
					}
				}
			}

		} catch (FileUploadException e) {
			e.printStackTrace();
			json.put("state", false);
			json.put("message", "上传失效");
		} catch (RuntimeException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			json.put("state", false);
			json.put("message", "上传失败，请重新上传");
		}
		response.getWriter().print(json.toJSONString());
	}

	/*
	 * private void clear() { File file = new File(TEMP_FOLDER); String[]
	 * tempList = file.list(); File temp = null; for (int i = 0; i <
	 * tempList.length; i++) { temp = new File(TEMP_FOLDER + "/" + tempList[i]);
	 * if (temp.isFile()) { temp.delete(); } } }
	 */
	public static void main(String[] args) {
		String a = "cgwas/a/b/";
		System.out.println(a.substring(0, a.indexOf("/") + 1));
	}
}
