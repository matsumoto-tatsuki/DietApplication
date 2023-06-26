package com.example.dietApplication.entity;

import lombok.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

@Data
public class Calendar {
    private LocalDate calendar;
    private String date;

    public Calendar(String date){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");

        String pattern = "\\d{4}-\\d{2}-\\d{2}";

        if(Pattern.matches(pattern, date)){
            this.calendar = LocalDate.parse(date);
        }else{
            this.calendar = LocalDate.parse(date, formatter);
        }
        this.date = date;
    }
}
