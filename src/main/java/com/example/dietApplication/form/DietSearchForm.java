package com.example.dietApplication.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DietSearchForm {
    private boolean favoriteSearch;
    private boolean selectSearch;
    private String categoryName;
    private String difficultName;
    private String keywordSearch;

}
