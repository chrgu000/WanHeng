package com.kg.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.kg.entity.Admin;
import com.kg.page.Page;

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

	void addAdminGarden(Map<String, Object> map);

	void deleteAdminGarden(Integer id);

	List<Integer> getGardenIds(Integer id);

}
