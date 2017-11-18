package com.yingtong.service;

import java.util.List;

import com.yingtong.entity.Enterprise;
import com.yingtong.page.Page;

public interface EnterpriseService {
	List<Enterprise> findAllEnterpriseByPage(Page page);

	Integer findRows(Page page);
	
	List<Enterprise> findAllEnterprises();

	boolean addEnterprise(Enterprise enterprise);

	boolean updateEnterprise(Enterprise enterprise);

	boolean deleteEnterpriseById(Integer id);

	Enterprise findEnterpriseById(Integer id);
}
