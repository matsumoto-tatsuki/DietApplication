package com.example.dietApplication.dao;

import com.example.dietApplication.entity.Calender;
import com.example.dietApplication.entity.Memo;
import com.example.dietApplication.form.MemoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ResponseBody;

@Repository
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
    public Memo getMemo(Calender calender) {
        return null;
    }

    @Override
    public int updateMemo(MemoForm memoForm) {
        return 0;
    }

}
