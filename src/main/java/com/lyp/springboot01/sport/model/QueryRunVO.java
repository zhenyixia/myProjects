package com.lyp.springboot01.sport.model;

import com.lyp.springboot01.common.bean.Page;
import java.util.Date;
import lombok.Data;

@Data
public class QueryRunVO extends Page {

  private Long id;

  private String address;

  private Integer year;

  private Integer month;

  /**
   * 查询一周数据，
   */
  private Integer week;

  private Date createTime;
}
