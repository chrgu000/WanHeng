package com.cgwas.companyEvaluation.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.companyEvaluation.entity.CompanyEvaluation;



public interface ICompanyEvaluationDao extends IDaoSupport {
	Page page(Page page, CompanyEvaluation companyEvaluation);
}