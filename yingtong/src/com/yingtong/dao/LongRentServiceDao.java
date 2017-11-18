package com.yingtong.dao;

import java.util.List;

import com.yingtong.entity.LongRentService;
import com.yingtong.page.Page;

public interface LongRentServiceDao {
	boolean addLongRentService(LongRentService longRentService);

	LongRentService findLongRentServiceById(Integer id);
	
	List<LongRentService> findAllLongRentService(Page page);
	
	List<LongRentService> findAllLongRentServices();
	
	Integer findRows(Page page);
	
	boolean deleteLongRentServiceById(Integer id);
}
