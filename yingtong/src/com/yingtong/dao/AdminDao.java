package com.yingtong.dao;

import java.util.List;

import com.yingtong.entity.Admin;
import com.yingtong.page.Page;

public interface AdminDao {
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
