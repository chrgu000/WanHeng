package com.cgwas.userCompany.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.userCompany.dao.api.IUserCompanyDao;
import com.cgwas.userCompany.entity.UserCompany;
import com.cgwas.userCompany.entity.UserCompanyVo;
import com.cgwas.userCompany.service.api.IUserCompanyService;

@Service
public class UserCompanyService implements IUserCompanyService {
	private IUserCompanyDao userCompanyDao;


	public Serializable save(UserCompany userCompany) {
		return userCompanyDao.save(userCompany);
	}

	public void delete(UserCompany userCompany) {
		userCompanyDao.delete(userCompany);
	}

	public void deleteByExample(UserCompany userCompany) {
		userCompanyDao.deleteByExample(userCompany);
	}

	public void update(UserCompany userCompany) {
		userCompanyDao.update(userCompany);
	}

	public void updateIgnoreNull(UserCompany userCompany) {
		userCompanyDao.updateIgnoreNull(userCompany);
	}

	public void updateByExample(UserCompany userCompany) {
		userCompanyDao.update("updateByExampleUserCompany", userCompany);
	}

	public UserCompanyVo load(UserCompany userCompany) {
		return (UserCompanyVo) userCompanyDao.reload(userCompany);
	}

	public UserCompanyVo selectForObject(UserCompany userCompany) {
		return (UserCompanyVo) userCompanyDao.selectForObject(
				"selectUserCompany", userCompany);
	}

	public List<UserCompanyVo> selectForList(UserCompany userCompany) {
		return (List<UserCompanyVo>) userCompanyDao.selectForList(
				"selectUserCompany", userCompany);
	}

	public Page page(Page page, UserCompany userCompany) {
		return userCompanyDao.page(page, userCompany);
	}

	@Autowired
	public void setIUserCompanyDao(
			@Qualifier("userCompanyDao") IUserCompanyDao userCompanyDao) {
		this.userCompanyDao = userCompanyDao;
	}

	@Override
	public UserCompany companygetUser(Long company_id) {
		return (UserCompany) userCompanyDao.selectForObject("companygetUser",
				company_id);
	}

	@Override
	public List<UserCompany> getUserHaveCompany(Long user_id, Long start,
			Long end, UserCompany userCompany, String sortType,
			String sortColumn) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("use_id", user_id);
		map.put("start", start);
		map.put("end", end);
		map.put("sConpany", userCompany);
		map.put("sortColumn", sortColumn);
		map.put("sortType", sortType);
		return (List<UserCompany>) userCompanyDao.selectForList(
				"getUserHaveCompany", map);
	}

	@Override
	public List<UserCompany> getUserJoinCompany(Long user_id, Long start,
			Long end, UserCompany userCompany, String sortType,
			String sortColumn) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("use_id", user_id);
		map.put("start", start);
		map.put("end", end);
		map.put("sConpany", userCompany);
		map.put("sortColumn", sortColumn);
		map.put("sortType", sortType);
		return (List<UserCompany>) userCompanyDao.selectForList(
				"getUserJoinCompany", map);
	}

	@Override
	public Long getUserJoinCompanyCount(Long user_id, UserCompany userCompany) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("use_id", user_id);
		map.put("sConpany", userCompany);
		return (Long) userCompanyDao.selectForObject("getUserJoinCompanyCount",
				map);
	}

	@Override
	public Long getUserHaveCompanyCount(Long user_id, UserCompany userCompany) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sConpany", userCompany);
		map.put("use_id", user_id);

		return (Long) userCompanyDao.selectForObject("getUserHaveCompanyCount",
				map);
	}

	@Override
	public List<UserCompany> getCompanyProjectNum(List<Long> ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", ids);

		return (List<UserCompany>) userCompanyDao.selectForList(
				"getCompanyProjectNum", map);
	}

	@Override
	public List<UserCompany> getAdminCompany(Long start, Long end,
			UserCompany userCompany, String sortType, String sortColumn) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("start", start);
		map.put("end", end);
		map.put("sConpany", userCompany);
		map.put("sortColumn", sortColumn);
		map.put("sortType", sortType);
		return (List<UserCompany>) userCompanyDao.selectForList(
				"getAdminCompany", map);
	}

	@Override
	public Long getAdminCompanyCount(UserCompany userCompany) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sConpany", userCompany);
		return (Long) userCompanyDao.selectForObject("getAdminCompanyCount",
				map);
	}

	@Override
	public String getCompanyUserUUID(Long company_id) {
		return (String) userCompanyDao.selectForObject("getCompanyUserUUID",
				company_id);
	}

	@Override
	public List<UserCompany> getCompanyEmpNum(List<Long> ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", ids);
		return (List<UserCompany>) userCompanyDao.selectForList(
				"getCompanyEmpNum", map);
	}

	@Override
	public List<UserCompany> getCompanyPositionNum(List<Long> ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", ids);
		return (List<UserCompany>) userCompanyDao.selectForList(
				"getCompanyPositionNum", map);
	}

	@Override
	public List<UserCompany> getCompanySectionNum(List<Long> ids) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", ids);
		return (List<UserCompany>) userCompanyDao.selectForList(
				"getCompanySectionNum", map);
	}

}
