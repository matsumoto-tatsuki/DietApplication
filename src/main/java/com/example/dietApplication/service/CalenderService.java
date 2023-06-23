package com.example.dietApplication.service;

import com.example.dietApplication.dao.DietResultDao;
import com.example.dietApplication.entity.Calendar;
import com.example.dietApplication.entity.DietResult;
import com.example.dietApplication.entity.DietResultDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalenderService {
    @Autowired
    private DietResultDao dietResultDao;

    public List<DietResultDate> getCalenderResult(int year,int month,String userId){
        return dietResultDao.getCalenderResult(year,month,userId);
    }



}
