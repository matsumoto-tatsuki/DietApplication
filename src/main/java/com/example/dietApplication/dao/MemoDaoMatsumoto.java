package com.example.dietApplication.dao;

import com.example.dietApplication.entity.Calendar;
import com.example.dietApplication.entity.DietResult;
import com.example.dietApplication.entity.Memo;
import com.example.dietApplication.form.MemoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;


public class MemoDaoMatsumoto implements MemoDao{


    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public int insertMemo(String user_id, MemoForm memoForm) {
        return 0;
    }

    @Override
    public Memo getMemo(Calendar calendar,String userId) {
        System.out.println("MemoDaoMatsumotoCheck(getMemo)");
        MapSqlParameterSource param = new MapSqlParameterSource();
        System.out.println(calendar.getCalendar());
        System.out.println(userId);
        param.addValue("date",calendar.getCalendar());
        param.addValue("userId",userId);
        var list = jdbcTemplate.query("SELECT id memoId\n" +
                        "       ,memo\n" +
                        " FROM memo\n" +
                        " WHERE date = :date" +
                        " AND user_id = :userId",param,
                new DataClassRowMapper<>(Memo.class));
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public int updateMemo(Calendar calendar,String memo,String userId) {
        System.out.println("MemoDaoMatsumotoCheck(updateMemo)");
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("memo",memo);
        param.addValue("date",calendar.getCalendar());
        param.addValue("userId",userId);

        return jdbcTemplate.update("UPDATE memo\n" +
                                        "SET memo = :memo\n" +
                                        "WHERE date = :date\n" +
                                        "AND user_id = :userId",param);
    }
}
