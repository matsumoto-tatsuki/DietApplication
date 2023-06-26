package com.example.dietApplication.Service;

import com.example.dietApplication.entity.UserInfo;
import com.example.dietApplication.dao.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    private UsersDao usersDao;

    public UserInfo getUserInfo(String userId){
        return usersDao.getUserInfo (userId);
    }

    public int userUp(String userName,String userId){
        return usersDao.update(userName,userId);
    }

    public int iconUp(String Icon, String userId){
        return usersDao.upIcon(Icon,userId);
    }
}