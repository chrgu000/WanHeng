package com.cgwas.userRole.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.userRole.dao.api.IUserRoleDao;
import com.cgwas.userRole.entity.UserRole;
import com.cgwas.userRole.entity.UserRoleVo;
import com.cgwas.userRole.service.api.IUserRoleService;
/**
 * 
 * @author yubangqiong
 *
 */
@Service
public class UserRoleService implements IUserRoleService {
	private IUserRoleDao userRoleDao;

	public Serializable save(UserRole userRole){
		return userRoleDao.save(userRole);
	}

	public void delete(UserRole userRole){
		userRoleDao.delete(userRole);
	}
	
	public void deleteAll(Map<String, Object> map, UserRole userRole) {
		userRoleDao.deleteAll(map,userRole);
		
	}
	
	public void deleteByExample(UserRole userRole){
		userRoleDao.deleteByExample(userRole);
	}

	public void update(UserRole userRole){
		userRoleDao.update(userRole);
	}
	
	public void updateIgnoreNull(UserRole userRole){
		userRoleDao.updateIgnoreNull(userRole);
	}
		
	public void updateByExample(UserRole userRole){
		userRoleDao.update("updateByExampleUserRole", userRole);
	}

	public UserRoleVo load(UserRole userRole){
		return (UserRoleVo)userRoleDao.reload(userRole);
	}
	
	public UserRoleVo selectForObject(UserRole userRole){
		return (UserRoleVo)userRoleDao.selectForObject("selectUserRole",userRole);
	}
	
	public List<UserRoleVo> selectForList(UserRole userRole){
		return (List<UserRoleVo>)userRoleDao.selectForList("selectUserRole",userRole);
	}
	
	public Page page(Page page, UserRole userRole) {
		return userRoleDao.page(page, userRole);
	}

	@Autowired
	public void setIUserRoleDao(
			@Qualifier("userRoleDao") IUserRoleDao  userRoleDao) {
		this.userRoleDao = userRoleDao;
	}

}
