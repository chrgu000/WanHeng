package com.cgwas.frameRateCompany.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.frameRateCompany.dao.api.IFrameRateCompanyDao;
import com.cgwas.frameRateCompany.entity.FrameRateCompany;
import com.cgwas.frameRateCompany.entity.FrameRateCompanyVo;
import com.cgwas.frameRateCompany.service.api.IFrameRateCompanyService;
/**
*  Author yangjun
*/
@Service
public class FrameRateCompanyService implements IFrameRateCompanyService {
	private IFrameRateCompanyDao frameRateCompanyDao;

	public Serializable save(FrameRateCompany frameRateCompany){
		return frameRateCompanyDao.save(frameRateCompany);
	}

	public void delete(FrameRateCompany frameRateCompany){
		frameRateCompanyDao.delete(frameRateCompany);
	}
	
	public void deleteByExample(FrameRateCompany frameRateCompany){
		frameRateCompanyDao.deleteByExample(frameRateCompany);
	}

	public void update(FrameRateCompany frameRateCompany){
		frameRateCompanyDao.update(frameRateCompany);
	}
	
	public void updateIgnoreNull(FrameRateCompany frameRateCompany){
		frameRateCompanyDao.updateIgnoreNull(frameRateCompany);
	}
		
	public void updateByExample(FrameRateCompany frameRateCompany){
		frameRateCompanyDao.update("updateByExampleFrameRateCompany", frameRateCompany);
	}

	public FrameRateCompanyVo load(FrameRateCompany frameRateCompany){
		return (FrameRateCompanyVo)frameRateCompanyDao.reload(frameRateCompany);
	}
	
	public FrameRateCompanyVo selectForObject(FrameRateCompany frameRateCompany){
		return (FrameRateCompanyVo)frameRateCompanyDao.selectForObject("selectFrameRateCompany",frameRateCompany);
	}
	
	public List<FrameRateCompanyVo> selectForList(FrameRateCompany frameRateCompany){
		return (List<FrameRateCompanyVo>)frameRateCompanyDao.selectForList("selectFrameRateCompany",frameRateCompany);
	}
	
	public Page page(Page page, FrameRateCompany frameRateCompany) {
		return frameRateCompanyDao.page(page, frameRateCompany);
	}

	@Autowired
	public void setIFrameRateCompanyDao(
			@Qualifier("frameRateCompanyDao") IFrameRateCompanyDao  frameRateCompanyDao) {
		this.frameRateCompanyDao = frameRateCompanyDao;
	}

}
