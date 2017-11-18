package com.jxc.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jxc.dao.UserDao;
import com.jxc.entity.User;
import com.jxc.page.Page;
import com.jxc.service.UserService;
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

	public boolean deleteUserById(Integer id) {
		return dao.deleteUserById(id);
	}

	public boolean addUser(User user) {
		return dao.addUser(user);
	}

	public User findUserByOpenId(String open_id) {
		return dao.findUserByOpenId(open_id);
	}

	public User findUserByTel(String tel) {
		return dao.findUserByTel(tel);
	}

	public boolean updateUser(User user) {
		return dao.updateUser(user);
	}

	public User findUserById(Integer user_id) {
		return dao.findUserById(user_id);
	}

}
