package com.jxc.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jxc.dao.AddressDao;
import com.jxc.entity.Address;
import com.jxc.page.Page;
import com.jxc.service.AddressService;
@Service("addressService")
@Transactional
public class AddressServiceImpl implements AddressService{
@Resource
private AddressDao dao;
	public List<Address> findAllAddress() {
		return dao.findAllAddress();
	}

	public List<Address> findAllAddressByPage(Page page) {
		return dao.findAllAddressByPage(page);
	}

	public Integer findRows(Page page) {
		return dao.findRows(page);
	}

	public Address findAddressById(Integer id) {
		return dao.findAddressById(id);
	}

	public boolean addAddress(Address address) {
		return dao.addAddress(address);
	}

	public boolean updateAddress(Address address) {
		return dao.updateAddress(address);
	}

	public boolean deleteAddressById(Integer id) {
		return dao.deleteAddressById(id);
	}

}
