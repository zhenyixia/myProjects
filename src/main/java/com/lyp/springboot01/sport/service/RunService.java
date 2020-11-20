package com.lyp.springboot01.sport.service;

import com.lyp.springboot01.common.bean.JsonResult;
import com.lyp.springboot01.sport.model.BatchRunDetailVO;
import com.lyp.springboot01.sport.model.QueryRunVO;
import org.springframework.web.multipart.MultipartFile;

public interface RunService {

  /**
   * 批量导入运动记录
   *
   * @param file 导入文件
   * @return 响应结果
   */
  JsonResult importOneWeekRun(MultipartFile file);

  /**
   * 列出当前月的所有跑步信息
   * @return JsonResult
   * @param queryVO
   */
  JsonResult listCurMonth(QueryRunVO queryVO);

  /**
   * 批量添加跑步信息
   * @param runDetailVOS
   * @return
   */
  JsonResult addRunDetails(BatchRunDetailVO runDetailVOS);
}
