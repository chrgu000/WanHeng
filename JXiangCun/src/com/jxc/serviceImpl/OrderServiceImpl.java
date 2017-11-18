package com.jxc.serviceImpl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jxc.dao.OrderDao;
import com.jxc.entity.Order;
import com.jxc.page.Page;
import com.jxc.service.OrderService;
@Service("orderService")
@Transactional

public class OrderServiceImpl implements OrderService {
	@Resource
	private OrderDao dao;
	public List<Order> findAllOrderByPage(Page page) {
		return dao.findAllOrderByPage(page);
	}

	public Integer findRows(Page page) {
		return dao.findRows(page);
	}

	public boolean deleteOrderById(Integer id) {
		return dao.deleteOrderById(id);
	}

	public boolean addOrder(Order order) {
		return dao.addOrder(order);
	}

	public Order findOrderById(Integer id) {
		return dao.findOrderById(id);
	}

	public boolean updateOrder(Order order) {
		return dao.updateOrder(order);
	}

	public List<Order> findOrdersByTitleId(Map<String,Integer> map) {
		return dao.findOrdersByTitleId(map);
	}

}
