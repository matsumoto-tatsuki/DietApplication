package com.example.dietApplication.dao;

import com.example.dietApplication.entity.User;
import com.example.dietApplication.entity.UserInfo;
import com.example.dietApplication.entity.UserLogin;
import com.example.dietApplication.form.AdminIdForm;
import com.example.dietApplication.form.AdminPassForm;
import com.example.dietApplication.form.InsertUserForm;
import com.example.dietApplication.form.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class UsersDaoZaKuA implements UsersDao{
    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    //ユーザログイン
    public UserLogin getUserLogin(UserForm userform){
        List<UserLogin> usersData = new ArrayList<>();
        var param = new MapSqlParameterSource();
        param.addValue("userId", userform.getUserId());
        String sql = "SELECT user_id,password,permission FROM users WHERE user_id = :userId";
        usersData = jdbcTemplate.query(sql, param, new DataClassRowMapper<>(UserLogin.class));
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
        return jdbcTemplate.update(sql, param);
    }


    //ユーザ情報
    public UserInfo getUserInfo(String userId){
        List<UserInfo> usersData = new ArrayList<>();
        var param = new MapSqlParameterSource();
        param.addValue("userId", userId);
        String sql = "SELECT id,user_id,user_symbol,user_name,weight FROM users WHERE user_id = :userId";
        usersData = jdbcTemplate.query(sql, param, new DataClassRowMapper<>(UserInfo.class));
        return usersData.isEmpty() ? null : usersData.get(0);
    }

    public UserLogin getUserIdCheck(InsertUserForm insertUserForm){
        List<UserLogin> usersData = new ArrayList<>();
        var param = new MapSqlParameterSource();
        param.addValue("userId", insertUserForm.getUserId());
        String sql = "SELECT user_id,password,permission FROM users WHERE user_id = :userId";
        usersData = jdbcTemplate.query(sql, param, new DataClassRowMapper<>(UserLogin.class));
        return usersData.isEmpty() ? null : usersData.get(0);
    }

    /* 管理者 */
    //全ユーザ数取得
    public int getAllUserNum(){
        return 0;
    }

    //全ユーザ情報取得
    public List<User> getAllUser() {
        return jdbcTemplate.query("SELECT id ,name FROM diet ORDER BY id", new DataClassRowMapper<>(User.class));
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
