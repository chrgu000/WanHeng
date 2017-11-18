package com.jxc.service;

import java.util.List;

import com.jxc.entity.Admin;
import com.jxc.page.Page;

public interface AdminService {
	boolean addAdmin(Admin admin);//ע��

	boolean updateAdmin(Admin admin);

	boolean deleteAdminById(Integer id);

	Admin findAdminByUsername(String userName);
	
	Admin findAdminById(Integer id);

	Admin login(Admin admin);//��¼

	List<Admin> findAllAdminByPage(Page page);//��ҳ��ѯ

	Integer findRows();

	List<Admin> findAllAdmin();//��ѯȫ���û�
}