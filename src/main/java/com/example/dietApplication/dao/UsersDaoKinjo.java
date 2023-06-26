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

import java.util.List;

@Repository
public class UsersDaoKinjo implements UsersDao{
//DBと連携するところ
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public UserLogin getUserLogin (UserForm userFrom) {
        return null;
    }

    @Override
    public int insertUser(InsertUserForm insertUserFrom) {
        return 0;
    }


    @Override
    public UserInfo getUserInfo(String userId) {
        var list = jdbcTemplate.query("SELECT * FROM account WHERE user_id = :userId",
                new MapSqlParameterSource("userId",userId),
                new DataClassRowMapper<>(UserInfo.class));
        return list.isEmpty()? null: list.get(0);
    }



    @Override
    public int update(String userName ,String userId){
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("user_id",userId);
        param.addValue("userName",userName);
        String sql = "UPDATE account SET user_name = :userName WHERE user_id = :user_id";
        return  jdbcTemplate.update(sql,param);
//      return jdbcTemplate.update("INSERT INTO account VALUES(:userName) WHERE id = userId",param);
    }

    @Override
    public int upIcon(String Icon, String userId){
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("user_id",userId);
        param.addValue("user_symbol",Icon);
        String sql = "UPDATE account SET user_symbol = :user_symbol WHERE user_id = :user_id";
        return  jdbcTemplate.update(sql,param);
    }


    @Override
    public int getAllUserNum() {
        return 0;
    }

    @Override
    public List<User> getAllUser() {
        return null;
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