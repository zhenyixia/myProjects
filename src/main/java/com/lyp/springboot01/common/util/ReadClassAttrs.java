package com.lyp.springboot01.common.util;

import com.lyp.springboot01.sport.model.RunDetailVO;
import java.lang.reflect.Field;

/**
 * 读取一个类的所有属性
 */
public class ReadClassAttrs {

  public static void main(String[] args) {
    Class claz = RunDetailVO.class;
    Field[] fields = claz.getDeclaredFields();
    StringBuilder sb = new StringBuilder();

    for (Field field : fields) {
      String attr = field.getName();
      sb.append("\"").append(attr).append("\",");
    }

    System.out.println(sb.toString());
    System.out.println(sb.deleteCharAt(sb.length()-1).toString());

  }
}
