package com.fengyun.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.fengyun.dao.ILearnerCourseDao;
import com.fengyun.entity.LearnerCourse;
import com.fengyun.entity.LearnerCourseVo;
import com.fengyun.service.ILearnerCourseService;
/**
*  Author yangjun
*/
@Service
public class LearnerCourseService implements ILearnerCourseService {
	private ILearnerCourseDao learnerCourseDao;

	public Serializable save(LearnerCourse learnerCourse){
		return learnerCourseDao.save(learnerCourse);
	}

	public void delete(LearnerCourse learnerCourse){
		learnerCourseDao.delete(learnerCourse);
	}
	
	public void deleteByExample(LearnerCourse learnerCourse){
		learnerCourseDao.deleteByExample(learnerCourse);
	}

	public void update(LearnerCourse learnerCourse){
		learnerCourseDao.update(learnerCourse);
	}
	
	public void updateIgnoreNull(LearnerCourse learnerCourse){
		learnerCourseDao.updateIgnoreNull(learnerCourse);
	}
		
	public void updateByExample(LearnerCourse learnerCourse){
		learnerCourseDao.update("updateByExampleLearnerCourse", learnerCourse);
	}

	public LearnerCourseVo load(LearnerCourse learnerCourse){
		return (LearnerCourseVo)learnerCourseDao.reload(learnerCourse);
	}
	
	public LearnerCourseVo selectForObject(LearnerCourse learnerCourse){
		return (LearnerCourseVo)learnerCourseDao.selectForObject("selectLearnerCourse",learnerCourse);
	}
	
	public List<LearnerCourseVo> selectForList(LearnerCourse learnerCourse){
		return (List<LearnerCourseVo>)learnerCourseDao.selectForList("selectLearnerCourse",learnerCourse);
	}
	
	public Page page(Page page, LearnerCourse learnerCourse) {
		return learnerCourseDao.page(page, learnerCourse);
	}

	@Autowired
	public void setILearnerCourseDao(
			@Qualifier("learnerCourseDao") ILearnerCourseDao  learnerCourseDao) {
		this.learnerCourseDao = learnerCourseDao;
	}

	@Override
	public LearnerCourse getLearnerCourseByUserMap(Map<String, Object> map) {
		return (LearnerCourse)learnerCourseDao.selectForObject("getLearnerCourseByUserMap",map);
	}

}
