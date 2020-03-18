package com.evan1.mapper;


import com.evan1.pojo.User;

public interface UserMapper {

    User getUser(Long id);

    int insertUser(User user);

    int deleteUser(Long id);
}
