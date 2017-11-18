package com.dq.dao;

import java.util.Map;

import com.dq.entity.IntegralProduct;

public interface IntegralProductDao extends BaseDao<IntegralProduct>{
	void updateByIds(Map<String, String[]> map);
}
