package com.jxc.dao;

import java.util.List;
import java.util.Map;

import com.jxc.entity.Order;
import com.jxc.page.Page;


public interface OrderDao {

	List<Order> findAllOrderByPage(Page page);

	Integer findRows(Page page);
	
	List<Order> findOrdersByTitleId(Map<String,Integer> map);
	
	Order findOrderById(Integer id);
   
	boolean addOrder(Order order);
	
	boolean deleteOrderById(Integer id);
	
	boolean updateOrder(Order order);
}
