package com.cgwas.rolePrivilege.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.common.utils.ConstantUtil;
import com.cgwas.companyPosition.entity.CompanyPositionVo;
import com.cgwas.companyPosition.service.api.ICompanyPositionService;
import com.cgwas.logInfo.entity.LogInfo;
import com.cgwas.logInfo.service.api.ILogInfoService;
import com.cgwas.privilegeInfo.entity.PrivilegeInfoVo;
import com.cgwas.privilegeInfo.service.api.IPrivilegeInfoService;
import com.cgwas.role.entity.RoleVo;
import com.cgwas.role.service.api.IRoleService;
import com.cgwas.rolePrivilege.dao.api.IRolePrivilegeDao;
import com.cgwas.rolePrivilege.entity.RolePrivilege;
import com.cgwas.rolePrivilege.entity.RolePrivilegeVo;
import com.cgwas.rolePrivilege.service.api.IRolePrivilegeService;
/**
 * 
 * @author yubangqiong
 *
 */
@Service
public class RolePrivilegeService implements IRolePrivilegeService {
	@Autowired
	private IRolePrivilegeDao rolePrivilegeDao;
	@Autowired
	private ILogInfoService logInfoService;
	@Autowired
	private IPrivilegeInfoService privilegeInfoService;
	@Autowired
	private IRoleService roleService;
	@Autowired
	private ICompanyPositionService companyPositionService;
	
	@Transactional
	public Boolean save(RolePrivilegeVo rolePrivilegeVo){
		RolePrivilegeVo obj= null;
		boolean flag=false;
		StringBuffer privileges= new StringBuffer();
		StringBuffer logContent= new StringBuffer();
		PrivilegeInfoVo privilegeInfo =null;
		try {
			/**
			 * 先删除相关用户权限关系记录，然后添加
			 */
			rolePrivilegeDao.delete(rolePrivilegeVo);
			/**
			 * 循环添加用户权限
			 */
			for (long privilege_id : rolePrivilegeVo.getIds()) {
				privilegeInfo= new PrivilegeInfoVo();
				privilegeInfo.setId(privilege_id);
				privilegeInfo=privilegeInfoService.selectForObject(privilegeInfo);
				privileges.append("【"+privilegeInfo.getPrivilege_name()+"】,");
				obj= new RolePrivilegeVo();
				obj.setRole_id(rolePrivilegeVo.getRole_id());
				obj.setPrivilege_id(privilege_id);
				obj.setRole_type(rolePrivilegeVo.getRole_type());
				rolePrivilegeDao.save(obj);
			}
			/**
			 * 执行操作日志
			 */
			LogInfo logInfo= new LogInfo();
			logInfo.setTable_name("s_role_privilege");
			logInfo.setUser_id(rolePrivilegeVo.getUserId());
			logInfo.setUser_name(rolePrivilegeVo.getUserName());
			if(rolePrivilegeVo.getRole_type().equals(ConstantUtil.POSITION_TYPE)){
				/**
				 * 查询更新权限的角色
				 */
				CompanyPositionVo companyPositionVo= new CompanyPositionVo();
				companyPositionVo.setId(rolePrivilegeVo.getRole_id());
				companyPositionVo= companyPositionService.selectForObject(companyPositionVo);
				if(rolePrivilegeVo.getIds().length>0){
					logInfo.setLog_type(ConstantUtil.LOG_SAVE);
					logContent.append(rolePrivilegeVo.getUserName());
					logContent.append("更新职称【");
					logContent.append(companyPositionVo.getPosition_name());
					logContent.append("】的权限，更新后的权限有：");
					logContent.append(privileges.toString());
				}else{
					logInfo.setLog_type(ConstantUtil.LOG_DELETE);
					logContent.append(rolePrivilegeVo.getUserName());
					logContent.append("取消职称【");
					logContent.append(companyPositionVo.getPosition_name());
					logContent.append("】的所有权限！");
				}
			}else if(rolePrivilegeVo.getRole_type().equals(ConstantUtil.ROLE_TYPE)){
				/**
				 * 查询更新权限的角色
				 */
				RoleVo roleVo= new RoleVo();
				roleVo.setId(rolePrivilegeVo.getRole_id());
				roleVo= roleService.selectForObject(roleVo);
				/**
				 * 执行操作日志
				 */
				logInfo.setTable_name("p_company_files_user");
				logInfo.setUser_id(rolePrivilegeVo.getUserId());
				logInfo.setUser_name(rolePrivilegeVo.getUserName());
				if(rolePrivilegeVo.getIds().length>0){
					logInfo.setLog_type(ConstantUtil.LOG_SAVE);
					logContent.append(rolePrivilegeVo.getUserName());
					logContent.append("更新角色【");
					logContent.append(roleVo.getRole_name());
					logContent.append("】的权限，更新后的权限有：");
					logContent.append(privileges.toString());
				}else{
					logInfo.setLog_type(ConstantUtil.LOG_DELETE);
					logContent.append(rolePrivilegeVo.getUserName());
					logContent.append("】的团队角色【");
					logContent.append(roleVo.getRole_name());
					logContent.append("】的所有权限！");
				}
			}
			logInfo.setContent(logContent.toString());
			logInfoService.save(logInfo);
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public void delete(RolePrivilege rolePrivilege){
		rolePrivilegeDao.delete(rolePrivilege);
	}
	
	public void deleteByExample(RolePrivilege rolePrivilege){
		rolePrivilegeDao.deleteByExample(rolePrivilege);
	}

	public void update(RolePrivilege rolePrivilege){
		rolePrivilegeDao.update(rolePrivilege);
	}
	
	@Transactional
	public void updateRolePrivilege(RolePrivilegeVo rolePrivilegeVo){
		try {
			//先删除指定权限
			rolePrivilegeDao.deleteByExample(rolePrivilegeVo);
			for (Long pid : rolePrivilegeVo.getPidList()) {
				rolePrivilegeVo.setId(null);
				rolePrivilegeVo.setPrivilege_id(pid);
//				obj.setCompany_file_id(rolePrivilegeVo.getCompany_file_id());
//				obj.setRole_id(rolePrivilegeVo.getRole_id());
//				obj.setRole_type(rolePrivilegeVo.getRole_type());
				rolePrivilegeDao.save(rolePrivilegeVo);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void updateIgnoreNull(RolePrivilege rolePrivilege){
		rolePrivilegeDao.updateIgnoreNull(rolePrivilege);
	}
		
	public void updateByExample(RolePrivilege rolePrivilege){
		rolePrivilegeDao.update("updateByExampleRolePrivilege", rolePrivilege);
	}

	public RolePrivilegeVo load(RolePrivilege rolePrivilege){
		return (RolePrivilegeVo)rolePrivilegeDao.reload(rolePrivilege);
	}
	
	public RolePrivilegeVo selectForObject(RolePrivilege rolePrivilege){
		return (RolePrivilegeVo)rolePrivilegeDao.selectForObject("selectRolePrivilege",rolePrivilege);
	}
	
	@SuppressWarnings("unchecked")
	public List<RolePrivilegeVo> selectForList(RolePrivilege rolePrivilege){
		return (List<RolePrivilegeVo>)rolePrivilegeDao.selectForList("selectRolePrivilege",rolePrivilege);
	}
	
	public Page page(Page page, RolePrivilege rolePrivilege) {
		return rolePrivilegeDao.page(page, rolePrivilege);
	}

}
