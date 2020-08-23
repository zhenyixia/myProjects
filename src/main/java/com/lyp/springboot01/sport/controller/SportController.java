package com.lyp.springboot01.sport.controller;

import static com.lyp.springboot01.common.utils.ErrorHandlUtils.processBindResult;

import com.lyp.springboot01.authmanage.model.QueryUserVO;
import com.lyp.springboot01.authmanage.model.User;
import com.lyp.springboot01.authmanage.service.UserService;
import com.lyp.springboot01.common.bean.JsonResult;
import com.lyp.springboot01.common.exception.MyException;
import com.lyp.springboot01.sport.model.QuerySportVO;
import com.lyp.springboot01.sport.model.SportDetail;
import com.lyp.springboot01.sport.service.SportDetailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 运动控制器
 */
@Slf4j
@RestController
@RequestMapping("/sport")
@Api(tags = "运动api操作")
public class SportController {

  @Autowired
  SportDetailService sportDetailService;

  @ApiOperation(value = "添加用户信息")
  @RequestMapping(value = "add", method = RequestMethod.POST)
  public JsonResult addUser(@RequestBody @Valid SportDetail sportDetail, BindingResult bindingResult) throws Exception {
    log.info("Begin to come in add interface.");
    processBindResult(bindingResult);
    return sportDetailService.addOneSport(sportDetail);
  }

  @ApiOperation(value = "批量导入运动信息")
  @GetMapping(value = "batchImport")
  public Object batchImport(MultipartFile file) {
    return sportDetailService.batchAdd(file);
  }


  @ApiOperation(value = "获取用户信息")
  @PostMapping(value = "getUsers")
  public JsonResult getSportsByCondition(@RequestBody @Valid QuerySportVO queryVO, BindingResult bindingResult) throws MyException {
    log.info("Begin to come in get users interface.");
    processBindResult(bindingResult);
    return sportDetailService.findByCondition(queryVO);
  }


}
