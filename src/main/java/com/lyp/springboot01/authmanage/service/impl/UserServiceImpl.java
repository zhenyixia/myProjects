package com.lyp.springboot01.authmanage.service.impl;

import com.lyp.springboot01.authmanage.mapper.UserMapper;
import com.lyp.springboot01.authmanage.model.User;
import com.lyp.springboot01.authmanage.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service(value = "userService")
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public List<User> findAllUser(int pageNum, int pageSize) {
        return userMapper.queryAll();
    }

    @Override
    public User findById(int id) {
        return userMapper.getUserById(id);
    }

    @Transactional
    @Override
    public int addUser(User user) throws Exception {
        userMapper.insert(user);

        log.info("id  is {}", user.getId());

        //模拟异常 测试事务注解
//        String str = null;
//        System.out.println(str.length());
//        user.setName(user.getName() + "1");
//        userMapper.insert(user);

        return 1;
    }

    @Override
    public int deleteUser(int id) {
        return userMapper.delete(id);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.update(user);
    }
}
