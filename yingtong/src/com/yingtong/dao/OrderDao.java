package com.yingtong.dao;

import java.util.List;
import java.util.Map;

import com.yingtong.entity.Order;
import com.yingtong.page.Page;

public interface OrderDao {
	
	List<Order> findAllOrderByPage(Page page);
	
	List<Order> findOrdersByPage(Page page);
	
	List<Order> findOrderByUserId(Integer user_id);
	
	Integer findRows(Page page);
	
	List<Order> findOrdersByCarId(Integer car_id);
	
	boolean addOrder(Order order);
   
	Order findOrderById(Integer id);
	
	Order findOrderByOrderNum(String order_num);
	
	List<Integer>  findCarIdsByUserId(Integer id);
	
	boolean  deleteOrderByUserId(Integer id);
	
	boolean updateOderStatus(Order order);
	
	boolean deleteOrderById(Integer id);
	
	boolean updateOrder(Order order);
}
