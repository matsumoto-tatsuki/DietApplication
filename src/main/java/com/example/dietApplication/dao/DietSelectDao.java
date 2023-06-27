package com.example.dietApplication.dao;


import com.example.dietApplication.entity.Calendar;
import com.example.dietApplication.entity.DietSelect;
import com.example.dietApplication.form.UserDietForm;
import com.example.dietApplication.form.UserEditDietForm;
import com.example.dietApplication.form.UserFavoriteForm;
import com.example.dietApplication.form.UserSelectForm;

import java.util.List;

public interface DietSelectDao {
    //お気に入り登録
    int insertDietFavorite(UserFavoriteForm userFavoriteForm,String userId);

    int deleteDietFavorite(UserFavoriteForm userFavoriteForm);

    //実施しているダイエットの情報取得
    DietSelect getDietSelect(UserSelectForm userSelectForm);
    DietSelect getDietSelect(int id);

    //今日の実施ダイエットの取得
    List<DietSelect> getDietSelect(String userId);

    //実施ダイエットの登録
    int insertDiet(UserDietForm userDietForm,String userId);

    //実施ダイエットの更新
    int updateDiet(UserEditDietForm userEditDietForm);

    //実施ダイエットの削除
    int deleteDiet(int id);
}
