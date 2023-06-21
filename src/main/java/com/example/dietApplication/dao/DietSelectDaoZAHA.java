package com.example.dietApplication.dao;

import com.example.dietApplication.entity.DietInfo;
import com.example.dietApplication.form.UserDietForm;
import com.example.dietApplication.form.UserFavoriteForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class DietSelectDaoZAHA implements DietSelectDao{
    @Autowired
    // private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate jdbcTemplate;

    public int deleteDietFavorite(UserFavoriteForm userFavoriteForm){
        var param = new MapSqlParameterSource();
        param.addValue("dietName", userFavoriteForm.getDietName());
        //名前からidを取得する
        String deleteDietFavoriteSql = "DELETE FROM users_favorite_diet WHERE diet_id = (SELECT diet_info.id FROM diet_info WHERE diet_info.diet_name = :dietName)";

        var dietInfoData = jdbcTemplate.update(deleteDietFavoriteSql, param);
        return dietInfoData;
    }

    //お気に入り登録
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

    //実施ダイエットの登録
    public int insertDiet(UserDietForm userDietForm){
        return 0;
    }

    //実施ダイエットの更新
    public int updateDiet(UserDietForm userDietForm){
        return 0;
    }

    //実施ダイエットの削除
    public int deleteDiet(int id){
        return 0;
    }
}
