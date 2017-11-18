package com.dq.dao;

import java.util.List;
import java.util.Map;

import com.dq.entity.Role;

public interface RoleDao extends BaseDao<Role> {
	Role getRoleByName(String name);
	
	void deleteRoleModule(Map<String, String[]> map);
	
	List<Role> getAllRole();
}
