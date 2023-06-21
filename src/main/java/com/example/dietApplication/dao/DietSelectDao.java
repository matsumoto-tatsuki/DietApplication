package com.example.dietApplication.dao;


import com.example.dietApplication.entity.Calendar;
import com.example.dietApplication.entity.DietSelect;
import com.example.dietApplication.form.UserDietForm;
import com.example.dietApplication.form.UserFavoriteForm;

import java.util.List;

public interface DietSelectDao {
    //お気に入り登録
    int insertDietFavorite(UserFavoriteForm userFavoriteForm);

    //今日の実施ダイエットの取得
    List<DietSelect> getDietSelect(String userId);

    //実施ダイエットの登録
    int insertDiet(UserDietForm userDietForm);

    //実施ダイエットの更新
    int updateDiet(UserDietForm userDietForm);

    //実施ダイエットの削除
    int deleteDiet(int id);
}
