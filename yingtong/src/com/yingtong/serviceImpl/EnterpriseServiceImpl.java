package com.yingtong.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yingtong.dao.EnterpriseDao;
import com.yingtong.entity.Enterprise;
import com.yingtong.page.Page;
import com.yingtong.service.EnterpriseService;

@Service("enterpriseService")
@Transactional
public class EnterpriseServiceImpl implements EnterpriseService{
@Resource
private EnterpriseDao dao;

public boolean addEnterprise(Enterprise enterprise) {
	 
	return dao.addEnterprise(enterprise);
}

public boolean updateEnterprise(Enterprise enterprise) {
 
	return dao.updateEnterprise(enterprise);
}

public boolean deleteEnterpriseById(Integer id) {
	
	return dao.deleteEnterpriseById(id);
}

public Enterprise findEnterpriseById(Integer id) {
	 
	return dao.findEnterpriseById(id);
}

public List<Enterprise> findAllEnterpriseByPage(Page page) {
	 
	return dao.findAllEnterpriseByPage(page);
}

public Integer findRows(Page page) {
	 
	return dao.findRows(page);
}

public List<Enterprise> findAllEnterprises() {
	 
	return dao.findAllEnterprises();
}

 
}
