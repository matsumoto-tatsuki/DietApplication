package com.example.dietApplication.controller.dietListController;

import com.example.dietApplication.dao.DietDao;
import com.example.dietApplication.entity.DietInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DietListService {
    @Autowired
    DietDao dietDao;

    public List<DietInfo> getDietList(){
        return dietDao.getDietList();
    }
}
