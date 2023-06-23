package com.example.dietApplication.dao;

import com.example.dietApplication.entity.DietDetail;
import com.example.dietApplication.entity.DietInfo;
import com.example.dietApplication.form.DietForm;
import com.example.dietApplication.form.DietSearchForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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

        String query = "SELECT " +
                "CASE " +
                "    WHEN subquery.diet_id IS NOT NULL " +
                "        THEN true " +
                "    ELSE false " +
                "END AS favorite, " +
                "diet_selects.id AS \"select\", " +
                "diet_info.diet_name AS dietName, " +
                "diet_selects.start_date || '～' || diet_selects.end_date AS period, " +
                "category_tags.type AS categoryName, " +
                "diet_info.difficulty AS difficultName " +
                "FROM " +
                "diet_info " +
                "LEFT JOIN diet_selects " +
                "ON diet_info.id = diet_selects.diet_id " +
                "INNER JOIN category_tags " +
                "ON diet_info.category_id = category_tags.category_id " +
                "LEFT JOIN ( " +
                "SELECT " +
                "favorite_diet.diet_id " +
                "FROM " +
                "diet_selects selects " +
                "LEFT JOIN users_favorite_diet favorite_diet " +
                "ON favorite_diet.diet_id = selects.diet_id " +
                ") AS subquery " +
                "ON subquery.diet_id = diet_info.id " +
                "ORDER BY diet_info.id ASC;";

//        String query = "SELECT " +
//                "diet_selects.user_id, " +
//                "CASE " +
//                "   WHEN subquery.diet_id IS NOT NULL " +
//                "       THEN true " +
//                "   ELSE false " +
//                "END AS \"favorite\", " +
//                "CASE " +
//                "   WHEN diet_selects.start_date IS NOT NULL AND diet_selects.end_date IS NOT NULL " +
//                "       THEN true " +
//                "   ELSE false " +
//                "END AS \"select\", " +
//                "diet_info.diet_name AS dietName, " +
//                "diet_selects.start_date || '～' || diet_selects.end_date AS period, " +
//                "category_tags.type AS categoryName, " +
//                "diet_info.difficulty AS difficultName, " +
//                "diet_channel.channel_name AS communityName " +
//                "FROM " +
//                "diet_selects " +
//                "LEFT JOIN diet_info " +
//                "   ON diet_selects.diet_id = diet_info.id " +
//                "LEFT JOIN category_tags " +
//                "   ON diet_info.category_id = category_tags.category_id " +
//                "LEFT JOIN diet_channel " +
//                "   ON diet_channel.diet_id = diet_selects.diet_id " +
//                "LEFT JOIN ( " +
//                "   SELECT " +
//                "       favorite_diet.diet_id " +
//                "   FROM " +
//                "       diet_selects selects " +
//                "       LEFT JOIN users_favorite_diet favorite_diet " +
//                "           ON favorite_diet.diet_id = selects.diet_id " +
//                ") AS subquery " +
//                "   ON subquery.diet_id = diet_selects.diet_id " +
//                "ORDER BY diet_selects.diet_id ASC;";

        return jdbcTemplate.query(query, new DataClassRowMapper<>(DietInfo.class));
    }

    //ダイエットの詳細確認
    public DietDetail getDietDetail(String dietName){
        var param = new MapSqlParameterSource();
        param.addValue("dietName",dietName);
        var detailSql = "SELECT id,detail,img FROM diet_info WHERE diet_name = :dietName";
        var dietData = jdbcTemplate.query(detailSql,param,new DataClassRowMapper<>(DietDetail.class));
        return dietData.isEmpty() ? null : dietData.get(0);
    }

    //絞り込み
    public List<DietInfo> getSearchDiet(DietSearchForm dietSearchForm){

        String query = "SELECT " +
                "CASE " +
                "WHEN subquery.diet_id IS NOT NULL " +
                "THEN true " +
                "ELSE false " +
                "END AS favorite, " +
                "diet_selects.id AS \"select\", " +
                "diet_info.diet_name AS dietName, " +
                "diet_selects.start_date || '～' || diet_selects.end_date AS period, " +
                "category_tags.type AS categoryName, " +
                "diet_info.difficulty AS difficultName " +
                "FROM " +
                "diet_info " +
                "LEFT JOIN diet_selects " +
                "ON diet_info.id = diet_selects.diet_id " +
                "INNER JOIN category_tags " +
                "ON diet_info.category_id = category_tags.category_id " +
                "LEFT JOIN ( " +
                "SELECT " +
                "favorite_diet.diet_id " +
                "FROM " +
                "diet_selects selects " +
                "LEFT JOIN users_favorite_diet favorite_diet " +
                "ON favorite_diet.diet_id = selects.diet_id " +
                ") AS subquery " +
                "ON subquery.diet_id = diet_info.id ";

        var param = new MapSqlParameterSource();
        StringBuilder whereClause = new StringBuilder();

        if (dietSearchForm.isFavoriteSearch()) {
            System.out.println("お気に入り 絞り込み適用");
            whereClause.append("(CASE WHEN subquery.diet_id IS NOT NULL THEN true ELSE false END = true) ");
        }

        if (dietSearchForm.isSelectSearch()) {
            System.out.println("実行済み 絞り込み適用");
            if (whereClause.length() > 0) {
                whereClause.append("AND ");
            }
            whereClause.append("(CASE WHEN diet_selects.id IS NOT NULL THEN true ELSE false END = true) ");
        }

        if (!dietSearchForm.getCategoryName().equals("カテゴリ")) {
            System.out.println("カテゴリ 絞り込み適用 " + dietSearchForm.getCategoryName());
            if (whereClause.length() > 0) {
                whereClause.append("AND ");
            }
            param.addValue("categoryName", dietSearchForm.getCategoryName());
            whereClause.append("category_tags.type = :categoryName ");
        }


        if (!dietSearchForm.getDifficultName().equals("難易度")) {
            System.out.println("難易度 絞り込み適用 "+dietSearchForm.getDifficultName());
            if (whereClause.length() > 0) {
                whereClause.append("AND ");
            }
            param.addValue("difficultName", dietSearchForm.getDifficultName());
            whereClause.append("CAST(diet_info.difficulty AS character varying) = :difficultName ");
        }

        if (!dietSearchForm.getKeywordSearch().isEmpty()) {
            System.out.println("キーワード 絞り込み適用" + dietSearchForm.getKeywordSearch());
            if (whereClause.length() > 0) {
                whereClause.append("AND ");
            }
            whereClause.append("diet_info.diet_name LIKE :keyword ");
            param.addValue("keyword", "%" + dietSearchForm.getKeywordSearch() + "%");
        }

        if (whereClause.length() > 0) {
            query += "WHERE " + whereClause.toString();
        }

        var orderBySQL = "ORDER BY diet_info.id ASC;";
        query += orderBySQL;

        System.out.println(query);

        return jdbcTemplate.query(query,param, new DataClassRowMapper<>(DietInfo.class));
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
