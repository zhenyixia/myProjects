package com.lyp.springboot01.authmanage.service;

import com.lyp.springboot01.authmanage.model.User;
import com.lyp.springboot01.common.bean.JsonResult;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

  List<User> findAllUser(int pageNum, int pageSize);

  User findById(int id);

  JsonResult addUser(User user) throws Exception;

  JsonResult deleteUser(long id);

  int updateUser(User user);

  Object batchImportBooks(MultipartFile file);
}
