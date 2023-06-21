package com.example.dietApplication.controller.userLoginController;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserLoginData {
    String userId;
    String password;
}
