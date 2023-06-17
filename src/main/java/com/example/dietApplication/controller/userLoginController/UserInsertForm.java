package com.example.dietApplication.controller.userLoginController;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class UserInsertForm {
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9_-]+$",message = "半角英数字記号(-_)で入力してください。")
    private String userId;
    @NotEmpty
    @Pattern(regexp = "^[a-zA-Z0-9_-]+$",message = "半角英数字記号(-_)で入力してください。")
    private String password;
    @Pattern(regexp = "^[0-9]*$",message = "数字で入力してください。")
    private String weight;
}
