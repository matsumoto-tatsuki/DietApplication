package com.example.dietApplication.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
@Data
public class Calendar {
    private LocalDate calendar;
    private String date;

    public Calendar(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.calendar = LocalDate.parse(date, formatter);
        this.date = date;
    }
}
