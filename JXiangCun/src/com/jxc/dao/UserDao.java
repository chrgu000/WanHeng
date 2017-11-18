package com.jxc.dao;

import java.util.List;

import com.jxc.entity.User;
import com.jxc.page.Page;


public interface UserDao {

	List<User> findAllUserByPage(Page page);

	Integer findRows(Page page);
   
	User findUserByOpenId(String open_id);
	
	User findUserByTel(String tel);
	 
	User findUserById(Integer user_id);
	
	boolean deleteUserById(Integer id);
	
	boolean addUser(User user);
	
	boolean updateUser(User user);
}
