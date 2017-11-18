package com.cgwas.userInfo.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.user.entity.User;
import com.cgwas.userInfo.dao.api.IUserInfoDao;
import com.cgwas.userInfo.entity.UserInfo;
import com.cgwas.userInfo.entity.UserInfoVo;
import com.cgwas.userInfo.service.api.IUserInfoService;

@Service
public class UserInfoService implements IUserInfoService {
	private IUserInfoDao userInfoDao;

	public Serializable save(UserInfo userInfo) {
		return userInfoDao.save(userInfo);
	}

	public void delete(UserInfo userInfo) {
		userInfoDao.delete(userInfo);
	}

	public void deleteByExample(UserInfo userInfo) {
		userInfoDao.deleteByExample(userInfo);
	}

	public void update(UserInfo userInfo) {
		userInfoDao.update(userInfo);
	}

	public void updateIgnoreNull(UserInfo userInfo) {
		userInfoDao.updateIgnoreNull(userInfo);
	}

	public void updateByExample(UserInfo userInfo) {
		userInfoDao.update("updateByExampleUserInfo", userInfo);
	}

	public UserInfoVo load(UserInfo userInfo) {
		return (UserInfoVo) userInfoDao.reload(userInfo);
	}

	public UserInfoVo selectForObject(UserInfo userInfo) {
		return (UserInfoVo) userInfoDao.selectForObject("selectUserInfo",
				userInfo);
	}

	public List<UserInfoVo> selectForList(UserInfo userInfo) {
		return (List<UserInfoVo>) userInfoDao.selectForList("selectUserInfo",
				userInfo);
	}

	public Page page(Page page, UserInfo userInfo) {
		return userInfoDao.page(page, userInfo);
	}

	@Autowired
	public void setIUserInfoDao(
			@Qualifier("userInfoDao") IUserInfoDao userInfoDao) {
		this.userInfoDao = userInfoDao;
	}

	@Override
	public List<UserInfo> getUserInfoById(Long userId) {
		List<UserInfo> retn = (List<UserInfo>) userInfoDao.selectForList(
				"getUserInfoById", userId);
		if (retn.size() == 0) {
			UserInfo userInfo = new UserInfo();
			userInfo.setUser_id(userId);
			userInfo.setSex("1");
			this.save(userInfo);
			retn.add(userInfo);
		}

		return retn;
	}

	@Override
	public Serializable updateUserInfoByUserId(UserInfo userInfo) {
		return userInfoDao.update("updateUserInfoByUserId", userInfo);
	}

	@Override
	public String getEmailByAccount(User user) {
		return (String) userInfoDao.selectForObject("getEmailByAccount", user);
	}

	@Override
	public Serializable rechargeMoney(UserInfo userInfo) {
		return userInfoDao.update("rechargeMoney", userInfo);

	}

	@Override
	public List<UserInfo> getUserHeardPicByName(String name) {

		return (List<UserInfo>) userInfoDao.selectForList(
				"getUserHeardPicByName", name);
	}

}
