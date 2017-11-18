package com.cgwas.userEvaluation.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.userEvaluation.entity.UserEvaluation;

public interface IUserEvaluationDao extends IDaoSupport {
	Page page(Page page, UserEvaluation userEvaluation);
}