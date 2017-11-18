package com.cgwas.companyPosition.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.companyPosition.dao.api.ICompanyPositionDao;
import com.cgwas.companyPosition.entity.CompanyPosition;
import com.cgwas.companyPosition.entity.CompanyPositionVo;
import com.cgwas.companyPosition.service.api.ICompanyPositionService;

/**
 * 
 * @author yubangqiong
 * 
 */
@Service
public class CompanyPositionService implements ICompanyPositionService {
	private ICompanyPositionDao companyPositionDao;

	public Serializable save(CompanyPosition companyPosition) {
		return companyPositionDao.save(companyPosition);
	}

	public void delete(CompanyPosition companyPosition) {
		companyPositionDao.delete(companyPosition);
	}

	public void deleteByExample(CompanyPosition companyPosition) {
		companyPositionDao.deleteByExample(companyPosition);
	}

	public void update(CompanyPosition companyPosition) {
		companyPositionDao.update(companyPosition);
	}

	public void updateIgnoreNull(CompanyPosition companyPosition) {
		companyPositionDao.updateIgnoreNull(companyPosition);
	}

	public void updateByExample(CompanyPosition companyPosition) {
		companyPositionDao.update("updateByExampleCompanyPosition",
				companyPosition);
	}

	public CompanyPositionVo load(CompanyPosition companyPosition) {
		return (CompanyPositionVo) companyPositionDao.reload(companyPosition);
	}

	public CompanyPositionVo selectForObject(CompanyPosition companyPosition) {
		return (CompanyPositionVo) companyPositionDao.selectForObject(
				"selectCompanyPosition", companyPosition);
	}

	public List<CompanyPositionVo> selectForList(CompanyPosition companyPosition) {
		return (List<CompanyPositionVo>) companyPositionDao.selectForList(
				"selectCompanyPosition", companyPosition);
	}

	public Page page(Page page, CompanyPosition companyPosition) {
		return companyPositionDao.page(page, companyPosition);
	}

	@Autowired
	public void setICompanyPositionDao(
			@Qualifier("companyPositionDao") ICompanyPositionDao companyPositionDao) {
		this.companyPositionDao = companyPositionDao;
	}

	@Override
	public Long getCompanyPositionListCount(CompanyPosition companyPosition) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("companyPosition", companyPosition);
		return (Long) companyPositionDao.selectForObject(
				"getCompanyPositionListCount", map);
	}

	@Override
	public void batchDeleteCompanyPosition(List<Long> ids, String is_delete,Long company_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("is_delete", is_delete);
		map.put("list", ids);
		map.put("company_id", company_id);
		
		companyPositionDao.update("batchDeleteCompanyPosition", map);
	}

	@Override
	public void updateCompanyPositionByInfo(CompanyPosition companyPosition) {
		companyPositionDao.update("updateCompanyPositionByInfo",
				companyPosition);

	}

	@Override
	public List<CompanyPosition> getCompanyPositionList(Page page,
			CompanyPosition companyPosition, String allFlag) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("companyPosition", companyPosition);
		map.put("page", page);
		if("all".equals(allFlag)){
			map.put("flag", "all");
		}else{
			map.put("flag", "page");
		}
		
		return (List<CompanyPosition>) companyPositionDao.selectForList(
				"getCompanyPositionList", map);
	}

}
