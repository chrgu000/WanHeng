package com.cgwas.arbitrateUserInfo.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.arbitrateUserInfo.dao.api.IArbitrateUserInfoDao;
import com.cgwas.arbitrateUserInfo.entity.ArbitrateUserInfo;
import com.cgwas.arbitrateUserInfo.entity.ArbitrateUserInfoVo;
import com.cgwas.arbitrateUserInfo.entity.CompanyArbitrateInfo;
import com.cgwas.arbitrateUserInfo.entity.UserArbitrateInfo;
import com.cgwas.arbitrateUserInfo.service.api.IArbitrateUserInfoService;
import com.cgwas.common.dataaccess.api.Page;

@Service
public class ArbitrateUserInfoService implements IArbitrateUserInfoService {
	private IArbitrateUserInfoDao arbitrateUserInfoDao;

	public Serializable save(ArbitrateUserInfo arbitrateUserInfo) {
		return arbitrateUserInfoDao.save(arbitrateUserInfo);
	}

	public void delete(ArbitrateUserInfo arbitrateUserInfo) {
		arbitrateUserInfoDao.delete(arbitrateUserInfo);
	}

	public void deleteByExample(ArbitrateUserInfo arbitrateUserInfo) {
		arbitrateUserInfoDao.deleteByExample(arbitrateUserInfo);
	}

	public void update(ArbitrateUserInfo arbitrateUserInfo) {
		arbitrateUserInfoDao.update(arbitrateUserInfo);
	}

	public void updateIgnoreNull(ArbitrateUserInfo arbitrateUserInfo) {
		arbitrateUserInfoDao.updateIgnoreNull(arbitrateUserInfo);
	}

	public void updateByExample(ArbitrateUserInfo arbitrateUserInfo) {
		arbitrateUserInfoDao.update("updateByExampleArbitrateUserInfo",
				arbitrateUserInfo);
	}
	@Override
	public void updateIsPass(ArbitrateUserInfo arbitrateUserInfo) {
		arbitrateUserInfoDao.update("updateArbitrateUserInfoIsPass",
				arbitrateUserInfo);
	}

	public ArbitrateUserInfoVo load(ArbitrateUserInfo arbitrateUserInfo) {
		return (ArbitrateUserInfoVo) arbitrateUserInfoDao
				.reload(arbitrateUserInfo);
	}

	public ArbitrateUserInfoVo selectForObject(
			ArbitrateUserInfo arbitrateUserInfo) {
		return (ArbitrateUserInfoVo) arbitrateUserInfoDao.selectForObject(
				"selectArbitrateUserInfo", arbitrateUserInfo);
	}

	public List<ArbitrateUserInfoVo> selectForList(
			ArbitrateUserInfo arbitrateUserInfo) {
		return (List<ArbitrateUserInfoVo>) arbitrateUserInfoDao.selectForList(
				"selectArbitrateUserInfo", arbitrateUserInfo);
	}

	public Page page(Page page, ArbitrateUserInfo arbitrateUserInfo) {
		return arbitrateUserInfoDao.page(page, arbitrateUserInfo);
	}

	@Autowired
	public void setIArbitrateUserInfoDao(
			@Qualifier("arbitrateUserInfoDao") IArbitrateUserInfoDao arbitrateUserInfoDao) {
		this.arbitrateUserInfoDao = arbitrateUserInfoDao;
	}

	@Override
	public List<UserArbitrateInfo> getUserArbitrateInfo(
			UserArbitrateInfo userArbitrateInfo) {
		List<UserArbitrateInfo> list = (List<UserArbitrateInfo>) arbitrateUserInfoDao
				.selectForList("getUserArbitrateInfo", userArbitrateInfo);
		return list;
		
	}

	@Override
	public List<CompanyArbitrateInfo> getCompanyArbitrateInfo(
			CompanyArbitrateInfo arbitrateInfo) {
		List<CompanyArbitrateInfo> list = (List<CompanyArbitrateInfo>) arbitrateUserInfoDao
				.selectForList("getCompanyArbitrateInfo", arbitrateInfo);
		
		return list;
		
	}

}
