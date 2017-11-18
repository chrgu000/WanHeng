package com.kg.dao;

import java.util.List;
import java.util.Map;

import com.kg.entity.Module;

public interface ModuleDao {
List<Module> getAllModule();

List<Module> getModulesByMap(Map<String,Object> map);

List<Module> getTopModules();

List<Integer> getModulesByRoleId(Integer role_id);

void deleteRoleModuleByRoleId(Integer role_id);

void addRoleModule(Map<String, Object> map);
}
