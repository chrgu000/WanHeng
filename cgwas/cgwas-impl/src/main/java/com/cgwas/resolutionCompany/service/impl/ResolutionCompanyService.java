package com.cgwas.resolutionCompany.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.resolutionCompany.dao.api.IResolutionCompanyDao;
import com.cgwas.resolutionCompany.entity.ResolutionCompany;
import com.cgwas.resolutionCompany.entity.ResolutionCompanyVo;
import com.cgwas.resolutionCompany.service.api.IResolutionCompanyService;
/**
*  Author yangjun
*/
@Service
public class ResolutionCompanyService implements IResolutionCompanyService {
	private IResolutionCompanyDao resolutionCompanyDao;

	public Serializable save(ResolutionCompany resolutionCompany){
		return resolutionCompanyDao.save(resolutionCompany);
	}

	public void delete(ResolutionCompany resolutionCompany){
		resolutionCompanyDao.delete(resolutionCompany);
	}
	
	public void deleteByExample(ResolutionCompany resolutionCompany){
		resolutionCompanyDao.deleteByExample(resolutionCompany);
	}

	public void update(ResolutionCompany resolutionCompany){
		resolutionCompanyDao.update(resolutionCompany);
	}
	
	public void updateIgnoreNull(ResolutionCompany resolutionCompany){
		resolutionCompanyDao.updateIgnoreNull(resolutionCompany);
	}
		
	public void updateByExample(ResolutionCompany resolutionCompany){
		resolutionCompanyDao.update("updateByExampleResolutionCompany", resolutionCompany);
	}

	public ResolutionCompanyVo load(ResolutionCompany resolutionCompany){
		return (ResolutionCompanyVo)resolutionCompanyDao.reload(resolutionCompany);
	}
	
	public ResolutionCompanyVo selectForObject(ResolutionCompany resolutionCompany){
		return (ResolutionCompanyVo)resolutionCompanyDao.selectForObject("selectResolutionCompany",resolutionCompany);
	}
	
	public List<ResolutionCompanyVo> selectForList(ResolutionCompany resolutionCompany){
		return (List<ResolutionCompanyVo>)resolutionCompanyDao.selectForList("selectResolutionCompany",resolutionCompany);
	}
	
	public Page page(Page page, ResolutionCompany resolutionCompany) {
		return resolutionCompanyDao.page(page, resolutionCompany);
	}

	@Autowired
	public void setIResolutionCompanyDao(
			@Qualifier("resolutionCompanyDao") IResolutionCompanyDao  resolutionCompanyDao) {
		this.resolutionCompanyDao = resolutionCompanyDao;
	}

}
