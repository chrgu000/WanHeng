package com.cgwas.userGroup.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.userGroup.dao.api.IUserGroupDao;
import com.cgwas.userGroup.entity.UserGroup;
import com.cgwas.userGroup.entity.UserGroupVo;
import com.cgwas.userGroup.service.api.IUserGroupService;

@Service
public class UserGroupService implements IUserGroupService {
	private IUserGroupDao userGroupDao;

	public Serializable save(UserGroup userGroup) {
		return userGroupDao.save(userGroup);
	}

	public void delete(UserGroup userGroup) {
		userGroupDao.delete(userGroup);
	}

	public void deleteByExample(UserGroup userGroup) {
		userGroupDao.deleteByExample(userGroup);
	}

	public void update(UserGroup userGroup) {
		userGroupDao.update(userGroup);
	}

	public void updateIgnoreNull(UserGroup userGroup) {
		userGroupDao.updateIgnoreNull(userGroup);
	}

	public void updateByExample(UserGroup userGroup) {
		userGroupDao.update("updateByExampleUserGroup", userGroup);
	}

	public UserGroupVo load(UserGroup userGroup) {
		return (UserGroupVo) userGroupDao.reload(userGroup);
	}

	public UserGroupVo selectForObject(UserGroup userGroup) {
		return (UserGroupVo) userGroupDao.selectForObject("selectUserGroup",
				userGroup);
	}

	public List<UserGroupVo> selectForList(UserGroup userGroup) {
		return (List<UserGroupVo>) userGroupDao.selectForList(
				"selectUserGroup", userGroup);
	}

	public Page page(Page page, UserGroup userGroup) {
		return userGroupDao.page(page, userGroup);
	}

	@Autowired
	public void setIUserGroupDao(
			@Qualifier("userGroupDao") IUserGroupDao userGroupDao) {
		this.userGroupDao = userGroupDao;
	}

	@Override
	public List<UserGroup> getUserGroupList(UserGroup userGroup,Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userGroup", userGroup);
		map.put("page", page);
		return (List<UserGroup>) userGroupDao.selectForList("getUserGroupList",
				map);
	}

	@Override
	public UserGroup getUserGroupById(Long id) {
		return (UserGroup) userGroupDao.selectForObject("getUserGroupById", id);
	}

	@Override
	public void updateUserGroupInfo(UserGroup userGroup) {
		userGroupDao.update("updateUserGroupInfo", userGroup);
	}

	@Override
	public void batchDeleteUserGroup(List<Long> userGroupIdList) {
		userGroupDao.update("batchDeleteUserGroup", userGroupIdList);
	}

	@Override
	public List<UserGroup> getCompanyGroupList(UserGroup userGroup, Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userGroup", userGroup);
		map.put("page", page);
		return (List<UserGroup>) userGroupDao.selectForList(
				"getCompanyGroupList", map);
	}

	@Override
	public Long getUserGroupListCount(UserGroup userGroup) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userGroup", userGroup);
		return (Long) userGroupDao.selectForObject("getUserGroupListCount",
				map);
	}

	@Override
	public Long getCompanyGroupListCount(UserGroup userGroup) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userGroup", userGroup);
		return (Long) userGroupDao.selectForObject("getCompanyGroupListCount",
				map);
	}

}
