package com.dq.dao;

import com.dq.entity.Admin;
import com.dq.entity.User;

public interface UserDao extends BaseDao<User> {
	User getUserByTel(String tel);
}
