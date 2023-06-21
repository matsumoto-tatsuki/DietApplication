package com.example.dietApplication.dao;

import com.example.dietApplication.entity.Calendar;
import com.example.dietApplication.entity.DietResult;
import com.example.dietApplication.entity.DietSelect;
import com.example.dietApplication.form.UserDietForm;
import com.example.dietApplication.form.UserFavoriteForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public class DietSelectDaoMatsumoto implements  DietSelectDao{
    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public int insertDietFavorite(UserFavoriteForm userFavoriteForm) {
        return 0;
    }

    @Override
    public List<DietSelect> getDietSelect(String userId) {
        System.out.println("DietSelectDaoMatsumotoCheck(getDietSelect)");
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("userId",userId);
        return jdbcTemplate.query("SELECT i.diet_name as dietName\n" +
                        "       ,s.action    as action\n" +
                        " ,start_date || '～' || end_date as date\n" +
                        " FROM diet_selects s\n" +
                        " JOIN diet_info i\n" +
                        " ON i.id = s.diet_id\n" +
                        " WHERE CURRENT_DATE\n" +
                        " BETWEEN start_date\n" +
                        " AND end_date\n" +
                        " AND user_id = :userId",param,
                new DataClassRowMapper<>(DietSelect.class));
    }

    @Override
    public int insertDiet(UserDietForm userDietForm) {
        return 0;
    }

    @Override
    public int updateDiet(UserDietForm userDietForm) {
        return 0;
    }

    @Override
    public int deleteDiet(int id) {
        return 0;
    }
}
