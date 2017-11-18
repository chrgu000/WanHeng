package com.to.service;


import com.to.entity.Role;
import com.to.page.Page;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


public interface RoleService {
	List<Role> getRoleByPage(Page page);
	 
	List<Role> getAllRole();
	
	 Integer getRows(Page page);
	 
	 Role getRoleById(Integer id);
	 
	void updateRole(Role role, HttpServletResponse response)throws Exception;
	
	void addRole(Role role, HttpServletResponse response)throws Exception;
	
	void deleteRole(String ids, HttpServletResponse response)throws Exception;
}
