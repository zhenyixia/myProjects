package com.lyp.springboot01.authmanage.controller;

import com.lyp.springboot01.authmanage.model.User;
import com.lyp.springboot01.authmanage.service.UserService;
import com.lyp.springboot01.common.bean.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/** 用户管理控制器 */
@Controller
@RequestMapping("/user")
@Api(tags = "用户api操作")
public class UserController {

  @Autowired UserService userService;

  @ApiOperation(value = "获取所有用户信息")
  @RequestMapping(value = "getAll", method = RequestMethod.GET)
  public @ResponseBody Object getAll() {
	  //模拟异常，测试全局异常处理
//    int x = 1/0;

//    String name = null;
//    name.toCharArray();

	  
    List<User> users = userService.findAllUser(0, 1);
    return users;
  }

  @ApiOperation(value = "通过id来用户信息")
  @RequestMapping(value = "getById", method = RequestMethod.GET)
  public @ResponseBody Object getById(@RequestParam("id") @NotNull Integer id) {
    User user = userService.findById(id);
    return user;
  }

  @ApiOperation(value = "添加用户信息")
  @RequestMapping(value = "add", method = RequestMethod.POST)
  // @Valid加一个就可以校验User类中定义的所有校验，包括hibernate的@Length等和javax自带的校验，不用再添加@NotNull等其它
  // BindingResult 对象必须在 @Valid 的紧挨着的后面，否则接收不到错误信息。
  //    BindingResult用于接收bean中的校验信息
  public @ResponseBody Object addUser(@RequestBody @Valid User user, BindingResult bindingResult) throws Exception {
    if (bindingResult.hasErrors()) {
      return JsonResult.fail(bindingResult.getFieldError().getDefaultMessage());
    }
    return userService.addUser(user);
  }

  @ApiOperation(value = "用get方法添加用户信息")
  @RequestMapping(value = "addByGet", method = RequestMethod.GET)
  public @ResponseBody Object addByGet(@ModelAttribute("user") @NotNull User user) throws Exception {
    return userService.addUser(user);
  }

  @ApiOperation(value = "通过id来删除用户信息")
  @RequestMapping(value = "deleteById", method = RequestMethod.GET)
  public @ResponseBody Object deleteById(@RequestBody @NotNull int id) {
    return userService.deleteUser(id);
  }

  @ApiOperation(value = "更新用户信息")
  @RequestMapping(value = "update", method = RequestMethod.POST)
  public @ResponseBody Object update(@RequestBody @NotNull User user) {
    return userService.updateUser(user);
  }
}
