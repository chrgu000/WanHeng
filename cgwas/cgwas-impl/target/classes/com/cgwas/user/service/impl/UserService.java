package com.cgwas.user.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.user.dao.api.IUserDao;
import com.cgwas.user.entity.AdminUser;
import com.cgwas.user.entity.User;
import com.cgwas.user.entity.UserVo;
import com.cgwas.user.service.api.IUserService;

@Service
public class UserService implements IUserService {
	private IUserDao userDao;

	public Serializable save(User user) {
		return userDao.save(user);
	}

	public void delete(User user) {
		userDao.delete(user);
	}

	public void deleteByExample(User user) {
		userDao.deleteByExample(user);
	}

	public void update(User user) {
		userDao.update(user);
	}

	public void updateIgnoreNull(User user) {
		userDao.updateIgnoreNull(user);
	}

	public void updateByExample(User user) {
		userDao.update("updateByExampleUser", user);
	}

	public UserVo load(User user) {
		return (UserVo) userDao.reload(user);
	}

	public UserVo selectForObject(User user) {
		return (UserVo) userDao.selectForObject("selectUser", user);
	}

	public List<UserVo> selectForList(User user) {
		return (List<UserVo>) userDao.selectForList("selectUser", user);
	}

	public Page page(Page page, User user) {
		return userDao.page(page, user);
	}

	@Autowired
	public void setIUserDao(@Qualifier("userDao") IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public List<Integer> getNumByTel(User user) {
		return (List<Integer>) userDao.selectForList("getNumByTel", user);
	}

	@Override
	public List<User> getUserByAccount(String account) {
		return (List<User>) userDao.selectForList("getUserByAccount", account);
	}

	@Override
	public Serializable updateByLastLoginTime(User user) {
		return userDao.update("updateByLastLoginTime", user);
	}

	@Override
	public Serializable batchDeleteUser(List<Long> userIdList) {
		return userDao.update("batchDeleteUser", userIdList);
	}

	@Override
	public User getUserById(Long id) {
		return (User) userDao.selectForObject("serachUserById", id);
	}

	@Override
	public Serializable updateUserByAccount(User user) {

		return userDao.update("updateUserByAccount", user);
	}

	@Override
	public List<AdminUser> getAdminUserList(AdminUser adminUser, Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("adminUser", adminUser);
		map.put("page", page);
		return (List<AdminUser>) userDao.selectForList("getAdminUserList", map);
	}

	@Override
	public Long getAdminUserListCount(AdminUser adminUser) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("adminUser", adminUser);
		return (Long) userDao.selectForObject("getAdminUserListCount", map);
	}

	@Override
	public User getUserByUUID(String uuid) {
		return (User) userDao.selectForObject("getUserByUUID", uuid);
	}

}
