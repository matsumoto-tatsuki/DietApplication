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

import java.util.List;

@Repository
public  class UserDaokura implements UsersDao {
//
//    private String userId;
//
//    private String userName;
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
        List<User> dataList = getAllUser();
        return dataList.size();
    }
    @Override
    public int getAllDietNum() {
        List<AdminDietInfo> DietList = getAllDiet();
        return  DietList.size();
    }

    @Override
    public List<AdminDietInfo> getAllDiet() {
        return jdbcTemplate.query("SELECT * FROM diet_info ORDER BY id",new DataClassRowMapper<>(AdminDietInfo.class));
    }

    @Override
    public List<User> getAllUser() {
        return jdbcTemplate.query("SELECT * FROM users  ORDER BY id", new DataClassRowMapper<>(User.class));

    }

    @Override
    public List<User> userDate(AdminDateSearch adminDateSearch) {
        var calender = new Calender(adminDateSearch.getStart_date());
        var endcalender = new Calender(adminDateSearch.getEnd_date());
        var param = new MapSqlParameterSource();
        param.addValue("start_date", calender.getCalender());
        param.addValue("end_date", endcalender.getCalender());
        var list = jdbcTemplate.query("SELECT * FROM users WHERE  insert_date between :start_date and :end_date ;\n",param ,new DataClassRowMapper<>(User.class));
        return list;
    }

    @Override
    public List<User> userDateResult() {
        return jdbcTemplate.query("select end_date from users", new DataClassRowMapper<>());
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
