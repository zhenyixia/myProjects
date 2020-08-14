package com.lyp.springboot01.authmanage.service.impl;

import com.lyp.springboot01.authmanage.mapper.UserMapper;
import com.lyp.springboot01.authmanage.model.QueryUserVO;
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
  private UserMapper userMapper;


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

  @Override
  public JsonResult deleteUser(long id) {
    log.info("Begin to delete user by id:{}.", id);
    int result = userMapper.delete(id);

    log.info("Delete user number is: {}.", result);
    String msg = MessageFormat.format("成功删除{0}条数据", result);
    return JsonResult.success(msg);
  }

  @Override
  public JsonResult updateUser(User user) {
    log.info("Begin to update user.");
    int updateNum = userMapper.update(user);

    log.info("Update user number is: {}.", updateNum);
    if (updateNum == 1) {
      return JsonResult.success("修改成功");
    }

    return JsonResult.fail("修改失败，请刷新重试");
  }


  @Override
  public JsonResult findAllUser(QueryUserVO queryVO) {
    int page = queryVO.getPage();
    int size = queryVO.getSize();
    int beginIndex = (page - 1) * size;
    queryVO.setBeginIndex(beginIndex);

    List<User> users = userMapper.selectByCondition(queryVO);
    log.info("Query users from database successfully. user number is: {}.", users.size());
    return JsonResult.success("查询成功", users);
  }
}
