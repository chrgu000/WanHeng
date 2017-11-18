package com.cgwas.forbid.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.forbid.dao.api.IForbidDao;
import com.cgwas.forbid.entity.CompanyForbid;
import com.cgwas.forbid.entity.Forbid;
import com.cgwas.forbid.entity.ForbidCompany;
import com.cgwas.forbid.entity.ForbidUser;
import com.cgwas.forbid.entity.ForbidVo;
import com.cgwas.forbid.entity.UserForbid;
import com.cgwas.forbid.service.api.IForbidService;

@Service
public class ForbidService implements IForbidService {
	private IForbidDao forbidDao;

	public Serializable save(Forbid forbid) {
		return forbidDao.save(forbid);
	}

	public void delete(Forbid forbid) {
		forbidDao.delete(forbid);
	}

	public void deleteByExample(Forbid forbid) {
		forbidDao.deleteByExample(forbid);
	}

	public void update(Forbid forbid) {
		forbidDao.update(forbid);
	}

	public void updateIgnoreNull(Forbid forbid) {
		forbidDao.updateIgnoreNull(forbid);
	}

	public void updateByExample(Forbid forbid) {
		forbidDao.update("updateByExampleForbid", forbid);
	}

	public ForbidVo load(Forbid forbid) {
		return (ForbidVo) forbidDao.reload(forbid);
	}

	public ForbidVo selectForObject(Forbid forbid) {
		return (ForbidVo) forbidDao.selectForObject("selectForbid", forbid);
	}

	public List<ForbidVo> selectForList(Forbid forbid) {
		return (List<ForbidVo>) forbidDao.selectForList("selectForbid", forbid);
	}

	public Page page(Page page, Forbid forbid) {
		return forbidDao.page(page, forbid);
	}

	@Autowired
	public void setIForbidDao(@Qualifier("forbidDao") IForbidDao forbidDao) {
		this.forbidDao = forbidDao;
	}

	@Override
	public List<UserForbid> getUserForbidList(Long userId) {
		return (List<UserForbid>) forbidDao.selectForList("getUserForbidList",
				userId);
	}

	@Override
	public List<ForbidCompany> getCompanyListForForbid(
			ForbidCompany forbidCompany, Page page) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("forbidCompany", forbidCompany);
		map.put("page", page);

		return (List<ForbidCompany>) forbidDao.selectForList(
				"getCompanyListForForbid", map);
	}

	@Override
	public List<ForbidUser> getUserListForForbid(ForbidUser forbidUser,
			Page page) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("forbidUser", forbidUser);
		map.put("page", page);
		return (List<ForbidUser>) forbidDao.selectForList(
				"getUserListForForbid", map);
	}

	@Override
	public Forbid getNewForbid(Forbid forbid) {

		return (Forbid) forbidDao.selectForObject("getNewForbid", forbid);

	}

	@Override
	public List<CompanyForbid> getConpanyForbidList(Long company_id) {
		return (List<CompanyForbid>) forbidDao.selectForList(
				"getConpanyForbidList", company_id);
	}

	@Override
	public Long getCompanyListForForbidCount(ForbidCompany forbidCompany) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("forbidCompany", forbidCompany);
		return (Long) forbidDao.selectForObject("getCompanyListForForbidCount",
				map);
	}

	@Override
	public Long getUserListForForbidCount(ForbidUser forbidUser) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("forbidUser", forbidUser);
		return (Long) forbidDao.selectForObject("getUserListForForbidCount",
				map);
	}

}
