package com.yingtong.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yingtong.dao.MotorcycleDao;
import com.yingtong.entity.Motorcycle;
import com.yingtong.page.Page;
import com.yingtong.service.MotorcycleService;
@Service("motorcycleService")
@Transactional
public class MotorcycleServiceImpl implements MotorcycleService {
@Resource
MotorcycleDao dao;

public List<Motorcycle> findAllMotorcycleByPage(Page page) {
	 
	return dao.findAllMotorcycleByPage(page);
}

public Motorcycle findMotorcycleById(Integer id) {
	 
	return dao.findMotorcycleById(id);
}

public boolean addMotorcycle(Motorcycle motorcycle) {
 
	return dao.addMotorcycle(motorcycle);
}

public boolean updateMotorcycle(Motorcycle motorcycle) {
 
	return dao.updateMotorcycle(motorcycle);
}

public boolean deleteMotorcycleById(Integer id) {
	 
	return dao.deleteMotorcycleById(id);
}

public List<Motorcycle> findMotorcyclesByBrandId(Integer brand_id) {
	 
	return dao.findMotorcyclesByBrandId(brand_id);
}

public Integer findRows(Page page) {
 
	return dao.findRows(page);
}
}
