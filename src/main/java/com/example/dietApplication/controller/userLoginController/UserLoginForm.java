package com.example.dietApplication.controller.userLoginController;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserLoginForm {
    @NotEmpty
    private String userId;
    @NotEmpty
    private String password;
}
