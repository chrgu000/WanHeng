package com.yingtong.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yingtong.dao.OrderDao;
import com.yingtong.entity.Order;
import com.yingtong.page.Page;
import com.yingtong.service.OrderService;
@Service("orderService")
@Transactional
public class OrderServiceImpl implements OrderService {
@Resource
private OrderDao dao;
	public boolean addOrder(Order order) {
		return dao.addOrder(order);
	}
	public Order findOrderById(Integer id) {
		return dao.findOrderById(id);
	}
	public boolean updateOderStatus(Order order) {
		 
		return dao.updateOderStatus(order);
	}
	public List<Order> findAllOrderByPage(Page page) {
	 
		return dao.findAllOrderByPage(page);
	}
	public Integer findRows(Page page) {
		 
		return dao.findRows(page);
	}
	public boolean deleteOrderById(Integer id) {
	 
		return dao.deleteOrderById(id);
	}
	public boolean deleteOrderByUserId(Integer id) {
		 
		return dao.deleteOrderByUserId(id);
	}
	public List<Integer> findCarIdsByUserId(Integer id) {
		return dao.findCarIdsByUserId(id);
	}
	public List<Order> findOrdersByPage(Page page) {
		 
		return dao.findOrdersByPage(page);
	}
	public List<Order> findOrderByUserId(Integer user_id) {
		 
		return dao.findOrderByUserId(user_id);
	}
	public boolean updateOrder(Order order) {
	 
		return dao.updateOrder(order);
	}
	public Order findOrderByOrderNum(String order_num) {
		return dao.findOrderByOrderNum(order_num);
	}
	public List<Order> findOrdersByCarId(Integer car_id) {
		return dao.findOrdersByCarId(car_id);
	}
}
