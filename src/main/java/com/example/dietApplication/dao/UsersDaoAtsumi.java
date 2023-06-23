package com.example.dietApplication.dao;

import com.example.dietApplication.entity.User;
import com.example.dietApplication.entity.UserInfo;
import com.example.dietApplication.entity.UserLogin;
import com.example.dietApplication.form.AdminIdForm;
import com.example.dietApplication.form.AdminPassForm;
import com.example.dietApplication.form.InsertUserForm;
import com.example.dietApplication.form.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

public class UsersDaoAtsumi implements UsersDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public UserLogin getUserLogin(UserForm userFrom) {
        return null;
    }

    @Override
    public int insertUser(InsertUserForm insertUserFrom) {
        return 0;
    }

    @Override
    public UserLogin getUserIdCheck(InsertUserForm insertUserForm) {
        return null;
    }

    @Override
    public UserInfo getUserInfo(String userId) {
        return null;
    }

    @Override
    public int getAllUserNum() {
        return 0;
    }

    @Override
    public List<User> getAllUser() {
        return null;
    }

    //管理者ID変更
    @Override
    public int updateAdminId(AdminIdForm adminIdFrom) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("user_id",adminIdFrom.getAdminId());
        return jdbcTemplate.update("UPDATE users SET user_id = :user_id WHERE permission = 1",param);
    }

    //管理者パスワード変更
    @Override
    public int updateAdminPass(AdminPassForm adminPassForm) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("password",adminPassForm.getAdminPassword());
        return jdbcTemplate.update("UPDATE users SET password = :password WHERE permission = 1",param);
    }

}
