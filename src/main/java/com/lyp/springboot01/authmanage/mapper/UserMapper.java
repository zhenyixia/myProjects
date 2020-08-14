package com.lyp.springboot01.authmanage.mapper;

import com.lyp.springboot01.authmanage.model.QueryUserVO;
import com.lyp.springboot01.authmanage.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

  List<User> selectByCondition(QueryUserVO queryVO);

  @Select("select * from tbl_user where id = #{id}")
  User findById(int id);

  int insert(User user);

  @Delete("delete from tbl_user where id = #{id}")
  int delete(long id);

//  @Update("update tbl_user set name=#{name},pwd=#{pwd},phone=#{phone} where id = #{id}")
  int update(User user);

  List<User> getUserAll();

  User getUserById(Integer id);

  void batchInsertUsers(List<User> tempList);
}
