package com.yingtong.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yingtong.dao.UserDao;
import com.yingtong.entity.Phone;
import com.yingtong.entity.User;
import com.yingtong.page.Page;
import com.yingtong.service.UserService;
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
@Resource
private UserDao dao;
	public List<User> findAllUserByPage(Page page) {
	 
		return dao.findAllUserByPage(page);
	}

	public Integer findRows(Page page) {
		 
		return dao.findRows(page);
	}

	public User login(User user) {
	 
		return dao.login(user);
	}

	public boolean regist(User user) {
		 
		return dao.regist(user);
	}

	public boolean deleteUserById(Integer id) {
		 
		return dao.deleteUserById(id);
	}

	public boolean updateUser(User user) {
		  
		return dao.updateUser(user);
	}

	public List<User> findAllUser() {
	 
		return dao.findAllUser();
	}

	public User findUserById(Integer id) {
	 
		return dao.findUserById(id);
	}

	public boolean updatePwdByTel(User user) {
		return dao.updatePwdByTel(user);
	}

	public User findUserByUsername(String username) {
	 
		return dao.findUserByUsername(username);
	}

	public User findUserByTel(String tel) {
		 
		return dao.findUserByTel(tel);
	}

	public boolean addPhone(Phone phone) {
		  
		return dao.addPhone(phone);
	}

	public boolean updatePhone(Phone phone) {
		 
		return dao.updatePhone(phone);
	}

	public List<Phone> findPhoneByTel(String tel) {
		 
		return dao.findPhoneByTel(tel);
	}

}
