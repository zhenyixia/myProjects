package com.lyp.springboot01.common.exception;

public class MyException extends Exception {

  private String message;

  //构造函数
  public MyException(String message) {
    super(message);
    this.message = message;
  }

}
