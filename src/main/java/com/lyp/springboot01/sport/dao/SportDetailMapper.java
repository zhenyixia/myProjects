package com.lyp.springboot01.sport.dao;

import com.lyp.springboot01.sport.model.SportDetail;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SportDetailMapper {

  int deleteByPrimaryKey(Long id);

  int insert(SportDetail record);

  int insertSelective(SportDetail record);

  SportDetail selectByPrimaryKey(Long id);

}