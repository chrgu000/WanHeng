package com.dq.service;

import java.util.List;

import com.dq.entity.GroupOrder;
import com.dq.entity.Orders;

public interface GroupOrderService extends BaseService<GroupOrder>{
	List<Orders> getOrdersByGroupOrderId(Integer group_order_id);
}
