package com.yingtong.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yingtong.dao.AddressDao;
import com.yingtong.entity.Address;
import com.yingtong.page.Page;
import com.yingtong.service.AddressService;
@Service("addressService")
@Transactional
public class AddressServiceImpl implements AddressService {
@Resource
private AddressDao dao;
 

	public Integer findRowsByTitleAddrId(Integer titleAddr_id) {
	 
		return dao.findRowsByTitleAddrId(titleAddr_id);
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

	public Address findAddressById(Integer id) {
		 
		return dao.findAddressById(id);
	}

	public List<Address> findAllAdressByPageTitleAddrId(Page page) {
		 
		return dao.findAllAdressByPageTitleAddrId(page);
	}

	public List<Address> findAllAddressByTitleAddrId(Integer titleAddr_id) {
		 
		return dao.findAllAddressByTitleAddrId(titleAddr_id);
	}

}
