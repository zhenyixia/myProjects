package com.lyp.springboot01.authmanage.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.validation.constraints.Min;
import lombok.Data;

@Data
public class QueryUserVO {

  private long id;

  private String name;

  private int sex;

  private String role;

  private Date createTime;

  @Min(value = 1, message = "最小值不能小于1")
  private int page;

  @Min(value = 1, message = "最小值不能小于1")
  private int size;

  @JsonIgnore
  private int beginIndex;
}
