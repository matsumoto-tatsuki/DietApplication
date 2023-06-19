package com.example.dietApplication.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
@Data
public class Calender {
    private LocalDate calender;
    private String date;

    public Calender(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        this.calender = LocalDate.parse(date, formatter);
        this.date = date;
    }
}
