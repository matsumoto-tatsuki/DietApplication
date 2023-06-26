package com.example.dietApplication.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class AdminIdForm {
    @NotBlank
    @Pattern(regexp = "[A-Za-z0-9]+", message = "英数字のみ使用できます")
    private String adminId;
}
