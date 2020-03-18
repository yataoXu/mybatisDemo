package com.evan.bean;

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
