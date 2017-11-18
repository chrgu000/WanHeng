package com.to.service;


import com.to.entity.Module;

import java.util.List;
import java.util.Map;

public interface ModuleService {
	List<Module> getAllModule();

	List<Module> getModulesByMap(Map<String, Object> map);
	
	List<Module> getTopModules();

	List<Integer> getModulesByRoleId(Integer role_id);

	void deleteRoleModuleByRoleId(Integer role_id);

	void addRoleModule(Map<String, Object> map);
}
