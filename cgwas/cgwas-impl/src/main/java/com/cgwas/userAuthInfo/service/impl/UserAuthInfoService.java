package com.cgwas.userAuthInfo.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.userAuthInfo.dao.api.IUserAuthInfoDao;
import com.cgwas.userAuthInfo.entity.AttestationUserAuthInfo;
import com.cgwas.userAuthInfo.entity.UserAuthInfo;
import com.cgwas.userAuthInfo.entity.UserAuthInfoVo;
import com.cgwas.userAuthInfo.service.api.IUserAuthInfoService;
import com.cgwas.userRole.dao.api.IUserRoleDao;
import com.cgwas.userRole.entity.UserRole;

@Service
public class UserAuthInfoService implements IUserAuthInfoService {
	private IUserAuthInfoDao userAuthInfoDao;
	@Autowired
	private IUserRoleDao userRoleDao;

	public Serializable save(UserAuthInfo userAuthInfo) {
		return userAuthInfoDao.save(userAuthInfo);
	}

	public void delete(UserAuthInfo userAuthInfo) {
		userAuthInfoDao.delete(userAuthInfo);
	}

	public void deleteByExample(UserAuthInfo userAuthInfo) {
		userAuthInfoDao.deleteByExample(userAuthInfo);
	}

	public void update(UserAuthInfo userAuthInfo) {
		userAuthInfoDao.update(userAuthInfo);
	}

	public void updateIgnoreNull(UserAuthInfo userAuthInfo) {
		this.getUserAuthInfoById(userAuthInfo.getUser_id());
		userAuthInfoDao.updateIgnoreNull(userAuthInfo);
	}

	public void updateByExample(UserAuthInfo userAuthInfo) {
		userAuthInfoDao.update("updateByExampleUserAuthInfo", userAuthInfo);
	}

	public UserAuthInfoVo load(UserAuthInfo userAuthInfo) {
		return (UserAuthInfoVo) userAuthInfoDao.reload(userAuthInfo);
	}

	public UserAuthInfoVo selectForObject(UserAuthInfo userAuthInfo) {
		return (UserAuthInfoVo) userAuthInfoDao.selectForObject(
				"selectUserAuthInfo", userAuthInfo);
	}

	public List<UserAuthInfoVo> selectForList(UserAuthInfo userAuthInfo) {
		return (List<UserAuthInfoVo>) userAuthInfoDao.selectForList(
				"selectUserAuthInfo", userAuthInfo);
	}

	public Page page(Page page, UserAuthInfo userAuthInfo) {
		return userAuthInfoDao.page(page, userAuthInfo);
	}

	@Autowired
	public void setIUserAuthInfoDao(
			@Qualifier("userAuthInfoDao") IUserAuthInfoDao userAuthInfoDao) {
		this.userAuthInfoDao = userAuthInfoDao;
	}

	@Override
	public List<UserAuthInfo> getUserAuthInfoById(Long userId) {
		List<UserAuthInfo> retn = (List<UserAuthInfo>) userAuthInfoDao
				.selectForList("getUserAuthInfoById", userId);
		if (retn.size() == 0) {// 为空则新建一条
			UserAuthInfo authInfo = new UserAuthInfo();
			authInfo.setUser_id(userId);
			authInfo.setStatus("待提交");
			this.save(authInfo);
			retn.add(authInfo);
		}

		return retn;
	}

	@Override
	public Serializable updateUserAuthInfoById(UserAuthInfo authInfo) {
		String retn = this.getUserAuthInfoStatus(authInfo.getUser_id());
		if (retn != null) {
			return userAuthInfoDao.update("updateUserAuthInfoById", authInfo);

		} else {
			return userAuthInfoDao.save(authInfo);
		}

	}

	@Override
	public String getUserAuthInfoStatus(Long userId) {
		String retn = (String) userAuthInfoDao.selectForObject(
				"getUserAuthInfoStatus", userId);
		return retn;
	}

	@Override
	public Serializable updateUserAuthInfoStatus(Long userId, Boolean status) {
		UserAuthInfo authInfo = new UserAuthInfo();
		authInfo.setUser_id(userId);
		if (status) {
			authInfo.setStatus("已认证");
			// 成为普通会员
			UserRole entity = new UserRole();
			entity.setUser_id(userId);
			entity.setRole_id(3L);
			userRoleDao.delete("deleteByExampleUserRole",entity);// 删除原有
			userRoleDao.save(entity);

		} else {
			authInfo.setStatus("不通过");
			// 发送不通过信息
			
		}
		return userAuthInfoDao.update("updateUserAuthInfoStatus", authInfo);
	}

	@Override
	public List<AttestationUserAuthInfo> getUserAuthInfoList(
			AttestationUserAuthInfo attestationUserAuthInfo, Page page) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("attestationUserAuthInfo", attestationUserAuthInfo);
		map.put("page", page);
		return (List<AttestationUserAuthInfo>) userAuthInfoDao.selectForList(
				"getUserAuthInfoList", map);
	}

	@Override
	public Long getUserAuthInfoListCount(
			AttestationUserAuthInfo attestationUserAuthInfo) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("attestationUserAuthInfo", attestationUserAuthInfo);
		return (Long) userAuthInfoDao.selectForObject(
				"getUserAuthInfoListCount", map);
	}

	@Override
	public UserAuthInfo getUserAuthInfoByUserId(Long user_id) {
		return (UserAuthInfo) userAuthInfoDao.selectForObject(
				"getUserAuthInfoByUserId", user_id);
	}

	@Override
	public Long getHaveIdCard(String idcard) {
		return  (Long) userAuthInfoDao.selectForObject(
				"getHaveIdCard", idcard);
	}

}
