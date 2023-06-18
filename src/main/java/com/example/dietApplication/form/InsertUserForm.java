package com.example.dietApplication.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class InsertUserForm {
    @NotBlank
    private String userId;
    @NotBlank
    private String password;
    @NotBlank
    private String weight;
}
