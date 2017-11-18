package com.kg.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.kg.entity.Log;
import com.kg.page.Page;

public interface LogService {
	List<Log> getLogByPage(Page page);

	Integer getRows(Page page);
	
	void updateLog(Log log);
	
	 Log getLogById(Integer id);
	
	 void updateLog(Log log,HttpServletResponse response,String flag) throws Exception;

	void deleteLog(String ids, HttpServletResponse response,String flag)
			throws Exception;

	List<Log> getLogsByBabyId(Integer babyId);

	void updateLog(Log log, HttpServletResponse response)throws Exception;

	void addLog(Log log, HttpServletResponse response)throws Exception;

	List<Log> getLogsByBabyId1(Integer babyId);
}
