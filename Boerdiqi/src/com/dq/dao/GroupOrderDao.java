package com.dq.dao;

import java.util.List;
import java.util.Map;

import com.dq.entity.GroupOrder;
import com.dq.entity.Orders;

public interface GroupOrderDao extends BaseDao<GroupOrder> {
	void updateByIds(Map<String, String[]> map);
	
	List<Orders> getOrdersByGroupOrderId(Integer group_order_id);
}
