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
    public List<DietInfo> getDietList(String userId){
        var param = new MapSqlParameterSource();
        param.addValue("userId",userId);

        String query = "SELECT "
                + "diet_info.id AS id, "
                + "CASE "
                + "    WHEN favo.diet_id IS NOT NULL "
                + "        THEN true "
                + "    ELSE false "
                + "    END AS favorite, "
                + "diet_selects.diet_id AS select, "
                + "diet_info.diet_name AS dietName, "
                + "diet_selects.start_date || '～' || diet_selects.end_date AS period, "
                + "category.type AS categoryName, "
                + "diet_info.difficulty AS difficultName "
                + "FROM "
                + "diet_info "
                + "LEFT JOIN ( "
                + "    SELECT "
                + "        diet_select.start_date, "
                + "        diet_select.end_date, "
                + "        diet_select.diet_id "
                + "    FROM "
                + "        diet_select "
                + "    WHERE "
                + "        diet_select.user_id = :userId "
                + ") AS diet_selects "
                + "    ON diet_info.id = diet_selects.diet_id "
                + "INNER JOIN category "
                + "    ON diet_info.category_id = category.id "
                + "LEFT JOIN ( "
                + "    SELECT "
                + "        users_favorite_diet.diet_id "
                + "    FROM "
                + "        users_favorite_diet "
                + "    WHERE "
                + "        users_favorite_diet.user_id = :userId "
                + ") AS favo "
                + "    ON favo.diet_id = diet_info.id "
                + "ORDER BY "
                + "diet_info.id ASC;";

        return jdbcTemplate.query(query,param, new DataClassRowMapper<>(DietInfo.class));
    }


    //ダイエットの詳細確認
    @Override
    public List<DietDetail> getDietDetail(String dietName){
        var param = new MapSqlParameterSource();
        param.addValue("dietName",dietName);
//        String detailSql = "SELECT " +
//                "diet_detail.id AS id, " +
//                "diet_detail.detail_title AS detailTitle, " +
//                "diet_detail.detail_text AS detail, " +
//                "diet_detail_img.img_path AS img " +
//                "FROM " +
//                "diet_info " +
//                "JOIN diet_detail ON diet_info.id = diet_detail.diet_id " +
//                "LEFT JOIN diet_detail_img ON diet_detail_img.detail_id = diet_detail.id " +
//                "WHERE diet_info.diet_name = :dietName";

        String detailSql = "SELECT " +
                "diet_detail.id AS id, " +
                "diet_detail.title AS detailTitle, " +
                "diet_detail.detail AS detail, " +
                "diet_detail.img_path AS img " +
                "FROM " +
                "diet_info JOIN diet_detail " +
                "ON diet_info.id = diet_detail.diet_id " +
                "WHERE " +
                "diet_info.diet_name = :dietName;";

        return jdbcTemplate.query(detailSql,param,new DataClassRowMapper<>(DietDetail.class));
    }

    //絞り込み
    public List<DietInfo> getSearchDiet(DietSearchForm dietSearchForm,String userId){

//        String query = "SELECT "
//                + "CASE "
//                + "    WHEN subquery.diet_id IS NOT NULL "
//                + "    THEN true "
//                + "    ELSE false "
//                + "END AS favorite, "
//                + "diet_select.id AS \"select\", "
//                + "diet_info.diet_name AS dietName, "
//                + "diet_select.start_date || '～' || diet_select.end_date AS period, "
//                + "category.type AS categoryName, "
//                + "diet_info.difficulty AS difficultName "
//                + "FROM "
//                + "diet_info "
//                + "LEFT JOIN diet_select "
//                + "    ON diet_info.id = diet_select.diet_id "
//                + "INNER JOIN category "
//                + "    ON diet_info.category_id = category.id "
//                + "LEFT JOIN ( "
//                + "    SELECT "
//                + "        users_favorite_diet.diet_id "
//                + "    FROM "
//                + "        users_favorite_diet "
//                + "    ) AS subquery "
//                + "    ON subquery.diet_id = diet_info.id ";

        var param = new MapSqlParameterSource();
        param.addValue("userId",userId);

        String query = "SELECT "
                + "diet_info.id AS id, "
                + "CASE "
                + "    WHEN favo.diet_id IS NOT NULL "
                + "        THEN true "
                + "    ELSE false "
                + "    END AS favorite, "
                + "diet_selects.diet_id AS select, "
                + "diet_info.diet_name AS dietName, "
                + "diet_selects.start_date || '～' || diet_selects.end_date AS period, "
                + "category.type AS categoryName, "
                + "diet_info.difficulty AS difficultName "
                + "FROM "
                + "diet_info "
                + "LEFT JOIN ( "
                + "    SELECT "
                + "        diet_select.id, "
                + "        diet_select.start_date, "
                + "        diet_select.end_date, "
                + "        diet_select.diet_id "
                + "    FROM "
                + "        diet_select "
                + "    WHERE "
                + "        diet_select.user_id = :userId "
                + ") AS diet_selects "
                + "    ON diet_info.id = diet_selects.diet_id "
                + "INNER JOIN category "
                + "    ON diet_info.category_id = category.id "
                + "LEFT JOIN ( "
                + "    SELECT "
                + "        users_favorite_diet.diet_id "
                + "    FROM "
                + "        users_favorite_diet "
                + "    WHERE "
                + "        users_favorite_diet.user_id = :userId "
                + ") AS favo "
                + "    ON favo.diet_id = diet_info.id ";

        StringBuilder whereClause = new StringBuilder();

        if (dietSearchForm.isFavoriteSearch()) {
            System.out.println("お気に入り 絞り込み適用");
            whereClause.append("(CASE WHEN favo.diet_id IS NOT NULL THEN true ELSE false END = true) ");
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
            whereClause.append("category.type = :categoryName ");
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
