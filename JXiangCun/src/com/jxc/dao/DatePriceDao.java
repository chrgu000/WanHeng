package com.jxc.dao;

import com.jxc.entity.DatePrice;

public interface DatePriceDao {
	boolean addDatePrice(DatePrice datePrice);
	
	boolean deleteDatePrice(Integer product_id);
}
