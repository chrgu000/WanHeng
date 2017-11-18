package com.cgwas.companySoftware.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.companySoftware.dao.api.ICompanySoftwareDao;
import com.cgwas.companySoftware.entity.CompanySoftware;
import com.cgwas.companySoftware.entity.CompanySoftwareVo;
import com.cgwas.companySoftware.service.api.ICompanySoftwareService;
/**
*  Author yangjun
*/
@Service
public class CompanySoftwareService implements ICompanySoftwareService {
	private ICompanySoftwareDao companySoftwareDao;

	public Serializable save(CompanySoftware companySoftware){
		return companySoftwareDao.save(companySoftware);
	}

	public void delete(CompanySoftware companySoftware){
		companySoftwareDao.delete(companySoftware);
	}
	
	public void deleteByExample(CompanySoftware companySoftware){
		companySoftwareDao.deleteByExample(companySoftware);
	}

	public void update(CompanySoftware companySoftware){
		companySoftwareDao.update(companySoftware);
	}
	
	public void updateIgnoreNull(CompanySoftware companySoftware){
		companySoftwareDao.updateIgnoreNull(companySoftware);
	}
		
	public void updateByExample(CompanySoftware companySoftware){
		companySoftwareDao.update("updateByExampleCompanySoftware", companySoftware);
	}

	public CompanySoftwareVo load(CompanySoftware companySoftware){
		return (CompanySoftwareVo)companySoftwareDao.reload(companySoftware);
	}
	
	public CompanySoftwareVo selectForObject(CompanySoftware companySoftware){
		return (CompanySoftwareVo)companySoftwareDao.selectForObject("selectCompanySoftware",companySoftware);
	}
	
	public List<CompanySoftwareVo> selectForList(CompanySoftware companySoftware){
		return (List<CompanySoftwareVo>)companySoftwareDao.selectForList("selectCompanySoftware",companySoftware);
	}
	
	public Page page(Page page, CompanySoftware companySoftware) {
		return companySoftwareDao.page(page, companySoftware);
	}

	@Autowired
	public void setICompanySoftwareDao(
			@Qualifier("companySoftwareDao") ICompanySoftwareDao  companySoftwareDao) {
		this.companySoftwareDao = companySoftwareDao;
	}

}
