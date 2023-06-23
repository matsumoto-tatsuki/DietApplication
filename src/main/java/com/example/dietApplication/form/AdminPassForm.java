package com.example.dietApplication.form;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class AdminPassForm {
    @NotBlank
    @Length(min = 8, max = 30)
    @Pattern(regexp = "[A-Za-z0-9]+", message = "英数字のみ使用できます")
    private String adminPassword;

    @NotBlank
    private String adminPassword2;
}
