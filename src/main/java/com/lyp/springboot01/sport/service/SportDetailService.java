package com.lyp.springboot01.sport.service;

import com.lyp.springboot01.common.bean.JsonResult;
import com.lyp.springboot01.sport.model.QuerySportVO;
import com.lyp.springboot01.sport.model.SportDetail;
import javax.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

public interface SportDetailService {

  /**
   * 增加一次运动
   */
  JsonResult addOneSport(SportDetail sportDetail);

  /**
   * 批量添加运动
   * @param sportDetails
   */
  JsonResult batchAdd(MultipartFile sportDetails);

  JsonResult findByCondition(@Valid QuerySportVO queryVO);
}
