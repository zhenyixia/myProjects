package com.lyp.springboot01.test.test;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTest {

  public static void getWeekFromDate() {
    String dateStr = "2020/10/25 15:25:59";
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("y/M/d H:m:s");
    LocalDateTime localTime = LocalDateTime.parse(dateStr, formatter);
    System.out.println(localTime);
    System.out.println(localTime.getDayOfWeek().getValue());
  }

  public static void main(String[] args) {
    getWeekFromDate();
  }
}
