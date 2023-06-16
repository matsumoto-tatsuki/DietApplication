package com.example.dietApplication.dao;

import com.example.dietApplication.controller.userLoginController.UserInsertForm;
import com.example.dietApplication.controller.userLoginController.UserLoginData;
import com.example.dietApplication.controller.userLoginController.UserLoginForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UsersDao {
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    public int InsertUser(UserInsertForm userInsertForm){
        var param = new MapSqlParameterSource();

        int weight = 0;
//        if(userInsertForm.getWeight() != null) {
//            weight = Integer.parseInt(userInsertForm.getWeight());
//        }else{
//            weight = 0;
//        }

        param.addValue("userId",userInsertForm.getUserId());
        param.addValue("password",userInsertForm.getPassword());
        param.addValue("weight",weight);

        String sql = "INSERT INTO users (user_id,password,weight) VALUES(:userId,:password,:weight)";
        return namedParameterJdbcTemplate.update(sql,param);
    }

    public UserLoginData loginCheck(UserLoginForm userLoginForm){
        List<UserLoginData> usersData = new ArrayList<>();
        var param = new MapSqlParameterSource("",userLoginForm.getUserId());
        param.addValue("userId",userLoginForm.getUserId());
        String sql = "SELECT user_id,password FROM users WHERE user_id = :userId";
        usersData = namedParameterJdbcTemplate.query(sql,param,new DataClassRowMapper<>(UserLoginData.class));
        return usersData.isEmpty() ? null : usersData.get(0);
    }
}
