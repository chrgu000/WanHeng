package com.fengyun.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.cgwas.common.dataaccess.api.Page;
import com.fengyun.entity.Chapter;
import com.fengyun.entity.Course;
import com.fengyun.entity.CourseVo;
import com.fengyun.entity.Program;
import com.fengyun.entity.Type;
/**
*  Author yangjun
*/
public interface ICourseService {

	/**
	 * 将对象保存，返回该条记录的操作数量，保存成功之后，将主键填充到参数对象中
	 */
	public abstract Serializable save(Course course);

	/**
	 * 按对象中的主键进行删除，如果是DRDS，还需要添加拆分键
	 */
	public abstract void delete(Course course);
	
	/**
	 * 按对象中的非空属性作为条件，进行删除
	 */
	public abstract void deleteByExample(Course course);

	/**
	 * 按对象中的主键进行所有属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void update(Course course);
	
	/**
	 * 按对象中的主键进行所有非空属性的修改，如果是DRDS，还需要添加拆分键
	 */
	public abstract void updateIgnoreNull(Course course);
	
	/**
	 * 按对象中的非空属性作为条件，进行修改
	 */
	public abstract void updateByExample(Course course);

	/**
	 * 按对象中的主键进行数据加载，如果是DRDS，还需要添加拆分键
	 */
	public abstract CourseVo load(Course course);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询实体
	 */
	public abstract CourseVo selectForObject(Course course);
	
	/**
	 * 按对象中的非空属性作为条件，进行查询列表
	 */
	public abstract List<CourseVo> selectForList(Course course);
	
	/**
	 * 按对象中的非空属性作为条件，进行分页查询
	 */
	public abstract Page page(Page page, Course course);

	public abstract Course getCourseById(Long id);

	public abstract void addCourseTradeSkill(Map<String, Object> map);

	public abstract void addCourseSoftware(Map<String, Object> map);

	public abstract List<Course> getCoursesByUserMap(Map<String, Object> map);

	public abstract List<Chapter> getChaptersByCourseId(Long course_id);

	public abstract void deleteCourseTradeSkill(Long course_id);

	public abstract void deleteCourseSoftware(Long course_id);

	public abstract Long getApplyCourseNumByUserId(Long user_id);

	public abstract Course getCourseInfoById(Long id);

	public abstract Long checkCourseIsMyOrNot(Map<String, Object> search);

	public abstract List<Course> getRecommendCourse();

	public abstract Page page1(Page page, Course course);

	public abstract Page page2(Page page, Course course);

	public abstract Long getsCourseIsMyOrNot(Map<String, Object> search);

	public abstract Page page3(Page page, Course course);

	public abstract List<Chapter> getChaptersByCourseIdOfCheck(Long course_id);

	public abstract Integer getUpdateCourseNum(Long id);

	public abstract List<Chapter> getChapterOrderOfCourse(Long course_id);

	public abstract Page page4(Page page, Course course);

	public abstract List<Type> getAllType();

	public abstract List<Program> getAllPrograms();

	public abstract void addProgram(Program p);

	public abstract Integer getCourseJoinNumsById(Long course_id);

	public abstract void updateJoinNum(Map<String, Object> query);

}