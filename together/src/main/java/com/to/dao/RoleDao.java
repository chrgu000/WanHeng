package com.to.dao;

import com.to.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * @Author :yangjun on 2017/3/28 0028.
 */
public interface RoleDao extends BaseDao<Role>{

    Role getRoleByName(String name);

    void deleteRoleModule(Map<String, String[]> map);

    List<Role> getAllRole();
}
