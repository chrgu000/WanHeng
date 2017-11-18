package com.cgwas.userCredibility.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.userCredibility.dao.api.IUserCredibilityDao;
import com.cgwas.userCredibility.entity.UserCredibility;
import com.cgwas.userCredibility.entity.UserCredibilityVo;
import com.cgwas.userCredibility.service.api.IUserCredibilityService;

@Service
public class UserCredibilityService implements IUserCredibilityService {
	private IUserCredibilityDao userCredibilityDao;

	public Serializable save(UserCredibility userCredibility) {
		return userCredibilityDao.save(userCredibility);
	}

	public void delete(UserCredibility userCredibility) {
		userCredibilityDao.delete(userCredibility);
	}

	public void deleteByExample(UserCredibility userCredibility) {
		userCredibilityDao.deleteByExample(userCredibility);
	}

	public void update(UserCredibility userCredibility) {
		userCredibilityDao.update(userCredibility);
	}

	public void updateIgnoreNull(UserCredibility userCredibility) {
		userCredibilityDao.updateIgnoreNull(userCredibility);
	}

	public void updateByExample(UserCredibility userCredibility) {
		userCredibilityDao.update("updateByExampleUserCredibility",
				userCredibility);
	}

	public UserCredibilityVo load(UserCredibility userCredibility) {
		return (UserCredibilityVo) userCredibilityDao.reload(userCredibility);
	}

	public UserCredibilityVo selectForObject(UserCredibility userCredibility) {
		return (UserCredibilityVo) userCredibilityDao.selectForObject(
				"selectUserCredibility", userCredibility);
	}

	public List<UserCredibilityVo> selectForList(UserCredibility userCredibility) {
		return (List<UserCredibilityVo>) userCredibilityDao.selectForList(
				"selectUserCredibility", userCredibility);
	}

	public Page page(Page page, UserCredibility userCredibility) {
		return userCredibilityDao.page(page, userCredibility);
	}

	@Autowired
	public void setIUserCredibilityDao(
			@Qualifier("userCredibilityDao") IUserCredibilityDao userCredibilityDao) {
		this.userCredibilityDao = userCredibilityDao;
	}

	@Override
	public UserCredibility selectUserCredibilityByUserId(
			UserCredibility userCredibility) {
		return (UserCredibility) userCredibilityDao.selectForObject(
				"selectUserCredibilityByUserId", userCredibility);
	}

}
