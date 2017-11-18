package com.jxc.service;

import java.util.List;

import com.jxc.entity.City;
import com.jxc.entity.User;
import com.jxc.page.Page;

public interface UserService {
	List<User> findAllUserByPage(Page page);

	Integer findRows(Page page);
	
	User findUserByOpenId(String open_id);
	
	User findUserByTel(String tel);
	
	User findUserById(Integer user_id);
	
	boolean deleteUserById(Integer id);

	boolean addUser(User user);
	
	boolean updateUser(User user);
}
