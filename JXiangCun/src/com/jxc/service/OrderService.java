package com.jxc.service;

import java.util.List;
import java.util.Map;

import com.jxc.entity.Order;
import com.jxc.page.Page;

public interface OrderService {
	List<Order> findAllOrderByPage(Page page);

	Integer findRows(Page page);
	
	Order findOrderById(Integer id);
	
	List<Order> findOrdersByTitleId(Map<String,Integer> map);
	
	boolean addOrder(Order order);
	
	boolean deleteOrderById(Integer id);
	
	boolean updateOrder(Order order);

}
