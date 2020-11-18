package com.lyp.springboot01.sport.service.impl;

import com.lyp.springboot01.common.bean.JsonResult;
import com.lyp.springboot01.sport.model.QueryRunVO;
import com.lyp.springboot01.sport.model.RunDetail;
import com.lyp.springboot01.sport.service.SportDetailService;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class SportDetailServiceImpl implements SportDetailService {

  @Override
  public JsonResult addOneSport(RunDetail runDetail) {
    return null;
  }

  @Override
  public JsonResult batchAdd(MultipartFile sportDetails) {
    return null;
  }

  @Override
  public JsonResult findByCondition(@Valid QueryRunVO queryVO) {
    return null;
  }
}
