package com.lyp.springboot01.authmanage.service;

import com.lyp.springboot01.authmanage.model.User;

import java.util.List;

public interface UserService {

  List<User> findAllUser(int pageNum, int pageSize);

  User findById(int id);

  int addUser(User user) throws Exception;

  int deleteUser(int id);

  int updateUser(User user);
}
