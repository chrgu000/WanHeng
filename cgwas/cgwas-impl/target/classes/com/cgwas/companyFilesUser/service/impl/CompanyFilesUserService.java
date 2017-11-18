package com.cgwas.companyFilesUser.service.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.companyFilesUser.dao.api.ICompanyFilesUserDao;
import com.cgwas.companyFilesUser.entity.CompanyFilesUser;
import com.cgwas.companyFilesUser.entity.CompanyFilesUserVo;
import com.cgwas.companyFilesUser.service.api.ICompanyFilesUserService;
/**
 * 
 * @author yubangqiong
 *
 */
@Service
public class CompanyFilesUserService implements ICompanyFilesUserService {
	private ICompanyFilesUserDao companyFilesUserDao;

	public Serializable save(CompanyFilesUser companyFilesUser){
		return companyFilesUserDao.save(companyFilesUser);
	}

	public void delete(CompanyFilesUser companyFilesUser){
		companyFilesUserDao.delete(companyFilesUser);
	}
	
	public void deleteAll(List<Long> ids){
		Map<String,Object> map= new HashMap<String,Object>();
		map.put("ids", ids);
		companyFilesUserDao.delete("deleteCompanyFilesUserAll", map);
	}
	
	public void deleteByExample(CompanyFilesUser companyFilesUser){
		companyFilesUserDao.deleteByExample(companyFilesUser);
	}

	public void update(CompanyFilesUser companyFilesUser){
		companyFilesUserDao.update(companyFilesUser);
	}
	
	public void updateIgnoreNull(CompanyFilesUser companyFilesUser){
		companyFilesUserDao.updateIgnoreNull(companyFilesUser);
	}
		
	public void updateByExample(CompanyFilesUser companyFilesUser){
		companyFilesUserDao.update("updateByExampleCompanyFilesUser", companyFilesUser);
	}

	public CompanyFilesUserVo load(CompanyFilesUser companyFilesUser){
		return (CompanyFilesUserVo)companyFilesUserDao.reload(companyFilesUser);
	}
	
	public CompanyFilesUserVo selectForObject(CompanyFilesUser companyFilesUser){
		return (CompanyFilesUserVo)companyFilesUserDao.selectForObject("selectCompanyFilesUser",companyFilesUser);
	}
	
	@SuppressWarnings("unchecked")
	public List<CompanyFilesUserVo> selectForList(CompanyFilesUser companyFilesUser){
		return (List<CompanyFilesUserVo>)companyFilesUserDao.selectForList("selectCompanyFilesUser",companyFilesUser);
	}
	
	@SuppressWarnings("unchecked")
	public List<CompanyFilesUserVo> selectCompanyFilesUserByCheck(CompanyFilesUserVo companyFilesUserVo){
		return (List<CompanyFilesUserVo>)companyFilesUserDao.selectForList("selectCompanyFilesUserByCheck",companyFilesUserVo);
	}
	
	public Page page(Page page, CompanyFilesUser companyFilesUser) {
		return companyFilesUserDao.page(page, companyFilesUser);
	}

	@Autowired
	public void setICompanyFilesUserDao(
			@Qualifier("companyFilesUserDao") ICompanyFilesUserDao  companyFilesUserDao) {
		this.companyFilesUserDao = companyFilesUserDao;
	}

}
