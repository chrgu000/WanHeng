package com.yingtong.service;

import java.util.List;

import com.yingtong.entity.Admin;
import com.yingtong.page.Page;

public interface AdminService {
	boolean addAdmin(Admin admin);//注册

	boolean updateAdmin(Admin admin);

	boolean deleteAdminById(Integer id);

	Admin findAdminByUsername(String userName);
	
	Admin findAdminById(Integer id);

	Admin login(Admin admin);//登录

	List<Admin> findAllAdminByPage(Page page);//分页查询

	Integer findRows();

	List<Admin> findAllAdmin();//查询全部用户
}
