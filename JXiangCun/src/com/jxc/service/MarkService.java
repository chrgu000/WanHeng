package com.jxc.service;

import java.util.List;
import java.util.Map;

import com.jxc.entity.Mark;
import com.jxc.page.Page;

public interface MarkService {
	List<Mark> findAllMark();

	List<Mark> findAllMarkByPage(Page page);

	List<Mark> findMarkByTitleIds(Map<String,List<Integer>> maps);
	
	Integer findRows(Page page);
	
	List<Mark> findMarksByTitleId(Integer title_id);
	
	List<Mark> findFiveMarks();
	
	Mark findMarkById(Integer id);

	boolean addMarkTitle(Map<String,Integer> map);
	
	boolean addMark(Mark Mark);

	boolean updateMark(Mark Mark);

	boolean deleteMarkById(Integer id);
	
	boolean deleteMarkTitle(Integer markId);
}
