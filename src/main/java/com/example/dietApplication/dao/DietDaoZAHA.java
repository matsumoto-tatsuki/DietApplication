package com.example.dietApplication.dao;

import com.example.dietApplication.entity.DietDetail;
import com.example.dietApplication.entity.DietInfo;
import com.example.dietApplication.form.DietForm;
import com.example.dietApplication.form.DietSearchForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DietDaoZAHA implements DietDao{
    @Autowired
    // private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate jdbcTemplate;
    //ダイエットの一覧表示
//    private boolean favorite; お気に入り プログラムから取得
//    private boolean select; 選択
//    private String dietName; ダイエット名前
//    private String period; 期間
//    private String communityName; コミュニティ
//    private String categoryName;  カテゴリ
//    private String difficultName; 難易度名
    public List<DietInfo> getDietList(){
//        var sql_test =
//                "SELECT " +
//                        "diet_info.diet_name AS dietName, " +
//                        "diet_selects.start_date || '～' || diet_selects.end_date AS period, " +
//                        "category_tags.type AS categoryName, " +
//                        "diet_info.difficulty AS difficultName, " +
//                        "diet_channel.channel_name AS communityName, " +
//                        "CASE " +
//                        "   WHEN subquery.diet_id IS NOT NULL THEN true " +
//                        "   ELSE false " +
//                        "END AS favorite " +
//                        "FROM " +
//                        "diet_selects " +
//                        "JOIN " +
//                        "diet_info ON diet_selects.diet_id = diet_info.id " +
//                        "JOIN " +
//                        "category_tags ON diet_info.category_id = category_tags.category_id " +
//                        "JOIN " +
//                        "diet_channel ON diet_channel.diet_id = diet_selects.diet_id " +
//                        "LEFT JOIN " +
//                        "(SELECT favorite_diet.diet_id " +
//                        "   FROM diet_selects selects " +
//                        "   LEFT JOIN users_favorite_diet favorite_diet " +
//                        "   ON favorite_diet.diet_id = selects.diet_id) AS subquery " +
//                        "ON subquery.diet_id = diet_selects.diet_id;";
//        var sql =
//        "SELECT " +
//        "diet_info.diet_name AS dietName, " +
//                "diet_selects.start_date || '～' || diet_selects.end_date AS period, "+
//        "category_tags.type AS categoryName, "+
//                "diet_info.difficulty AS difficultName, "+
//        "diet_channel.channel_name AS communityName "+
//                "FROM "+
//        "diet_selects "+
//                "JOIN "+
//        "diet_info ON diet_selects.diet_id = diet_info.id "+
//        "JOIN "+
//        "category_tags ON diet_info.category_id = category_tags.category_id "+
//        "JOIN "+
//        "diet_channel ON diet_channel.diet_id = diet_selects.diet_id;";

        String query = "SELECT " +
                "diet_selects.user_id, " +
                "CASE " +
                "   WHEN subquery.diet_id IS NOT NULL " +
                "       THEN true " +
                "   ELSE false " +
                "END AS \"favorite\", " +
                "CASE " +
                "   WHEN diet_selects.start_date IS NOT NULL AND diet_selects.end_date IS NOT NULL " +
                "       THEN true " +
                "   ELSE false " +
                "END AS \"select\", " +
                "diet_info.diet_name AS dietName, " +
                "diet_selects.start_date || '～' || diet_selects.end_date AS period, " +
                "category_tags.type AS categoryName, " +
                "diet_info.difficulty AS difficultName, " +
                "diet_channel.channel_name AS communityName " +
                "FROM " +
                "diet_selects " +
                "LEFT JOIN diet_info " +
                "   ON diet_selects.diet_id = diet_info.id " +
                "LEFT JOIN category_tags " +
                "   ON diet_info.category_id = category_tags.category_id " +
                "LEFT JOIN diet_channel " +
                "   ON diet_channel.diet_id = diet_selects.diet_id " +
                "LEFT JOIN ( " +
                "   SELECT " +
                "       favorite_diet.diet_id " +
                "   FROM " +
                "       diet_selects selects " +
                "       LEFT JOIN users_favorite_diet favorite_diet " +
                "           ON favorite_diet.diet_id = selects.diet_id " +
                ") AS subquery " +
                "   ON subquery.diet_id = diet_selects.diet_id " +
                "ORDER BY diet_selects.diet_id ASC;";

//        String sqlQuery = "SELECT " +
//                "CASE " +
//                "WHEN subquery.diet_id IS NOT NULL THEN true " +
//                "ELSE false " +
//                "END AS favorite, " +
//                "diet_info.diet_name AS dietName, " +
//                "diet_selects.start_date || '～' || diet_selects.end_date AS period, " +
//                "category_tags.type AS categoryName, " +
//                "diet_info.difficulty AS difficultName, " +
//                "diet_channel.channel_name AS communityName " +
//                "FROM diet_selects " +
//                "LEFT JOIN diet_info ON diet_selects.diet_id = diet_info.id " +
//                "LEFT JOIN category_tags ON diet_info.category_id = category_tags.category_id " +
//                "LEFT JOIN diet_channel ON diet_channel.diet_id = diet_selects.diet_id " +
//                "LEFT JOIN ( " +
//                "SELECT favorite_diet.diet_id " +
//                "FROM diet_selects selects " +
//                "LEFT JOIN users_favorite_diet favorite_diet ON favorite_diet.diet_id = selects.diet_id " +
//                ") AS subquery ON subquery.diet_id = diet_selects.diet_id;";

        return jdbcTemplate.query(query,
                new DataClassRowMapper<>(DietInfo.class));
    }

    //ダイエットの詳細確認
    public DietDetail getDietDetail(int id){
        return null;
    }

    //絞り込み
    public List<DietInfo> getSearchDiet(DietSearchForm dietSearchForm){
        return null;
    }



    /* 管理人 */
    //全ダイエット数
    public int getAllDietNum(){
        return 0;
    }

    //ダイエット情報の登録
    public int insertDietInfo(DietForm dietForm){
        return 0;
    }

    //ダイエット情報の更新
    public int updateDietInfo(DietForm dietForm){
        return 0;
    }

    //ダイエット情報の削除
    public int deleteDietInfo(int id){
        return 0;
    }
}
