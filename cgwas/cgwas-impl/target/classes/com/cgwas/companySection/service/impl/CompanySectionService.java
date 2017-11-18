package com.cgwas.companySection.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.companySection.dao.api.ICompanySectionDao;
import com.cgwas.companySection.entity.CompanySection;
import com.cgwas.companySection.entity.CompanySectionVo;
import com.cgwas.companySection.service.api.ICompanySectionService;

/**
 * 
 * @author yubangqiong
 * 
 */
@Service
public class CompanySectionService implements ICompanySectionService {
	private ICompanySectionDao companySectionDao;

	public Serializable save(CompanySection companySection) {
		return companySectionDao.save(companySection);
	}

	public void delete(CompanySection companySection) {
		companySectionDao.delete(companySection);
	}

	public void deleteByExample(CompanySection companySection) {
		companySectionDao.deleteByExample(companySection);
	}

	public void update(CompanySection companySection) {
		companySectionDao.update(companySection);
	}

	public void updateIgnoreNull(CompanySection companySection) {
		companySectionDao.updateIgnoreNull(companySection);
	}

	public void updateByExample(CompanySection companySection) {
		companySectionDao.update("updateByExampleCompanySection",
				companySection);
	}

	public CompanySectionVo load(CompanySection companySection) {
		return (CompanySectionVo) companySectionDao.reload(companySection);
	}

	public CompanySectionVo selectForObject(CompanySection companySection) {
		return (CompanySectionVo) companySectionDao.selectForObject(
				"selectCompanySection", companySection);
	}

	public List<CompanySectionVo> selectForList(CompanySection companySection) {
		return (List<CompanySectionVo>) companySectionDao.selectForList(
				"selectCompanySection", companySection);
	}

	public Page page(Page page, CompanySection companySection) {
		return companySectionDao.page(page, companySection);
	}

	@Autowired
	public void setICompanySectionDao(
			@Qualifier("companySectionDao") ICompanySectionDao companySectionDao) {
		this.companySectionDao = companySectionDao;
	}
	@Override
	public List<CompanySection> getcompanySectionList(Page page,
			CompanySection companySection,String allFlag) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("companySection", companySection);
		map.put("page", page);
		if("all".equals(allFlag)){
			map.put("flag", "all");
		}else{
			map.put("flag", "page");
		}
		
		return (List<CompanySection>) companySectionDao.selectForList(
				"getcompanySectionList", map);
	}

	@Override
	public Long getcompanySectionListCount(CompanySection companySection) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("companySection", companySection);
		return (Long) companySectionDao.selectForObject(
				"getcompanySectionListCount", map);
	}

	@Override
	public void batchDeleteCompanySection(List<Long> ids, String is_delete,Long company_id) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("is_delete", is_delete);
		map.put("list", ids);
		map.put("company_id", company_id);
		
		companySectionDao.update("batchDeleteCompanySection", map);
	}

	@Override
	public void updateCompanySectionByInfo(CompanySection companySection) {
		companySectionDao.update("updateCompanySectionByInfo", companySection);
	}

}
