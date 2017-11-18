package com.kg.dao;

import java.util.List;
import java.util.Map;

import com.kg.entity.Teacher;
import com.kg.page.Page;

public interface TeacherDao {
	List<Teacher> getTeacherByPage(Page page);
	 
	 Integer getRows(Page page);
	 
	 List<Teacher> getTeacherByGardenIds(Map<String,String[]> map);
	 
	 List<Teacher> getTeachersByGardenId(Integer id);
	 
	 Teacher getTeacherById(Integer id);
	 
	 void updateTeacher(Teacher teacher);
	 
	void deleteByIds(Map<String, String[]> map);
	 
	 void addTeacher(Teacher teacher);

	List<Teacher> findAllTeacher();

	Teacher login(Teacher teacher);

	void updatePwdByTel(Teacher teacher);

	Teacher getTeacher(Integer id);

	List<Teacher> getTeacherByIds(Map<String, String[]> map);
	
}
