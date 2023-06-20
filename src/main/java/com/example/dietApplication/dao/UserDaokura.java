package com.example.dietApplication.dao;

import com.example.dietApplication.dao.UsersDao;
import com.example.dietApplication.entity.User;
import com.example.dietApplication.entity.UserInfo;
import com.example.dietApplication.entity.UserLogin;
import com.example.dietApplication.form.AdminIdForm;
import com.example.dietApplication.form.AdminPassForm;
import com.example.dietApplication.form.InsertUserForm;
import com.example.dietApplication.form.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public  class UserDaokura implements UsersDao {

    private String userId;

    private String userName;
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
    public UserInfo getUserInfo(String userId) {
        return null;
    }

    @Override
    public int getAllUserNum() {
        return 0;
    }

    @Override
    public List<User> getAllUser() {
        return jdbcTemplate.query("SELECT id ,name FROM diet ORDER BY id", new DataClassRowMapper<>(User.class));

    }

    @Override
    public int updateAdminId(AdminIdForm adminIdFrom) {
        return 0;
    }

    @Override
    public int updateAdminPass(AdminPassForm adminPassForm) {
        return 0;
    }


}
