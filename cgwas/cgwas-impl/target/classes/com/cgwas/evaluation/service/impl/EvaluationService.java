package com.cgwas.evaluation.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.evaluation.dao.api.IEvaluationDao;
import com.cgwas.evaluation.entity.Evaluation;
import com.cgwas.evaluation.entity.EvaluationVo;
import com.cgwas.evaluation.service.api.IEvaluationService;

@Service
public class EvaluationService implements IEvaluationService {
	private IEvaluationDao evaluationDao;

	public Serializable save(Evaluation evaluation) {
		return evaluationDao.save(evaluation);
	}

	public void delete(Evaluation evaluation) {
		evaluationDao.delete(evaluation);
	}

	public void deleteByExample(Evaluation evaluation) {
		evaluationDao.deleteByExample(evaluation);
	}

	public void update(Evaluation evaluation) {
		evaluationDao.update(evaluation);
	}

	public void updateIgnoreNull(Evaluation evaluation) {
		evaluationDao.updateIgnoreNull(evaluation);
	}

	public void updateByExample(Evaluation evaluation) {
		evaluationDao.update("updateByExampleEvaluation", evaluation);
	}

	public EvaluationVo load(Evaluation evaluation) {
		return (EvaluationVo) evaluationDao.reload(evaluation);
	}

	public EvaluationVo selectForObject(Evaluation evaluation) {
		return (EvaluationVo) evaluationDao.selectForObject("selectEvaluation",
				evaluation);
	}

	public List<EvaluationVo> selectForList(Evaluation evaluation) {
		return (List<EvaluationVo>) evaluationDao.selectForList(
				"selectEvaluation", evaluation);
	}

	public Page page(Page page, Evaluation evaluation) {
		return evaluationDao.page(page, evaluation);
	}

	@Autowired
	public void setIEvaluationDao(
			@Qualifier("evaluationDao") IEvaluationDao evaluationDao) {
		this.evaluationDao = evaluationDao;
	}

}
