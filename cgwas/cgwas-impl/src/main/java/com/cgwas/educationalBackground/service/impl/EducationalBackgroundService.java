package com.cgwas.educationalBackground.service.impl;


import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.cgwas.educationalBackground.dao.api.IEducationalBackgroundDao;
import com.cgwas.educationalBackground.entity.EducationalBackground;
import com.cgwas.educationalBackground.entity.EducationalBackgroundVo;
import com.cgwas.educationalBackground.service.api.IEducationalBackgroundService;
@Service
public class EducationalBackgroundService implements IEducationalBackgroundService {
	private IEducationalBackgroundDao educationalBackgroundDao;

	public Serializable save(EducationalBackground educationalBackground){
		return educationalBackgroundDao.save(educationalBackground);
	}

	public void delete(EducationalBackground educationalBackground){
		educationalBackgroundDao.delete(educationalBackground);
	}
	
	public void deleteByExample(EducationalBackground educationalBackground){
		educationalBackgroundDao.deleteByExample(educationalBackground);
	}

	public void update(EducationalBackground educationalBackground){
		educationalBackgroundDao.update(educationalBackground);
	}
	
	public void updateIgnoreNull(EducationalBackground educationalBackground){
		educationalBackgroundDao.updateIgnoreNull(educationalBackground);
	}
		
	public void updateByExample(EducationalBackground educationalBackground){
		educationalBackgroundDao.update("updateByExampleEducationalBackground", educationalBackground);
	}

	public EducationalBackgroundVo load(EducationalBackground educationalBackground){
		return (EducationalBackgroundVo)educationalBackgroundDao.reload(educationalBackground);
	}
	
	public EducationalBackgroundVo selectForObject(EducationalBackground educationalBackground){
		return (EducationalBackgroundVo)educationalBackgroundDao.selectForObject("selectEducationalBackground",educationalBackground);
	}
	
	public List<EducationalBackgroundVo> selectForList(EducationalBackground educationalBackground){
		return (List<EducationalBackgroundVo>)educationalBackgroundDao.selectForList("selectEducationalBackground",educationalBackground);
	}
	
	public Page page(Page page, EducationalBackground educationalBackground) {
		return educationalBackgroundDao.page(page, educationalBackground);
	}

	@Autowired
	public void setIEducationalBackgroundDao(
			@Qualifier("educationalBackgroundDao") IEducationalBackgroundDao  educationalBackgroundDao) {
		this.educationalBackgroundDao = educationalBackgroundDao;
	}

	@Override
	public List<EducationalBackground> getEducationalBackgroundByUserId(
			Long user_id) {
		return (List<EducationalBackground>) educationalBackgroundDao.selectForList("getEducationalBackgroundByUserId", user_id);
	}

	@Override
	public Serializable updateEducationalBackgroundByUserId(
			EducationalBackground educationalBackground) {
		return educationalBackgroundDao.update("updateEducationalBackgroundByUserId", educationalBackground);
	}

}
