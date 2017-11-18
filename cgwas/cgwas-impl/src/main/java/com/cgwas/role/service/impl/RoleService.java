package com.cgwas.role.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.menuRole.dao.api.IMenuRoleDao;
import com.cgwas.menuRole.entity.MenuRole;
import com.cgwas.role.dao.api.IRoleDao;
import com.cgwas.role.entity.Role;
import com.cgwas.role.entity.RoleVo;
import com.cgwas.role.service.api.IRoleService;
import com.cgwas.rolePrivilege.dao.api.IRolePrivilegeDao;
import com.cgwas.rolePrivilege.entity.RolePrivilege;
import com.cgwas.userRole.dao.api.IUserRoleDao;
import com.cgwas.userRole.entity.UserRole;
@Service
public class RoleService implements IRoleService {
	private IRoleDao roleDao;
	@Autowired
	private IRolePrivilegeDao rolePrivilegeDao;
	@Autowired
	private IUserRoleDao userRoleDao;
	@Autowired
	private IMenuRoleDao menuRoleDao;

	public Serializable save(Role role){
		return roleDao.save(role);
	}

	public void delete(Role role){
		roleDao.delete(role);
	}
	
	@Transactional
	public void deleteAll(Map<String,Object> map,Role role){
		try {
			/**
			 * 删除角色相关数据信息
			 */
			rolePrivilegeDao.deleteAll(map, new RolePrivilege());
			userRoleDao.deleteAll(map, new UserRole());
			menuRoleDao.deleteAll(map, new MenuRole());
			/**
			 * 删除角色数据信息
			 */
			roleDao.deleteAll(map,role);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void deleteByExample(Role role){
		roleDao.deleteByExample(role);
	}

	public void update(Role role){
		roleDao.update(role);
	}
	
	public void updateIgnoreNull(Role role){
		roleDao.updateIgnoreNull(role);
	}
		
	public void updateByExample(Role role){
		roleDao.update("updateByExampleRole", role);
	}

	public RoleVo load(Role role){
		return (RoleVo)roleDao.reload(role);
	}
	
	public RoleVo selectForObject(Role role){
		return (RoleVo)roleDao.selectForObject("selectRole",role);
	}
	
	public RoleVo selectForUserId(Long user_id){
		return (RoleVo)roleDao.selectForObject("selectForUserId",user_id);
	}
	
	@SuppressWarnings("unchecked")
	public List<RoleVo> selectForList(Role role){
		return (List<RoleVo>)roleDao.selectForList("selectRole",role);
	}
	
	public Page page(Page page, Role role) {
		return roleDao.page(page, role);
	}

	@Autowired
	public void setIRoleDao(
			@Qualifier("roleDao") IRoleDao  roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public RoleVo getRoleByUserId(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
