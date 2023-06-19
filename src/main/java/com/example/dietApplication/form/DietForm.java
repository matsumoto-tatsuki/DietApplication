package com.example.dietApplication.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DietForm {
    @NotBlank
    private String dietName;
    @NotBlank
    private String communityName;
    @NotBlank
    private String categoryName;
    @NotBlank
    private String difficultName;
    @NotBlank
    private String dietDetail;
}
