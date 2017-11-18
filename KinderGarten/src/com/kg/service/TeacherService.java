package com.kg.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kg.entity.Teacher;
import com.kg.page.Page;

public interface TeacherService {
	List<Teacher> getTeacherByPage(Page page);
	 
	 Integer getRows(Page page);
	 
	 Teacher getTeacherById(Integer id);
	 
	 void updateTeacher(Teacher teacher);
	 
	 List<Teacher> getTeachersByGardenId(Integer id);
	 
	 void addTeacher(Teacher teacher);

	void updateTeacher(Teacher teacher, HttpServletResponse response) throws Exception;

	void addTeacher(Teacher teacher, HttpServletResponse response)throws Exception;

	void deleteTeacher(String ids, HttpServletResponse response,HttpSession session)throws Exception;

	List<Teacher> findAllTeacher();
	
	void regist(Teacher teacher);

	Teacher login(Teacher teacher);

	void updatePwdByTel(Teacher teacher);

	Teacher getTeacher(Integer teacherId);
}
