package com.lyp.springboot01.sport.model;

import java.util.Date;
import lombok.Data;

@Data
public class SportDetail {
  private Long id;

  private String name;

  private String desc;

  private String address;

  private String avgSpeed;

  private Date sportDate;

  private Integer duration;

  private Date beginTime;

  private Date endTime;

  private Integer year;

  private Integer month;

  private String weekDay;

  private String weekDateInterval;

  private Date updateTime;

}
