package com.example.dietApplication.form;

import lombok.Data;

@Data
public class UserDietForm {
    private String dietName;
    private String action;
    private String startDate;
    private String finishDate;
}
