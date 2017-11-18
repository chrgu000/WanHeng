package com.dq.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.dq.page.Page;

public interface BaseService <T>{
	List<T> getByPage(Page page);
	 
	 Integer getRows(Page page);
	 
	 T getById(Integer id);
	 
	void update(T t,HttpServletResponse response)throws Exception;
	
	void add(T t,HttpServletResponse response)throws Exception;
	
	void delete(String  ids,HttpServletResponse response)throws Exception;
}
