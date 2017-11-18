package com.kg.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.kg.entity.Role;
import com.kg.page.Page;

public interface RoleService {
	List<Role> getRoleByPage(Page page);
	 
	List<Role> getAllRole(); 
	
	 Integer getRows(Page page);
	 
	 Role getRoleById(Integer id);
	 
	void updateRole(Role role,HttpServletResponse response)throws Exception;
	
	void addRole(Role role,HttpServletResponse response)throws Exception;
	
	void deleteRole(String  ids,HttpServletResponse response)throws Exception;
}
