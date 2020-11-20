package com.lyp.springboot01.sport.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RunDetailVO {

  @NotNull
  private String address;

  @Pattern(regexp = "(\\d+)|(\\d+\\.\\d+)")
  private String kilometer;

  /**
   * 跑步日期，格式如： 10.26表示10月26号
   */
  @JsonFormat(pattern = "MM.dd", timezone = "GMT+8")
  private String runDate;

  /**
   * 跑步持续时长，格式为：10.34 表示10分34秒
   */
  @Pattern(regexp = "(\\d+)|(\\d+\\.\\d+)")
  private String lastTime;
}
