package com.lyp.springboot01.sport.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

@Data
public class RunDetail {

  private Long id;

  private BigDecimal kilometer;

  private String address;

  /**
   * 运动时长，单位为秒
   */
  private int runSecond;

  /**
   * 平均速度，每小时跑多少公里，如：6.8
   */
  private BigDecimal kmByHour;

  /**
   * 平均配速，即每公里用时，如8分11秒每公里
   */
  private BigDecimal timeByKm;

  @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
  private Date runDate;
}
