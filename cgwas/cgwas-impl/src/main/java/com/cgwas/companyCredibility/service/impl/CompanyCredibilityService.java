package com.cgwas.companyCredibility.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.companyCredibility.dao.api.ICompanyCredibilityDao;
import com.cgwas.companyCredibility.entity.CompanyCredibility;
import com.cgwas.companyCredibility.entity.CompanyCredibilityVo;
import com.cgwas.companyCredibility.service.api.ICompanyCredibilityService;

@Service
public class CompanyCredibilityService implements ICompanyCredibilityService {
	private ICompanyCredibilityDao companyCredibilityDao;

	public Serializable save(CompanyCredibility companyCredibility) {
		return companyCredibilityDao.save(companyCredibility);
	}

	public void delete(CompanyCredibility companyCredibility) {
		companyCredibilityDao.delete(companyCredibility);
	}

	public void deleteByExample(CompanyCredibility companyCredibility) {
		companyCredibilityDao.deleteByExample(companyCredibility);
	}

	public void update(CompanyCredibility companyCredibility) {
		companyCredibilityDao.update(companyCredibility);
	}

	public void updateIgnoreNull(CompanyCredibility companyCredibility) {
		companyCredibilityDao.updateIgnoreNull(companyCredibility);
	}

	public void updateByExample(CompanyCredibility companyCredibility) {
		companyCredibilityDao.update("updateByExampleCompanyCredibility",
				companyCredibility);
	}

	public CompanyCredibilityVo load(CompanyCredibility companyCredibility) {
		return (CompanyCredibilityVo) companyCredibilityDao
				.reload(companyCredibility);
	}

	public CompanyCredibilityVo selectForObject(
			CompanyCredibility companyCredibility) {
		return (CompanyCredibilityVo) companyCredibilityDao.selectForObject(
				"selectCompanyCredibility", companyCredibility);
	}

	public List<CompanyCredibilityVo> selectForList(
			CompanyCredibility companyCredibility) {
		return (List<CompanyCredibilityVo>) companyCredibilityDao
				.selectForList("selectCompanyCredibility", companyCredibility);
	}

	public Page page(Page page, CompanyCredibility companyCredibility) {
		return companyCredibilityDao.page(page, companyCredibility);
	}

	@Autowired
	public void setICompanyCredibilityDao(
			@Qualifier("companyCredibilityDao") ICompanyCredibilityDao companyCredibilityDao) {
		this.companyCredibilityDao = companyCredibilityDao;
	}

	@Override
	public CompanyCredibility GetCompanyCredibilityByCompanyId(Long company_id) {

		CompanyCredibility out = (CompanyCredibility) companyCredibilityDao
				.selectForObject("GetCompanyCredibilityByCompanyId", company_id);
		if (out == null) {
			out = new CompanyCredibility();
			out.setCedibility("0");
			out.setCompany_id(company_id);
			out.setGood_at("");
			out.setCreat_time(new Date());
			out.setUpdate_tiems(0l);
			companyCredibilityDao.save(out);
		}
		return out;
	}

}
