package com.jxc.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jxc.dao.EvaluateDao;
import com.jxc.entity.Evaluate;
import com.jxc.page.EvaluatePage;
import com.jxc.service.EvaluateService;
@Service("evaluateService")
@Transactional
public class EvaluateServiceImpl implements EvaluateService{
@Resource
private EvaluateDao dao;
	public boolean addEvaluate(Evaluate evaluate) {
		  
		return dao.addEvaluate(evaluate);
	}

	public List<Evaluate> findEvaluateByPage(EvaluatePage page) {
		 
		return dao.findEvaluateByPage(page);
	}

	public Integer findRows(EvaluatePage page) {
		 
		return dao.findRows(page);
	}

	public boolean deleteEvaluate(Integer id) {
		 
		return dao.deleteEvaluate(id);
	}

}
