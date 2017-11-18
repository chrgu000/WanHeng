package com.to.dao;

import com.to.entity.User;

import java.util.List;

/**
 * @Author :yangjun on 2017/3/28 0028.
 */
public interface UserDao extends BaseDao<User>{
    List<String> findAllTel();

    User login(User user);

    void updatePwdByTel(User user);

    List<String> findAllAreaByCity(String city);
}
