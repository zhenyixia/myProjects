package com.lyp.springboot01.test.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 加密测试
 */
@RestController
public class JasyptController {

    @Value("${mail.auth.password}")
    private String password;

    @RequestMapping("/hello")
    public String testJasypt() {
        return password;
    }
}
