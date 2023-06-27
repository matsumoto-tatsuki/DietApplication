package com.example.dietApplication.dao;

import com.example.dietApplication.entity.Calendar;
import com.example.dietApplication.entity.DietSelect;
import com.example.dietApplication.form.UserDietForm;
import com.example.dietApplication.form.UserEditDietForm;
import com.example.dietApplication.form.UserFavoriteForm;
import com.example.dietApplication.form.UserSelectForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DietSelectDaoMaZa implements DietSelectDao{

    @Autowired
    private NamedParameterJdbcTemplate jdbcTemplate;
    @Override
    public int insertDietFavorite(UserFavoriteForm userFavoriteForm,String userId){
        var param = new MapSqlParameterSource();
        param.addValue("dietName", userFavoriteForm.getDietName());
        param.addValue("userId", userId);
        //名前からidを取得する
        String addDietFavoriteSql = "INSERT " +
                "INTO users_favorite_diet (user_id,diet_id) " +
                "VALUES (" +
                ":userId,"+
                "(SELECT diet_info.id FROM diet_info WHERE diet_info.diet_name = :dietName));";
        var dietInfoData = jdbcTemplate.update(addDietFavoriteSql, param);
        return dietInfoData;
    }

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
        System.out.println("DietSelectDaoMatsumotoCheck(getDietSelect)");
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("dietName",userSelectForm.getDietName());
        param.addValue("startDate",userSelectForm.getStartDate());
        param.addValue("finishDate",userSelectForm.getFinishDate());
        param.addValue("userId",userSelectForm.getUserId());
        var list = jdbcTemplate.query("SELECT i.diet_name as dietName\n" +
                        "       ,s.action    as action\n" +
                        "       ,start_date || '～' || end_date as date\n" +
                        " FROM diet_select s\n" +
                        " JOIN diet_info i\n" +
                        " ON i.id = s.diet_id\n" +
                        " WHERE diet_id =  (SELECT id FROM diet_info WHERE diet_name = '豆腐ダイエット')\n" +
                        " AND start_date = '2023/06/20'\n" +
                        " AND end_date = '2023/06/30'\n" +
                        " AND user_id = 'testuser';",param,
                new DataClassRowMapper<>(DietSelect.class));
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public DietSelect getDietSelect(int id) {
        System.out.println("DietSelectDaoMatsumotoCheck(getDietSelect)");
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id",id);
        var list = jdbcTemplate.query("SELECT i.diet_name as dietName\n" +
                        "       ,s.action    as action\n" +
                        "       ,start_date || '～' || end_date as date\n" +
                        " FROM diet_select s\n" +
                        " JOIN diet_info i\n" +
                        " ON i.id = s.diet_id\n" +
                        " WHERE s.id = :id",param,
                new DataClassRowMapper<>(DietSelect.class));
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<DietSelect> getDietSelect(String userId) {
        System.out.println("DietSelectDaoMatsumotoCheck(getDietSelect)");
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("userId",userId);
        return jdbcTemplate.query("SELECT i.diet_name as dietName\n" +
                        "       ,s.action    as action\n" +
                        " ,start_date || '～' || end_date as date\n" +
                        " FROM diet_select s\n" +
                        " JOIN diet_info i\n" +
                        " ON i.id = s.diet_id\n" +
                        " WHERE CURRENT_DATE\n" +
                        " BETWEEN start_date\n" +
                        " AND end_date\n" +
                        " AND user_id = :userId",param,
                new DataClassRowMapper<>(DietSelect.class));
    }

    @Override
    public int insertDiet(UserDietForm userDietForm,String userId) {
        System.out.println("DietSelectDaoMatsumotoCheck(insertDiet)");
        MapSqlParameterSource param = new MapSqlParameterSource();
        var startDate = new Calendar(userDietForm.getStartDate());
        var finishDate = new Calendar(userDietForm.getFinishDate());
        param.addValue("userId",userId);
        param.addValue("dietName",userDietForm.getDietName());
        param.addValue("action",userDietForm.getAction());
        param.addValue("startDate",startDate.getCalendar());
        param.addValue("finishDate",finishDate.getCalendar());

        return jdbcTemplate.update("INSERT INTO diet_select(user_id,diet_id,action,start_date,end_date)\n" +
                "VALUES\n" +
                "(:userId,(SELECT id\n" +
                "  FROM diet_info\n" +
                "  WHERE diet_name = :dietName)\n" +
                "  ,:action,:startDate,:finishDate);",param);
    }

    @Override
    public int updateDiet(UserEditDietForm userEditDietForm) {
        System.out.println("DietSelectDaoMatsumotoCheck(updateDiet)");
        MapSqlParameterSource param = new MapSqlParameterSource();
        var startDate = new Calendar(userEditDietForm.getStartDate());
        var finishDate = new Calendar(userEditDietForm.getFinishDate());
        param.addValue("id",userEditDietForm.getId());
        param.addValue("action",userEditDietForm.getAction());
        param.addValue("startDate",startDate.getCalendar());
        param.addValue("finishDate",finishDate.getCalendar());

        return jdbcTemplate.update("UPDATE diet_select\n" +
                "SET action = :action\n" +
                "    ,start_date = :startDate\n" +
                "    ,end_date = :finishDate\n" +
                "WHERE id = :id",param);
    }

    @Override
    public int deleteDiet(int id) {
        System.out.println("DietSelectDaoMatsumotoCheck(updateDiet)");
        MapSqlParameterSource param = new MapSqlParameterSource();
        param.addValue("id",id);
        return jdbcTemplate.update("DELETE FROM diet_select WHERE id = :id",param);
    }
}
