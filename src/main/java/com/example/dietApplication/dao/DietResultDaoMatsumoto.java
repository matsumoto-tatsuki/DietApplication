package com.example.dietApplication.dao;

import com.example.dietApplication.entity.Calendar;
import com.example.dietApplication.entity.DietResult;
import com.example.dietApplication.entity.DietResultDate;
import com.example.dietApplication.form.ResultForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DietResultDaoMatsumoto implements DietResultDao{

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Override
    public int insertResult(DietResult dietResult,String userId) {
        System.out.println("DietResultDaoMatsumotoCheck(insertResult)");
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("dietName",dietResult.getDietName());
        param.addValue("action",dietResult.getAction());
        param.addValue("userId",userId);
        param.addValue("flag",dietResult.isResult());

        return jdbcTemplate.update("INSERT INTO diet_result(user_select_id,result,date)\n" +
                                        "VALUES\n" +
                                        "((SELECT s.id\n" +
                                        " FROM diet_selects s\n" +
                                        " JOIN diet_info i\n" +
                                        " ON i.id = s.diet_id\n" +
                                        " WHERE CURRENT_DATE\n" +
                                        " BETWEEN start_date\n" +
                                        " AND end_date\n" +
                                        " AND i.diet_name = :dietName\n" +
                                        " AND s.action = :action\n" +
                                        " AND user_id = :userId)\n" +
                                        " ,:flag,CURRENT_DATE)",param);
    }

    @Override
    public List<DietResult> getDietResult(Calendar calendar,String userId) {
        System.out.println("DietResultDaoMatsumotoCheck(getCalenderResult)");
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("date",calendar.getCalendar());
        param.addValue("userId",userId);
        return jdbcTemplate.query("SELECT r.id dietResultId\n" +
                        "       ,i.diet_name dietName\n" +
                        "       ,s.action action\n" +
                        "       ,r.result result\n" +
                        " FROM diet_selects s\n" +
                        " JOIN diet_result r \n" +
                        " ON r.user_select_id = s.id\n" +
                        " JOIN diet_info i\n" +
                        " ON i.id = s.diet_id\n" +
                        " WHERE date = :date\n" +
                        " AND s.user_id = :userId\n" +
                        " ORDER BY r.id;",param,
                new DataClassRowMapper<>(DietResult.class));
    }

    @Override
    public int updateDietResult(DietResult dietResult,String userId) {
        System.out.println("DietResultDaoMatsumotoCheck(updateDietResult)");
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("resultId",dietResult.getDietResultId());
        param.addValue("selectAction",dietResult.getAction());
        param.addValue("flag",dietResult.isResult());
        param.addValue("userId",userId);
        var num1 = jdbcTemplate.update("UPDATE diet_result\n" +
                                            "SET result = :flag\n" +
                                            "WHERE id = :resultId;",param);
        var num2 = jdbcTemplate.update("UPDATE diet_selects\n" +
                                            "SET action = :selectAction\n" +
                                            "WHERE id = (SELECT user_select_id\n" +
                                            "            FROM diet_result\n" +
                                            "            WHERE id = :resultId)\n" +
                                            "AND user_id = :userId",param);
        return num1 + num2;
    }

    @Override
    public List<DietResultDate> getCalenderResult(int year,int month,String userId) {
        System.out.println("DietResultDaoMatsumotoCheck(getCalenderResult)");
        MapSqlParameterSource param = new MapSqlParameterSource();
        System.out.println(year);
        System.out.println(month);
        System.out.println(userId);

        param.addValue("year", year);
        param.addValue("month",month);
        param.addValue("userId",userId);
        return jdbcTemplate.query("SELECT date,result\n" +
                        " FROM diet_result r\n" +
                        " JOIN diet_selects s\n" +
                        " ON r.user_select_id = s.id \n" +
                        " WHERE EXTRACT(YEAR FROM date) = :year AND EXTRACT(MONTH FROM date) = :month\n" +
                        " AND s.user_id = :userId",param,
                new DataClassRowMapper<>(DietResultDate.class));
    }
}
