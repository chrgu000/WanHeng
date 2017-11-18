package com.to.serviceImpl;


import com.to.dao.ModuleDao;
import com.to.entity.Module;
import com.to.service.ModuleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service("moduleService")
@Transactional
public class ModuleServiceImpl implements ModuleService {
@Resource
private ModuleDao dao;
	public List<Module> getAllModule() {
		return dao.getAllModule();
	}

	public List<Module> getModulesByMap(Map<String, Object> map) {
		return dao.getModulesByMap(map);
	}

	public List<Module> getTopModules() {
		return dao.getTopModules();
	}

	public List<Integer> getModulesByRoleId(Integer role_id) {
		return dao.getModulesByRoleId(role_id);
	}

	public void deleteRoleModuleByRoleId(Integer roleId) {
		dao.deleteRoleModuleByRoleId(roleId);
	}

	public void addRoleModule(Map<String, Object> map) {
		dao.addRoleModule(map);
	}

}
