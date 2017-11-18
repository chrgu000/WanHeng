package com.dq.service;

import java.util.List;

import com.dq.entity.Address;
import com.dq.page.Page;

public interface AddressService {
	/**
	 * 查询所有
	 */
	public List<Address> getAllByPage(Page page);
	
	/**
	 * 查询行数
	 */
	public Integer getRows(Page page);
}
