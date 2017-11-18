package com.jxc.dao;

import java.util.List;

import com.jxc.entity.Evaluate;
import com.jxc.page.EvaluatePage;

public interface EvaluateDao {
	boolean addEvaluate(Evaluate evaluate);

	List<Evaluate> findEvaluateByPage(EvaluatePage page);

	Integer findRows(EvaluatePage page);

	boolean deleteEvaluate(Integer id);
}
