package com.evan.bean;

import lombok.Data;

@Data
public class User {
    private Long id;
    private String userName;
    private String password;
    private String address;
}
