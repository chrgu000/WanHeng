package com.yingtong.dao;

import java.util.List;

import com.yingtong.entity.Phone;
import com.yingtong.entity.User;
import com.yingtong.page.Page;

public interface UserDao {
List<User> findAllUserByPage(Page page);

Integer findRows(Page page);

List<User> findAllUser();

User login(User user);

 User findUserById(Integer id);
 
 User findUserByUsername(String username);
 
 User findUserByTel(String tel);
 
boolean regist(User user);

boolean deleteUserById(Integer id);

boolean updateUser(User user);

boolean updatePwdByTel(User user);

boolean addPhone(Phone phone);

boolean updatePhone(Phone phone);

List<Phone> findPhoneByTel(String tel);
}
