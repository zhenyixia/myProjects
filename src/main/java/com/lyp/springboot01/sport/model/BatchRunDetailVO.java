package com.lyp.springboot01.sport.model;

import java.util.List;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class BatchRunDetailVO {

  @Valid
  @NotEmpty
  List<RunDetailVO> runDetailVOList;
}
