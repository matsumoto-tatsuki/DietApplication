package com.example.dietApplication.dao;

import com.example.dietApplication.entity.DietDetail;
import com.example.dietApplication.entity.DietInfo;
import com.example.dietApplication.form.DietForm;
import com.example.dietApplication.form.DietSearchForm;

import java.util.List;

public interface DietDao {
    //ダイエットの一覧表示
    List<DietInfo> getDietList();

    //ダイエットの詳細確認
    List<DietDetail> getDietDetail(String dietName);

    //絞り込み
    List<DietInfo> getSearchDiet(DietSearchForm dietSearchForm);



    /* 管理人 */
    //全ダイエット数
    int getAllDietNum();

    //ダイエット情報の登録
    int insertDietInfo(DietForm dietForm);

    //ダイエット情報の更新
    int updateDietInfo(DietForm dietForm);

    //ダイエット情報の削除
    int deleteDietInfo(int id);
}
