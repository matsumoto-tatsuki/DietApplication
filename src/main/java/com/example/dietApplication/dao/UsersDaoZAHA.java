package com.example.dietApplication.dao;


import com.example.dietApplication.entity.*;
import com.example.dietApplication.form.AdminIdForm;
import com.example.dietApplication.form.AdminPassForm;
import com.example.dietApplication.form.InsertUserForm;
import com.example.dietApplication.form.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UsersDaoZAHA implements UsersDao {
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    //ユーザログイン
    public UserLogin getUserLogin(UserForm userform){
        List<UserLogin> usersData = new ArrayList<>();
        var param = new MapSqlParameterSource();
        param.addValue("userId", userform.getUserId());
        String sql = "SELECT user_id,password,permission FROM users WHERE user_id = :userId";
        usersData = namedParameterJdbcTemplate.query(sql, param, new DataClassRowMapper<>(UserLogin.class));
        return usersData.isEmpty() ? null : usersData.get(0);
    }

    //新規登録
    public int insertUser(InsertUserForm insertUserFrom)  {
        var param = new MapSqlParameterSource();
        int weight = 0;
        //体重が何も入力されていないときは0で初期化しておく。
        if (!insertUserFrom.getWeight().isEmpty()) {
            weight = Integer.parseInt(insertUserFrom.getWeight());
        }

        param.addValue("userId", insertUserFrom.getUserId());
        param.addValue("password", insertUserFrom.getPassword());
        param.addValue("weight", weight);
        param.addValue("userName", insertUserFrom.getUserId());

        String sql = "INSERT INTO users (user_id,password,weight,user_name,insert_date,permission) VALUES(:userId,:password,:weight,:userName,CURRENT_DATE,2)";
        return namedParameterJdbcTemplate.update(sql, param);
    }


    //ユーザ情報
    public UserInfo getUserInfo(String userId){
        List<UserInfo> usersData = new ArrayList<>();
        var param = new MapSqlParameterSource();
        param.addValue("userId", userId);
        String sql = "SELECT id,user_id,user_symbol,user_name,weight FROM users WHERE user_id = :userId";
        usersData = namedParameterJdbcTemplate.query(sql, param, new DataClassRowMapper<>(UserInfo.class));
        return usersData.isEmpty() ? null : usersData.get(0);
    }

    @Override
    public int update(String userName, String userId) {
        return 0;
    }

    @Override
    public int upIcon(String Icon, String userId) {
        return 0;
    }

    @Override
    public int deleteInfo(String userid) {
        return 0;
    }

    public UserLogin getUserIdCheck(InsertUserForm insertUserForm){
        List<UserLogin> usersData = new ArrayList<>();
        var param = new MapSqlParameterSource();
        param.addValue("userId", insertUserForm.getUserId());
        String sql = "SELECT user_id,password,permission FROM users WHERE user_id = :userId";
        usersData = namedParameterJdbcTemplate.query(sql, param, new DataClassRowMapper<>(UserLogin.class));
        return usersData.isEmpty() ? null : usersData.get(0);
    }

    /* 管理者 */
    //全ユーザ数取得
    public int getAllUserNum(){
        return 0;
    }

    //全ユーザ情報取得
    public List<User> getAllUser() {
        return null;
    }

    @Override
    public List<AdminDietInfo> getAllDiet() {
        return null;
    }

    @Override
    public int getAllDietNum() {
        return 0;
    }

    //管理者ID変更
    public int updateAdminId(AdminIdForm adminIdFrom){
        return 0;
    }

    //管理者パスワード変更
    public int updateAdminPass(AdminPassForm adminPassForm){
        return 0;
    }

    @Override
    public List<User> userDate(AdminDateSearch adminDateSearch) {
        return null;
    }

}

//    public int InsertUser(UserInsertForm userInsertForm) {
//        var param = new MapSqlParameterSource();
//        int weight = 0;
//        //体重が何も入力されていないときは0で初期化しておく。
//        if (!userInsertForm.getWeight().isEmpty()) {
//            weight = Integer.parseInt(userInsertForm.getWeight());
//        }
//
//        param.addValue("userId", userInsertForm.getUserId());
//        param.addValue("password", userInsertForm.getPassword());
//        param.addValue("weight", weight);
//
//        String sql = "INSERT INTO users (user_id,password,weight) VALUES(:userId,:password,:weight)";
//        return namedParameterJdbcTemplate.update(sql, param);
//    }
//
//    public UserIdData UserIdCheck(UserInsertForm userInsertForm) {
//        List<UserIdData> usersIdData = new ArrayList<>();
//        var param = new MapSqlParameterSource();
//        param.addValue("userId", userInsertForm.getUserId());
//        String sql = "SELECT user_id FROM users WHERE user_id = :userId";
//        usersIdData = namedParameterJdbcTemplate.query(sql, param, new DataClassRowMapper<>(UserIdData.class));
//        return usersIdData.isEmpty() ? null : usersIdData.get(0);
//    }
//
//    public UserLoginData loginCheck(UserLoginForm userLoginForm) {
//        List<UserLoginData> usersData = new ArrayList<>();
//        var param = new MapSqlParameterSource();
//        param.addValue("userId", userLoginForm.getUserId());
//        String sql = "SELECT user_id,password FROM users WHERE user_id = :userId";
//        usersData = namedParameterJdbcTemplate.query(sql, param, new DataClassRowMapper<>(UserLoginData.class));
//        return usersData.isEmpty() ? null : usersData.get(0);
//    }
