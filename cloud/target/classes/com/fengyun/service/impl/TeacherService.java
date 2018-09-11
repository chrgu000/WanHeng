package com.fengyun.service.impl;

import java.io.Serializable;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cgwas.common.dataaccess.api.Page;
import com.fengyun.dao.ITeacherDao;
import com.fengyun.entity.Teacher;
import com.fengyun.entity.TeacherVo;
import com.fengyun.entity.UserInfo;
import com.fengyun.service.ITeacherService;
/**
*  Author yangjun
*/
@Service
public class TeacherService implements ITeacherService {
	@Autowired
	private ITeacherDao teacherDao;

	public Serializable save(Teacher teacher){
		return teacherDao.save(teacher);
	}

	public void delete(Teacher teacher){
		teacherDao.delete(teacher);
	}
	
	public void deleteByExample(Teacher teacher){
		teacherDao.deleteByExample(teacher);
	}

	public void update(Teacher teacher){
		teacherDao.update(teacher);
	}
	
	public void updateIgnoreNull(Teacher teacher){
		teacherDao.updateIgnoreNull(teacher);
	}
		
	public void updateByExample(Teacher teacher){
		teacherDao.update("updateByExampleTeacher", teacher);
	}

	public TeacherVo load(Teacher teacher){
		return (TeacherVo)teacherDao.reload(teacher);
	}
	
	public TeacherVo selectForObject(Teacher teacher){
		return (TeacherVo)teacherDao.selectForObject("selectTeacher",teacher);
	}
	
	public List<TeacherVo> selectForList(Teacher teacher){
		return (List<TeacherVo>)teacherDao.selectForList("selectTeacher",teacher);
	}
	
	public Page page(Page page, Teacher teacher) {
		return teacherDao.page(page, teacher);
	}

	
	 
	@Override
	public Teacher getTeacherByUsrId(Long userId) {
		return (TeacherVo)teacherDao.selectForObject("getTeacherByUsrId",userId);
	}

	@Override
	public Teacher getTeacherById(Long id) {
		return (TeacherVo)teacherDao.selectForObject("getTeacherById", id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Teacher> getTeacherByName(String name) {
		return (List<Teacher>)teacherDao.selectForList("getTeacherByName",name);
	}

	@Override
	public Teacher checkTeacherByUserId(Long user_id) {
		return (Teacher)teacherDao.selectForObject("checkTeacherByUserId", user_id);
	}

	@Override
	public UserInfo getUserInfoByUserId(Long user_id) {
		return (UserInfo)teacherDao.selectForObject("com.fengyun.dao.ITeacherDao.getUserInfoByUserId", user_id);
	}

}
