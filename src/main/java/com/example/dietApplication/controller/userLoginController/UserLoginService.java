package com.example.dietApplication.controller.userLoginController;

import com.example.dietApplication.dao.UsersDao;
import com.example.dietApplication.entity.UserInfo;
import com.example.dietApplication.entity.UserLogin;
import com.example.dietApplication.form.InsertUserForm;
import com.example.dietApplication.form.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserLoginService {
    @Autowired
    UsersDao usersDao;

    public UserLogin getUserLogin(UserForm userform){
        return usersDao.getUserLogin(userform);
    }

    //新規登録
    public int insertUser(InsertUserForm insertUserFrom){
        return usersDao.insertUser(insertUserFrom);
    }

    public UserLogin getUserIdCheck(InsertUserForm insertUserForm){
        return usersDao.getUserIdCheck(insertUserForm);
    }

    //ユーザ情報
    public UserInfo getUserInfo(String userId){
        return usersDao.getUserInfo(userId);
    }
}
