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

import java.util.List;

public class UsersDaokura implements UsersDao {

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
        var calender = new Calendar(adminDateSearch.getStart_date());
        var endcalender = new Calendar(adminDateSearch.getEnd_date());
        var param = new MapSqlParameterSource();
        param.addValue("start_date", calender.getCalendar());
        param.addValue("end_date", endcalender.getCalendar());
        var list = jdbcTemplate.query("SELECT * FROM users WHERE  insert_date between :start_date and :end_date ;\n",param ,new DataClassRowMapper<>(User.class));
        return list;
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
