package com.to.service;


import com.to.entity.Price;
import com.to.page.Page;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


public interface PriceService {
	List<Price> getPriceByPage(Page page);
	 
	List<Price> getAllPrice();
	
	 Integer getRows(Page page);
	 
	 Price getPriceById(Integer id);
	 
	void updatePrice(Price price, HttpServletResponse response)throws Exception;
	
	void addPrice(Price price, HttpServletResponse response)throws Exception;
	
	void deletePrice(String ids, HttpServletResponse response)throws Exception;
}
