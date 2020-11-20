package com.lyp.springboot01.authmanage.controller;

import com.lyp.springboot01.common.bean.JsonResult;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 全局异常处理
 *
 * ControllerAdvice，这是一个增强的 Controller。使用这个 Controller ，可以实现三个方面的功能： 1,全局异常处理 2,全局数据绑定 3,全局数据预处理
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler({ArithmeticException.class})
  @ResponseBody
  public Map<String, Object> handleArithmeticException(ArithmeticException e) {
    Map<String, Object> map = new HashMap<>();
    e.printStackTrace();
    map.put("errorCode", "201");
    map.put("errorMsg", "算数异常" + e.getMessage());
    return map;
  }

  @ExceptionHandler(Exception.class)
  @ResponseBody
  public Map<String, Object> handleException(Exception e) {
    e.printStackTrace();
    Map<String, Object> map = new HashMap<>();
    map.put("errorCode", "201");
    map.put("errorMsg", "未知异常" + e.getMessage());
    return map;
  }

  /**
   * 参数校验异常类
   */
  @ResponseStatus(HttpStatus.OK)
  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseBody
  public JsonResult handlerConstraintViolationException(ConstraintViolationException exception) {
    log.warn(exception.getMessage(), exception);
    Set<ConstraintViolation<?>> constraintViolations = exception.getConstraintViolations();
    StringBuilder strBuilder = new StringBuilder();
    for (ConstraintViolation<?> constraintViolation : constraintViolations) {
      String message = constraintViolation.getMessage();

      strBuilder.append(message).append("\n");
    }
    return JsonResult.validFail(strBuilder.toString());
  }
}
