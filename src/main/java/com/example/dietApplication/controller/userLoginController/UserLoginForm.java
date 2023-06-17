package com.example.dietApplication.controller.userLoginController;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserLoginForm {
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9_-]+$",message = "半角英数字記号(-_)で入力してください。")
    private String userId;
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9_-]+$",message = "半角英数字記号(-_)で入力してください。")
    private String password;
}
