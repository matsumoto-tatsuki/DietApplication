package com.example.dietApplication.controller;

import com.example.dietApplication.entity.DietResultDate;
import com.example.dietApplication.service.CalenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TopRestController {

    @Autowired
    private CalenderService calenderService;

    @GetMapping("/api/calenderResult/{year}/{month}")
    public List<DietResultDate> calenderResult(@PathVariable("year") int year,@PathVariable("month") int month){
        var userId = "testuser";
        return calenderService.getCalenderResult(year,month,userId);
    }
}
