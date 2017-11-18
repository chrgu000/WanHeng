package com.dq.service;

import java.util.List;
import java.util.Map;

public interface BaseService1<T> {

	Long getTotal(Map<String, Object> map);

	List<T> getByPage(Map<String, Object> map);

	List<T> getByMap(Map<String, Object> map);

	T findById(Integer id);

	T findByMap(Map<String, Object> map);
}
