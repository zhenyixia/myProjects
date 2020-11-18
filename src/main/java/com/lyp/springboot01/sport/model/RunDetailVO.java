package com.lyp.springboot01.sport.model;

import lombok.Data;

@Data
public class RunDetailVO {

  private String address;

  private String kilometer;

  /**
   * 跑步日期，格式如： 10月26号
   */
  private String runDate;

  /**
   * 跑步持续时长，格式为：10分34秒
   */
  private String lastTime;
}
