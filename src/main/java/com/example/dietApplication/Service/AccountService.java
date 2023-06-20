package com.example.dietApplication.Service;

//import com.example.dietApplication.Record.ProductRecord;

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

}
