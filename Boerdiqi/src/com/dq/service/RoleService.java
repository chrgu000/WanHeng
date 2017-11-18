package com.dq.service;

import java.util.List;

import com.dq.entity.Role;

public interface RoleService extends BaseService<Role>{
	List<Role> getAllRole(); 
}
