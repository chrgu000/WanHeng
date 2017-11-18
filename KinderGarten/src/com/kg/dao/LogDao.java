package com.kg.dao;

import java.util.List;
import java.util.Map;

import com.kg.entity.Log;
import com.kg.page.Page;

public interface LogDao {
	List<Log> getLogByPage(Page page);

	Integer getRows(Page page);

	 List<Log> getLogByTeacherIds(Map<String,String[]> map);
	
	 List<Log> getLogByBabyIds(Map<String,String[]> map);
	 
	Log getLogById(Integer id);

	void updateLog(Log log);

	void deleteByIds(Map<String, String[]> map);

	void addLog(Log log);

	List<Log> getLogsByBabyId(Integer baby_id);

	List<Log> getLogsByBabyId1(Integer babyId);

}
