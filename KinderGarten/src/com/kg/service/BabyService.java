package com.kg.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kg.entity.Baby;
import com.kg.page.Page;

public interface BabyService {
	List<Baby> getBabyByPage(Page page);
	 
	 Integer getRows(Page page);
	 
	 Baby getBabyById(Integer id);
	 
	 void updateBaby(Baby baby);
	 
	 void addBaby(Baby baby);

	void updateBaby(Baby baby, HttpServletResponse response) throws Exception;

	void addBaby(Baby baby, HttpServletResponse response)throws Exception;

	void deleteBaby(String ids, HttpServletResponse response,HttpSession session)throws Exception;

	List<Baby> findAllBaby();

	void regist(Baby baby);

	Baby login(Baby baby);

	void updatePwdByTel(Baby baby);

	Baby getBaby(Integer id);

}
