package com.lyp.springboot01.common.util;

import com.lyp.springboot01.common.exception.MyException;
import org.springframework.validation.BindingResult;

/**
 * 错误处理工具类
 */
public class ErrorHandlUtils {

  /**
   * 前端参数校验消息处理
   */
  public static void processBindResult(BindingResult bindingResult) throws MyException {
    if (bindingResult != null && bindingResult.hasErrors()) {
      StringBuilder errorMsg = new StringBuilder();
      bindingResult.getAllErrors().forEach(error -> errorMsg.append("[").append(error.getObjectName())
          .append("-").append(error.getDefaultMessage()).append("]"));
      throw new MyException(errorMsg.toString());
    }
  }

}
