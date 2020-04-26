package com.lyp.springboot01.authmanage.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 全局异常处理
 */
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
}
