package com.cgwas.subproject.service.impl;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.utils.ConstantUtil;
import com.cgwas.common.utils.OSSFilesUtil;
import com.cgwas.companyFiles.entity.CompanyFilesVo;
import com.cgwas.companyFiles.service.api.ICompanyFilesService;
import com.cgwas.director.dao.api.IDirectorDao;
import com.cgwas.director.entity.DirectorVo;
import com.cgwas.gRole.entity.GRoleVo;
import com.cgwas.gRole.service.api.IGRoleService;
import com.cgwas.groupUser.entity.GroupUser;
import com.cgwas.groupUser.entity.GroupUserVo;
import com.cgwas.groupUser.service.api.IGroupUserService;
import com.cgwas.principal.dao.api.IPrincipalDao;
import com.cgwas.principal.entity.PrincipalVo;
import com.cgwas.project.dao.api.IProjectDao;
import com.cgwas.projectSearch.entity.ProjectSearchVo;
import com.cgwas.projectStatus.dao.api.IProjectStatusDao;
import com.cgwas.screenwriter.dao.api.IScreenwriterDao;
import com.cgwas.screenwriter.entity.ScreenwriterVo;
import com.cgwas.subproject.dao.api.ISubProjectDao;
import com.cgwas.subproject.entity.SubProject;
import com.cgwas.subproject.entity.SubProjectVo;
import com.cgwas.subproject.service.api.ISubProjectService;
import com.cgwas.user.dao.api.IUserDao;
import com.cgwas.user.entity.User;
import com.cgwas.userCompany.service.api.IUserCompanyService;
import com.cgwas.userInfo.entity.UserInfoVo;
import com.cgwas.util.ExcelImportUtil;

/**
 * Author yangjun
 */
@Service
public class SubProjectService implements ISubProjectService {
	@Autowired
	private ISubProjectDao subProjectDao;// 子项目
	@Autowired
	private IUserDao userDao;// 用户
	@Autowired
	private IDirectorDao directorDao;// 导演
	@Autowired
	private IPrincipalDao principalDao;// 负责人
	@Autowired
	private IProjectDao projectDao;// 根父项目
	@Autowired
	private IScreenwriterDao screenwriterDao;// 编剧
	@Autowired
	private IProjectStatusDao projectStatusDao;// 项目状态
	@Autowired
	private IUserCompanyService userCompanyService;
	@Autowired
	private IGRoleService gRoleService;
	@Autowired
	private IGroupUserService groupUserService;
	@Autowired
	private ICompanyFilesService companyFilesService;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	public Serializable save(SubProject subProject) {
		return subProjectDao.save(subProject);
	}

	public void deleteByExample(SubProject subProject) {
		subProjectDao.deleteByExample(subProject);
	}

	public void updateIgnoreNull(SubProject subProject) {
		subProjectDao.updateIgnoreNull(subProject);
	}

	public void updateByExample(SubProject subProject) {
		subProjectDao.update(
				"com.cgwas.subproject.dao.updateByExampleSubProject",
				subProject);
	}

	public SubProjectVo load(SubProject subProject) {
		return (SubProjectVo) subProjectDao.reload(subProject);
	}

	public SubProjectVo selectForObject(SubProject subProject) {
		return (SubProjectVo) subProjectDao.selectForObject(
				"com.cgwas.subproject.dao.selectSubProject", subProject);
	}

	public List<SubProjectVo> selectForList(SubProject subProject) {
		return (List<SubProjectVo>) subProjectDao.selectForList(
				"com.cgwas.subproject.dao.selectSubProject", subProject);
	}

	public Page page(Page page, SubProject subProject) {
		return subProjectDao.page(page, subProject);
	}

	/**
	 * 修改项目
	 */
	@Override
	@Transactional
	public void update(SubProjectVo project, String directorIds,
			String principalIds, String screenwriterIds, String beginTime,
			String endTime, HttpSession session) {
		Long company_id = (Long) session.getAttribute("projectCompany");
		String errorInfo = "";
		project.setModified_time(new Date());
		try {
			project.setBegin_time(sdf.parse(beginTime));
			project.setEnd_time(sdf.parse(endTime));
		} catch (ParseException e) {
			errorInfo += "时间格式错误,请按照yyyy-MM-dd HH:mm:ss格式录入时间\n";
			throw new RuntimeException(errorInfo);
		}
		// Map<String, String> m1 = new HashMap<String, String>();
		// m1.put("id", project.getUser_id()+"");
		// UserInfoVo i = (UserInfoVo) userDao.selectForObject(
		// "com.cgwas.user.dao.getUserInfoByUserId", m1);// 根据创建id查出user对象
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
		// project.setCreater_name(null);
		// throw new RuntimeException(errorInfo);
		// }
		subProjectDao.updateIgnoreNull(project);
		/**
		 * 删除导演角色成员
		 */
		GRoleVo g=new GRoleVo();
		g.setIs_parent_poject("0");
		g.setProject_id(project.getId());
		g.setRole_p_id(1L);
		g=gRoleService.selectForObject(g);
		GroupUserVo gUser=new GroupUserVo();
		gUser.setIs_parent_project("0");
		gUser.setProject_id(project.getId());
		gUser.setRole_id(g.getId());
		groupUserService.delete(gUser);
		
		/**
		 * 删除负责人角色成员
		 */
		g=new GRoleVo();
		g.setIs_parent_poject("0");
		g.setProject_id(project.getId());
		g.setRole_p_id(3L);
		g=gRoleService.selectForObject(g);
		gUser.setRole_id(g.getId());
		groupUserService.delete(gUser);
		
		/**
		 * 删除编剧角色成员
		 */
		g=new GRoleVo();
		g.setIs_parent_poject("0");
		g.setProject_id(project.getId());
		g.setRole_p_id(4L);
		g=gRoleService.selectForObject(g);
		gUser.setRole_id(g.getId());
		groupUserService.delete(gUser);
		Map<String, Long> m = new HashMap<String, Long>();
		m.put("project_id", project.getId());
		m.put("is_parent_project", 0L);
		projectDao.deleteProjectDirector(m);// 删除项目导演关系表中数据
		projectDao.deleterProjectPrincipal(m);// 删除项目负责人关系表中数据
		projectDao.deleteProjectScreenwriter(m);// 删除项目编剧关系表中数据
		Map<String, Object> map = new HashMap<String, Object>();
		if (directorIds != null && directorIds.trim().length() > 0) {
			g = new GRoleVo();
			g.setIs_parent_poject("0");
			g.setProject_id(project.getId());
			g.setRole_p_id(1L);
			g=gRoleService.selectForObject(g);
			String[] directIds = directorIds.split(",");// 根据前台传来的导演id字符串转化为id数组
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
						"com.cgwas.director.dao.getDirectorByUserId", dMap);// 根据导演用户名查询在导演表里是否存在
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
				map.put("is_parent_poject", "0");// 0表不是根项目
				projectDao.addProjectDirector(map);// 将导演数据插入到项目与导演的中间表中
				  gUser=new GroupUserVo();
					gUser.setIs_parent_project(ConstantUtil.NO_PARENT_POJECT);
					gUser.setProject_id(project.getId());
					gUser.setCompany_id(company_id);
					gUser.setRole_id(g.getId());
					gUser.setUser_id(Long.parseLong(id));
					groupUserService.saveGroup(gUser);
			}
		}
		if (principalIds != null && principalIds.trim().length() > 0) {
			g = new GRoleVo();
			g.setIs_parent_poject("0");
			g.setProject_id(project.getId());
			g.setRole_p_id(3L);
			g=gRoleService.selectForObject(g);
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
						"com.cgwas.principal.dao.getPrincipalByUserId", pMap);// 根据负责人用户名查询在负责人表里是否存在
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
				map.put("is_parent_poject", "0");// 0表不是根项目
				projectDao.addProjectPrincipal(map);// 将负责人数据插入到项目与负责人的中间表中
				gUser=new GroupUserVo();
				gUser.setIs_parent_project(ConstantUtil.NO_PARENT_POJECT);
				gUser.setProject_id(project.getId());
				gUser.setCompany_id(company_id);
				gUser.setRole_id(g.getId());
				gUser.setUser_id(Long.parseLong(id));
				groupUserService.saveGroup(gUser);
			}
		}
		if (screenwriterIds != null && screenwriterIds.trim().length() > 0) {
			g = new GRoleVo();
			g.setRole_p_id(4L);
			g.setIs_parent_poject("0");
			g.setProject_id(project.getId());
			g=gRoleService.selectForObject(g);
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
				map.put("is_parent_poject", "0");// 0表不是根项目
				projectDao.addProjectScreenwriter(map);// 将负责人数据插入到项目与负责人的中间表中
				gUser=new GroupUserVo();
				gUser.setIs_parent_project(ConstantUtil.NO_PARENT_POJECT);
				gUser.setProject_id(project.getId());
				gUser.setCompany_id(company_id);
				gUser.setRole_id(g.getId());
				gUser.setUser_id(Long.parseLong(id));
				groupUserService.saveGroup(gUser);
			}
		}

		SubProjectVo v = new SubProjectVo();
		if (project.getSub_parent_project_id() != null) {// 当添加完项目后，如果父项目不是根父项目，则需要将其父项目标志is_parent为1,表示该父项目有子项目了
			v.setId(project.getSub_parent_project_id());
			v = selectForObject(v);
			if (v != null) {
				v.setIs_parent("1");
				subProjectDao.updateIgnoreNull(v);
			}
		}

	}

	/**
	 * 添加子项目
	 */
	@Override
	@Transactional
	public void create(SubProjectVo project, String directorIds,
			String principalIds, String screenwriterIds, String beginTime,
			String endTime, HttpSession session) {
		String errorInfo = "";
		project.setCreate_time(new Date());
		project.setModified_time(new Date());
		try {
			project.setBegin_time(sdf.parse(beginTime));
			project.setEnd_time(sdf.parse(endTime));
		} catch (ParseException e) {
			errorInfo += "时间格式错误,请按照yyyy-MM-dd HH:mm:ss格式录入时间\n";
			throw new RuntimeException(errorInfo);
		}
		Long user_id = ((User) session.getAttribute("loginUser")).getId();
		project.setUser_id(user_id);
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
		// project.setCreater_name(null);
		// throw new RuntimeException(errorInfo);
		// }
		Long company_id = (Long) session.getAttribute("projectCompany");
		project.setCompany_id(company_id);
		System.out.println(project);
		subProjectDao.save(project);
		GRoleVo g = new GRoleVo();
		g.setIs_parent_poject("0");
		g.setProject_id(project.getId());
		gRoleService.saveSysRole(g);
		
		/**
		 * 判断该路径是否存在 若不存在添加文件夹
		 */
		CompanyFilesVo vo = new CompanyFilesVo();
		if (!OSSFilesUtil.isFile(project.getFolder_path()+"/")) {
			vo.setFile_type(ConstantUtil.ZC_SUB_PROJECT);
			vo.setCompany_id(company_id);
			vo.setIs_file(ConstantUtil.IS_FILE);
			vo.setParent_id(project.getParentFileId());
			vo.setCreator_id(user_id);
			vo.setFile_url(project.getProject_folder());
			vo.setFile_name(project.getProject_folder().substring(project.getProject_folder().lastIndexOf("/")+1,project.getProject_folder().length()));
			vo.setFor_id(project.getId());
			companyFilesService.save(vo);
		} else {
			/**
			 * 查询选择的文件夹，并更新为项目文件夹
			 */
			vo.setFile_url(project.getProject_folder());
			vo=companyFilesService.selectForObject(vo);
			vo.setFile_type(ConstantUtil.ZC_SUB_PROJECT);
			vo.setFor_id(project.getId());
			companyFilesService.updateIgnoreNull(vo);
		}
		
		Map<String, Object> map = new HashMap<String, Object>();
		if (directorIds != null && directorIds.trim().length() > 0) {
			g = new GRoleVo();
			g.setIs_parent_poject("0");
			g.setProject_id(project.getId());
			g.setRole_p_id(1L);
			g=gRoleService.selectForObject(g);
			String[] directIds = directorIds.split(",");// 根据前台传来的导演id字符串转化为id数组
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
						"com.cgwas.director.dao.getDirectorByUserId", dMap);// 根据导演用户名查询在导演表里是否存在
				if (d == null) {// 当导演在导演表里不存在时，添加导演对象到导演表中
					d = new DirectorVo();
					d.setUser_id(Long.parseLong(id));
					d.setDirector_name(u.getName());
					d.setHead_pic_path(u.getHead_pic_path());
					directorDao.save(d);
				}
				map.put("director_id", d.getId());
				map.put("project_id", project.getId());
				map.put("is_parent_poject", "0");// 0表不是根项目
				projectDao.addProjectDirector(map);// 将导演数据插入到项目与导演的中间表中
				GroupUserVo gUser=new GroupUserVo();
				gUser.setIs_parent_project("0");
				gUser.setProject_id(project.getId());
				gUser.setCompany_id(company_id);
				gUser.setRole_id(g.getId());
				gUser.setUser_id(Long.parseLong(id));
				groupUserService.saveGroup(gUser);
			}
		}
		if (principalIds != null && principalIds.trim().length() > 0) {
			g = new GRoleVo();
			g.setIs_parent_poject("0");
			g.setProject_id(project.getId());
			g.setRole_p_id(3L);
			g=gRoleService.selectForObject(g);
			String[] principIds = principalIds.split(",");// 根据前台传来的负责人id字符串转化为id数组
			for (String id : principIds) {
				Map<String, String> pMap = new HashMap<String, String>();
				pMap.put("id", id);
				UserInfoVo u = (UserInfoVo) userDao.selectForObject(
						"getUserInfoByUserId", pMap);// 根据负责人用户id查出user对象
				if (u == null) {
					errorInfo += "负责人不在平台中,请核实再录入!\n";
					throw new RuntimeException(errorInfo);
				}
				PrincipalVo p = (PrincipalVo) principalDao.selectForObject(
						"com.cgwas.principal.dao.getPrincipalByUserId", pMap);// 根据负责人用户名查询在负责人表里是否存在
				if (p == null) {// 当负责人在负责人里不存在时，添加负责人对象到负责人表中
					p = new PrincipalVo();
					p.setUser_id(Long.parseLong(id));
					p.setPrincipal_name(u.getName());
					p.setHead_pic_path(u.getHead_pic_path());
					principalDao.save(p);
				}
				map.put("principal_id", p.getId());
				map.put("project_id", project.getId());
				map.put("is_parent_poject", "0");// 0表不是根项目
				projectDao.addProjectPrincipal(map);// 将负责人数据插入到项目与负责人的中间表中
				GroupUserVo gUser=new GroupUserVo();
				gUser.setIs_parent_project("0");
				gUser.setProject_id(project.getId());
				gUser.setCompany_id(company_id);
				gUser.setRole_id(g.getId());
				gUser.setUser_id(Long.parseLong(id));
				groupUserService.saveGroup(gUser);
			}
		}
		if (screenwriterIds != null && screenwriterIds.trim().length() > 0) {
			g = new GRoleVo();
			g.setIs_parent_poject("0");
			g.setProject_id(project.getId());
			g.setRole_p_id(4L);
			g=gRoleService.selectForObject(g);
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
				}
				map.put("screenwriter_id", s.getId());
				map.put("project_id", project.getId());
				map.put("is_parent_poject", "0");// 0表不是根项目
				projectDao.addProjectScreenwriter(map);// 将负责人数据插入到项目与负责人的中间表中
				GroupUserVo gUser=new GroupUserVo();
				gUser.setIs_parent_project("0");
				gUser.setProject_id(project.getId());
				gUser.setCompany_id(company_id);
				gUser.setRole_id(g.getId());
				gUser.setUser_id(Long.parseLong(id));
				groupUserService.saveGroup(gUser);
			}
		}

		SubProjectVo v = new SubProjectVo();
		if (project.getSub_parent_project_id() != null) {
			// 当添加完项目后，如果父项目不是根父项目，则需要将其父项目标志is_parent为1,表示该父项目有子项目了
			v.setId(project.getSub_parent_project_id());
			v = selectForObject(v);
			if (v != null) {
				v.setIs_parent("1");
				subProjectDao.updateIgnoreNull(v);
			}
		}
	}

	/**
	 * 批量导入子项目
	 * 
	 * @param map
	 * @param session
	 * @param project_id根父项目
	 * @param sub_parent_project_id父项目
	 * @param company_id公司id
	 * @param num第几条记录
	 * @return
	 */
	private String insertProject(Map<String, Object> map, Long project_id,
			Long sub_parent_project_id, Long company_id, Integer num,
			HttpSession session) {
		String errorInfo = "";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SubProjectVo project = (SubProjectVo) map.get("project");// 从map获取待插入的project对象
		Long status_id = projectStatusDao.getStatusId(map);// 根据map中的status查询状态id
		if (status_id == null) {
			try {
				errorInfo += "第" + num + "行:" + map.get("status")
						+ "项目状态不在平台中，请核实在录入数据\n";
				status_id.intValue();
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(errorInfo);
			}
		}
		Long user_id = ((User) session.getAttribute("loginUser")).getId();
		project.setUser_id(user_id);// 将用户id赋值给project对象
		if (user_id == null) {// 当用户id为空时，说明添加的用户不是平台中的用户，则将创建者名称设置为空
			try {
				errorInfo += "第" + num + "行:" + project.getCreater_name()
						+ "创建人不在平台中，请核实在录入数据\n";
				project.setCreater_name(null);
				user_id.intValue();
			} catch (Exception e) {
				e.printStackTrace();
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
		project.setProject_id(project_id);
		project.setSub_parent_project_id(sub_parent_project_id);
		project.setProject_status_id(status_id + "");// 将状态id赋值给project对象
		project.setCreate_time(new Date());
		project.setModified_time(new Date());
		String begin_time = (String) map.get("begin_time");
		String end_time = (String) map.get("end_time");
		try {
			project.setBegin_time(sdf.parse(begin_time));
			project.setEnd_time(sdf.parse(end_time));
		} catch (ParseException e) {
			e.printStackTrace();
			errorInfo += "第" + num + "行:日期格式不正确,请按照yyyy-MM-dd HH:mm:ss格式\n";
			throw new RuntimeException(errorInfo);
		}
		project.setCompany_id(company_id);
		String uuid = userCompanyService.getCompanyUserUUID(company_id);
		String folder_path = ConstantUtil.USER_PATH + "/" + uuid + "/data/"
				+ project.getProject_folder();
		if (!OSSFilesUtil.isFile(folder_path)) {
			OSSFilesUtil.addFile(folder_path);
		}
		subProjectDao.save(project);// 将项目对象添加到数据表中
		GRoleVo g=new GRoleVo();
		g.setIs_parent_poject("0");
		g.setProject_id(project.getId());
		gRoleService.saveSysRole(g);
		String principals = (String) map.get("principals");// 获取excel中的负责人
		if(principals!=null&&principals.trim().length()>0){
			g = new GRoleVo();
			g.setIs_parent_poject("0");
			g.setProject_id(project.getId());
			g.setRole_p_id(3L);
			g=gRoleService.selectForObject(g);
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
					map.put("is_parent_poject", "0");// 表示该项目不是根项目
					projectDao.addProjectPrincipal(map);
					GroupUserVo gUser=new GroupUserVo();
					gUser.setIs_parent_project("0");
					gUser.setProject_id(project.getId());
					gUser.setCompany_id(company_id);
					gUser.setRole_id(g.getId());
					gUser.setUser_id(u.getUser_id());
					groupUserService.saveGroup(gUser);
				} else {
					try {
						principal_id.intValue();
					} catch (Exception e) {
						e.printStackTrace();
						errorInfo += "第" + num + "行:" + s + "负责人不存在！\n";
						throw new RuntimeException(errorInfo);
					}
				}
			}
		}

		String directors = (String) map.get("directors");// 获取excel中的导演
		if(directors!=null&&directors.trim().length()>0){
			g.setRole_p_id(1L);
			g = new GRoleVo();
			g.setIs_parent_poject("0");
			g.setProject_id(project.getId());
			g=gRoleService.selectForObject(g);			String[] drs = null;
			if (directors.indexOf(",") != -1) {// 当导演是英文“，”隔开的，则将导演转换为string数组
				drs = directors.split(",");
			} else if (directors.indexOf("，") != -1) {// 当导演是中文“，”隔开的，则将导演转换为string数组
				drs = directors.split("，");
			} else {
				drs = new String[] { directors };
			}
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
					map.put("is_parent_poject", "0");// 表示该项目不是根项目
					projectDao.addProjectDirector(map);
					GroupUserVo gUser=new GroupUserVo();
					gUser.setIs_parent_project("0");
					gUser.setProject_id(project.getId());
					gUser.setCompany_id(company_id);
					gUser.setRole_id(g.getId());
					gUser.setUser_id(u.getUser_id());
					groupUserService.saveGroup(gUser);
				} else {
					try {
						director_id.intValue();
					} catch (Exception e) {
						e.printStackTrace();
						errorInfo += "第" + num + "行:" + s + "导演不存在！\n";
						throw new RuntimeException(errorInfo);
					}
				}
			}
		}
		
		String screenwriters = (String) map.get("screenwriters");// 获取excel中的编剧
		if(screenwriters!=null&&screenwriters.trim().length()>0){
			g = new GRoleVo();
			g.setRole_p_id(4L);
			g.setIs_parent_poject("0");
			g.setProject_id(project.getId());
			g=gRoleService.selectForObject(g);
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
					map.put("is_parent_poject", "0");// 表示该项目不是根项目
					projectDao.addProjectScreenwriter(map);
					GroupUserVo gUser=new GroupUserVo();
					gUser.setIs_parent_project("0");
					gUser.setProject_id(project.getId());
					gUser.setCompany_id(company_id);
					gUser.setRole_id(g.getId());
					gUser.setUser_id(u.getUser_id());
					groupUserService.saveGroup(gUser);
				} else {
					try {
						screenwriter_id.intValue();
					} catch (Exception e) {
						e.printStackTrace();
						errorInfo += "第" + num + "行:" + s + "编剧不存在！\n";
						throw new RuntimeException(errorInfo);
					}
				}
			}
		}
	
		SubProjectVo v = new SubProjectVo();
		if (project.getSub_parent_project_id() != null) {
			v.setId(project.getSub_parent_project_id());
			v = selectForObject(v);
			if (v != null) {
				v.setIs_parent("1");
				subProjectDao.updateIgnoreNull(v);
			}
		}
		return errorInfo;
	}

	@Override
	@Transactional
	public String importProject(String filePath, Long project_id,
			Long sub_parent_project_id, Long company_id, String errorInfo,
			HttpSession session) throws Exception {
		errorInfo += "";
		Integer num = 1;
		List<Map<String, Object>> list = ExcelImportUtil
				.importSubProjectInfo(filePath);
		for (Map<String, Object> map : list) {
			try {
				insertProject(map, project_id, sub_parent_project_id,
						company_id, num, session);
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

	@Override
	public Object selectForList(ProjectSearchVo vo) {
		return subProjectDao.selectForList(
				"com.cgwas.subproject.dao.getSubProjectSearch", vo);
	}

	@Override
	public SubProjectVo getSubProjectById(Long id) {
		return subProjectDao.getSubProjectById(id);
	}

	@Override
	public List<SubProjectVo> getSubProjects(Map<String, Object> map) {
		return subProjectDao.getSubProjects(map);
	}

	public void delete(SubProject subProject) {
		subProjectDao.delete(subProject);
	}

	@Override
	@Transactional
	public void deleteAll(Map<String, String[]> map) {
		String[] ids=map.get("ids");
		List<SubProjectVo> subProjects=subProjectDao.getSubProjectsByIds(map);
		for (SubProjectVo s : subProjects) {
			GroupUser g=new GroupUser();
			g.setProject_id(s.getId());
			g.setIs_parent_project("0");
			groupUserService.delete(g);
			if(s.getCover_path()!=null&&!s.getCover_path().endsWith("sc/page/companyDefHeard.jpg")){
				OSSFilesUtil.deleteFile(s.getCover_path());
			}
			companyFilesService.deleteRelationByFileUrl(s.getProject_folder());
		}
		for (String id : ids) {
			GroupUserVo gUser=new GroupUserVo();
			gUser.setProject_id(Long.parseLong(id));
			gUser.setIs_parent_project("0");
			groupUserService.delete(gUser);
		    GRoleVo role=new GRoleVo();
		    role.setProject_id(Long.parseLong(id));
		    role.setIs_parent_poject("0");
		    gRoleService.delete(role);
		}
		subProjectDao.deleteAll(map);
		subProjectDao.deleteProjectDirectorByProjectId(map);// 删除导演
		subProjectDao.deleterProjectPrincipalByProjectId(map);// 删除负责人
		subProjectDao.deleteProjectScreenwriterByProjectId(map);// 删除编辑
		Set<Long> parentProjectIds=new HashSet<Long>();
		for (String id : ids) {
			Long project_id=subProjectDao.getParentId(Long.parseLong(id));
			if(project_id!=null){
				Map<String, String[]> m = new HashMap<String, String[]>();
				map.put("ids", new String[]{project_id+""});
				List<Long> subProjectIds = subProjectDao.getSubProjectIds(map);// 获取删除项目下的直接子项目
				if(subProjectIds.size()==0){
					parentProjectIds.add(project_id);
				}
			}
		}
		Map<String,Object> m=new HashMap<String,Object>();
		m.put("parentProjectIds", parentProjectIds);
		if(parentProjectIds.size()>0){
			subProjectDao.updateProject(m);
		}
	}

	@Override
	public List<Long> getSubProjectIds(Map<String, String[]> map) {

		return subProjectDao.getSubProjectIds(map);
	}

	@Override
	public List<Long> getModelTaskIds(Map<String, String[]> map) {

		return subProjectDao.getModelTaskIds(map);
	}

	@Override
	public List<Long> getAnimationLightTaskIds(Map<String, String[]> map) {

		return subProjectDao.getAnimationLightTaskIds(map);
	}

	@Override
	public List<SubProjectVo> getDataList(SubProjectVo project) {
		return subProjectDao.getDataList(project);
	}

	@Override
	public List<Long> getModelTaskIdByMaps(Map<String, Object> map) {
		return subProjectDao.getModelTaskIdByMaps(map);
	}

	@Override
	public List<Long> getAnimationLightTaskIdByMaps(Map<String, Object> map) {
		return subProjectDao.getAnimationLightTaskIdByMaps(map);
	}

	@Override
	public SubProjectVo getProjectDetails(Long project_id) {
		return subProjectDao.getProjectDetails(project_id);
	}

	@Override
	public Long getParentParentId(Map<String, Object> map) {
		return subProjectDao.getParentParentId(map);
	}

	@Override
	public Long getSubParentId(Map<String, Object> map) {
		return subProjectDao.getSubParentId(map);
	}

	@Override
	public Long getParentId(Long id) {
		return subProjectDao.getParentId(id);
	}

	@Override
	public List<SubProjectVo> getSubProjectsByUserId(Long user_id) {
		return subProjectDao.getSubProjectsByUserId(user_id);
	}

	@Override
	public void updateProjectState(Map<String, Object> m) {
		subProjectDao.updateProjectState(m);
		
	}
}
