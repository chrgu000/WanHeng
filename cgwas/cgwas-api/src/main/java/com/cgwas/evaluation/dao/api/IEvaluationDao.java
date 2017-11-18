package com.cgwas.evaluation.dao.api;

import com.cgwas.common.dataaccess.api.IDaoSupport;
import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.evaluation.entity.Evaluation;

public interface IEvaluationDao extends IDaoSupport {
	Page page(Page page, Evaluation evaluation);
}