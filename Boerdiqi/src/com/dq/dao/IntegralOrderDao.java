package com.dq.dao;

import java.util.Map;

import com.dq.entity.IntegralOrder;

public interface IntegralOrderDao extends BaseDao<IntegralOrder> {
	void updateByIds(Map<String, String[]> map);
}
