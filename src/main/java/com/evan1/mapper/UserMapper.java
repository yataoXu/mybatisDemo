package com.evan1.mapper;


import com.evan1.pojo.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select(value = "select * from user where id=#{id}")
    User getUser(Long id);
}
