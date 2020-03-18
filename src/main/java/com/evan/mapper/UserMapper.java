package com.evan.mapper;


import com.evan.bean.User;
import org.apache.ibatis.annotations.Select;

public interface UserMapper {
    @Select(value = "select * from user where id=#{id}")
    User getUser(Long id);
}
