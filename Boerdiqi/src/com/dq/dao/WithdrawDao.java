package com.dq.dao;

import java.util.List;

import com.dq.entity.Withdraw;
import com.dq.page.Page;

public interface WithdrawDao {
	public Integer getRows(Page page);
	
	List<Withdraw> getAllByPage(Page page);
	
	void update(Withdraw withdraw);
}
