package com.dq.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dq.dao.AddressDao;
import com.dq.entity.Address;
import com.dq.page.Page;
import com.dq.service.AddressService;

@Service("addressService")
@Transactional
public class  AddressServiceImpl implements AddressService{
@Resource
private AddressDao dao;
	public List<Address> getAllByPage(Page page) {
		return dao.getAllByPage(page);
	}

	public Integer getRows(Page page) {
		return dao.getRows(page);
	}

}
