package com.lyp.springboot01.authmanage.service.impl;

import com.lyp.springboot01.authmanage.mapper.UserMapper;
import com.lyp.springboot01.authmanage.model.User;
import com.lyp.springboot01.authmanage.service.UserService;
import com.lyp.springboot01.common.bean.JsonResult;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

@Service(value = "userService")
@Slf4j
public class UserServiceImpl implements UserService {

  @Autowired
  UserMapper userMapper;

  @Override
  public List<User> findAllUser(int pageNum, int pageSize) {
    return userMapper.queryAll();
  }

  @Override
  public User findById(int id) {
    return userMapper.getUserById(id);
  }

  @Transactional
  @Override
  public JsonResult addUser(User user) throws Exception {
    log.info("Begin to add user to database.");
    user.setCreateTime(new Date());
    userMapper.insert(user);
    log.info("Add user info successfully. id  is {}", user.getId());
    return JsonResult.success("添加用户成功");
  }

  @Override
  public JsonResult deleteUser(long id) {
    log.info("Begin to delete user by id:{}.", id);
    int result = userMapper.delete(id);

    log.info("Delete user number is: {}.", result);
    String msg = MessageFormat.format("成功删除{0}条数据", result);
    return JsonResult.success(msg);

  }

  @Override
  public int updateUser(User user) {
    return userMapper.update(user);
  }

  @Override
  public Object batchImportBooks(MultipartFile file) {
    if (file.isEmpty()) {
      return JsonResult.fail("导入文件为空");
    }

    JsonResult result = new JsonResult();
    //记录集合
    List<User> mapList = new ArrayList<>();

    int insertNum = 200;
    if (!CollectionUtils.isEmpty(mapList)) {
      for (int i = 0, size = mapList.size(); i < size; i += insertNum) {
        int toIndex = Math.min(i + insertNum, size);
        List<User> tempList = mapList.subList(i, toIndex);
        userMapper.batchInsertUsers(tempList);
      }
    }

    return result;
  }
}
