package com.cgwas.companyFiles.action;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.alibaba.fastjson.JSONObject;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.dataaccess.api.PageUtils;
import com.cgwas.common.json.entity.Result;
import com.cgwas.common.listener.FileUploadUtil;
import com.cgwas.common.utils.CompanyFilesTree;
import com.cgwas.common.utils.ConstantUtil;
import com.cgwas.common.utils.FileUtils;
import com.cgwas.common.utils.OSSFilesUtil;
import com.cgwas.company.service.api.ICompanyService;
import com.cgwas.companyFiles.entity.CompanyFiles;
import com.cgwas.companyFiles.entity.CompanyFilesVo;
import com.cgwas.companyFiles.entity.Resouce;
import com.cgwas.companyFiles.service.api.ICompanyFilesService;
import com.cgwas.companyFilesUser.entity.CompanyFilesUserVo;
import com.cgwas.companyFilesUser.service.api.ICompanyFilesUserService;
import com.cgwas.employee.entity.EmployeeVo;
import com.cgwas.employee.service.api.IEmployeeService;
import com.cgwas.gRole.entity.GRoleVo;
import com.cgwas.gRole.service.api.IGRoleService;
import com.cgwas.logInfo.entity.LogInfo;
import com.cgwas.logInfo.service.api.ILogInfoService;
import com.cgwas.privilegeInfo.entity.PrivilegeInfoVo;
import com.cgwas.privilegeInfo.service.api.IPrivilegeInfoService;
import com.cgwas.role.entity.RoleVo;
import com.cgwas.role.service.api.IRoleService;
import com.cgwas.subproject.entity.SubProjectVo;
import com.cgwas.subproject.service.api.ISubProjectService;
import com.cgwas.user.entity.User;
import com.cgwas.userCompany.entity.UserCompany;
import com.cgwas.userCompany.service.api.IUserCompanyService;

/**
 * 
 * @author yubangqiong
 * 
 */
@Controller
@RequestMapping("cgwas/companyFilesAction")
public class CompanyFilesAction {
	@Autowired
	private ICompanyFilesService companyFilesService;
	@Autowired
	private IPrivilegeInfoService privilegeInfoService;
	@Autowired
	private ISubProjectService subProjectService;
	@Autowired
	private IGRoleService gRoleService;
	@Autowired
	private ICompanyService companyService;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private IUserCompanyService userCompanyService;
	@Autowired
	private IEmployeeService employeeService;
	@Autowired
	private ICompanyFilesUserService companyFilesUserService;
	@Autowired
	private ILogInfoService logInfoService;
	private List<Long> ids = null;
	private List<String> fileUrls = null;
	/**
	 * 资产管理列表
	 * 
	 * @param pageSize
	 * @param pageNo
	 * @param parentId
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/list")
	@ResponseBody
	public Result list(String pageSize, String pageNo,
			Long parent_id,String key_word,
			HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		Map<String, Object> taskObject = new HashMap<String, Object>();
		PrivilegeInfoVo privilegeVo= new PrivilegeInfoVo();
		CompanyFilesVo companyFilesVo = new CompanyFilesVo();
		GRoleVo gRoleVo = new GRoleVo();
		List<GRoleVo> gRoleList=new ArrayList<GRoleVo>();
		Page page = null;
		List<PrivilegeInfoVo> privilegeList=new ArrayList<PrivilegeInfoVo>();
		if (parent_id == null) {
			companyFilesVo.setParent_id(0l);
		}else{
			companyFilesVo.setParent_id(parent_id);
		}
		try {
			if (companyFilesVo.getParent_id() > 0) {
				companyFilesVo.setId(parent_id);
				companyFilesVo.setParent_id(null);
				companyFilesVo = companyFilesService.selectForObject(companyFilesVo);
				/**
				 * 参数重置
				 */
				companyFilesVo.setParent_id(companyFilesVo.getId());
				companyFilesVo.setId(null);
			}
			if(key_word!=null && !key_word.equals("")){
				companyFilesVo.setFile_name("%"+key_word+"%");
			}else{
				companyFilesVo.setFile_name("");
			}
			/**
			 * 初始化参数
			 */
			companyFilesVo.setVisible(0l);
			User user = (User) request.getSession().getAttribute("loginUser");
			// 根据登录用户的id查询公司文件(夹)列表
			Map<String, String> params = new HashMap<String, String>();
			// parent_id为null的时候说明是资产管理第一层级，初始值为0
			params.put("pageSize", pageSize);
			params.put("pageNo", pageNo);
			page = PageUtils.createPage(params);
			/**
			 * 参数注入
			 */
			companyFilesVo.setUser_id(user.getId());
			RoleVo roleVo = roleService.selectForUserId(user.getId());
			if (roleVo == null) {
				return new Result("RY0053", null);
			}
			/**
			 * 若角色为超级管理员
			 */
			if (roleVo.getRole_type().equals("ADMIN")) {
				page = companyFilesService.page(page, companyFilesVo);
				privilegeVo.setParent_id(16L);
				privilegeList=privilegeInfoService.selectForList(privilegeVo);
				if(companyFilesVo.getFile_type() != null && companyFilesVo.getFile_type().equals(ConstantUtil.ZC_TASK)){
					taskObject.put("taskType", companyFilesVo.getTask_type());
					taskObject.put("id", companyFilesVo.getFor_id());
				}
			}else {
				/**
				 * 当前用户可能是用户也有可能是公司 此时用户和公司文件列表合并
				 */
				if(companyFilesVo.getParent_id()==0){
					/**
					 * 角色初始化
					 */
					request.getSession().setAttribute("gRoleVo",null);
					/**
					 * 获取文件列表
					 */
					page = companyFilesService.pageForCompany(page, companyFilesVo);
					if(page.getDataList().size()==0){
						return new Result("SYS_PRIVILEGE", null);
					}
					request.getSession().setAttribute("isCompanyFiles",null);
				}else{
					if(companyFilesVo.getCreator_id()!=null && companyFilesVo.getFile_type()!=null && user.getId().equals(companyFilesVo.getCreator_id())
							&& companyFilesVo.getFile_type().equals(ConstantUtil.ZC_COMPANY)){
						page = companyFilesService.page(page, companyFilesVo);
						request.getSession().setAttribute("isCompanyFiles","Y");
						privilegeVo.setParent_id(16L);
						privilegeList=privilegeInfoService.selectForList(privilegeVo);
					}else{
						String isCompanyFiles=(String)request.getSession().getAttribute("isCompanyFiles");
						if(isCompanyFiles!=null && isCompanyFiles.equals("Y")){
							page = companyFilesService.page(page, companyFilesVo);
							privilegeVo.setParent_id(16L);
							privilegeList=privilegeInfoService.selectForList(privilegeVo);
							if(companyFilesVo.getFile_type() != null && companyFilesVo.getFile_type().equals(ConstantUtil.ZC_TASK)){
								taskObject.put("taskType", companyFilesVo.getTask_type());
								taskObject.put("id", companyFilesVo.getFor_id());
							}
						}else{
							/**
							 * 判断该用户是否进入拥有管理项目文件夹权限
							 */
							page = companyFilesService.pageForGRole(page, companyFilesVo);
							if (page.getDataList().size() == 0) {
								if(companyFilesVo.getFile_type() != null && companyFilesVo.getFile_type().equals(ConstantUtil.ZC_PROJECT)){
									/**
									 * 初始化gRoleVo
									 */
									request.getSession().setAttribute("gRoleVo", null);
									page = companyFilesService.page(page, companyFilesVo);
									/**
									 * 获取用户所属团队角色
									 */
									gRoleVo.setUser_id(user.getId());
									gRoleVo.setIs_parent_poject(ConstantUtil.IS_PARENT_POJECT);
									gRoleVo.setProject_id(companyFilesVo.getFor_id());
									gRoleList=gRoleService.getGRoleListByUserId(gRoleVo);
									if(gRoleList.size()>0){
										if(gRoleList.size()==1 && gRoleList.get(0).getRole_p_id()==5){
											request.getSession().setAttribute("gRoleVo", null);
										}else{
											request.getSession().setAttribute("gRoleVo", gRoleVo);
										}
									}
								}else if(companyFilesVo.getFile_type() != null && companyFilesVo.getFile_type().equals(ConstantUtil.ZC_SUB_PROJECT)) {
									page = companyFilesService.page(page, companyFilesVo);
									/**
									 * 获取用户所属团队角色
									 */
									gRoleVo=(GRoleVo) request.getSession().getAttribute("gRoleVo");
									if(gRoleVo==null){
										gRoleVo= new GRoleVo();
										gRoleVo.setUser_id(user.getId());
										gRoleVo.setIs_parent_poject(ConstantUtil.NO_PARENT_POJECT);
										gRoleVo.setProject_id(companyFilesVo.getFor_id());
										gRoleList=gRoleService.getGRoleListByUserId(gRoleVo);
										if(gRoleList.size()>0){
											if(gRoleList.size()==1 && gRoleList.get(0).getRole_p_id()==5){
												request.getSession().setAttribute("gRoleVo", null);
											}else{
												request.getSession().setAttribute("gRoleVo", gRoleVo);
											}
										}
									}
								}else if(companyFilesVo.getFile_type() != null && companyFilesVo.getFile_type().equals(ConstantUtil.ZC_TASK)){
									GRoleVo vo=(GRoleVo) request.getSession().getAttribute("fGRoleVo");
									GRoleVo vo1=(GRoleVo) request.getSession().getAttribute("gRoleVo");
									if(vo!=null){
										request.getSession().setAttribute("gRoleVo",vo);
									}else{
										request.getSession().setAttribute("gRoleVo",vo1);
									}
									page = companyFilesService.page(page, companyFilesVo);
									taskObject.put("taskType", companyFilesVo.getTask_type());
									taskObject.put("id", companyFilesVo.getFor_id());
								}else{
									page = companyFilesService.page(page, companyFilesVo);
								}
								
							}else{
								if(companyFilesVo.getFile_type() != null && companyFilesVo.getFile_type().equals(ConstantUtil.ZC_PROJECT) ){
									/**
									 * 初始化gRoleVo
									 */
									request.getSession().setAttribute("gRoleVo", null);
									request.getSession().setAttribute("fGRoleVo", null);
									/**
									 * 获取用户所属团队角色
									 */
									gRoleVo.setUser_id(user.getId());
									gRoleVo.setIs_parent_poject(ConstantUtil.IS_PARENT_POJECT);
									gRoleVo.setProject_id(companyFilesVo.getFor_id());
									gRoleList=gRoleService.getGRoleListByUserId(gRoleVo);
									if(gRoleList.size()>0){
										if(gRoleList.size()==1 && gRoleList.get(0).getRole_p_id()==5){
											request.getSession().setAttribute("fGRoleVo", gRoleVo);
										}else{
											page = companyFilesService.page(page, companyFilesVo);
											request.getSession().setAttribute("gRoleVo", gRoleVo);
										}
									}
								}else if(companyFilesVo.getFile_type() != null && companyFilesVo.getFile_type().equals(ConstantUtil.ZC_SUB_PROJECT)) {
									/**
									 * 获取用户所属团队角色
									 */
									gRoleVo=(GRoleVo) request.getSession().getAttribute("gRoleVo");
									request.getSession().setAttribute("fGRoleVo", null);
									if(gRoleVo==null){
										gRoleVo= new GRoleVo();
										gRoleVo.setUser_id(user.getId());
										gRoleVo.setIs_parent_poject(ConstantUtil.NO_PARENT_POJECT);
										gRoleVo.setProject_id(companyFilesVo.getFor_id());
										gRoleList=gRoleService.getGRoleListByUserId(gRoleVo);
										if(gRoleList.size()>0){
											if(gRoleList.size()==1 && gRoleList.get(0).getRole_p_id()==5){
												request.getSession().setAttribute("fGRoleVo", gRoleVo);
											}else{
												page = companyFilesService.page(page, companyFilesVo);
												request.getSession().setAttribute("gRoleVo", gRoleVo);
											}
										}else{
											request.getSession().setAttribute("gRoleVo", null);
										}
									}
								}else{
									request.getSession().setAttribute("gRoleVo", null);
								}
							}
						}
					}
					gRoleVo=(GRoleVo) request.getSession().getAttribute("gRoleVo");
					if(gRoleVo!=null){
						privilegeList=privilegeInfoService.selectPrivilegeListByUser(gRoleVo);
					}
					
				}
			}
			map = new HashMap<String, Object>();
			map.put("total", page.getTotal());
			map.put("pageSize", page.getLimit());
			map.put("pageNo", page.getCurrentPage());
			map.put("dataList", page.getDataList());
			map.put("privilegeList", privilegeList);
			map.put("taskObject", taskObject);
			return new Result(Boolean.TRUE, "成功", map);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("SYS_DATA_ERRO", null);
		}
	}
	
	/**
	 * 根据团队角色获取权限列表结构
	 * @param privilegeInfo
	 * @param model
	 * @return
	 */
	@RequestMapping("/gRolePrivilegeList")
	public @ResponseBody Result gRolePrivilegeList(PrivilegeInfoVo privilegeInfoVo) {
		try {
			List<PrivilegeInfoVo>  list= privilegeInfoService.selectPrivilegeByGRole(privilegeInfoVo);
			return new Result(Boolean.TRUE,"成功",list);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("SYS_DATA_ERRO",null);
		}
	}
	
	/**
	 * 弹窗获取项目资源列表以及公司文件列表结构
	 * 
	 * @param companyFiles
	 * @return
	 */
	@RequestMapping("/getResouceModel")
	@ResponseBody
	public Result getResouceModel(Long parentId, String type) {
		// 所属id
		CompanyFilesVo companyFilesVo = new CompanyFilesVo();
		SubProjectVo subProjectVo = null;
		if (parentId == null) {
			return new Result("SYS_PRIVILEGE", null);
		}
		if (type == null) {
			type = ConstantUtil.ZC_COMPANY;
		}
		if (type.equals(ConstantUtil.ZC_COMPANY)
				|| type.equals(ConstantUtil.ZC_PROJECT)
				|| type.equals(ConstantUtil.ZC_SUB_PROJECT)) {
		} else {
			return new Result("SYS_PARAMETER", null);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		CompanyFiles companyFiles = new CompanyFiles();
		try {
			// 查询
			companyFilesVo.setFor_id(parentId);
			companyFilesVo.setFile_type(type);
			companyFilesVo = companyFilesService.selectForObject(companyFilesVo);
			// 记录
			int i = 0;
			// 如果存在该文件夹则跳出
			if(type.equals(ConstantUtil.ZC_COMPANY) || type.equals(ConstantUtil.ZC_PROJECT)){
				if(companyFilesVo == null){
					return new Result("ZC0006", null);
				}
			}else{
				if (companyFilesVo == null) {
					i++;
					/**
					 * 此代码说明子项目没有创建文件夹，所以获取父项目文件夹对象
					 */
					do {
						subProjectVo = new SubProjectVo();
						subProjectVo.setId(parentId);
						subProjectVo = subProjectService.selectForObject(subProjectVo);
						if (subProjectVo.getProject_id() == null) {
							companyFilesVo = new CompanyFilesVo();
							companyFilesVo.setFor_id(subProjectVo.getSub_parent_project_id());
							companyFilesVo.setFile_type(ConstantUtil.ZC_SUB_PROJECT);
							companyFilesVo = companyFilesService.selectForObject(companyFilesVo);
							parentId = subProjectVo.getSub_parent_project_id();
						} else {
							companyFilesVo = new CompanyFilesVo();
							companyFilesVo.setFor_id(subProjectVo.getProject_id());
							companyFilesVo.setFile_type(ConstantUtil.ZC_PROJECT);
							companyFilesVo = companyFilesService.selectForObject(companyFilesVo);
							parentId = subProjectVo.getProject_id();
						}
						if (companyFilesVo != null) {
							break;
						}
						if (i > 5) {
							return new Result(Boolean.FALSE, "循环嵌套异常跳出", null);
						}
					} while (true);
				}
			}
			// 获取当前文件夹下的子文件夹
			companyFiles.setParent_id(companyFilesVo.getId());
			List<CompanyFilesVo> companyFilesList = companyFilesService.selectForList(companyFiles);
			companyFiles.setId(companyFilesVo.getId());
			companyFiles.setFile_name(companyFilesVo.getFile_name());
			companyFiles.setParent_id(companyFilesVo.getParent_id());
			map.put("companyFiles", companyFiles);
			map.put("fileList", companyFilesList);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("SYS_DATA_ERRO", null);
		}
		return new Result(Boolean.TRUE, "成功", map);
	}

	/**
	 * 父文件路径
	 * @param id
	 * @return
	 */
	@RequestMapping("/parentFileUrl")
	@ResponseBody
	public Result parentFileUrl(Long parentId, String type) {
		if (!(type.equals(ConstantUtil.ZC_COMPANY)
				|| type.equals(ConstantUtil.ZC_PROJECT)
				|| type.equals(ConstantUtil.ZC_SUB_PROJECT)) || parentId == null) {
			return new Result("SYS_PARAMETER", null);
		}
		CompanyFilesVo companyFilesVo = new CompanyFilesVo();
		//Map<String,Object> map=new HashMap<String, Object>();
		try {
			// 注入id
			companyFilesVo.setFor_id(parentId);
			companyFilesVo.setFile_type(type);
			companyFilesVo = companyFilesService.selectForObject(companyFilesVo);
			SubProjectVo subProjectVo = null;
			if (companyFilesVo == null) {
				/**
				 * 此代码说明父项目没有创建文件夹，所以获取父项目文件夹对象
				 */
				do {
					subProjectVo = new SubProjectVo();
					subProjectVo.setId(parentId);
					subProjectVo = subProjectService
							.selectForObject(subProjectVo);
					if(subProjectVo==null){
						return new Result("ZC0007", null);
					}
					if (subProjectVo.getProject_id() == null) {
						companyFilesVo = new CompanyFilesVo();
						companyFilesVo.setFor_id(subProjectVo.getSub_parent_project_id());
						companyFilesVo.setFile_type(ConstantUtil.ZC_SUB_PROJECT);
						companyFilesVo = companyFilesService.selectForObject(companyFilesVo);
						parentId = subProjectVo.getSub_parent_project_id();
					} else {
						companyFilesVo = new CompanyFilesVo();
						companyFilesVo.setFor_id(subProjectVo.getProject_id());
						companyFilesVo.setFile_type(ConstantUtil.ZC_PROJECT);
						companyFilesVo = companyFilesService.selectForObject(companyFilesVo);
						parentId = subProjectVo.getProject_id();
					}
					if (companyFilesVo != null) {
						break;
					}
				} while (true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("SYS_DATA_ERRO", null);
		}
		return new Result(Boolean.TRUE, null, companyFilesVo.getFile_url());
	}
	
	/**
	 * 
	 * @param companyFiles
	 * @return
	 */
	@RequestMapping("/fileListById")
	@ResponseBody
	public Result getFileListById(Long id, HttpServletRequest request) {
		// 所属id
		if (id == null) {
			return new Result("SYS_PARAMETER", null);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		CompanyFilesVo companyFilesVo = new CompanyFilesVo();
		try {
			companyFilesVo.setId(id);
			companyFilesVo=companyFilesService.selectForObject(companyFilesVo);
			if (companyFilesVo == null) {
				return new Result("ZC0001", null);
			}else{
				if(companyFilesVo.getIs_file().equals(ConstantUtil.NO_FILE)){
					return new Result("ZC0011", null);
				}
			}
			// 获取当前文件夹下的子文件夹
			companyFilesVo = new CompanyFilesVo();
			companyFilesVo.setParent_id(id);
			List<CompanyFilesVo> companyFilesList = companyFilesService
					.selectForList(companyFilesVo);
			map.put("fileList", companyFilesList);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("SYS_DATA_ERRO", null);
		}
		return new Result(Boolean.TRUE, "成功", map);
	}
	
	/**
	 * 
	 * @param file_url
	 * @return
	 */
	@RequestMapping("/getIdByFileUrl")
	@ResponseBody
	public Result getIdByFileUrl(String file_url) {
		if (file_url == null) {
			return new Result("SYS_PARAMETER", null);
		}
		CompanyFilesVo companyFilesVo = new CompanyFilesVo();
		try {
			companyFilesVo.setFile_url(file_url);
			companyFilesVo = companyFilesService.selectForObject(companyFilesVo);
			if(companyFilesVo==null){
				return new Result("ZC0016", null);
			}
		} catch (Exception e) {
			return new Result("SYS_DATA_ERRO", null);
		}
		return new Result(Boolean.TRUE, "成功", companyFilesVo.getId());
	}
	
	@RequestMapping("/uploadProgress") 
	public void uploadFile(HttpServletRequest request,HttpServletResponse response, 
	            @RequestParam("file") CommonsMultipartFile file) throws IOException { 
	   response.setContentType("text/html"); 
	   response.setCharacterEncoding("GBK"); 
	   PrintWriter out; 
	   boolean flag = false; 
	   if (file.getSize() > 0) { 
	     //文件上传的位置可以自定义 
	     flag = FileUploadUtil.uploadFile(request,file); 
	   } 
	   out = response.getWriter(); 
	   if (flag == true) { 
	    out.print("1"); 
	   } else { 
	    out.print("2"); 
	   } 
	} 
	
	/** 
     * 从阿里云下载文件 （以附件形式下载） 
     * @param request 
     * @param response 
     */  
	@ResponseBody  
    @RequestMapping(value = "/downLoadFile", method = RequestMethod.GET)  
    public void downLoadFile(Long id,HttpServletResponse response, HttpServletRequest request){  
        try {  
        	CompanyFilesVo companyFilesVo= new CompanyFilesVo();
    		companyFilesVo.setId(id);
    		companyFilesVo = companyFilesService.selectForObject(companyFilesVo);
    		// 创建OSSClient实例
    		if(companyFilesVo!=null){
    			/**
    			 * 拼接文件夹路径
    			 */
    			//通知浏览器以附件形式下载 
    			response.reset();  
    			response.setContentType("application/x-msdownload");  
    			//response.setHeader("Content-Disposition","attachment;filename=" + new String(fileName.getBytes("gb2312"),"ISO8859-1"));  
    			response.setHeader("Content-Disposition","attachment;filename="+URLEncoder.encode(companyFilesVo.getFile_name(),"utf-8"));  
    			response.setContentType("application/octet-stream");// 设置为下载application/x-download
    			String uuid=userCompanyService.getCompanyUserUUID(companyFilesVo.getCompany_id());
    			StringBuffer fileUrls=new StringBuffer(ConstantUtil.PATH_FOLDER);
    			fileUrls.append(uuid);
    			fileUrls.append("/data/");
    			fileUrls.append(companyFilesVo.getFile_url());	
    			// 以流的形式下载文件。
    			File file =new File(fileUrls.toString());
    			InputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
    			// 清空response
    			response.reset();
    			response.setCharacterEncoding("UTF-8");
    			file.getName().getBytes("UTF-8");
    			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
    			response.setHeader("Content-Disposition", "attachment;filename="+ companyFilesVo.getFile_name());
    			byte[] buffer = new byte[1024 * 1024 * 4];
    			int i = -1;
    			while ((i = fis.read(buffer)) != -1) {
    				toClient.write(buffer, 0, i);
    			}
    			fis.read(buffer);
    			fis.close();
    			// response.setContentType("application/octet-stream");
    			// 如果输出的是中文名的文件，在此处就要用URLEncoder.encode方法进行处理
    			toClient.flush();
    			toClient.close();
    		}
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
	
	/** 
     * @param request 
     * @param response 
     */  
	@ResponseBody  
    @RequestMapping(value = "/downLoadFileByFileUrl", method = RequestMethod.GET)  
    public void downLoadFileByFileUrl(String file_url,HttpServletResponse response, HttpServletRequest request){  
        try {  
        	CompanyFilesVo companyFilesVo= new CompanyFilesVo();
    		companyFilesVo.setFile_url(file_url);
    		companyFilesVo = companyFilesService.selectForObject(companyFilesVo);
    		// 创建OSSClient实例
    		if(companyFilesVo!=null){
    			/**
    			 * 拼接文件夹路径
    			 */
    			//通知浏览器以附件形式下载 
    			response.reset();  
    			response.setContentType("application/x-msdownload");  
    			//response.setHeader("Content-Disposition","attachment;filename=" + new String(fileName.getBytes("gb2312"),"ISO8859-1"));  
    			response.setHeader("Content-Disposition","attachment;filename="+URLEncoder.encode(companyFilesVo.getFile_name(),"utf-8"));  
    			response.setContentType("application/octet-stream");// 设置为下载application/x-download
    			String uuid=userCompanyService.getCompanyUserUUID(companyFilesVo.getCompany_id());
    			StringBuffer fileUrls=new StringBuffer(ConstantUtil.PATH_FOLDER);
    			fileUrls.append(uuid);
    			fileUrls.append("/data/");
    			fileUrls.append(companyFilesVo.getFile_url());	
    			// 以流的形式下载文件。
    			File file =new File(fileUrls.toString());
    			InputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
    			// 清空response
    			response.reset();
    			response.setCharacterEncoding("UTF-8");
    			file.getName().getBytes("UTF-8");
    			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
    			response.setHeader("Content-Disposition", "attachment;filename="+ companyFilesVo.getFile_name());
    			byte[] buffer = new byte[1024 * 1024 * 4];
    			int i = -1;
    			while ((i = fis.read(buffer)) != -1) {
    				toClient.write(buffer, 0, i);
    			}
    			fis.read(buffer);
    			fis.close();
    			// response.setContentType("application/octet-stream");
    			// 如果输出的是中文名的文件，在此处就要用URLEncoder.encode方法进行处理
    			toClient.flush();
    			toClient.close();
    		}
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
	
	/**
	 * 文件路径
	 * @param id
	 * @return
	 */
	public Map<String,Object> fileUrl(Long id) {
		CompanyFilesVo companyFilesVo = new CompanyFilesVo();
		Map<String,Object> map= new HashMap<String, Object>();
		// 注入id
		companyFilesVo.setId(id);
		try {
			companyFilesVo = companyFilesService.selectForObject(companyFilesVo);
			// 参数注入
			if (companyFilesVo == null) {
				map.put("filePath", "");
				return map;
			}
			map.put("company_id", companyFilesVo.getCompany_id());
			map.put("filePath", companyFilesVo.getFile_url());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	/**
	 *根据id获取父id
	 * @param id
	 * @return
	 */
	@RequestMapping("/getParentId")
	public @ResponseBody Result getParent_id(Long id) {
		if(id==null){
			return new Result("SYS_PARAMETER", null);
		}
		if(id==0){
			return new Result("ZC0012", null);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		CompanyFilesVo companyFilesVo = new CompanyFilesVo();
		// 注入id
		companyFilesVo.setId(id);
		try {
			companyFilesVo = companyFilesService.selectForObject(companyFilesVo);
			if (companyFilesVo == null) {
				return new Result("ZC0001", null);
			}
			map.put("parent_id", companyFilesVo.getParent_id());
		} catch (Exception e) {
			return new Result("SYS_DATA_ERRO", map);
		}
		return new Result(Boolean.TRUE, "成功", map);
	}
	
	/**
	 * 获取当前文件夹的相对路径
	 * 
	 * @param companyFiles
	 * @return
	 */
	@RequestMapping("/getFileUrl")
	public @ResponseBody
	Result getFileUrl(Long id) {
		// 注入id
		if (id == null) {
			return new Result("SYS_PARAMETER", null);
		}
		CompanyFilesVo companyFilesVo= new CompanyFilesVo();
		companyFilesVo.setId(id);
		companyFilesVo = companyFilesService.selectForObject(companyFilesVo);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("fileUrl", companyFilesVo.getFile_url());
		map.put("id", companyFilesVo.getId());
		return new Result(Boolean.TRUE, "成功", map);
	}

	/**
	 * 空间不足消息提醒 ： 您的可用空间不足 param ,请及时扩充容量，以免耽误项目进度！
	 */

	/**
	 * 资源属性
	 * 
	 * @param companyFiles
	 * @return
	 */
	@RequestMapping("/attribute")
	public @ResponseBody
	Result attribute(CompanyFilesVo companyFilesVo) {
		// 注入id
		if (companyFilesVo.getId() == null) {
			return new Result("SYS_PARAMETER", null);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		PrivilegeInfoVo privilegeInfoVo = new PrivilegeInfoVo();
		GRoleVo gRoleVo = null;
		Resouce resouce = new Resouce();
		try {
			companyFilesVo = companyFilesService.selectForObject(companyFilesVo);
			// 参数注入
			if (companyFilesVo == null) {
				return new Result("SYS_DATA_ERRO", map);
			}
			String fileUrl=companyFilesVo.getFile_url();
			Long company_id=companyFilesVo.getCompany_id();
			/**
			 * 判断当前文件类型
			 */
			if (companyFilesVo.getFile_type() != null
					&& !companyFilesVo.getFile_type().equals(ConstantUtil.ZC_COMPANY)) {
				if (companyFilesVo.getFile_type().equals(ConstantUtil.ZC_PROJECT)) {
					gRoleVo = new GRoleVo();
					gRoleVo.setProject_id(companyFilesVo.getFor_id());
					gRoleVo.setIs_parent_poject(ConstantUtil.IS_PARENT_POJECT);
				} else if (companyFilesVo.getFile_type().equals(
						ConstantUtil.ZC_SUB_PROJECT)) {
					gRoleVo = new GRoleVo();
					gRoleVo.setProject_id(companyFilesVo.getFor_id());
					gRoleVo.setIs_parent_poject(ConstantUtil.NO_PARENT_POJECT);
				}
				List<GRoleVo> gRoleVoList=new ArrayList<GRoleVo>();
				List<PrivilegeInfoVo> privilegeList=new ArrayList<PrivilegeInfoVo>();
				// 根据对应的项目文件夹获取对应的项目角色
				if(gRoleVo!=null){
					gRoleVoList = gRoleService.selectForList(gRoleVo);
					// 根据资产菜单显示相关权限
					privilegeInfoVo.setParent_id(16l);
					privilegeList = privilegeInfoService.selectForList(privilegeInfoVo);
				}
				map.put("privilegeList", privilegeList);
				map.put("gRoleVoList", gRoleVoList);
			}
			// 文件信息
			// 目录的字节大小
			long currentTotalByte = 0;
			// 目录的字节大小
			// 目录的大小
			String currentTotal = "";
			// 目录占用空间的字节大小
			long currentSpaceTotalByte = 0l;
			// 目录占用空间的大小
			String currentSpaceTotal = FileUtils
					.FormetFileSize(currentSpaceTotalByte);
			/**
			 * 拼接文件夹路径
			 */
			String uuid=userCompanyService.getCompanyUserUUID(company_id);
			StringBuffer fileUrls=new StringBuffer(ConstantUtil.USER_PATH);
			System.out.println("uuid:"+uuid);
			fileUrls.append(uuid);
			fileUrls.append("/data/");
			fileUrls.append(fileUrl);
			Map<String, Object> resouceMap = OSSFilesUtil.getFileSize(fileUrls.toString());
			// 目录的字节大小
			currentTotalByte = (Long) resouceMap.get("totalSize");
			// 目录的大小
			currentTotal = FileUtils.FormetFileSize(currentTotalByte);
			// 目录占用空间的字节大小
			currentSpaceTotalByte = (Long) resouceMap.get("spaceSize");
			// 目录占用空间的大小
			currentSpaceTotal = FileUtils.FormetFileSize(currentSpaceTotalByte);
			// 文件夹路径
			resouce.setFileUrl(companyFilesVo.getFile_url());
			// 创建时间
			resouce.setLastModified(companyFilesVo.getCreate_time());
			// 是否文件夹
			resouce.setIsFile(companyFilesVo.getIs_file());
			if (companyFilesVo.getIs_file().equals("Y")) { // 如果路径是文件夹的话
				// 文件个数
				long document = (Long) resouceMap.get("documentNum");
				// 文件夹个数
				long files = (Long) resouceMap.get("filesNum");
				resouce.setFiles(files);
				resouce.setDocument(document);
			}
			resouce.setCurrentTotalByte(currentTotalByte);
			resouce.setCurrentTotal(currentTotal);
			resouce.setCurrentSpaceTotalByte(currentSpaceTotalByte);
			resouce.setCurrentSpaceTotal(currentSpaceTotal);
			map.put("resouce", resouce);

		} catch (Exception e) {
			e.printStackTrace();
			return new Result("SYS_DATA_ERRO", map);
		}
		return new Result(Boolean.TRUE, "success", map);
	}

	/**
	 * 获取当前公司的总容量
	 * 
	 * @param companyFiles
	 * @return
	 */
	@RequestMapping("/companyVolume")
	@ResponseBody
	public Result companyVolume(Long parent_id, HttpServletRequest request) {
		CompanyFilesVo companyFilesVo = new CompanyFilesVo();
		// 注入
		if (parent_id == null) {
			return new Result("SYS_PARAMETER", null);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		Resouce resouce = new Resouce();
		Long company_id=0l;
		String file_url="";
		long totalTolume = 0;
		try {
			// 注入参数
			companyFilesVo.setId(parent_id);
			companyFilesVo = companyFilesService.selectForObject(companyFilesVo);
			if(companyFilesVo==null){
				return new Result("ZC0001", null);
			}
			if(companyFilesVo.getParent_id()==0){
				request.getSession().setAttribute("zc_company_id",companyFilesVo.getCompany_id());
				file_url=companyFilesVo.getFile_url();
				company_id=companyFilesVo.getCompany_id();
			}else{
				company_id=(Long)request.getSession().getAttribute("zc_company_id");
				if(company_id==null){
					company_id=companyFilesVo.getCompany_id();
				}
				file_url=companyFilesVo.getFile_url();
				file_url=file_url.substring(0, file_url.indexOf("/"));
			}
			if(company_id==0){
				return new Result("ZC0001", null);
			}
			// 总容量
			totalTolume =companyFilesService.getTotalTolume(company_id);
			/**
			 * 拼接文件夹路径
			 */
			String uuid=userCompanyService.getCompanyUserUUID(company_id);
			StringBuffer fileUrls=new StringBuffer(ConstantUtil.USER_PATH);
			fileUrls.append(uuid);
			fileUrls.append("/data/");
			fileUrls.append(file_url);
			fileUrls.append("/");
			Map<String, Object> resouceMap = OSSFilesUtil.getFileSize(fileUrls.toString());
			// 占用空间
			long occupySpace = (Long) resouceMap.get("spaceSize");
			// 剩余空间
			long surplusSpace =  totalTolume* 1024 * 1024 * 1024- occupySpace;
			if(totalTolume==0){
				resouce.setPercentage(String.valueOf(100));
			}else{
				BigDecimal percentage = new BigDecimal(occupySpace*100/(totalTolume* 1024 * 1024 * 1024));
				percentage = percentage.setScale(0, RoundingMode.HALF_UP);
				resouce.setPercentage(String.valueOf(percentage));
			}
			resouce.setTotalTolume(String.valueOf(totalTolume));
			resouce.setOccupySpace(FileUtils.FormetFileSize(occupySpace));
			resouce.setSurplusSpace(FileUtils.FormetFileSize(surplusSpace));
			map.put("resouce", resouce);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("SYS_DATA_ERRO", null);
		}
		return new Result(Boolean.TRUE, "success", map);
	}
	public static void main(String[] args) {
		String path="a/b";
		if(path.indexOf("/")==-1){
			System.out.println(path);
		}else{
			System.out.println(path.substring(0, path.indexOf("/")));
		}
		
	}

	/**
	 * 获取oss图片列表
	 * 
	 * @param companyFiles
	 * @return
	 */
	@RequestMapping("/imgList")
	@ResponseBody
	public Result imgList(String path) {
		if (path == null || path.equals("")) {
			return new Result("SYS_PARAMETER", null);
		}
		CompanyFilesVo companyFilesVo= new CompanyFilesVo();
		companyFilesVo.setFile_url(path);
		companyFilesVo = companyFilesService.selectForObject(companyFilesVo);
		if(companyFilesVo==null){
			return new Result("该文件夹已失效", null);
		}
		/**
		 * 拼接文件夹路径
		 */
		String uuid=userCompanyService.getCompanyUserUUID(companyFilesVo.getCompany_id());
		StringBuffer fileUrls=new StringBuffer(ConstantUtil.USER_PATH);
		fileUrls.append(uuid);
		fileUrls.append("/data/");
		fileUrls.append(path);
		fileUrls.append("/");
		List<String> list = OSSFilesUtil.list(fileUrls.toString());
		return new Result(Boolean.TRUE, "success", list);
	}

	/**
	 * 新建文件夹
	 * 
	 * @param companyFiles 
	 * @return
	 */
	@RequestMapping("/createFile")
	@ResponseBody
	public Result createFile(Long parent_id,String file_name,HttpServletRequest request) {
		if(parent_id==null || file_name==null){
			return new Result("SYS_PARAMETER",null);
		}
		/**
		 * 获取当前登录的用户
		 */
		User user = (User) request.getSession().getAttribute("loginUser");
		CompanyFiles companyFiles= new CompanyFiles();
		Map<String,Object> param=fileUrl(parent_id);
		try {
			String fileUrl = (String)param.get("filePath");
			Long company_id =(Long)param.get("company_id");
			if(fileUrl.equals("") ||company_id ==null){
				return new Result("ZC0003", null);
			}
			companyFiles.setParent_id(parent_id);
			companyFiles.setFile_name(file_name);
			if(companyFilesService.selectForObject(companyFiles)!=null){
				return new Result("ZC0004", null);
			}
			/**
			 * 拼接文件夹路径
			 */
			String uuid=userCompanyService.getCompanyUserUUID(company_id);
			StringBuffer fileUrls=new StringBuffer(ConstantUtil.USER_PATH);
			fileUrls.append(uuid);
			fileUrls.append("/data/");
			fileUrls.append(fileUrl);
			fileUrls.append("/");
			fileUrls.append(file_name);
			fileUrls.append("/");
			/**
			 * 添加文件夹到OSS
			 */
			boolean flag=OSSFilesUtil.addFile(fileUrls.toString());
			if(!flag){
				return new Result("ZC0002", null);
			}
			/**
			 * 添加文件夹记录表
			 */
			companyFiles= new CompanyFiles();
			companyFiles.setParent_id(parent_id);
			companyFiles.setFile_name(file_name);
			companyFiles.setIs_file(ConstantUtil.IS_FILE);
			companyFiles.setCompany_id(company_id);
			companyFiles.setFile_url(fileUrl+"/"+file_name);
			companyFiles.setCreator_id(user.getId());
			companyFilesService.save(companyFiles);
			/**
			 * 执行操作日志
			 */
			LogInfo logInfo= new LogInfo();
			logInfo.setLog_type(ConstantUtil.LOG_SAVE);
			logInfo.setTable_name("p_company_files");
			logInfo.setUser_id(user.getId());
			logInfo.setUser_name(user.getUsername());
			logInfo.setContent("新建文件夹-"+companyFiles.getFile_name());
			logInfoService.save(logInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("SYS_DATA_ERRO", null);
		}
		return new Result(Boolean.TRUE, "文件夹创建成功", null);
	}
	
	/**
	 * 文件是否存在
	 * 
	 * @param companyFiles 
	 * @return
	 */
	@RequestMapping("/isDocument")
	@ResponseBody
	public Result isDocument(Long parent_id,String file_name,HttpServletRequest request) {
		if(parent_id==null || file_name==null){
			return new Result("SYS_PARAMETER",null);
		}
		CompanyFiles companyFiles= new CompanyFiles();
		try {
			companyFiles.setParent_id(parent_id);
			companyFiles.setFile_name(file_name);
			companyFiles=companyFilesService.selectForObject(companyFiles);
			if(companyFiles !=null){
				return new Result("ZC0010", null);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("SYS_DATA_ERRO", null);
		}
		return new Result(Boolean.TRUE, "", null);
	}
	
	/**
	 * 根据子文件id获取父文件夹集
	 * 
	 * @param companyFiles 
	 * @return
	 */
	@RequestMapping("/getparentFilesById")
	@ResponseBody
	public Result getparentFilesById(Long id,String file_name,HttpServletRequest request) {
		if(id==null){
			return new Result("SYS_PARAMETER",null);
		}
		if(id>0){
			CompanyFilesVo companyFilesVo= new CompanyFilesVo();
			CompanyFilesVo obj= new CompanyFilesVo();
			Map<String,Object> param= null;
			List<Map<String,Object>> list = new ArrayList<Map<String,Object>>();
			//输出list
			List<Map<String,Object>> outlist = new ArrayList<Map<String,Object>>();
			try {
				//参数注入
				companyFilesVo.setParent_id(id);
				do{
					obj.setId(companyFilesVo.getParent_id());
					companyFilesVo=companyFilesService.selectForObject(obj);
					if(companyFilesVo==null){
						return new Result("ZC0015", null);
					}
					//参数注入
					param= new HashMap<String, Object>();
					param.put("id", companyFilesVo.getId());
					param.put("file_name", companyFilesVo.getFile_name());
					list.add(param);
					if(companyFilesVo.getParent_id()==0){
						break;
					}
				}while(true);
				
				/**
				 * 路径反转
				 */
				for (int i = list.size()-1; i >=0; i--) {
					outlist.add(list.get(i));
				}
				return new Result(Boolean.TRUE, "成功", outlist);
			} catch (Exception e) {
				e.printStackTrace();
				return new Result("SYS_DATA_ERRO", null);
			}
		}else{
			return new Result(Boolean.TRUE, "成功", null);
		}
	}
	
	/**
	 *获取文件夹id和oss下路径
	 * @param companyFiles
	 * @return
	 */
	public Boolean getMap(CompanyFilesVo companyFilesVo) {
		boolean flag=true;
		CompanyFilesVo objVo= new CompanyFilesVo();
		if(companyFilesVo!=null){
			if(companyFilesVo.getFile_type()!=null){
				if(companyFilesVo.getFile_type().equals(ConstantUtil.ZC_COMPANY)){
					throw new StopMsgException("ZC0008");
				}
				if(companyFilesVo.getFile_type().equals(ConstantUtil.ZC_PROJECT) || 
						companyFilesVo.getFile_type().equals(ConstantUtil.ZC_SUB_PROJECT)|| 
						companyFilesVo.getFile_type().equals(ConstantUtil.ZC_TASK)){
					/**
					 * 跳出递归循环
					 */
					throw new StopMsgException("ZC0005");
				}
			}
			/**
			 * 拼接文件夹路径
			 */
			String uuid=userCompanyService.getCompanyUserUUID(companyFilesVo.getCompany_id());
			StringBuffer fileUrl=new StringBuffer(ConstantUtil.USER_PATH);
			fileUrl.append(uuid);
			fileUrl.append("/data/");
			fileUrl.append(companyFilesVo.getFile_url());
			if(companyFilesVo.getIs_file().equals(ConstantUtil.IS_FILE)){
				fileUrl.append("/");
			}
			String companyFileUrl=fileUrl.toString();
			ids.add(companyFilesVo.getId());
			if(companyFilesVo.getIs_file().equals(ConstantUtil.IS_FILE)){
				fileUrls.add(companyFileUrl);
				objVo = new CompanyFilesVo();
				objVo.setParent_id(companyFilesVo.getId());
				List<CompanyFilesVo> list= companyFilesService.selectForList(objVo);
				if(list.size()>0){
					for (CompanyFilesVo vo : list) {
						getMap(vo);
					}
				}
			}else{
				companyFileUrl=companyFileUrl.substring(0, companyFileUrl.length()-1);
				fileUrls.add(companyFileUrl);
			}
		}else{
			flag=false;
		}
		return flag;
	}
	
	/**
	 * 删除文件（夹）
	 * 
	 * @param companyFiles
	 * @return
	 */
	@RequestMapping("/delete")
	@ResponseBody
	public Result delete(Long id,HttpServletRequest request) {
		boolean flag=true;
		User user = (User) request.getSession().getAttribute("loginUser");
		ids = new ArrayList<Long>();
		fileUrls=new ArrayList<String>();
		if(id==null){
			return new Result("SYS_PARAMETER",null);
		}
		CompanyFilesVo companyFilesVo= new CompanyFilesVo();
		companyFilesVo.setId(id);
		companyFilesVo=companyFilesService.selectForObject(companyFilesVo);
		if(companyFilesVo==null){
			return new Result("ZC0001",null);
		}
		if(companyFilesVo.getIs_file().equals(ConstantUtil.IS_FILE)){
			try {
				flag=getMap(companyFilesVo);
			} catch (StopMsgException e) {
				return new Result(e.getExceptionName(),null);
			}
			
			if(flag){
				/**
				 * 批量删除OSS文件以及文件夹
				 */
				OSSFilesUtil.deleteFiles(fileUrls);
				companyFilesVo.setIds(ids);
				companyFilesService.deleteAll(companyFilesVo);
			}else{
				return new Result("ZC0001",null);
			}
		}else{//删除文件
			String uuid=userCompanyService.getCompanyUserUUID(companyFilesVo.getCompany_id());
			StringBuffer fileUrl=new StringBuffer(ConstantUtil.USER_PATH);
			fileUrl.append(uuid);
			fileUrl.append("/data/");
			fileUrl.append(companyFilesVo.getFile_url());
			companyFilesService.delete(companyFilesVo);
			OSSFilesUtil.deleteFile(fileUrl.toString());
		}
		/**
		 * 执行操作日志
		 */
		LogInfo logInfo= new LogInfo();
		logInfo.setLog_type(ConstantUtil.LOG_DELETE);
		logInfo.setTable_name("p_company_files");
		logInfo.setUser_id(user.getId());
		logInfo.setUser_name(user.getUsername());
		StringBuffer content= new StringBuffer();
		if(companyFilesVo.getIs_file().equals(ConstantUtil.IS_FILE)){
			content.append("删除文件夹【");
			content.append(companyFilesVo.getFile_url());
			content.append(companyFilesVo.getFile_name());
			logInfo.setContent("】，以及子文件和子文件夹。");
		}else{
			content.append("删除文件'");
			content.append(companyFilesVo.getFile_url());
			logInfo.setContent("'");
		}
		logInfo.setContent(content.toString());
		logInfoService.save(logInfo);
		return new Result("删除成功");
	}
	
	/**
	 * 获取移动文件夹列表
	 * @param companyFiles
	 * @return
	 */
	@SuppressWarnings( "rawtypes")
	@RequestMapping("/getMoveList")
	@ResponseBody
	public Result getMoveList(Long resouce_id,HttpServletRequest request) {
		if(resouce_id==null || resouce_id==null){
			return new Result("SYS_PARAMETER",null);
		}
		User user = (User) request.getSession().getAttribute("loginUser");
		Map<String,Object> map= new HashMap<String, Object>();
		List<Map> mapList= new ArrayList<Map>();
		CompanyFilesVo objVo= new CompanyFilesVo();
		CompanyFilesVo companyFilesVo= new CompanyFilesVo();
		companyFilesVo.setId(resouce_id);
		companyFilesVo=companyFilesService.selectForObject(companyFilesVo);
		UserCompany userCompany= userCompanyService.companygetUser(companyFilesVo.getCompany_id());
		try {
			if(user.getId()==1 ||user.getId().equals(userCompany.getUse_id())){
				objVo.setCompany_id(companyFilesVo.getCompany_id());
				mapList=companyFilesService.selectCompanyFilesMap(objVo);
			}else{
				GRoleVo gRoleVo=(GRoleVo) request.getSession().getAttribute("gRoleVo");
				List<GRoleVo> gRoleList=gRoleService.getGRoleListByUserId(gRoleVo);
				if(gRoleList.size()>0){
					objVo.setCompany_id(companyFilesVo.getCompany_id());
					mapList=companyFilesService.selectCompanyFilesMap(objVo);
				}else{
					return new Result("SYS_PRIVILEGE", null);
				}
			}
			JSONObject json=null;
			if(mapList.size()>0){
				json=CompanyFilesTree.getNodeModel(mapList);
			}
			
			map.put("companyFilesList",json);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("SYS_DATA_ERRO", null);
		}
		return new Result(Boolean.TRUE,"成功",map);
	}
	
	/**
	 * 获取移动文件夹列表
	 * @param companyFiles
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/getMoveList1")
	@ResponseBody
	public Result getMoveList1(Long resouce_id,HttpServletRequest request) {
		if(resouce_id==null || resouce_id==null){
			return new Result("SYS_PARAMETER",null);
		}
		User user = (User) request.getSession().getAttribute("loginUser");
		Map<String,Object> map= new HashMap<String, Object>();
		Map param= new HashMap();
		List<Map> mapList= new ArrayList<Map>();
		CompanyFilesVo objVo= new CompanyFilesVo();
		CompanyFilesVo companyFilesVo= new CompanyFilesVo();
		companyFilesVo.setId(resouce_id);
		companyFilesVo=companyFilesService.selectForObject(companyFilesVo);
		UserCompany userCompany= userCompanyService.companygetUser(companyFilesVo.getCompany_id());
		try {
			if(user.getId()==1 ||user.getId().equals(userCompany.getUse_id())){
				objVo.setCompany_id(companyFilesVo.getCompany_id());
				mapList=companyFilesService.selectCompanyFilesMap(objVo);
			}else{
				GRoleVo gRoleVo=(GRoleVo) request.getSession().getAttribute("gRoleVo");
				List<GRoleVo> gRoleList=gRoleService.getGRoleListByUserId(gRoleVo);
				if(gRoleList.size()>0){
					if(gRoleList.size()==1 && gRoleList.get(0).getRole_p_id()==5){
						List<CompanyFilesUserVo> CompanyFilesUser=null;
						CompanyFilesVo parentFilesVo= new CompanyFilesVo();
						CompanyFilesUserVo comapnyFilesUservo = new CompanyFilesUserVo();
						comapnyFilesUservo.setCompany_files_id(companyFilesVo.getId());
						comapnyFilesUservo.setUser_id(user.getId());
						parentFilesVo.setId(companyFilesVo.getParent_id());
						while(true){
							CompanyFilesUser=companyFilesUserService.selectForList(comapnyFilesUservo);
							if(CompanyFilesUser!=null){
								break;
							}
							companyFilesVo=companyFilesService.selectForObject(parentFilesVo);
							comapnyFilesUservo = new CompanyFilesUserVo();
							comapnyFilesUservo.setCompany_files_id(companyFilesVo.getId());
							comapnyFilesUservo.setUser_id(user.getId());
							//查询当前文件夹的父文件夹
							parentFilesVo= new CompanyFilesVo();
							parentFilesVo.setId(companyFilesVo.getParent_id());
						}
						/**
						 * 获取文件夹集合
						 */
						objVo.setCompany_id(companyFilesVo.getCompany_id());
						objVo.setFile_url(companyFilesVo.getFile_url()+"/%");
						param.put("id", companyFilesVo.getId());
						param.put("file_name", companyFilesVo.getFile_name());
						param.put("parent_id", null);
						mapList=companyFilesService.selectCompanyFilesMap(objVo);
						mapList.add(param);
					}else{
						EmployeeVo employee = new EmployeeVo();
						employee.setCompany_id(companyFilesVo.getCompany_id());
						employee.setUser_id(user.getId());
						/**
						 * 判断该用户是否是公司员工
						 */
						employee = employeeService.selectForObject(employee);
						if (employee == null) {
							companyFilesVo=new CompanyFilesVo();
							if(gRoleVo.getIs_parent_poject().equals(ConstantUtil.IS_PARENT_POJECT)){
								companyFilesVo.setFile_type(ConstantUtil.ZC_PROJECT);
							}else{
								companyFilesVo.setFile_type(ConstantUtil.ZC_SUB_PROJECT);
							}
							companyFilesVo.setFor_id(gRoleVo.getProject_id());
							companyFilesVo=companyFilesService.selectForObject(companyFilesVo);
							objVo.setCompany_id(companyFilesVo.getCompany_id());
							objVo.setFile_url(companyFilesVo.getFile_url()+"/%");
							param.put("id", companyFilesVo.getId());
							param.put("file_name", companyFilesVo.getFile_name());
							param.put("parent_id", null);
							mapList=companyFilesService.selectCompanyFilesMap(objVo);
							mapList.add(param);
						}else{
							objVo.setCompany_id(companyFilesVo.getCompany_id());
							mapList=companyFilesService.selectCompanyFilesMap(objVo);
						}
					}
				}else{
					return new Result("SYS_PRIVILEGE", null);
				}
			}
			JSONObject json=null;
			if(mapList.size()>0){
				json=CompanyFilesTree.getNodeModel(mapList);
			}
			map.put("companyFilesList", json);
		} catch (Exception e) {
			e.printStackTrace();
			return new Result("SYS_DATA_ERRO", null);
		}
		return new Result(Boolean.TRUE,"成功",map);
	}
	
	/**
	 * 移动文件
	 * @param companyFiles
	 * @return
	 */
	@RequestMapping("/move")
	@ResponseBody
	public Result move(Long id,Long target_id,HttpServletRequest request) {
		if(target_id==null || id==null){
			return new Result("SYS_PARAMETER",null);
		}
		User user = (User) request.getSession().getAttribute("loginUser");
		CompanyFilesVo moveVo= new CompanyFilesVo();
		/**
		 * 查询移动到的指定文件是否是文件夹
		 */
		CompanyFilesVo targetVo =new CompanyFilesVo();
		targetVo.setId(target_id);
		targetVo=companyFilesService.selectForObject(targetVo);
		if(!targetVo.getIs_file().equals("Y")){
			return new Result("ZC0013",null);
		}
		/**
		 * 查询移动的文件
		 */
		CompanyFilesVo companyFilesVo =new CompanyFilesVo();
		companyFilesVo.setId(id);
		companyFilesVo=companyFilesService.selectForObject(companyFilesVo);
		/**
		 * 判断移动的是否为文件
		 */
		if(companyFilesVo.getIs_file().equals("Y")){
			return new Result("ZC0014",null);
		}
		/**
		 * 拼接根目录
		 */
		String uuid=userCompanyService.getCompanyUserUUID(companyFilesVo.getCompany_id());
		StringBuffer fileUrls=new StringBuffer(ConstantUtil.USER_PATH);
		fileUrls.append(uuid);
		fileUrls.append("/data/");
		/**
		 * 更新文件路径
		 */
		moveVo.setId(id);
		moveVo.setFile_url(targetVo.getFile_url()+"/"+companyFilesVo.getFile_name());
		moveVo.setParent_id(target_id);
		/**
		 * 执行操作日志
		 */
		LogInfo logInfo= new LogInfo();
		logInfo.setLog_type(ConstantUtil.LOG_MOVE);
		logInfo.setTable_name("p_company_files");
		logInfo.setUser_id(user.getId());
		logInfo.setUser_name(user.getUsername());
		logInfo.setContent("移动文件'"+companyFilesVo.getFile_name()+"'到文件夹'"+targetVo.getFile_name()+"'下");
		logInfoService.save(logInfo);
		companyFilesService.updateIgnoreNull(moveVo);
		/**
		 * 复制文件到指定目录
		 */
		OSSFilesUtil.copyObject(fileUrls.toString()+companyFilesVo.getFile_url(), fileUrls.toString()+moveVo.getFile_url());
		/**
		 * 删除源文件
		 */
		OSSFilesUtil.deleteFile(fileUrls.toString()+companyFilesVo.getFile_url());
		return new Result("移动成功");
	}
	
	/**
	 * 根据路径获取parent_id
	 * 
	 * @param path
	 * @return
	 */
	@RequestMapping("/selectFileByPath")
	@ResponseBody
	public Result selectFileByPath(String path,String is_parent_project,Long project_id,HttpServletRequest request) {
		if(path==null){
			return new Result("SYS_PARAMETER",null);
		}
		CompanyFilesVo companyFilesVo= new CompanyFilesVo();
		companyFilesVo.setFile_url(path);
		List<CompanyFilesUserVo> list=null;
		List<GRoleVo> gRoleList=null;
		User user = (User) request.getSession().getAttribute("loginUser");
		try {
			GRoleVo gRoleVo= new GRoleVo();
			gRoleVo.setIs_parent_poject(is_parent_project);
			gRoleVo.setProject_id(project_id);
			gRoleVo.setUser_id(user.getId());
			gRoleList=gRoleService.getGRoleListByUserId(gRoleVo);
			/**
			 * 若存在团队成员
			 */
			companyFilesVo=companyFilesService.selectForObject(companyFilesVo);
			if(companyFilesVo==null){
				return new Result("ZC0009",null);
			}
			UserCompany userCompany= userCompanyService.companygetUser(companyFilesVo.getCompany_id());
			if(userCompany==null || user.getId().longValue()!=userCompany.getUse_id().longValue()){
				if(gRoleList.size()>0){
					if(gRoleList.size()==1 && gRoleList.get(0).getRole_p_id()==5){
						/**
						 * 根据当前用户与请求路径获取权限记录
						 */
						CompanyFilesUserVo companyFilesUser= new CompanyFilesUserVo();
						companyFilesUser.setUser_id(user.getId());
						companyFilesUser.setCompany_files_id(companyFilesVo.getId());
						list= companyFilesService.selectCpmpanyFilesUsers(companyFilesUser);
						if(list.size()==0){
							return new Result("SYS_PRIVILEGE",null);
						}
						request.getSession().setAttribute("fGRoleVo",gRoleVo);
					}else{
						request.getSession().setAttribute("gRoleVo",gRoleVo);
					}
				}else{
					/**
					 * 不存在当前的子项目团队成员中，可能存在父项目团队成员中
					 */
					if(is_parent_project.equals("0")){
						SubProjectVo subObjVo =new SubProjectVo();
						SubProjectVo subProjectVo =new SubProjectVo();
						subObjVo.setId(project_id);
						int i=0;
						while(true){
							i++;
							subProjectVo= new SubProjectVo();
							subProjectVo.setId(subObjVo.getId());
							subObjVo=subProjectService.selectForObject(subProjectVo);
							if(subObjVo.getProject_id()!=null){
								gRoleVo= new GRoleVo();
								gRoleVo.setIs_parent_poject("1");
								gRoleVo.setProject_id(subObjVo.getProject_id());
								gRoleVo.setUser_id(user.getId());
								gRoleList=gRoleService.getGRoleListByUserId(gRoleVo);
								if(gRoleList.size()==0){
									return new Result("SYS_PRIVILEGE",null);
								}else{
									request.getSession().setAttribute("gRoleVo",gRoleVo);
								}
								break;
							}else{//可能当前子项目的父项目仍为子项目
								gRoleVo= new GRoleVo();
								gRoleVo.setIs_parent_poject("0");
								gRoleVo.setProject_id(subObjVo.getSub_parent_project_id());
								gRoleVo.setUser_id(user.getId());
								gRoleList=gRoleService.getGRoleListByUserId(gRoleVo);
								if(gRoleList.size()>0){
									request.getSession().setAttribute("gRoleVo",gRoleVo);
									break;
								}else{
									subObjVo.setId(subObjVo.getSub_parent_project_id());
								}
							}
							if(i>3){
								return new Result("SYS_PRIVILEGE",null);
							}
						}
					}else{
						return new Result("SYS_PRIVILEGE",null);
					}
				}
			}
		} catch (StopMsgException e) {
			//TODO
			return new Result(e.getExceptionName(),null);
		}
		return new Result(Boolean.TRUE,"成功",companyFilesVo.getId());
	}
	
	@RequestMapping("/test")
	public void test(){
		System.out.println("测试");
	}
	
	/**
	 * 添加异常
	 * @author John
	 *
	 */
	static class StopMsgException extends RuntimeException {

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;  
		private String exceptionName; 
		public StopMsgException(String exceptionName){
			  this.exceptionName=exceptionName;
		}
		public String getExceptionName() {
			return exceptionName;
		}
		public void setExceptionName(String exceptionName) {
			this.exceptionName = exceptionName;
		}
		
    } 
}
