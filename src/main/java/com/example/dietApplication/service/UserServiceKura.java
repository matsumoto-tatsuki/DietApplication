package com.example.dietApplication.service;

import com.example.dietApplication.dao.UserDaokura;
import com.example.dietApplication.dao.UsersDao;
import com.example.dietApplication.entity.User;
import com.example.dietApplication.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceKura  {
    @Autowired
    private UsersDao userDao;

    public List<User> getAllUser() {
        return userDao.getAllUser();
    }

    public int getAllUserNum() {
        return userDao.getAllUserNum();
    }

    public List<User> userDateId(UserInfo userInfo) {
        return userDao.userDateId(userInfo);
    }
}
