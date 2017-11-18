package com.dq.dao;

import java.util.List;
import java.util.Map;

public interface BaseDao1<T> {
	Long getTotal(Map<String, Object> map);

	List<T> getByPage(Map<String, Object> map);

	int add(T t);

	int upd(T t);

	int del(Long id);

	List<T> getByMap(Map<String, Object> map);

	T findById(Integer id);

	T findByMap(Map<String, Object> map);

}
