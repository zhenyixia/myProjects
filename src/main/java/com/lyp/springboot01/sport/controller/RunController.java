package com.lyp.springboot01.sport.controller;

import com.lyp.springboot01.common.bean.JsonResult;
import com.lyp.springboot01.sport.model.QueryRunVO;
import com.lyp.springboot01.sport.service.RunService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 跑步控制层
 */
@Slf4j
@RestController
@RequestMapping("/run")
@Api(tags = "跑步api操作")
public class RunController {

  @Autowired
  RunService runService;

  /**
   * 导入一周的运动信息
   *
   * @return JsonResult
   */
  @ApiOperation(value = "导入一周的跑步数据")
  @PostMapping(value = "add")
  public JsonResult importOneWeek(MultipartFile file) {
    log.info("Begin to come in import one week run info interface.");
    return runService.importOneWeekRun(file);
  }

  /**
   * 列举当前月份每天的运动信息，加上总运动次数，总跑步公里数
   *
   * @return JsonResult
   */
  @ApiOperation(value = "查询当前月每天的运动信息")
  @PostMapping(value = "list/curMonth")
  public JsonResult listCurMonthRunInfo(@RequestBody QueryRunVO queryVO) {
    log.info("Begin to come in add interface.");
    return runService.listCurMonth(queryVO);
  }
}
