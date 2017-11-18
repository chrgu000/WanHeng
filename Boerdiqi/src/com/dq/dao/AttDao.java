package com.dq.dao;

import java.util.List;
import java.util.Map;

import com.dq.entity.Att;

public interface AttDao extends BaseDao1<Att> {

	List<Att> getByPro(Map<String, Object> map);

	List<Att> getByAtt(Map<String, Object> map);

	int updByTitle(Map<String, Object> map);

	List<Att> getAtt(Map<String, Object> map);

}
