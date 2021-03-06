package com.dq.dao;

import java.util.List;
import java.util.Map;

import com.dq.page.Page;

public interface BaseDao <T>{
	/**
	 * 添加
	 */
	public void save(T entity);
	
	/**
	 * 根据id删除
	 */
	void deleteByIds(Map<String, String[]> map);
	
	/**
	 * 根据id修改
	 */
	public void update(T entity);
	
	/**
	 * 根据id查询
	 */
	public T getById(Integer id);
 
	/**
	 * 查询所有
	 */
	public List<T> getAllByPage(Page page);
	
	/**
	 * 查询行数
	 */
	public Integer getRows(Page page);
}
