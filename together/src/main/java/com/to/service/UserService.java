package com.to.service;


import com.to.entity.User;
import com.to.page.Page;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


public interface UserService {
	List<User> getUserByPage(Page page);
	 
	 Integer getRows(Page page);
	 
	 User getUserById(Integer id);
	 
	void updateUser(User user, HttpServletResponse response)throws Exception;
	
	void addUser(User user, HttpServletResponse response)throws Exception;
	
	void deleteUser(String ids, HttpServletResponse response)throws Exception;

	List<String> findAllTel();

	User login(User user);

	void updatePwdByTel(User user);

	List<String> findAllAreaByCity(String city);
}
