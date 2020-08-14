package com.lyp.springboot01.authmanage.service;

import com.lyp.springboot01.authmanage.model.QueryUserVO;
import com.lyp.springboot01.authmanage.model.User;
import com.lyp.springboot01.common.bean.JsonResult;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface UserService {

  JsonResult addUser(User user) throws Exception;

  Object batchImportBooks(MultipartFile file);

  JsonResult deleteUser(long id);

  JsonResult updateUser(User user);

  JsonResult findAllUser(QueryUserVO queryVO);
}
