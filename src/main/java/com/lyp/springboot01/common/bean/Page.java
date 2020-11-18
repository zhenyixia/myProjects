package com.lyp.springboot01.common.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.validation.constraints.Min;
import lombok.Data;

@Data
public class Page {

  @Min(value = 1, message = "最小值不能小于1")
  private int page;

  @Min(value = 1, message = "最小值不能小于1")
  private Integer size;

  @JsonIgnore
  private Integer beginIndex;
}
