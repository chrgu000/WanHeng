package com.kg.dao;

import java.util.List;
import java.util.Map;

import com.kg.entity.Admin;
import com.kg.page.Page;

public interface AdminDao {
	List<Admin> getAdminByPage(Page page);

	Integer getRows(Page page);

	Admin login(Admin admin);

	List<String> getPermissions(String username);

	Admin getAdminById(Integer id);
	
	Admin getAdminByUsername(String username);

	void updateAdmin(Admin admin);

	void deleteByIds(Map<String, String[]> map);

	void addAdmin(Admin admin);
	
	void addAdminGarden(Map<String, Object> map);
	
	void deleteAdminGarden(Integer id);
	
	List<Integer> getGardenIds(Integer id);

	void deleteAdminGardenByIds(Map<String, String[]> map);
}
