package com.example.dietApplication.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class AdminIdForm {
    @NotBlank
    private String adminId;
}
