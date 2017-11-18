package com.yingtong.service;

import java.util.List;
import java.util.Map;

import com.yingtong.entity.Order;
import com.yingtong.page.Page;

public interface OrderService {

	List<Order> findAllOrderByPage(Page page);
	
	Integer findRows(Page page);
	
	List<Order> findOrdersByPage(Page page);
	
	List<Order> findOrderByUserId(Integer user_id);
	
	boolean addOrder(Order order);
	
	Order findOrderById(Integer id);
	
	Order findOrderByOrderNum(String order_num);
	
	List<Order> findOrdersByCarId(Integer car_id);
	
	List<Integer>  findCarIdsByUserId(Integer id);
	
	boolean deleteOrderByUserId(Integer id);
	
	boolean deleteOrderById(Integer id);
	
	boolean updateOderStatus(Order order);
	
	boolean updateOrder(Order order);
}
