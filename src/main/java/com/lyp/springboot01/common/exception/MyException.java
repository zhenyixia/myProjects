package com.lyp.springboot01.common.exception;

import java.text.MessageFormat;

public class MyException extends Exception {

  private String message;

  //构造函数
  public MyException(String message) {
    super(message);
    this.message = message;
  }

  //构造函数
  public MyException(String message,Object...params) {
    super(message);
    this.message = MessageFormat.format(message,params);
  }

}
