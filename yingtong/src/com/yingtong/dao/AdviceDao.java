package com.yingtong.dao;

import java.util.List;

import com.yingtong.entity.Advice;
import com.yingtong.page.AdvicePage;

public interface AdviceDao {
	boolean addAdvice(Advice advice);
	
	List<Advice> findAdviceByPage(AdvicePage page);
	
	Integer findRows(AdvicePage page);
	
	boolean deleteAdvice(Integer id);
}
