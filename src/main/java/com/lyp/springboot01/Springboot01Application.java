package com.lyp.springboot01;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
@MapperScan("com.lyp.springboot01.authmanage.mapper")
public class Springboot01Application {

  public static void main(String[] args) {
    SpringApplication.run(Springboot01Application.class, args);
  }
}
