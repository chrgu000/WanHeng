package com.cgwas.userPrivilege.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.userPrivilege.dao.api.IUserPrivilegeDao;
import com.cgwas.userPrivilege.entity.UserPrivilege;
import com.cgwas.userPrivilege.entity.UserPrivilegeVo;
import com.cgwas.userPrivilege.service.api.IUserPrivilegeService;
/**
 * 
 * @author yubangqiong
 *
 */
@Service
public class UserPrivilegeService implements IUserPrivilegeService {
	private IUserPrivilegeDao userPrivilegeDao;

	@Transactional
	public Boolean save(UserPrivilegeVo userPrivilegeVo){
		UserPrivilegeVo obj= null;
		boolean flag=false;
		try {
			/**
			 * 先删除相关用户权限关系记录，然后添加
			 */
			userPrivilegeDao.delete(userPrivilegeVo);
			/**
			 * 循环添加用户权限
			 */
			for (long privilege_id : userPrivilegeVo.getIds()) {
				obj= new UserPrivilegeVo();
				obj.setUser_id(userPrivilegeVo.getUser_id());
				obj.setPrivilege_id(privilege_id);
				userPrivilegeDao.save(obj);
			}
			flag=true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public void delete(UserPrivilege userPrivilege){
		userPrivilegeDao.delete(userPrivilege);
	}
	
	public void deleteAll(UserPrivilegeVo userPrivilegeVo){
		for (long user_id : userPrivilegeVo.getIds()) {
			userPrivilegeVo.setUser_id(user_id);
			userPrivilegeDao.delete(userPrivilegeVo);
		}
	}
	
	public void deleteByExample(UserPrivilege userPrivilege){
		userPrivilegeDao.deleteByExample(userPrivilege);
	}

	public void update(UserPrivilege userPrivilege){
		userPrivilegeDao.update(userPrivilege);
	}
	
	public void updateIgnoreNull(UserPrivilege userPrivilege){
		userPrivilegeDao.updateIgnoreNull(userPrivilege);
	}
		
	public void updateByExample(UserPrivilege userPrivilege){
		userPrivilegeDao.update("updateByExampleUserPrivilege", userPrivilege);
	}

	public UserPrivilegeVo load(UserPrivilege userPrivilege){
		return (UserPrivilegeVo)userPrivilegeDao.reload(userPrivilege);
	}
	
	public UserPrivilegeVo selectForObject(UserPrivilege userPrivilege){
		return (UserPrivilegeVo)userPrivilegeDao.selectForObject("selectUserPrivilege",userPrivilege);
	}
	
	@SuppressWarnings("unchecked")
	public List<UserPrivilegeVo> selectForList(UserPrivilege userPrivilege){
		return (List<UserPrivilegeVo>)userPrivilegeDao.selectForList("selectUserPrivilege",userPrivilege);
	}
	
	public Page page(Page page, UserPrivilege userPrivilege) {
		return userPrivilegeDao.page(page, userPrivilege);
	}

	@Autowired
	public void setIUserPrivilegeDao(
			@Qualifier("userPrivilegeDao") IUserPrivilegeDao  userPrivilegeDao) {
		this.userPrivilegeDao = userPrivilegeDao;
	}

}
