package com.lyp.springboot01.authmanage.controller;

import com.lyp.springboot01.authmanage.model.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CacheConfig(cacheNames = "user") //统一代替 @Cacheable(value = "user") 中的value
public class RedisController {

   /* @GetMapping("/user/{id}")
    public User getUser(@PathVariable int id) {
        User user = new User();
        user.setPassword("12345");
        user.setId(id);
        user.setName("lyp" + id);
        System.out.println("查询数据库getUser: " + id);
        return user;
    }*/


    @Cacheable
    @GetMapping("/cache/user/{id}")
    public User getCacheUser(@PathVariable int id) {
        User user = new User();
        user.setPassword("12345");
        user.setId(id);
        user.setName("lyp" + id);
        System.out.println("查询数据库getUser: " + id);
        return user;
    }


    @CachePut(value = "user")
    @GetMapping("/update/user/{id}")
    public User updateUser(@PathVariable int id) {
        User user = new User();
        user.setPassword("12345");
        user.setId(123);
        user.setName("lyp" + id + "--update");
        System.out.println("update id: " + id);
        return user;
    }


    @CacheEvict(value = "user", key = "#id")
    @GetMapping("/delete/user/{id}")
    public void deleteUser(@PathVariable int id) {
        System.out.println("删除数据库user：" + id);
    }



}
