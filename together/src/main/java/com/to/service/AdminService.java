package com.to.service;

import com.to.entity.Admin;
import com.to.page.Page;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface AdminService {
	List<Admin> getAdminByPage(Page page);

	 Integer getRows(Page page);
	 
	 Admin login(Admin admin);
	 
	 List<String> getPermissions(String username);
	 
	 Admin getAdminById(Integer id);
	 
	 void updateAdmin(Admin admin);
	 

	 void addAdmin(Admin admin);

	void updateAdmin(Admin admin, HttpServletResponse response) throws Exception;

	void addAdmin(Admin admin, HttpServletResponse response)throws Exception;

	void deleteAdmin(String ids, HttpServletResponse response)throws Exception;

	void updPsd(String oldpassword, String password,
				HttpServletResponse response) throws Exception;

}
