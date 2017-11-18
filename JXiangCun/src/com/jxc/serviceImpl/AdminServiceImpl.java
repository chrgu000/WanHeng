package com.jxc.serviceImpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jxc.dao.AdminDao;
import com.jxc.entity.Admin;
import com.jxc.page.Page;
import com.jxc.service.AdminService;
@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService{
@Resource 
private AdminDao dao;
	public boolean addAdmin(Admin admin) {
		return dao.addAdmin(admin);
	}

	public boolean updateAdmin(Admin admin) {
		 
		return dao.updateAdmin(admin);
	}

	public boolean deleteAdminById(Integer id) {
		return dao.deleteAdminById(id);
	}

	public Admin findAdminByUsername(String userName) {
		return dao.findAdminByUsername(userName);
	}

	public Admin findAdminById(Integer id) {
		return dao.findAdminById(id);
	}

	public Admin login(Admin admin) {
		
		return dao.login(admin);
	}

	public List<Admin> findAllAdminByPage(Page page) {
		return dao.findAllAdminByPage(page);
	}

	public Integer findRows() {
		return dao.findRows();
	}

	public List<Admin> findAllAdmin() {
		return dao.findAllAdmin();
	}

}
