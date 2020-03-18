package com.evan1.pojo;

import lombok.Data;

import java.util.Date;

@Data
public class User {
    private Long id;
    private String userName;
    private String password;
    private String address;
    private Date createAt;
    private Date upadateAt;

}
