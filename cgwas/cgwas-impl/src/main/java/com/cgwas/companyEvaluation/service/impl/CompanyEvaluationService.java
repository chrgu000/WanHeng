package com.cgwas.companyEvaluation.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.companyEvaluation.dao.api.ICompanyEvaluationDao;
import com.cgwas.companyEvaluation.entity.CompanyEvaluation;
import com.cgwas.companyEvaluation.entity.CompanyEvaluationVo;
import com.cgwas.companyEvaluation.service.api.ICompanyEvaluationService;

@Service
public class CompanyEvaluationService implements ICompanyEvaluationService {
	private ICompanyEvaluationDao companyEvaluationDao;

	public Serializable save(CompanyEvaluation companyEvaluation) {
		return companyEvaluationDao.save(companyEvaluation);
	}

	public void delete(CompanyEvaluation companyEvaluation) {
		companyEvaluationDao.delete(companyEvaluation);
	}

	public void deleteByExample(CompanyEvaluation companyEvaluation) {
		companyEvaluationDao.deleteByExample(companyEvaluation);
	}

	public void update(CompanyEvaluation companyEvaluation) {
		companyEvaluationDao.update(companyEvaluation);
	}

	public void updateIgnoreNull(CompanyEvaluation companyEvaluation) {
		companyEvaluationDao.updateIgnoreNull(companyEvaluation);
	}

	public void updateByExample(CompanyEvaluation companyEvaluation) {
		companyEvaluationDao.update("updateByExampleCompanyEvaluation",
				companyEvaluation);
	}

	public CompanyEvaluationVo load(CompanyEvaluation companyEvaluation) {
		return (CompanyEvaluationVo) companyEvaluationDao
				.reload(companyEvaluation);
	}

	public CompanyEvaluationVo selectForObject(
			CompanyEvaluation companyEvaluation) {
		return (CompanyEvaluationVo) companyEvaluationDao.selectForObject(
				"selectCompanyEvaluation", companyEvaluation);
	}

	public List<CompanyEvaluationVo> selectForList(
			CompanyEvaluation companyEvaluation) {
		return (List<CompanyEvaluationVo>) companyEvaluationDao.selectForList(
				"selectCompanyEvaluation", companyEvaluation);
	}

	public Page page(Page page, CompanyEvaluation companyEvaluation) {
		return companyEvaluationDao.page(page, companyEvaluation);
	}

	@Autowired
	public void setICompanyEvaluationDao(
			@Qualifier("companyEvaluationDao") ICompanyEvaluationDao companyEvaluationDao) {
		this.companyEvaluationDao = companyEvaluationDao;
	}

	@Override
	public Long getCompanyEvaluationVListCount(
			CompanyEvaluation companyEvaluation) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sEvaluation", companyEvaluation);
		return (Long) companyEvaluationDao.selectForObject(
				"getCompanyEvaluationVListCount", map);
	}

	@Override
	public List<CompanyEvaluationVo> getUserEvaluationVList(
			CompanyEvaluation companyEvaluation, Page page, String allFlag) {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("sEvaluation", companyEvaluation);
		map.put("page", page);

		if ("any".equals(allFlag)) {
			map.put("flag", "any");
		} else {
			map.put("flag", "other");
		}

		return (List<CompanyEvaluationVo>) companyEvaluationDao.selectForList(
				"getCompanyEvaluationVList", map);
	}

	@Override
	public Long getGCBEvaluationCountCompany(Long company_id, Long evaluate_type) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("company_id", company_id);
		map.put("evaluate_type", evaluate_type);
		return (Long) companyEvaluationDao.selectForObject(
				"getGCBEvaluationCountCompany", map);
	}

}
