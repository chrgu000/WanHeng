package com.cgwas.gRolePrivilege.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.utils.ConstantUtil;
import com.cgwas.gRole.entity.GRoleVo;
import com.cgwas.gRole.service.api.IGRoleService;
import com.cgwas.gRolePrivilege.dao.api.IGRolePrivilegeDao;
import com.cgwas.gRolePrivilege.entity.GRolePrivilege;
import com.cgwas.gRolePrivilege.entity.GRolePrivilegeVo;
import com.cgwas.gRolePrivilege.service.api.IGRolePrivilegeService;
import com.cgwas.logInfo.entity.LogInfo;
import com.cgwas.logInfo.service.api.ILogInfoService;
import com.cgwas.privilegeInfo.entity.PrivilegeInfoVo;
import com.cgwas.privilegeInfo.service.api.IPrivilegeInfoService;
import com.cgwas.project.entity.ProjectVo;
import com.cgwas.project.service.api.IProjectService;
import com.cgwas.subproject.entity.SubProjectVo;
import com.cgwas.subproject.service.api.ISubProjectService;
/**
 * 
 * @author yubangqiong
 *
 */
@Service
public class GRolePrivilegeService implements IGRolePrivilegeService {
	@Autowired
	private IGRolePrivilegeDao gRolePrivilegeDao;
	@Autowired
	private ILogInfoService logInfoService;
	@Autowired
	private IGRoleService gRoleService;
	@Autowired
	private IProjectService projectService;
	@Autowired
	private ISubProjectService suISubProjectService;
	@Autowired
	private IPrivilegeInfoService privilegeInfoService;
	public Serializable save(GRolePrivilege gRolePrivilege){
		return gRolePrivilegeDao.save(gRolePrivilege);
	}
	
	@Transactional
	public Boolean saveAndUpdate(GRolePrivilegeVo gRolePrivilegeVo){
		GRolePrivilegeVo obj= null;
		boolean flag=false;
		StringBuffer privileges= new StringBuffer();
		StringBuffer logContent= new StringBuffer();
		String projectName=null;//项目名称
		PrivilegeInfoVo privilegeInfo =null;
		try {
			/**
			 * 先删除相关用户权限关系记录，然后添加
			 */
			gRolePrivilegeDao.delete(gRolePrivilegeVo);
			/**
			 * 循环添加用户权限
			 */
			for (long privilege_id : gRolePrivilegeVo.getIds()) {
				privilegeInfo= new PrivilegeInfoVo();
				privilegeInfo.setId(privilege_id);
				privilegeInfo=privilegeInfoService.selectForObject(privilegeInfo);
				privileges.append("【"+privilegeInfo.getPrivilege_name()+"】,");
				obj= new GRolePrivilegeVo();
				obj.setRole_id(gRolePrivilegeVo.getRole_id());
				obj.setPrivilege_id(privilege_id);
				obj.setCompany_file_id(gRolePrivilegeVo.getCompany_file_id());
				gRolePrivilegeDao.save(obj);
			}
			/**
			 * 查询更新权限的角色
			 */
			GRoleVo gRoleVo= new GRoleVo();
			gRoleVo.setId(gRolePrivilegeVo.getRole_id());
			gRoleVo=gRoleService.selectForObject(gRoleVo);
			/**
			 * 查询当前角色所在的项目
			 */
			if(gRoleVo.getIs_parent_poject().equals(ConstantUtil.IS_PARENT_POJECT)){
				ProjectVo project = new ProjectVo();
				project.setId(gRoleVo.getProject_id());
				project=projectService.selectForObject(project);
				projectName=project.getName();
			}else{
				SubProjectVo project = new SubProjectVo();
				project.setId(gRoleVo.getProject_id());
				project=suISubProjectService.selectForObject(project);
				projectName=project.getName();
			}
			/**
			 * 执行操作日志
			 */
			LogInfo logInfo= new LogInfo();
			logInfo.setTable_name("p_company_files_user");
			logInfo.setUser_id(gRolePrivilegeVo.getUserId());
			logInfo.setUser_name(gRolePrivilegeVo.getUserName());
			if(gRolePrivilegeVo.getIds().length>0){
				logInfo.setLog_type(ConstantUtil.LOG_SAVE);
				logContent.append(gRolePrivilegeVo.getUserName());
				logContent.append("更新项目【"+projectName);
				logContent.append("】的团队角色【");
				logContent.append(gRoleVo.getRole_name());
				logContent.append("】的权限，更新后的权限有：");
				logContent.append(privileges.toString());
			}else{
				logInfo.setLog_type(ConstantUtil.LOG_DELETE);
				logContent.append(gRolePrivilegeVo.getUserName());
				logContent.append("取消项目【"+projectName);
				logContent.append("】的团队角色【");
				logContent.append(gRoleVo.getRole_name());
				logContent.append("】的所有权限！");
			}
			logInfo.setContent(logContent.toString());
			logInfoService.save(logInfo);
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public void delete(GRolePrivilege gRolePrivilege){
		gRolePrivilegeDao.delete(gRolePrivilege);
	}
	
	public void deleteByExample(GRolePrivilege gRolePrivilege){
		gRolePrivilegeDao.deleteByExample(gRolePrivilege);
	}

	public void update(GRolePrivilege gRolePrivilege){
		gRolePrivilegeDao.update(gRolePrivilege);
	}
	
	public void updateIgnoreNull(GRolePrivilege gRolePrivilege){
		gRolePrivilegeDao.updateIgnoreNull(gRolePrivilege);
	}
		
	public void updateByExample(GRolePrivilege gRolePrivilege){
		gRolePrivilegeDao.update("updateByExampleGRolePrivilege", gRolePrivilege);
	}

	public GRolePrivilegeVo load(GRolePrivilege gRolePrivilege){
		return (GRolePrivilegeVo)gRolePrivilegeDao.reload(gRolePrivilege);
	}
	
	public GRolePrivilegeVo selectForObject(GRolePrivilege gRolePrivilege){
		return (GRolePrivilegeVo)gRolePrivilegeDao.selectForObject("selectGRolePrivilege",gRolePrivilege);
	}
	
	@SuppressWarnings("unchecked")
	public List<GRolePrivilegeVo> selectForList(GRolePrivilege gRolePrivilege){
		return (List<GRolePrivilegeVo>)gRolePrivilegeDao.selectForList("selectGRolePrivilege",gRolePrivilege);
	}
	
	public Page page(Page page, GRolePrivilege gRolePrivilege) {
		return gRolePrivilegeDao.page(page, gRolePrivilege);
	}

}
