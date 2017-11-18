package com.dq.service;

import java.util.List;

import com.dq.entity.Balance;
import com.dq.page.Page;

public interface BalanceService{
	List<Balance> getAllByPage(Page page);
	
	public Integer getRows(Page page);
}
