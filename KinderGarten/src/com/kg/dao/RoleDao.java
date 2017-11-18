package com.kg.dao;

import java.util.List;
import java.util.Map;

import com.kg.entity.Role;
import com.kg.page.Page;

public interface RoleDao {
	List<Role> getRoleByPage(Page page);
	 
	List<Role> getAllRole(); 
	
	Integer getRows(Page page);
	 
	 Role getRoleById(Integer id);
	 
	 Role getRoleByName(String name);
	 
	 void updateRole(Role role);
	 
	 void deleteByIds(Map<String, String[]> map);
	 
	 void deleteRoleModule(Map<String, String[]> map);
	 
	 void addRole(Role role);
}
