package com.lyp.springboot01;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {Runner.class})
public class RunnerTests {

    //操作类型都string用这个
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    //有非string可以用这个
    @Autowired
    private RedisTemplate redisTemplate;


    @Test
    public void test1() {
        stringRedisTemplate.opsForValue().set("site3", "www.yidiankt.com");
//        String val = stringRedisTemplate.opsForValue().get("site");
//        System.out.println(val);
    }

    @Test
    public void test2() {
        User user = new User();
        user.setName("lyp");
        user.setPassword("123456");

        redisTemplate.opsForValue().set("user2", user);
//        String val = stringRedisTemplate.opsForValue().get("site");
//        System.out.println(val);
    }

}
