package com.yingtong.service;

import java.util.List;

import com.yingtong.entity.Motorcycle;
import com.yingtong.page.Page;

public interface MotorcycleService {
	List<Motorcycle> findAllMotorcycleByPage(Page page);

	Integer findRows(Page page);

	Motorcycle findMotorcycleById(Integer id);
	
	List<Motorcycle> findMotorcyclesByBrandId(Integer brand_id);

	boolean addMotorcycle(Motorcycle motorcycle);

	boolean updateMotorcycle(Motorcycle motorcycle);

	boolean deleteMotorcycleById(Integer id);
}
