package com.jxc.service;

import java.util.List;

import com.jxc.entity.Address;
import com.jxc.page.Page;

public interface AddressService {
	List<Address> findAllAddress();

	List<Address> findAllAddressByPage(Page page);

	Integer findRows(Page page);

	Address findAddressById(Integer id);

	boolean addAddress(Address address);

	boolean updateAddress(Address address);

	boolean deleteAddressById(Integer id);
}
