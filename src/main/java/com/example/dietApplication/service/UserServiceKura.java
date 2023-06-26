package com.example.dietApplication.service;

import com.example.dietApplication.dao.UsersDao;
import com.example.dietApplication.entity.AdminDateSearch;
import com.example.dietApplication.entity.AdminDietInfo;
import com.example.dietApplication.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceKura  {
    @Autowired
    private UsersDao userDao;

    public List<User> getAllUser() {

        return userDao.getAllUser();
    }

    public List<AdminDietInfo> getAllDiet() {

        return userDao.getAllDiet();
    }

    public int getAllUserNum() {

        return userDao.getAllUserNum();
    }

    public int getAllDietNum() {
        return userDao.getAllDietNum();
    }

    public List<User> userDate(AdminDateSearch adminDateSearch) {
        return userDao.userDate(adminDateSearch);
    }



}
