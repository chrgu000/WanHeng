package com.yingtong.dao;

import java.util.List;

import com.yingtong.entity.Address;
import com.yingtong.page.Page;

public interface AddressDao {
List<Address> findAllAdressByPageTitleAddrId(Page page);

Integer findRowsByTitleAddrId(Integer titleAddr_id);

boolean addAddress(Address address);

boolean updateAddress(Address address);

boolean deleteAddressById(Integer id);

Address findAddressById(Integer id);

List<Address> findAllAddressByTitleAddrId(Integer titleAddr_id);
}
