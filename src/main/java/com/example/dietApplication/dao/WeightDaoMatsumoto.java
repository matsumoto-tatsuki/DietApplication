package com.example.dietApplication.dao;

import com.example.dietApplication.entity.Calendar;
import com.example.dietApplication.entity.Memo;
import com.example.dietApplication.entity.UserWeight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WeightDaoMatsumoto implements WeightDao{

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public List<UserWeight> getUsersWeight(Calendar calendar) {
        return null;
    }

    @Override
    public UserWeight getUserWeight(Calendar calendar,String userId) {
        System.out.println("WeightDaoMatsumotoCheck(getUserWeight)");
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("date",calendar.getCalendar());
        param.addValue("userId",userId);
        var list = jdbcTemplate.query("SELECT id userId\n" +
                        "       ,weight\n" +
                        " FROM weight\n" +
                        " WHERE date = :date\n" +
                        " AND user_id = :userId",param,
                new DataClassRowMapper<>(UserWeight.class));
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public int updateUserWeight(UserWeight userWeight) {
        return 0;
    }
}
