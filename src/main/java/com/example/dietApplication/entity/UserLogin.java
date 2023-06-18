package com.example.dietApplication.entity;

import lombok.Data;

@Data
public class UserLogin {
    private String userId;

    private String password;

    private int permission;
}
