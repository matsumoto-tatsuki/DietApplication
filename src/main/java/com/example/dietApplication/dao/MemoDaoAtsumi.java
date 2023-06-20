package com.example.dietApplication.dao;

import com.example.dietApplication.entity.Calendar;
import com.example.dietApplication.entity.Calender;
import com.example.dietApplication.entity.Memo;
import com.example.dietApplication.form.MemoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;


public class MemoDaoAtsumi implements MemoDao {

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public int insertMemo(String user_id, MemoForm insert_memo) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("memoID",user_id);
        param.addValue("memo",insert_memo.getMemo());
        return jdbcTemplate.update("INSERT INTO memo (user_id,memo,date) VALUES (:memoID,:memo,CURRENT_DATE)",param);
    }

    @Override
    public Memo getMemo(Calendar calendar,String userId) {
        var list = jdbcTemplate.query("SELECT memo FROM memo WHERE date = CURRENT_DATE",
                new DataClassRowMapper<Memo>(Memo.class));
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public int updateMemo(Calendar calendar, String memo, String userId) {
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("memo",memoForm.getMemo());
        return jdbcTemplate.update("UPDATE memo SET memo = :memo WHERE date = CURRENT_DATE",param);
    }

}
