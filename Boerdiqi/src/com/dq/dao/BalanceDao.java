package com.dq.dao;

import java.util.List;

import com.dq.entity.Balance;
import com.dq.page.Page;

public interface BalanceDao {
	public Integer getRows(Page page);
	
	List<Balance> getAllByPage(Page page);
}
