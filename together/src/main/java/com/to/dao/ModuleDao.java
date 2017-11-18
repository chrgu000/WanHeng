package com.to.dao;

import com.to.entity.Module;

import java.util.List;
import java.util.Map;

/**
 * @Author :yangjun on 2017/3/28 0028.
 */
public interface ModuleDao {
    List<Module> getAllModule();

    List<Module> getModulesByMap(Map<String,Object> map);

    List<Module> getTopModules();

    List<Integer> getModulesByRoleId(Integer role_id);

    void deleteRoleModuleByRoleId(Integer role_id);

    void addRoleModule(Map<String, Object> map);
}
