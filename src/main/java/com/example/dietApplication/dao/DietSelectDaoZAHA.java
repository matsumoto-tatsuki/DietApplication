package com.example.dietApplication.dao;

import com.example.dietApplication.entity.DietSelect;
import com.example.dietApplication.form.UserDietForm;
import com.example.dietApplication.form.UserEditDietForm;
import com.example.dietApplication.form.UserFavoriteForm;
import com.example.dietApplication.form.UserSelectForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

public class DietSelectDaoZAHA implements DietSelectDao{
    @Autowired
    // private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public int deleteDietFavorite(UserFavoriteForm userFavoriteForm){
        var param = new MapSqlParameterSource();
        param.addValue("dietName", userFavoriteForm.getDietName());
        //名前からidを取得する
        String deleteDietFavoriteSql = "DELETE FROM users_favorite_diet WHERE diet_id = (SELECT diet_info.id FROM diet_info WHERE diet_info.diet_name = :dietName)";

        var dietInfoData = jdbcTemplate.update(deleteDietFavoriteSql, param);
        return dietInfoData;
    }

    @Override
    public DietSelect getDietSelect(UserSelectForm userSelectForm) {
        return null;
    }

    @Override
    public DietSelect getDietSelect(int id) {
        return null;
    }

    @Override
    public List<DietSelect> getDietSelect(String userId) {
        return null;
    }

    @Override
    public int insertDiet(UserDietForm userDietForm, String userId) {
        return 0;
    }

    @Override
    public int updateDiet(UserEditDietForm userEditDietForm) {
        return 0;
    }

    @Override
    public int deleteDiet(int id) {
        return 0;
    }

    //お気に入り登録
    @Override
    public int insertDietFavorite(UserFavoriteForm userFavoriteForm){
        var param = new MapSqlParameterSource();
        param.addValue("dietName", userFavoriteForm.getDietName());
        //名前からidを取得する
        String addDietFavoriteSql = "INSERT " +
                "INTO users_favorite_diet (user_id,diet_id) " +
                "VALUES (" +
                "(SELECT users.user_id FROM users WHERE users.user_id = 'zaha')," +
                "(SELECT diet_info.id FROM diet_info WHERE diet_info.diet_name = :dietName));";
        var dietInfoData = jdbcTemplate.update(addDietFavoriteSql, param);
        return dietInfoData;
    }

}
