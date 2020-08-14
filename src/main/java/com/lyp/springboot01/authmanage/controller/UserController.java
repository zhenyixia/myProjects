package com.lyp.springboot01.authmanage.controller;

import static com.lyp.springboot01.common.utils.ErrorHandlUtils.processBindResult;

import com.lyp.springboot01.authmanage.model.User;
import com.lyp.springboot01.authmanage.service.UserService;
import com.lyp.springboot01.common.bean.JsonResult;
import com.lyp.springboot01.common.exception.MyException;
import com.lyp.springboot01.common.utils.ErrorHandlUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

/**
 * 用户管理控制器
 */
@Slf4j
@RestController
@RequestMapping("/user")
@Api(tags = "用户api操作")
public class UserController {

  @Autowired
  UserService userService;

  @ApiOperation(value = "添加用户信息")
  @RequestMapping(value = "add", method = RequestMethod.POST)
  public JsonResult addUser(@RequestBody @Valid User user, BindingResult bindingResult) throws Exception {
    log.info("Begin to come in add interface.");
    processBindResult(bindingResult);
    return userService.addUser(user);
  }

  @ApiOperation(value = "通过id来删除用户信息")
  @GetMapping(value = "deleteById")
  public JsonResult deleteById(@RequestParam long id) {
    log.info("Begin to come in deleteById interface.");
    return userService.deleteUser(id);
  }

  @ApiOperation(value = "获取所有用户信息")
  @RequestMapping(value = "getAll", method = RequestMethod.GET)
  public Object getAll() {

    System.out.println("测试重启，热部署");

    List<User> users = userService.findAllUser(0, 1);
    return users;
  }

  @ApiOperation(value = "通过id来用户信息")
  @RequestMapping(value = "getById", method = RequestMethod.GET)
  public Object getById(@RequestParam("id") @NotNull Integer id) {
    User user = userService.findById(id);
    return user;
  }


  @ApiOperation(value = "用get方法添加用户信息")
  @RequestMapping(value = "addByGet", method = RequestMethod.GET)
  public Object addByGet(@ModelAttribute("user") @NotNull User user) throws Exception {
    return userService.addUser(user);
  }


  @ApiOperation(value = "更新用户信息")
  @RequestMapping(value = "update", method = RequestMethod.POST)
  public Object update(@RequestBody @NotNull User user) {
    return userService.updateUser(user);
  }


  @ApiOperation(value = "批量导入课本信息")
  @GetMapping(value = "batchImport")
  public Object batchImport(MultipartFile file) {
    return userService.batchImportBooks(file);
  }
}
