package com.lyp.springboot01.sport.mapper;

import com.lyp.springboot01.sport.model.QueryRunVO;
import com.lyp.springboot01.sport.model.RunDetail;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
//@Repository
public interface RunDetailMapper {

  int batchInsert(List<RunDetail> runDetails);

  List<RunDetail> selectByMonth(QueryRunVO queryVO);
}