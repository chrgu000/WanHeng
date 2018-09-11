package com.fengyun.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.fengyun.dao.ICourseTradeSkillDao;
import com.fengyun.entity.CourseTradeSkill;
import com.fengyun.entity.CourseTradeSkillVo;
import com.fengyun.service.ICourseTradeSkillService;
/**
*  Author yangjun
*/
@Service
public class CourseTradeSkillService implements ICourseTradeSkillService {
	private ICourseTradeSkillDao courseTradeSkillDao;

	public Serializable save(CourseTradeSkill courseTradeSkill){
		return courseTradeSkillDao.save(courseTradeSkill);
	}

	public void delete(CourseTradeSkill courseTradeSkill){
		courseTradeSkillDao.delete(courseTradeSkill);
	}
	
	public void deleteByExample(CourseTradeSkill courseTradeSkill){
		courseTradeSkillDao.deleteByExample(courseTradeSkill);
	}

	public void update(CourseTradeSkill courseTradeSkill){
		courseTradeSkillDao.update(courseTradeSkill);
	}
	
	public void updateIgnoreNull(CourseTradeSkill courseTradeSkill){
		courseTradeSkillDao.updateIgnoreNull(courseTradeSkill);
	}
		
	public void updateByExample(CourseTradeSkill courseTradeSkill){
		courseTradeSkillDao.update("updateByExampleCourseTradeSkill", courseTradeSkill);
	}

	public CourseTradeSkillVo load(CourseTradeSkill courseTradeSkill){
		return (CourseTradeSkillVo)courseTradeSkillDao.reload(courseTradeSkill);
	}
	
	public CourseTradeSkillVo selectForObject(CourseTradeSkill courseTradeSkill){
		return (CourseTradeSkillVo)courseTradeSkillDao.selectForObject("selectCourseTradeSkill",courseTradeSkill);
	}
	
	public List<CourseTradeSkillVo> selectForList(CourseTradeSkill courseTradeSkill){
		return (List<CourseTradeSkillVo>)courseTradeSkillDao.selectForList("selectCourseTradeSkill",courseTradeSkill);
	}
	
	public Page page(Page page, CourseTradeSkill courseTradeSkill) {
		return courseTradeSkillDao.page(page, courseTradeSkill);
	}

	@Autowired
	public void setICourseTradeSkillDao(
			@Qualifier("courseTradeSkillDao") ICourseTradeSkillDao  courseTradeSkillDao) {
		this.courseTradeSkillDao = courseTradeSkillDao;
	}

}
