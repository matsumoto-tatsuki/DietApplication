package com.example.dietApplication.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserDietForm {
    private String dietName;
    private String action;
    private String startDate;
    private String finishDate;

    public UserDietForm(String dietName, String action, String startDate, String finishDate) {
        this.dietName = dietName;
        this.action = action;
        this.startDate = startDate;
        this.finishDate = finishDate;
    }

    public UserDietForm() {
    }
}
