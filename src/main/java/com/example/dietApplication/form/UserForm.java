package com.example.dietApplication.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserForm {
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_-]+$",message = "半角英数字記号(-_)で入力してください。")
    private String userId;
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9_-]+$",message = "半角英数字記号(-_)で入力してください。")
    private String password;
}
