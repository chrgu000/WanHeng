package com.dq.service;

import java.util.List;

import com.dq.entity.Withdraw;
import com.dq.page.Page;

public interface WithdrawService{
    public Integer getRows(Page page);
	
	List<Withdraw> getAllByPage(Page page);
	
	void update(Withdraw withdraw);
}
