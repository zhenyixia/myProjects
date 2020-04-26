package com.lyp.springboot01.authmanage.mapper;

import com.lyp.springboot01.authmanage.model.User;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {

  @Select("select * from auth_user")
  List<User> queryAll();

  @Select("select * from auth_user where id = #{id}")
  User findById(int id);

  int insert(User user);

  @Delete("delete from auth_user where id = #{id}")
  int delete(int id);

  @Update("update auth_user set name=#{name},password=#{password},phone=#{phone} where id = #{id}")
  int update(User user);

  List<User> getUserAll();

  User getUserById(Integer id);
}
