package com.dq.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.dq.entity.Admin;
import com.dq.page.Page;

public interface AdminService extends BaseService<Admin>{
	
	 Admin login(Admin admin);
	 
	 List<String> getPermissions(String username);
	 
	void updPsd(String oldpassword, String password,
			HttpServletResponse response) throws Exception;

}
