package com.yingtong.service;

import java.util.List;

import com.yingtong.entity.Phone;
import com.yingtong.entity.User;
import com.yingtong.page.Page;

public interface UserService {
	List<User> findAllUserByPage(Page page);

	Integer findRows(Page page);

	List<User> findAllUser();

	User findUserById(Integer id);

	User login(User user);

	User findUserByUsername(String username);

	User findUserByTel(String tel);

	boolean addPhone(Phone phone);

	boolean updatePhone(Phone phone);

	List<Phone> findPhoneByTel(String tel);

	boolean updatePwdByTel(User user);

	boolean regist(User user);

	boolean deleteUserById(Integer id);

	boolean updateUser(User user);
}
