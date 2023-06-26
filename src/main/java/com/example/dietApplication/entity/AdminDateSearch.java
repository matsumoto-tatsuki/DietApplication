package com.example.dietApplication.entity;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class AdminDateSearch {

    @NotEmpty(message = "空白はダメ！")
    private String start_date;
    @NotEmpty(message = "空白はダメ！")
    private String end_date;
}
