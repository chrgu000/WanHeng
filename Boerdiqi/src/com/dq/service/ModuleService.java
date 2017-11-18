package com.dq.service;

import java.util.List;
import java.util.Map;

import com.dq.entity.Module;

public interface ModuleService{
	List<Module> getAllModule();

	List<Module> getModulesByMap(Map<String,Object> map);
	
	List<Module> getTopModules();

	List<Integer> getModulesByRoleId(Integer role_id);

	void deleteRoleModuleByRoleId(Integer role_id);

	void addRoleModule(Map<String, Object> map);
}
