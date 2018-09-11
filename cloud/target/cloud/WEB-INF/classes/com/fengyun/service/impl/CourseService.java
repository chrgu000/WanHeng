package com.fengyun.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.fengyun.dao.ICourseDao;
import com.fengyun.entity.Chapter;
import com.fengyun.entity.Course;
import com.fengyun.entity.CourseVo;
import com.fengyun.entity.Program;
import com.fengyun.entity.Type;
import com.fengyun.service.ICourseService;
/**
*  Author yangjun
*/
@Service
public class CourseService implements ICourseService {
	private ICourseDao courseDao;

	public Serializable save(Course course){
		return courseDao.save(course);
	}

	public void delete(Course course){
		courseDao.delete(course);
	}
	
	public void deleteByExample(Course course){
		courseDao.deleteByExample(course);
	}

	public void update(Course course){
		courseDao.update(course);
	}
	
	public void updateIgnoreNull(Course course){
		courseDao.updateIgnoreNull(course);
	}
		
	public void updateByExample(Course course){
		courseDao.update("updateByExampleCourse", course);
	}

	public CourseVo load(Course course){
		return (CourseVo)courseDao.reload(course);
	}
	
	public CourseVo selectForObject(Course course){
		return (CourseVo)courseDao.selectForObject("selectCourse",course);
	}
	
	@SuppressWarnings("unchecked")
	public List<CourseVo> selectForList(Course course){
		return (List<CourseVo>)courseDao.selectForList("selectCourse",course);
	}
	
	public Page page(Page page, Course course) {
		return courseDao.page(page, course);
	}

	@Autowired
	public void setICourseDao(
			@Qualifier("courseDao") ICourseDao  courseDao) {
		this.courseDao = courseDao;
	}

	@Override
	public Course getCourseById(Long id) {
		return (Course) courseDao.selectForObject("getCourseById", id);
	}

	@Override
	public void addCourseTradeSkill(Map<String, Object> map) {
		courseDao.save("addCourseTradeSkill",map);
	}

	@Override
	public void addCourseSoftware(Map<String, Object> map) {
		courseDao.save("addCourseSoftware",map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getCoursesByUserMap(Map<String, Object> map) {
		return (List<Course>) courseDao.selectForList("getCoursesByUserMap", map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Chapter> getChaptersByCourseId(Long course_id) {
		return (List<Chapter>) courseDao.selectForList("getChaptersByCourseId", course_id);
	}

	@Override
	public void deleteCourseTradeSkill(Long course_id) {
		courseDao.delete("com.fengyun.dao.ICourseDao.deleteCourseTradeSkill", course_id);
	}

	@Override
	public void deleteCourseSoftware(Long course_id) {
		courseDao.delete("com.fengyun.dao.ICourseDao.deleteCourseSoftware", course_id);
		
	}

	@Override
	public Long getApplyCourseNumByUserId(Long user_id) {
		return (Long) courseDao.selectForObject("getApplyCourseNumByUserId",user_id);
	}

	@Override
	public Course getCourseInfoById(Long id) {
		return (Course) courseDao.selectForObject("getCourseInfoById", id);
	}

	@Override
	public Long checkCourseIsMyOrNot(Map<String, Object> search) {
		return (Long) courseDao.selectForObject("checkCourseIsMyOrNot", search);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Course> getRecommendCourse() {
		return (List<Course>) courseDao.selectForList("getRecommendCourse");
	}

	@Override
	public Page page1(Page page, Course course) {
		return courseDao.page1(page, course);
	}

	@Override
	public Page page2(Page page, Course course) {
		return courseDao.page2(page, course);
	}

	@Override
	public Long getsCourseIsMyOrNot(Map<String, Object> search) {
		return (Long) courseDao.selectForObject("getsCourseIsMyOrNot", search);
	}

	@Override
	public Page page3(Page page, Course course) {
		return courseDao.page3(page, course);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Chapter> getChaptersByCourseIdOfCheck(Long course_id) {
		return (List<Chapter>) courseDao.selectForList("getChaptersByCourseIdOfCheck", course_id);
	}

	@Override
	public Integer getUpdateCourseNum(Long id) {
		return (Integer) courseDao.selectForObject("getUpdateCourseNum", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Chapter> getChapterOrderOfCourse(Long course_id) {
		return (List<Chapter>) courseDao.selectForList("getChapterOrderOfCourse", course_id);
	}

	@Override
	public Page page4(Page page, Course course) {
		return courseDao.page4(page, course);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Type> getAllType() {
		return (List<Type>) courseDao.selectForList("getAllType");
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Program> getAllPrograms() {
		return (List<Program>) courseDao.selectForList("getAllPrograms");
	}

	@Override
	public void addProgram(Program p) {
		 courseDao.update("addProgram",p);
		
	}

	/* (non-Javadoc)
	 * @see com.fengyun.service.ICourseService#getCourseJoinNumsById(java.lang.Long)
	 */
	@Override
	public Integer getCourseJoinNumsById(Long course_id) {
		return  (Integer) courseDao.selectForObject("getCourseJoinNumsById", course_id);
	}

	@Override
	public void updateJoinNum(Map<String, Object> query) {
		courseDao.update("updateJoinNum",query);
	}

	 

}
