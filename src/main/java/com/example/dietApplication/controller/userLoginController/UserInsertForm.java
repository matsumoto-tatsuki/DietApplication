package com.example.dietApplication.controller.userLoginController;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UserInsertForm {
    @NotEmpty
    private String userId;
    @NotEmpty
    private String password;
    private String weight;
}
