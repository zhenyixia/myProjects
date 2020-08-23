package com.lyp.springboot01.sport.service.impl;

import com.lyp.springboot01.common.bean.JsonResult;
import com.lyp.springboot01.sport.model.QuerySportVO;
import com.lyp.springboot01.sport.model.SportDetail;
import com.lyp.springboot01.sport.service.SportDetailService;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class SportDetailServiceImpl implements SportDetailService {

  @Override
  public JsonResult addOneSport(SportDetail sportDetail) {
    return null;
  }

  @Override
  public JsonResult batchAdd(MultipartFile sportDetails) {
    return null;
  }

  @Override
  public JsonResult findByCondition(@Valid QuerySportVO queryVO) {
    return null;
  }
}
