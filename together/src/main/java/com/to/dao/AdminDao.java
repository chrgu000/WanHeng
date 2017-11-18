package com.to.dao;

import com.to.entity.Admin;

import java.util.List;

/**
 * Created by Administrator on 2017/3/28 0028.
 */
public interface AdminDao extends BaseDao<Admin> {


    List<String> getPermissions(String username);

    Admin login(Admin admin);

    Admin getAdminByUsername(String username);
}
