package com.fengyun.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.fengyun.dao.ICourseSoftwareDao;
import com.fengyun.entity.CourseSoftware;
import com.fengyun.entity.CourseSoftwareVo;
import com.fengyun.service.ICourseSoftwareService;
/**
*  Author yangjun
*/
@Service
public class CourseSoftwareService implements ICourseSoftwareService {
	private ICourseSoftwareDao courseSoftwareDao;

	public Serializable save(CourseSoftware courseSoftware){
		return courseSoftwareDao.save(courseSoftware);
	}

	public void delete(CourseSoftware courseSoftware){
		courseSoftwareDao.delete(courseSoftware);
	}
	
	public void deleteByExample(CourseSoftware courseSoftware){
		courseSoftwareDao.deleteByExample(courseSoftware);
	}

	public void update(CourseSoftware courseSoftware){
		courseSoftwareDao.update(courseSoftware);
	}
	
	public void updateIgnoreNull(CourseSoftware courseSoftware){
		courseSoftwareDao.updateIgnoreNull(courseSoftware);
	}
		
	public void updateByExample(CourseSoftware courseSoftware){
		courseSoftwareDao.update("updateByExampleCourseSoftware", courseSoftware);
	}

	public CourseSoftwareVo load(CourseSoftware courseSoftware){
		return (CourseSoftwareVo)courseSoftwareDao.reload(courseSoftware);
	}
	
	public CourseSoftwareVo selectForObject(CourseSoftware courseSoftware){
		return (CourseSoftwareVo)courseSoftwareDao.selectForObject("selectCourseSoftware",courseSoftware);
	}
	
	public List<CourseSoftwareVo> selectForList(CourseSoftware courseSoftware){
		return (List<CourseSoftwareVo>)courseSoftwareDao.selectForList("selectCourseSoftware",courseSoftware);
	}
	
	public Page page(Page page, CourseSoftware courseSoftware) {
		return courseSoftwareDao.page(page, courseSoftware);
	}

	@Autowired
	public void setICourseSoftwareDao(
			@Qualifier("courseSoftwareDao") ICourseSoftwareDao  courseSoftwareDao) {
		this.courseSoftwareDao = courseSoftwareDao;
	}

}
