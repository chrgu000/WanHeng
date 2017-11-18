package com.dq.dao;

import java.util.List;

import com.dq.entity.Admin;

public interface AdminDao extends BaseDao<Admin> {
	Admin login(Admin admin);

	List<String> getPermissions(String username);
	
	Admin getAdminByUsername(String username);
}
