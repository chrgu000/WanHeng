package com.cgwas.companySpace.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.companySpace.dao.api.ICompanySpaceDao;
import com.cgwas.companySpace.entity.CompanySpace;
import com.cgwas.companySpace.entity.CompanySpaceVo;
import com.cgwas.companySpace.service.api.ICompanySpaceService;
/**
 * 
 * @author yubangqiong
 *
 */
@Service
public class CompanySpaceService implements ICompanySpaceService {
	private ICompanySpaceDao companySpaceDao;

	public Serializable save(CompanySpace companySpace){
		return companySpaceDao.save(companySpace);
	}

	public void delete(CompanySpace companySpace){
		companySpaceDao.delete(companySpace);
	}
	
	public void deleteByExample(CompanySpace companySpace){
		companySpaceDao.deleteByExample(companySpace);
	}

	public void update(CompanySpace companySpace){
		companySpaceDao.update(companySpace);
	}
	
	public void updateIgnoreNull(CompanySpace companySpace){
		companySpaceDao.updateIgnoreNull(companySpace);
	}
		
	public void updateByExample(CompanySpace companySpace){
		companySpaceDao.update("updateByExampleCompanySpace", companySpace);
	}

	public CompanySpaceVo load(CompanySpace companySpace){
		return (CompanySpaceVo)companySpaceDao.reload(companySpace);
	}
	
	public CompanySpaceVo selectForObject(CompanySpace companySpace){
		return (CompanySpaceVo)companySpaceDao.selectForObject("selectCompanySpace",companySpace);
	}
	
	public CompanySpaceVo getInitSpace(CompanySpace companySpace){
		return (CompanySpaceVo)companySpaceDao.selectForObject("getInitSpace",companySpace);
	}
	
	@SuppressWarnings("unchecked")
	public List<CompanySpaceVo> selectForList(CompanySpace companySpace){
		return (List<CompanySpaceVo>)companySpaceDao.selectForList("selectCompanySpace",companySpace);
	}
	
	public Page page(Page page, CompanySpace companySpace) {
		return companySpaceDao.page(page, companySpace);
	}

	@Autowired
	public void setICompanySpaceDao(
			@Qualifier("companySpaceDao") ICompanySpaceDao  companySpaceDao) {
		this.companySpaceDao = companySpaceDao;
	}

}
