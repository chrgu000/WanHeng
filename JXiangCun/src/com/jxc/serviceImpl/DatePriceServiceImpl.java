package com.jxc.serviceImpl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jxc.dao.DatePriceDao;
import com.jxc.entity.DatePrice;
import com.jxc.service.DatePriceService;
@Service("datePriceService")
@Transactional
public class DatePriceServiceImpl implements DatePriceService{
@Resource
private DatePriceDao dao;
	public boolean addDatePrice(DatePrice datePrice) {
		return dao.addDatePrice(datePrice);
	}

	public boolean deleteDatePrice(Integer product_id) {
		return dao.deleteDatePrice(product_id);
	}

}
