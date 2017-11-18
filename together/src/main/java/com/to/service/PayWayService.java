package com.to.service;


import com.to.entity.PayWay;
import com.to.page.Page;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


public interface PayWayService {
	List<PayWay> getPayWayByPage(Page page);
	 
	List<PayWay> getAllPayWay();
	
	 Integer getRows(Page page);
	 
	 PayWay getPayWayById(Integer id);
	 
	void updatePayWay(PayWay payWay, HttpServletResponse response)throws Exception;
	
	void addPayWay(PayWay payWay, HttpServletResponse response)throws Exception;
	
	void deletePayWay(String ids, HttpServletResponse response)throws Exception;
}
