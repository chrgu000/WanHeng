package com.cgwas.project.service.impl;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.utils.ConstantUtil;
import com.cgwas.common.utils.OSSFilesUtil;
import com.cgwas.company.entity.Company;
import com.cgwas.companyFiles.entity.CompanyFilesVo;
import com.cgwas.companyFiles.service.api.ICompanyFilesService;
import com.cgwas.companyPlugin.dao.api.ICompanyPluginDao;
import com.cgwas.companyPlugin.entity.CompanyPluginVo;
import com.cgwas.companyPlugin.service.api.ICompanyPluginService;
import com.cgwas.companySoftware.dao.api.ICompanySoftwareDao;
import com.cgwas.companySoftware.entity.CompanySoftwareVo;
import com.cgwas.companySoftware.service.api.ICompanySoftwareService;
import com.cgwas.director.dao.api.IDirectorDao;
import com.cgwas.director.entity.DirectorVo;
import com.cgwas.frameRateCompany.dao.api.IFrameRateCompanyDao;
import com.cgwas.gRole.entity.GRoleVo;
import com.cgwas.gRole.service.api.IGRoleService;
import com.cgwas.groupUser.entity.GroupUser;
import com.cgwas.groupUser.entity.GroupUserVo;
import com.cgwas.groupUser.service.api.IGroupUserService;
import com.cgwas.labelTag.entity.LabelTag;
import com.cgwas.principal.dao.api.IPrincipalDao;
import com.cgwas.principal.entity.PrincipalVo;
import com.cgwas.project.dao.api.IProjectDao;
import com.cgwas.project.entity.Project;
import com.cgwas.project.entity.ProjectVo;
import com.cgwas.project.entity.UserProject;
import com.cgwas.project.service.api.IProjectService;
import com.cgwas.projectStatus.dao.api.IProjectStatusDao;
import com.cgwas.projectType.dao.api.IProjectTypeDao;
import com.cgwas.resolutionCompany.dao.api.IResolutionCompanyDao;
import com.cgwas.screenwriter.dao.api.IScreenwriterDao;
import com.cgwas.screenwriter.entity.ScreenwriterVo;
import com.cgwas.search.entity.Search;
import com.cgwas.subproject.entity.SubProject;
import com.cgwas.user.dao.api.IUserDao;
import com.cgwas.user.entity.User;
import com.cgwas.userCompany.service.api.IUserCompanyService;
import com.cgwas.userInfo.entity.UserInfoVo;
import com.cgwas.util.ExcelImportUtil;

/**
 * Author yangjun
 */
@Service
public class ProjectService implements IProjectService {
	@Autowired
	private IProjectDao projectDao;// 项目
	@Autowired
	private ICompanySoftwareDao companySoftwareDao;// 软件
	@Autowired
	private ICompanyPluginDao companyPluginDao;// 插件
	@Autowired
	private ICompanySoftwareService companySoftwareService;// 软件
	@Autowired
	private ICompanyPluginService companyPluginService;// 插件
	@Autowired
	private IProjectStatusDao projectStatusDao;// 项目状态
	@Autowired
	private IProjectTypeDao projectTypeDao;// 项目类型
	@Autowired
	private IResolutionCompanyDao resolutionDao;// 分辨率
	@Autowired
	private IFrameRateCompanyDao frameRateDao;// 帧数率
	@Autowired
	private IUserDao userDao;// 用户
	@Autowired
	private IDirectorDao directorDao;// 导演
	@Autowired
	private IPrincipalDao principalDao;// 负责人
	@Autowired
	private IScreenwriterDao screenwriterDao;// 编剧
	@Autowired
	IUserCompanyService userCompanyService;
	@Autowired
	private IGRoleService gRoleService;
	@Autowired
	private IGroupUserService groupUserService;
	@Autowired
	private ICompanyFilesService companyFilesService;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	/**
	 * 项目修改
	 */
	@Override
	@Transactional
	public void update(ProjectVo project, String directorIds,
			String screenwriterIds, String principalIds, String plugins,
			String softwares, String labeltagIds,String beginTime, String endTime,
			HttpSession session) throws Exception {
		project.setModified_time(new Timestamp(System.currentTimeMillis()));
		Long company_id = (Long) session.getAttribute("projectCompany");
		String errorInfo = "";
		try {
			project.setBegin_time(sdf.parse(beginTime));
			project.setEnd_time(sdf.parse(endTime));
		} catch (ParseException e) {
			e.printStackTrace();
			errorInfo += "时间格式错误,请按照yyyy-MM-dd HH:mm:ss格式录入时间\n";
			throw new RuntimeException(errorInfo);
		}
		// Map<String, String> m1 = new HashMap<String, String>();
		// m1.put("id", project.getUser_id()+"");
		// UserInfoVo i = (UserInfoVo) userDao.selectForObject(
		// "com.cgwas.user.dao.getUserInfoByUserId", m1);// 根据导演用户id查出user对象
		// if(i!=null){
		// project.setCreater_name(i.getName());
		// project.setHead_pic_path(i.getHead_pic_path());
		// }
		// Map<String, String> user = new HashMap<String, String>();
		// user.put("createName", project.getCreater_name());
		// Long user_id = (Long) userDao.selectForObject(
		// "com.cgwas.user.dao.getUserId", user);// 根据创建者用户名查看是否存在
		// project.setUser_id(user_id);
		// // 倘若用户不再平台中，则将用户名设置为空，表示没用添加平台中的用户
		// if (user_id == null) {
		// errorInfo += project.getCreater_name() + "创建者不在平台中,请核实再录入!\n";
		// throw new RuntimeException(errorInfo);
		// }
		System.out.println(sdf.format(project.getBegin_time()));
		System.out.println(sdf.format(project.getEnd_time()));
		System.out.println(project.getId());
		projectDao.updateIgnoreNull(project);
		Map<String, Long> m = new HashMap<String, Long>();
		m.put("project_id", project.getId());
		m.put("is_parent_project", 1L);// 1表示当前项目是根父项目
		GRoleVo g = new GRoleVo();
		/**
		 * 删除导演角色成员
		 */
		g.setIs_parent_poject("1");
		g.setProject_id(project.getId());
		g.setRole_p_id(1L);
		g = gRoleService.selectForObject(g);
		GroupUserVo gUser = new GroupUserVo();
		gUser.setIs_parent_project("1");
		gUser.setProject_id(project.getId());
		gUser.setRole_id(g.getId());
		groupUserService.delete(gUser);
		
		/**
		 * 删除负责人角色成员
		 */
		g = new GRoleVo();
		g.setIs_parent_poject("1");
		g.setProject_id(project.getId());
		g.setRole_p_id(3L);
		g = gRoleService.selectForObject(g);
		gUser.setRole_id(g.getId());
		groupUserService.delete(gUser);
		
		/**
		 * 删除编剧角色成员
		 */
		g = new GRoleVo();
		g.setIs_parent_poject("1");
		g.setProject_id(project.getId());
		g.setRole_p_id(4L);
		g = gRoleService.selectForObject(g);
		gUser.setRole_id(g.getId());
		groupUserService.delete(gUser);
		projectDao.deleteCompanyPlugin(m);// 删除插件
		projectDao.deleteCompanySoftware(m);// 删除软件
		projectDao.deleteProjectDirector(m);// 删除导演
		projectDao.deleterProjectPrincipal(m);// 删除负责人
		projectDao.deleteProjectScreenwriter(m);// 删除编剧
		projectDao.deleteProjectLabelTag(m);
		List<CompanyPluginVo> cp = JSON.parseArray(plugins,
				CompanyPluginVo.class);
		Map<String, Object> map = new HashMap<String, Object>();
		for (CompanyPluginVo c : cp) {
			if (c.getId() == null) {// 当插件id为空时，表示，该插件是新添加的
				Map<String, Object> cMap = new HashMap<String, Object>();
				cMap.put("pluginName", c.getPlugin_name());
				cMap.put("company_id", project.getCompany_id());
				Long id = (Long) companyPluginDao.selectForObject(
						"com.cgwas.companyPlugin.dao.getPluginId", cMap);// 根据插件名称查看该插件在
				if (id == null) {// 数据表中是否存在，不存在则添加进去
					CompanyPluginVo cpv = new CompanyPluginVo();
					cpv.setPlugin_name(c.getPlugin_name());
					cpv.setCompany_id(project.getCompany_id());
					companyPluginService.save(cpv);// 保存插件至数据表中
					map.put("plugin_id", cpv.getId());
				} else {
					map.put("plugin_id", id);
				}
			} else {
				map.put("plugin_id", c.getId());
			}
			map.put("project_id", project.getId());
			projectDao.addProjectCompanyPlugin(map);// 将插件保存到项目与插件的中间表中
		}
		List<CompanySoftwareVo> cs = JSON.parseArray(softwares,
				CompanySoftwareVo.class);
		for (CompanySoftwareVo c : cs) {
			if (c.getId() == null) {// 当软件id为空时，表示，该软件是新添加的
				Map<String, Object> sMap = new HashMap<String, Object>();
				sMap.put("softwareName", c.getSoftware_name());
				sMap.put("company_id", project.getCompany_id());
				Long id = (Long) companySoftwareDao.selectForObject(
						"com.cgwas.companySoftware.dao.getSoftwareId", sMap);// 根据软件名称查看该软件在
				if (id == null) {// 数据表中是否存在，不存在则添加进去
					CompanySoftwareVo csv = new CompanySoftwareVo();
					csv.setSoftware_name(c.getSoftware_name());
					csv.setCompany_id(project.getCompany_id());
					companySoftwareService.save(csv);// 保存软件至数据表中
					map.put("software_id", csv.getId());
				} else {
					map.put("software_id", id);
				}
			} else {
				map.put("software_id", c.getId());
			}

			map.put("project_id", project.getId());
			map.put("model_type", "project");
			projectDao.addProjectCompanySoftware(map);// 将软件保存到项目与软件的中间表中
		}
		if (directorIds != null && directorIds.trim().length() > 0) {
			g = new GRoleVo();
			g.setRole_p_id(1L);
			g.setIs_parent_poject("1");
			g.setProject_id(project.getId());
			g = gRoleService.selectForObject(g);
			String[] directIds = directorIds.split(",");
			for (String id : directIds) {
				Map<String, String> dMap = new HashMap<String, String>();
				dMap.put("id", id);
				System.out.println("===========================>id:"+id);
				UserInfoVo u = (UserInfoVo) userDao.selectForObject(
						"com.cgwas.user.dao.getUserInfoByUserId", dMap);// 根据导演用户id查出user对象
				if (u == null) {
					errorInfo += "导演不在平台中,请核实再录入!\n";
					throw new RuntimeException(errorInfo);
				}
				DirectorVo d = (DirectorVo) directorDao.selectForObject(
						"com.cgwas.director.dao.getDirectorByUserId", dMap);// 根据导演用户id查询在导演表里是否存在
				if (d == null) {// 当导演在导演表里不存在时，添加导演对象到导演表中
					d = new DirectorVo();
					d.setUser_id(Long.parseLong(id));
					d.setDirector_name(u.getName());
					d.setHead_pic_path(u.getHead_pic_path());
					directorDao.save(d);
				}else{
					d.setHead_pic_path(u.getHead_pic_path());
					directorDao.updateIgnoreNull(d);
				}
				map.put("director_id", d.getId());
				map.put("project_id", project.getId());
				map.put("is_parent_poject", "1");
				projectDao.addProjectDirector(map);// 将导演数据插入到项目与导演的中间表中
				gUser = new GroupUserVo();
				gUser.setIs_parent_project("1");
				gUser.setProject_id(project.getId());
				gUser.setCompany_id(company_id);
				gUser.setRole_id(g.getId());
				gUser.setUser_id(Long.parseLong(id));
				groupUserService.saveGroup(gUser);
			}
		}
		if (principalIds != null && principalIds.trim().length() > 0) {
			g = new GRoleVo();
			g.setIs_parent_poject("1");
			g.setProject_id(project.getId());
			g.setRole_p_id(3L);
			g = gRoleService.selectForObject(g);
			String[] principIds = principalIds.split(",");
			for (String id : principIds) {
				Map<String, String> pMap = new HashMap<String, String>();
				pMap.put("id", id);
				UserInfoVo u = (UserInfoVo) userDao.selectForObject(
						"com.cgwas.user.dao.getUserInfoByUserId", pMap);// 根据负责人用户id查出user对象
				if (u == null) {
					errorInfo += "负责人不在平台中,请核实再录入!\n";
					throw new RuntimeException(errorInfo);
				}
				PrincipalVo p = (PrincipalVo) principalDao.selectForObject(
						"com.cgwas.principal.dao.getPrincipalByUserId", pMap);// 根据负责人用户id查询在负责人表里是否存在
				if (p == null) {// 当负责人在负责人里不存在时，添加负责人对象到负责人表中
					p = new PrincipalVo();
					p.setUser_id(Long.parseLong(id));
					p.setPrincipal_name(u.getName());
					p.setHead_pic_path(u.getHead_pic_path());
					principalDao.save(p);
				}else{
					p.setHead_pic_path(u.getHead_pic_path());
				    principalDao.updateIgnoreNull(p);
				}
				map.put("principal_id", p.getId());
				map.put("project_id", project.getId());
				map.put("is_parent_poject", "1");
				projectDao.addProjectPrincipal(map);// 将负责人数据插入到项目与负责人的中间表中
				gUser = new GroupUserVo();
				gUser.setIs_parent_project("1");
				gUser.setProject_id(project.getId());
				gUser.setCompany_id(company_id);
				gUser.setRole_id(g.getId());
				gUser.setUser_id(Long.parseLong(id));
				groupUserService.saveGroup(gUser);
			}
		}
		if (screenwriterIds != null && screenwriterIds.trim().length() > 0) {
			g = new GRoleVo();
			g.setIs_parent_poject("1");
			g.setProject_id(project.getId());
			g.setRole_p_id(4L);
			g = gRoleService.selectForObject(g);
			String[] screenwriIds = screenwriterIds.split(",");// 根据前台传来的编剧id字符串转化为id数组
			for (String id : screenwriIds) {
				Map<String, String> pMap = new HashMap<String, String>();
				pMap.put("id", id);
				UserInfoVo u = (UserInfoVo) userDao.selectForObject(
						"com.cgwas.user.dao.getUserInfoByUserId", pMap);// 根据编剧用户id查出user对象
				if (u == null) {
					errorInfo += "编剧不在平台中,请核实再录入!\n";
					throw new RuntimeException(errorInfo);
				}
				ScreenwriterVo s = (ScreenwriterVo) screenwriterDao
						.selectForObject(
								"com.cgwas.screenwriter.dao.getScreenwriterByUserId",
								pMap);// 根据编剧用户名查询在编剧表里是否存在
				if (s == null) {// 当编剧在编剧里不存在时，添加编剧对象到编剧表中
					s = new ScreenwriterVo();
					s.setUser_id(Long.parseLong(id));
					s.setScreenwriter_name(u.getName());
					s.setHead_pic_path(u.getHead_pic_path());
					screenwriterDao.save(s);
				}else{
					s.setHead_pic_path(u.getHead_pic_path());
					screenwriterDao.updateIgnoreNull(s);
				}
				map.put("screenwriter_id", s.getId());
				map.put("project_id", project.getId());
				map.put("is_parent_poject", "1");
				projectDao.addProjectScreenwriter(map);// 将负责人数据插入到项目与负责人的中间表中
				gUser = new GroupUserVo();
				gUser.setIs_parent_project("1");
				gUser.setProject_id(project.getId());
				gUser.setCompany_id(company_id);
				gUser.setRole_id(g.getId());
				gUser.setUser_id(Long.parseLong(id));
				groupUserService.saveGroup(gUser);
			}
		}
		if (labeltagIds != null && labeltagIds.trim().length() > 0) {
			String[] labelIds = labeltagIds.split(",");// 根据前台传来的标签id字符串转化为id数组
			for (String id : labelIds) {
				Map<String, Object> mp = new HashMap<String, Object>();
				mp.put("label_tag_id", id);
				mp.put("project_id", project.getId());
				projectDao.addProjectLabel(mp);
			}
		}
	}

	/**
	 * 添加项目
	 */
	@Override
	@Transactional
	public void create(ProjectVo project, String directorIds,
			String principalIds, String plugins, String screenwriterIds,
			String softwares,String labeltagIds, String beginTime, String endTime,
			HttpSession session) throws RuntimeException {
		Long company_id = (Long) session.getAttribute("projectCompany");
		Long user_id = ((User) session.getAttribute("loginUser")).getId();
		String errorInfo = "";
		project.setCreate_time(new Date());
		project.setModified_time(new Date());
		project.setCompany_id(company_id);
		try {
			project.setBegin_time(sdf.parse(beginTime));
			project.setEnd_time(sdf.parse(endTime));
		} catch (ParseException e) {
			errorInfo += "时间格式错误,请按照yyyy-MM-dd HH:mm:ss格式录入时间\n";
			throw new RuntimeException(errorInfo);
		}
		Map<String, String> m = new HashMap<String, String>();
		m.put("id", user_id + "");
		UserInfoVo i = (UserInfoVo) userDao.selectForObject(
				"com.cgwas.user.dao.getUserInfoByUserId", m);// 根据导演用户id查出user对象
		if (i != null) {
			project.setCreater_name(i.getName());
			project.setHead_pic_path(i.getHead_pic_path());
		}
		// Map<String, String> user = new HashMap<String, String>();
		// user.put("createName", project.getCreater_name());
		// Long user_id = (Long) userDao.selectForObject(
		// "com.cgwas.user.dao.getUserId", user);// 根据创建者用户名查看是否存在
		// project.setUser_id(user_id);
		// // 倘若用户不再平台中，则将用户名设置为空，表示没用添加平台中的用户
		// if (user_id == null) {
		// errorInfo += project.getCreater_name() + "创建者不在平台中,请核实再录入!\n";
		// throw new RuntimeException(errorInfo);
		// }

		projectDao.save(project);
		GRoleVo g = new GRoleVo();
		g.setIs_parent_poject("1");
		g.setProject_id(project.getId());
		gRoleService.saveSysRole(g);
		
		CompanyFilesVo vo = new CompanyFilesVo();
		/**
		 * 判断该路径是否存在
		 */
		if (!OSSFilesUtil.isFile(project.getFolder_path() + "/")) {
			/**
			 * 保存文件
			 */
			vo.setFile_type(ConstantUtil.ZC_PROJECT);
			vo.setCompany_id(company_id);
			vo.setIs_file(ConstantUtil.IS_FILE);
			vo.setParent_id(project.getParentFileId());
			vo.setCreator_id(user_id);
			vo.setFile_url(project.getProject_folder());
			vo.setFile_name(project.getProject_folder().substring(
					project.getProject_folder().lastIndexOf("/") + 1,
					project.getProject_folder().length()));
			vo.setFor_id(project.getId());
			companyFilesService.save(vo);
		} else {
			/**
			 * 查询选择的文件夹，并更新为项目文件夹
			 */
			vo = new CompanyFilesVo();
			vo.setFile_url(project.getProject_folder());
			vo = companyFilesService.selectForObject(vo);
			vo.setFile_type(ConstantUtil.ZC_PROJECT);
			vo.setFor_id(project.getId());
			companyFilesService.updateIgnoreNull(vo);
		}
		
		List<CompanyPluginVo> cp = JSON.parseArray(plugins,
				CompanyPluginVo.class);// 根据前台数据，转化为公司插件集合对象
		Map<String, Object> map = new HashMap<String, Object>();
		for (CompanyPluginVo c : cp) {
			if (c.getId() == null) {// 当插件id为空时，表示，该插件是新添加的
				Map<String, Object> cMap = new HashMap<String, Object>();
				cMap.put("pluginName", c.getPlugin_name());
				System.out.println("pluginName" + c.getPlugin_name());
				cMap.put("company_id", project.getCompany_id());
				Long id = (Long) companyPluginDao.selectForObject(
						"com.cgwas.companyPlugin.dao.getPluginId", cMap);// 根据插件名称查看该插件在
				System.out.println("id：" + id);
				if (id == null) {// 数据表中是否存在，不存在则添加进去
					CompanyPluginVo cpv = new CompanyPluginVo();
					cpv.setPlugin_name(c.getPlugin_name());
					System.out.println(c.getPlugin_name());
					cpv.setCompany_id(project.getCompany_id());
					companyPluginService.save(cpv);// 保存插件至数据表中
					map.put("plugin_id", cpv.getId());
				} else {
					map.put("plugin_id", id);
				}
			} else {
				map.put("plugin_id", c.getId());
			}
			map.put("project_id", project.getId());
			projectDao.addProjectCompanyPlugin(map);// 将插件保存到项目与插件的中间表中
		}
		List<CompanySoftwareVo> cs = JSON.parseArray(softwares,
				CompanySoftwareVo.class);// 根据前台数据，转化为公司软件集合对象
		for (CompanySoftwareVo c : cs) {
			if (c.getId() == null) {// 当软件id为空时，表示，该软件是新添加的
				Map<String, Object> sMap = new HashMap<String, Object>();
				sMap.put("softwareName", c.getSoftware_name());
				sMap.put("company_id", project.getCompany_id());
				Long id = (Long) companySoftwareDao.selectForObject(
						"com.cgwas.companySoftware.dao.getSoftwareId", sMap);// 根据软件名称查看该软件在
				System.out.println("softwareName:" + c.getSoftware_name());
				System.out.println("id" + id);
				if (id == null) {// 数据表中是否存在，不存在则添加进去
					CompanySoftwareVo csv = new CompanySoftwareVo();
					csv.setSoftware_name(c.getSoftware_name());
					csv.setCompany_id(project.getCompany_id());
					companySoftwareService.save(csv);// 保存软件至数据表中
					map.put("software_id", csv.getId());
				} else {
					map.put("software_id", id);
				}
			} else {
				map.put("software_id", c.getId());
			}
			map.put("project_id", project.getId());
			map.put("model_type", "project");
			projectDao.addProjectCompanySoftware(map);// 将软件保存到项目与软件的中间表中
		}
		if (directorIds != null && directorIds.trim().length() > 0) {
			String[] directIds = directorIds.split(",");// 根据前台传来的导演id字符串转化为id数组
			g = new GRoleVo();
			g.setIs_parent_poject("1");
			g.setProject_id(project.getId());
			g.setRole_p_id(1L);
			g = gRoleService.selectForObject(g);

			for (String id : directIds) {
				Map<String, String> dMap = new HashMap<String, String>();
				dMap.put("id", id);
				UserInfoVo u = (UserInfoVo) userDao.selectForObject(
						"com.cgwas.user.dao.getUserInfoByUserId", dMap);// 根据导演用户id查出user对象
				if (u == null) {
					errorInfo += "导演不在平台中,请核实再录入!\n";
					throw new RuntimeException(errorInfo);
				}
				DirectorVo d = (DirectorVo) directorDao.selectForObject(
						"com.cgwas.director.dao.getDirectorByUserId", dMap);// 根据导演用户id查询在导演表里是否存在
				if (d == null) {// 当导演在导演表里不存在时，添加导演对象到导演表中
					d = new DirectorVo();
					d.setUser_id(Long.parseLong(id));
					d.setDirector_name(u.getName());
					d.setHead_pic_path(u.getHead_pic_path());
					directorDao.save(d);
				}
				map.put("director_id", d.getId());
				map.put("project_id", project.getId());
				map.put("is_parent_poject", "1");
				projectDao.addProjectDirector(map);// 将导演数据插入到项目与导演的中间表中
				GroupUserVo gUser = new GroupUserVo();
				gUser.setIs_parent_project("1");
				gUser.setProject_id(project.getId());
				gUser.setCompany_id(company_id);
				gUser.setRole_id(g.getId());
				gUser.setUser_id(Long.parseLong(id));
				groupUserService.saveGroup(gUser);
			}
		}
		if (principalIds != null && principalIds.trim().length() > 0) {
			g = new GRoleVo();
			g.setIs_parent_poject("1");
			g.setProject_id(project.getId());
			g.setRole_p_id(3L);
			g = gRoleService.selectForObject(g);
			
			String[] principIds = principalIds.split(",");// 根据前台传来的负责人id字符串转化为id数组
			for (String id : principIds) {
				Map<String, String> pMap = new HashMap<String, String>();
				pMap.put("id", id);
				UserInfoVo u = (UserInfoVo) userDao.selectForObject(
						"com.cgwas.user.dao.getUserInfoByUserId", pMap);// 根据负责人用户id查出user对象
				if (u == null) {
					errorInfo += "负责人不在平台中,请核实再录入!\n";
					throw new RuntimeException(errorInfo);
				}
				PrincipalVo p = (PrincipalVo) principalDao.selectForObject(
						"com.cgwas.principal.dao.getPrincipalByUserId", pMap);// 根据负责人用户id查询在负责人表里是否存在
				if (p == null) {// 当负责人在负责人里不存在时，添加负责人对象到负责人表中
					p = new PrincipalVo();
					p.setUser_id(Long.parseLong(id));
					p.setPrincipal_name(u.getName());
					p.setHead_pic_path(u.getHead_pic_path());
					principalDao.save(p);
				}
				map.put("principal_id", p.getId());
				map.put("project_id", project.getId());
				map.put("is_parent_poject", "1");
				projectDao.addProjectPrincipal(map);// 将负责人数据插入到项目与负责人的中间表中
				GroupUserVo gUser = new GroupUserVo();
				gUser.setIs_parent_project("1");
				gUser.setProject_id(project.getId());
				gUser.setCompany_id(company_id);
				gUser.setRole_id(g.getId());
				gUser.setUser_id(Long.parseLong(id));
				groupUserService.saveGroup(gUser);
			}
		}
		if (screenwriterIds != null && screenwriterIds.trim().length() > 0) {
			String[] screenwriIds = screenwriterIds.split(",");// 根据前台传来的编剧id字符串转化为id数组
			g = new GRoleVo();
			g.setIs_parent_poject("1");
			g.setProject_id(project.getId());
			g.setRole_p_id(4L);
			g = gRoleService.selectForObject(g);
			for (String id : screenwriIds) {
				Map<String, String> pMap = new HashMap<String, String>();
				pMap.put("id", id);
				UserInfoVo u = (UserInfoVo) userDao.selectForObject(
						"com.cgwas.user.dao.getUserInfoByUserId", pMap);// 根据编剧用户id查出user对象
				if (u == null) {
					errorInfo += "编剧不在平台中,请核实再录入!\n";
					throw new RuntimeException(errorInfo);
				}
				ScreenwriterVo s = (ScreenwriterVo) screenwriterDao
						.selectForObject(
								"com.cgwas.screenwriter.dao.getScreenwriterByUserId",
								pMap);// 根据编剧用户名查询在编剧表里是否存在
				if (s == null) {// 当编剧在编剧里不存在时，添加编剧对象到编剧表中
					s = new ScreenwriterVo();
					s.setUser_id(Long.parseLong(id));
					s.setScreenwriter_name(u.getName());
					s.setHead_pic_path(u.getHead_pic_path());
					screenwriterDao.save(s);
				}
				map.put("screenwriter_id", s.getId());
				map.put("project_id", project.getId());
				map.put("is_parent_poject", "1");
				projectDao.addProjectScreenwriter(map);// 将负责人数据插入到项目与负责人的中间表中
				GroupUserVo gUser = new GroupUserVo();
				gUser.setIs_parent_project("1");
				gUser.setProject_id(project.getId());
				gUser.setCompany_id(company_id);
				gUser.setRole_id(g.getId());
				gUser.setUser_id(Long.parseLong(id));
				groupUserService.saveGroup(gUser);
			}
		}
		if (labeltagIds != null && labeltagIds.trim().length() > 0) {
			String[] labelIds = labeltagIds.split(",");// 根据前台传来的标签id字符串转化为id数组
			for (String id : labelIds) {
				Map<String, Object> mp = new HashMap<String, Object>();
				mp.put("label_tag_id", id);
				mp.put("project_id", project.getId());
				projectDao.addProjectLabel(mp);
			}
		}
	}

	// @Transactional
	private String insertProject(Map<String, Object> map, Long company_id,
			Integer num, HttpSession session) {
		String errorInfo = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Project project = (Project) map.get("project");// 从map获取待插入的project对象
		Long status_id = projectStatusDao.getStatusId(map);// 根据map中的status查询状态id
		Long type_id = projectTypeDao.getTypeId(map);// 根据map中的type查询类型id

		if (status_id == null) {
			try {
				errorInfo += "第" + num + "行:" + map.get("status")
						+ "项目状态不在平台中，请核实在录入数据\n";
				status_id.intValue();
			} catch (Exception e) {
				throw new RuntimeException(errorInfo);
			}
		}
		if (type_id == null) {
			try {
				errorInfo += "第" + num + "行:" + map.get("type")
						+ "项目类型不在平台中，请核实在录入数据\n";
				type_id.intValue();
			} catch (Exception e) {
				throw new RuntimeException(errorInfo);
			}

		}
		project.setProject_status_id(status_id);// 将状态id赋值给project对象
		project.setProject_type_id(type_id);// 将类型id赋值给project对象
		project.setCompany_id(company_id);
		project.setCreate_time(new Date());
		project.setModified_time(new Date());
		map.put("company_id", company_id);// 将公司id添加到map中
		Long user_id = ((User) session.getAttribute("loginUser")).getId();
		project.setUser_id(user_id);// 将用户id赋值给project对象
		if (user_id == null) {// 当用户id为空时，说明添加的用户不是平台中的用户，则将创建者名称设置为空
			try {
				errorInfo += "第" + num + "行:" + project.getCreater_name()
						+ "创建者不在平台中，请核实在录入数据\n";
				project.setCreater_name(null);
				user_id.intValue();
			} catch (Exception e) {
				throw new RuntimeException(errorInfo);
			}

		} else {
			Map<String, String> m = new HashMap<String, String>();
			m.put("id", user_id + "");
			UserInfoVo i = (UserInfoVo) userDao.selectForObject(
					"com.cgwas.user.dao.getUserInfoByUserId", m);// 根据导演用户id查出user对象
			if (i != null) {
				project.setCreater_name(i.getName());
				project.setHead_pic_path(i.getHead_pic_path());
			}
		}
		String begin_time = (String) map.get("begin_time");
		String end_time = (String) map.get("end_time");
		try {
			project.setBegin_time(sdf.parse(begin_time));
			project.setEnd_time(sdf.parse(end_time));
		} catch (ParseException e) {
			errorInfo += "第" + num + "行:日期格式不正确,请按照yyyy-MM-dd HH:mm:ss格式\n";
			throw new RuntimeException(errorInfo);
		}
		String uuid = userCompanyService.getCompanyUserUUID(company_id);
		String folder_path = ConstantUtil.USER_PATH + "/" + uuid + "/data/"
				+ project.getProject_folder();
		if (!OSSFilesUtil.isFile(folder_path)) {
			OSSFilesUtil.addFile(folder_path);
		}
		projectDao.save(project);// 将项目对象添加到数据表中
		GRoleVo g = new GRoleVo();
		g.setIs_parent_poject("1");
		g.setProject_id(project.getId());
		gRoleService.saveSysRole(g);
		String software = (String) map.get("software");// 获取excel中的软件
		String[] softwares = null;
		if (software.indexOf(",") != -1) {// 当软件是英文“，”隔开的，则将软件转换为string数组
			softwares = software.split(",");
		} else if (software.indexOf("，") != -1) {// 当软件是中文“，”隔开的，则将软件转换为string数组
			softwares = software.split("，");
		} else {
			softwares = new String[] { software };
		}
		for (String s : softwares) {// 遍历软件
			Map<String, Object> softwareMap = new HashMap<String, Object>();// 软件参数对象
			Map<String, Object> sMap = new HashMap<String, Object>();
			softwareMap.put("softwareName", s);// 将软件名称存入软件对象中
			softwareMap.put("company_id", company_id);// 将公司id存入软件对象中
			Long software_id = (Long) companySoftwareDao.selectForObject(
					"com.cgwas.companySoftware.dao.getSoftwareId", softwareMap);// 根据软件名称和公司id查询软件id
			if (software_id == null) {// 当软件id为空时，说明添加的软件是新的软件，需要添加到对应的数据表中
				CompanySoftwareVo csv = new CompanySoftwareVo();
				csv.setSoftware_name(s);
				csv.setCompany_id(company_id);
				companySoftwareService.save(csv);// 将软件插入对应的数据表中
				sMap.put("project_id", project.getId());
				sMap.put("software_id", csv.getId());
				sMap.put("model_type", "project");
				projectDao.addProjectCompanySoftware(sMap);// 将软件插入软件和项目的中间表中
			} else {// 当软件id不为空时，说明添加的软件在数据表中
				sMap.put("project_id", project.getId());
				sMap.put("software_id", software_id);
				sMap.put("model_type", "project");
				projectDao.addProjectCompanySoftware(sMap);// 将软件插入软件和项目的中间表中
			}
		}
		String plugin = (String) map.get("plugin");// 获取excel中的插件
		String[] plugins = null;
		if (plugin.indexOf(",") != -1) {// 当插件是英文“，”隔开的，则将插件转换为string数组
			plugins = plugin.split(",");
		} else if (plugin.indexOf("，") != -1) {// 当插件是中文“，”隔开的，则将插件转换为string数组
			plugins = plugin.split("，");
		} else {
			plugins = new String[] { plugin };
		}
		for (String s : plugins) {// 遍历插件
			Map<String, Object> pluginMap = new HashMap<String, Object>();
			Map<String, Object> pMap = new HashMap<String, Object>();
			pluginMap.put("pluginName", s);
			pluginMap.put("company_id", company_id);
			Long plugin_id = (Long) companyPluginDao.selectForObject(
					"com.cgwas.companyPlugin.dao.getPluginId", pluginMap);// 根据插件名称和公司id查询插件id
			if (plugin_id == null) {// 当插件id为空时，说明添加的插件是新的插件，需要添加到对应的数据表中
				CompanyPluginVo cpv = new CompanyPluginVo();
				cpv.setPlugin_name(s);
				cpv.setCompany_id(company_id);
				companyPluginService.save(cpv);// 将插件插入对应的数据表中
				pMap.put("project_id", project.getId());
				pMap.put("plugin_id", cpv.getId());
				projectDao.addProjectCompanyPlugin(pMap);// 将插件插入插件和项目的中间表中
			} else {// 当插件id不为空时，说明添加的插件在数据表中
				pMap.put("project_id", project.getId());
				pMap.put("plugin_id", plugin_id);
				projectDao.addProjectCompanyPlugin(pMap);// 将插件插入插件和项目的中间表中
			}
		}
		String principals = (String) map.get("principals");// 获取excel中的负责人
		if (principals != null && principals.trim().length() > 0) {
			g = new GRoleVo();
			g.setIs_parent_poject("1");
			g.setProject_id(project.getId());
			g.setRole_p_id(3L);
			g = gRoleService.selectForObject(g);
			String[] pls = null;
			if (principals.indexOf(",") != -1) {// 当负责人是英文“，”隔开的，则将负责人转换为string数组
				pls = principals.split(",");
			} else if (principals.indexOf("，") != -1) {// 当负责人是中文“，”隔开的，则将负责人转换为string数组
				pls = principals.split("，");
			} else {
				pls = new String[] { principals };
			}
			for (String s : pls) {// 遍历负责人
				Map<String, String> pMap = new HashMap<String, String>();
				pMap.put("principalName", s);
				Long principal_id = (Long) userDao.selectForObject(
						"com.cgwas.user.dao.getPrincipal_id", pMap);// 根据负责人姓名，在user表中查询，看插入到数据表中的数据是否在平台中
				if (principal_id != null) {// 当负责人id不为空，说明插入的负责人是平台中的用户
					pMap.put("id", principal_id + "");
					UserInfoVo u = (UserInfoVo) userDao.selectForObject(
							"com.cgwas.user.dao.getUserInfoByUserId", pMap);// 根据负责人用户id查出user对象
					pMap.put("name", s);
					PrincipalVo p = (PrincipalVo) principalDao.selectForObject(
							"com.cgwas.principal.dao.getPrincipalByName", pMap);// 根据负责人姓名查看负责人对象
					if (p == null) {// 倘若查询出来的负责人对象为空，则需要重新添加到负责人表中
						p = new PrincipalVo();
						p.setUser_id(principal_id);
						p.setPrincipal_name(s);
						p.setHead_pic_path(u.getHead_pic_path());
						principalDao.save(p);// 将负责人对象添加到负责人数据表中
					}
					// 倘若负责人对象在负责人表中存在，不用再插入了，直接将其插入到负责人和项目的中间表中
					map.put("principal_id", p.getId());
					map.put("project_id", project.getId());
					map.put("is_parent_poject", "1");
					projectDao.addProjectPrincipal(map);
					GroupUserVo gUser = new GroupUserVo();
					gUser.setIs_parent_project("1");
					gUser.setProject_id(project.getId());
					gUser.setCompany_id(company_id);
					gUser.setRole_id(g.getId());
					gUser.setUser_id(u.getUser_id());
					groupUserService.saveGroup(gUser);
				} else {
					try {
						principal_id.intValue();
					} catch (Exception e) {
						errorInfo += "第" + num + "行:" + s + "负责人不存在！\n";
						throw new RuntimeException(errorInfo);
					}
				}
			}
		}

		String directors = (String) map.get("directors");// 获取excel中的导演
		if (directors != null && directors.trim().length() > 0) {
			String[] drs = null;
			if (directors.indexOf(",") != -1) {// 当导演是英文“，”隔开的，则将导演转换为string数组
				drs = directors.split(",");
			} else if (directors.indexOf("，") != -1) {// 当导演是中文“，”隔开的，则将导演转换为string数组
				drs = directors.split("，");
			} else {
				drs = new String[] { directors };
			}
			g = new GRoleVo();
			g.setIs_parent_poject("1");
			g.setProject_id(project.getId());
			g.setRole_p_id(1L);
			g = gRoleService.selectForObject(g);
			for (String s : drs) {// 遍历导演
				Map<String, String> dMap = new HashMap<String, String>();
				dMap.put("directorName", s);
				Long director_id = (Long) userDao.selectForObject(
						"com.cgwas.user.dao.getDirector_id", dMap);// 根据导演姓名查看导演对象
				if (director_id != null) {// 当导演id不为空，说明插入的导演是平台中的用户
					dMap.put("id", director_id + "");
					UserInfoVo u = (UserInfoVo) userDao.selectForObject(
							"com.cgwas.user.dao.getUserInfoByUserId", dMap);// 根据导演用户id查出user对象
					dMap.put("name", s);
					DirectorVo d = (DirectorVo) directorDao.selectForObject(
							"com.cgwas.director.dao.getDirectorByName", dMap);// 根据导演姓名查看导演对象
					if (d == null) {// 倘若查询出来的导演对象为空，则需要重新添加到导演表中
						d = new DirectorVo();
						d.setUser_id(director_id);
						d.setDirector_name(s);
						d.setHead_pic_path(u.getHead_pic_path());
						directorDao.save(d);// 将导演对象添加到导演数据表中
					}
					// 倘若导演对象在导演表中存在，不用再插入了，直接将其插入到导演和项目的中间表中
					map.put("director_id", d.getId());
					map.put("project_id", project.getId());
					map.put("is_parent_poject", "1");
					projectDao.addProjectDirector(map);
					GroupUserVo gUser = new GroupUserVo();
					gUser.setIs_parent_project("1");
					gUser.setProject_id(project.getId());
					gUser.setCompany_id(company_id);
					gUser.setRole_id(g.getId());
					gUser.setUser_id(u.getUser_id());
					groupUserService.saveGroup(gUser);
				} else {
					try {
						director_id.intValue();
					} catch (Exception e) {
						errorInfo += "第" + num + "行:" + s + "导演不存在！\n";
						throw new RuntimeException(errorInfo);
					}
				}
			}
		}

		String screenwriters = (String) map.get("screenwriters");// 获取excel中的编剧
		if (screenwriters != null && screenwriters.trim().length() > 0) {
			g = new GRoleVo();
			g.setIs_parent_poject("1");
			g.setProject_id(project.getId());
			g.setRole_p_id(4L);
			g = gRoleService.selectForObject(g);
			String[] scrw = null;
			if (screenwriters.indexOf(",") != -1) {// 当编剧是英文“，”隔开的，则将编剧转换为string数组
				scrw = screenwriters.split(",");
			} else if (screenwriters.indexOf("，") != -1) {// 当编剧是中文“，”隔开的，则将编剧转换为string数组
				scrw = screenwriters.split("，");
			} else {
				scrw = new String[] { screenwriters };
			}
			for (String s : scrw) {// 遍历编剧
				Map<String, String> sMap = new HashMap<String, String>();
				sMap.put("screenwriterName", s);
				Long screenwriter_id = (Long) userDao.selectForObject(
						"com.cgwas.user.dao.getScreenwriter_id", sMap);// 根据编剧姓名查看编剧对象
				if (screenwriter_id != null) {// 当编剧id不为空，说明插入的编剧是平台中的用户
					sMap.put("id", screenwriter_id + "");
					UserInfoVo u = (UserInfoVo) userDao.selectForObject(
							"com.cgwas.user.dao.getUserInfoByUserId", sMap);// 根据编剧用户id查出user对象
					sMap.put("name", s);
					ScreenwriterVo d = (ScreenwriterVo) screenwriterDao
							.selectForObject(
									"com.cgwas.screenwriter.dao.getScreenwriterByName",
									sMap);// 根据导演姓名查看导演对象
					if (d == null) {// 倘若查询出来的导演对象为空，则需要重新添加到导演表中
						d = new ScreenwriterVo();
						d.setUser_id(screenwriter_id);
						d.setScreenwriter_name(s);
						d.setHead_pic_path(u.getHead_pic_path());
						directorDao.save(d);// 将导演对象添加到导演数据表中
					}
					// 倘若导演对象在导演表中存在，不用再插入了，直接将其插入到导演和项目的中间表中
					map.put("screenwriter_id", d.getId());
					map.put("project_id", project.getId());
					map.put("is_parent_poject", "1");
					projectDao.addProjectScreenwriter(map);
					GroupUserVo gUser = new GroupUserVo();
					gUser.setIs_parent_project("1");
					gUser.setProject_id(project.getId());
					gUser.setCompany_id(company_id);
					gUser.setRole_id(g.getId());
					gUser.setUser_id(u.getUser_id());
					groupUserService.saveGroup(gUser);
				} else {
					try {
						screenwriter_id.intValue();
					} catch (Exception e) {
						errorInfo += "第" + num + "行:" + s + "编剧不存在！\n";
						throw new RuntimeException(errorInfo);
					}
				}
			}
		}

		return errorInfo;

	}

	@Override
	@Transactional
	public String importProject(String filePath, Long company_id,
			String errorInfo, HttpSession session) throws Exception {
		errorInfo += "";
		Integer num = 1;
		List<Map<String, Object>> list = ExcelImportUtil
				.importProjectInfo(filePath);
		for (Map<String, Object> map : list) {
			try {
				insertProject(map, company_id, num, session);
			} catch (RuntimeException e) {
				errorInfo += e.getMessage();
			}
			num++;
		}
		if (errorInfo.trim().length() > 0) {
			throw new RuntimeException(errorInfo);
		}
		return errorInfo;
	}

	public Serializable save(Project project) {
		return projectDao.save(project);
	}

	public void delete(Project project) {
		projectDao.delete(project);
	}

	public void deleteByExample(Project project) {
		projectDao.deleteByExample(project);
	}

	public void update(Project project) {
		projectDao.update(project);
	}

	public void updateIgnoreNull(Project project) {
		projectDao.updateIgnoreNull(project);
	}

	public void updateByExample(Project project) {
		projectDao.update("updateByExampleProject", project);
	}

	public ProjectVo load(Project project) {
		return (ProjectVo) projectDao.reload(project);
	}

	public ProjectVo selectForObject(Project project) {
		return (ProjectVo) projectDao.selectForObject("selectProject", project);
	}

	public List<ProjectVo> selectForList(Project project) {
		return (List<ProjectVo>) projectDao.selectForList("selectProject",
				project);
	}

	@Override
	public void addProjectCompanyPlugin(Map<String, Object> map) {
		projectDao.addProjectCompanyPlugin(map);

	}

	@Override
	public void addProjectCompanySoftware(Map<String, Object> map) {
		projectDao.addProjectCompanySoftware(map);

	}

	@Override
	public void addProjectDirector(Map<String, Object> map) {
		projectDao.addProjectDirector(map);

	}

	@Override
	public void addProjectPrincipal(Map<String, Object> map) {
		projectDao.addProjectPrincipal(map);
	}

	@Override
	@Transactional
	public void deleteAll(Map<String, String[]> map) {
		String[] ids = map.get("ids");
		List<ProjectVo> projectVos=projectDao.getProjectsByIds(map);
		for (ProjectVo p : projectVos) {
			GroupUser u=new GroupUser();
			u.setProject_id(p.getId());
			u.setIs_parent_project("1");
			groupUserService.delete(u);
			if(p.getSlider_img()!=null){
				OSSFilesUtil.deleteFile(p.getSlider_img());
			}
			if(p.getShow_img()!=null){
				OSSFilesUtil.deleteFile(p.getShow_img());
			}
			if(p.getRecommend_img()!=null){
				OSSFilesUtil.deleteFile(p.getRecommend_img());
			}
		}
		for (ProjectVo p : projectVos) {
			companyFilesService.deleteRelationByFileUrl(p.getProject_folder());
			if(p.getCover_path()!=null&&!p.getCover_path().endsWith("sc/page/companyDefHeard.jpg")){
				OSSFilesUtil.deleteFile(p.getCover_path());
			}
		}
		for (String id : ids) {
			GroupUserVo gUser = new GroupUserVo();
			gUser.setProject_id(Long.parseLong(id));
			gUser.setIs_parent_project("1");
			groupUserService.delete(gUser);
			GRoleVo role = new GRoleVo();
			role.setProject_id(Long.parseLong(id));
			role.setIs_parent_poject("1");
			gRoleService.delete(role);
		}
		projectDao.deleteAll(map);
		projectDao.deleteCompanyPluginByProjectId(map);// 删除插件
		projectDao.deleteCompanySoftwareByProjectId(map);// 删除软件
		projectDao.deleteProjectDirectorByProjectId(map);// 删除导演
		projectDao.deleterProjectPrincipalByProjectId(map);// 删除负责人
		projectDao.deleteProjectScreenwriterByProjectId(map);// 删除编剧

	}

	@Override
	public List<UserProject> getProjectByUserIds(Long userId) {
		return projectDao.getProjectByUserIds(userId);
	}

	@Override
	public Page page(Page page, Project project) {
		return projectDao.page(page, project);
	}

	@Override
	public List<Project> getProjectByIds(Map<String, String[]> map) {
		return projectDao.getProjectByIds(map);
	}

	@Override
	public ProjectVo getProjectById(Long id) {
		return projectDao.getProjectById(id);
	}

	@Override
	public List<Long> getSubProjectIds(Map<String, String[]> map) {
		return projectDao.getSubProjectIds(map);
	}

	@Override
	public List<Long> getModelTaskIds(Map<String, String[]> projectIds) {
		return projectDao.getModelTaskIds(projectIds);
	}

	@Override
	public List<Long> getAnimationLightTaskIds(Map<String, String[]> projectIds) {
		return projectDao.getAnimationLightTaskIds(projectIds);
	}

	@Override
	public List<ProjectVo> getDataList(ProjectVo project) {
		return projectDao.getDataList(project);
	}

	@Override
	public List<SubProject> getParentSubProjects(Map<String, Object> mapIds) {
		return projectDao.getParentSubProjects(mapIds);
	}

	@Override
	public List<SubProject> getSubProjects(Map<String, Object> mapIds) {
		return projectDao.getSubProjects(mapIds);
	}

	@Override
	public Integer getTotalUndistributed(Map<String, Object> mapIds) {
		return projectDao.getTotalUndistributed(mapIds);
	}

	@Override
	public Integer getTotalRunning(Map<String, Object> mapIds) {
		return projectDao.getTotalRunning(mapIds);
	}

	@Override
	public Integer getTotalFinished(Map<String, Object> mapIds) {
		return projectDao.getTotalFinished(mapIds);
	}

	@Override
	public Integer getModelTaskFinishedTotal(Map<String, Object> mapIds) {
		return projectDao.getModelTaskFinishedTotal(mapIds);
	}

	@Override
	public Integer getModelTaskTotal(Map<String, Object> mapIds) {
		return projectDao.getModelTaskTotal(mapIds);
	}

	@Override
	public Integer getAnimationTaskFinishedTotal(Map<String, Object> mapIds) {
		return projectDao.getAnimationTaskFinishedTotal(mapIds);
	}

	@Override
	public Integer getanimationTaskTotal(Map<String, Object> mapIds) {
		return projectDao.getanimationTaskTotal(mapIds);
	}

	@Override
	public Integer getLightTaskFinishedTotal(Map<String, Object> mapIds) {
		return projectDao.getLightTaskFinishedTotal(mapIds);
	}

	@Override
	public Integer getLightTaskTotal(Map<String, Object> mapIds) {
		return projectDao.getLightTaskTotal(mapIds);
	}

	@Override
	public Integer getTotal(Map<String, Object> mapIds) {
		return projectDao.getTotal(mapIds);
	}

	@Override
	public Long getProjectTotal(Map<String, Long> m) {
		return projectDao.getProjectTotal(m);
	}

	@Override
	public Date getMinPublishDate(Map<String, Object> mapIds) {
		return projectDao.getMinPublishDate(mapIds);
	}

	@Override
	public ProjectVo getProjectDetails(Long project_id) {
		return projectDao.getProjectDetails(project_id);
	}

	@Override
	public Integer getModelTaskMakerNums(Map<String, Object> mapIds) {
		return projectDao.getModelTaskMakerNums(mapIds);
	}

	@Override
	public Integer getAnimationTaskMakerNums(Map<String, Object> mapIds) {
		return projectDao.getAnimationTaskMakerNums(mapIds);
	}

	@Override
	public Project getProjectByProjectNo(String projectNo) {
		return projectDao.getProjectByProjectNo(projectNo);
	}

	@Override
	public Long getMaxId() {
		return projectDao.getMaxId();
	}

	@Override
	public Page page3(Page page, ProjectVo project) {
		return projectDao.page3(page, project);
	}

	@Override
	public Date getActualBeginTime(Map<String, Object> mapIds) {
		return projectDao.getActualBeginTime(mapIds);
	}

	@Override
	public Integer getActualFinishTotal(Map<String, Object> mapIds) {
		return projectDao.getActualFinishTotal(mapIds);
	}

	@Override
	public Date getActualEndTime(Map<String, Object> mapIds) {
		return projectDao.getActualEndTime(mapIds);
	}

	@Override
	public List<ProjectVo> getProjectsByUserId(Long user_id) {
		return projectDao.getProjectsByUserId(user_id);
	}

	@Override
	public List<ProjectVo> getProjectsByCompanyId(Long company_id) {
		return projectDao.getProjectsByCompanyId(company_id);
	}

	@Override
	public Integer getManagerNums(Map<String, Object> map) {
		return projectDao.getManagerNums(map);
	}

	@Override
	public List<LabelTag> getAllLabelTag() {
		return (List<LabelTag>)projectDao.getAllLabelTag();
	}

	@Override
	public List<Long> getParentIds() {
		return projectDao.getParentIds();
	}

	@Override
	public List<Long> getSubIds() {
		return projectDao.getSubIds();
	}

	@Override
	public Double getTaskUseTimesOfProjects(Map<String, Object> map) {
		return projectDao.getTaskUseTimesOfProjects(map);
	}

	@Override
	public Long getTaskUserNumsOfParents(Map<String, Object> map) {
		return projectDao.getTaskUserNumsOfParents(map);
	}

	@Override
	public Double getTaskCheckUseTimesOfProjects(Map<String, Object> map) {
		return projectDao.getTaskCheckUseTimesOfProjects(map);
	}

	@Override
	public Long getCheckNums(Map<String, Object> map) {
		return projectDao.getCheckNums(map);
	}

	@Override
	public List<Long> getUserIdsByMap(Map<String, Object> map) {
		return projectDao.getUserIdsByMap(map);
	}

	@Override
	public List<Long> getIdsByMap(Map<String, Object> map) {
		return projectDao.getIdsByMap(map);
	}

	@Override
	public Date getJoinTimeOfMaker(Map<String, Object> map) {
		return projectDao.getJoinTimeOfMaker(map);
	}

	@Override
	public List<ProjectVo> getSliderProjects() {
		return projectDao.getSliderProjects();
	}

	@Override
	public ProjectVo getRecommendProject() {
		return projectDao.getRecommendProject();
	}

	@Override
	public List<ProjectVo> getShowProjects() {
		return projectDao.getShowProjects();
	}

	@Override
	public Company getCompanyById(Long id) {
		return projectDao.getCompanyById(id);
	}

	@Override
	public Page page1(Page page, ProjectVo project) {
		return projectDao.page1(page, project);
	}

	 

	@Override
	public List<ProjectVo> getOldShowProjects() {
		return projectDao.getOldShowProjects();
	}

	@Override
	public void updateProjectState(Map<String, Object> m) {
		projectDao.updateProjectState(m);
	}

	@Override
	public ProjectVo getOldRecommendProject() {
		return projectDao.getOldRecommendProject();
	}

	@Override
	public Search getSearch(String search) {
		return projectDao.getSearch(search);
	}

	@Override
	public void updateSearch(Search search) {
		  projectDao.updateSearch(search);
		
	}

	@Override
	public void addSearch(Search search) {
		 projectDao.addSearch(search);
	}

	@Override
	public List<Search> getHotSearchs() {
		return projectDao.getHotSearchs();
	}
}
