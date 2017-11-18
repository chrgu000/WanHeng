package com.kg.dao;

import java.util.List;
import java.util.Map;

import com.kg.entity.Baby;
import com.kg.page.Page;

public interface BabyDao {
	List<Baby> getBabyByPage(Page page);
	 
	 Integer getRows(Page page);
	 
	 List<Baby> getBabyByGardenIds(Map<String,String[]> map);
	 
	 List<Baby> getBabyByTeacherIds(Map<String,String> map);
	 
	 List<Baby> getBabyByTeacher(Map<String, String[]> map);
	 
	 Baby getBabyById(Integer id);
	 
	 void updateBaby(Baby baby);
	 
	void deleteByIds(Map<String, String[]> map);
	 
	 void addBaby(Baby baby);

	List<Baby> findAllBaby();

	Baby login(Baby baby);

	void updatePwdByTel(Baby baby);
	
	Baby getBaby(Integer id);

	List<Baby> findAllBabyIsStudy();

	List<Baby> getBabyByIds(Map<String, String[]> map);

}
