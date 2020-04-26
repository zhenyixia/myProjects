package com.lyp.springboot01.common.controller;


import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

/**
 * 注解@ControllerAdvice的类可以拥有@ExceptionHandler, @InitBinder或 @ModelAttribute注解的方法，
 * 启动应用后，被 @ExceptionHandler、@InitBinder、@ModelAttribute 注解的方法，
 * 都会作用在 被@RequestMapping 注解的方法上
 *
 * @RestControllerAdvice 类似于 @RestController 与 @Controller的区别
 */

@ControllerAdvice
public class BaseController {
    @InitBinder
    public void initBinder(WebDataBinder binder) {

    }
}
