package com.example.dietApplication.dao;

public interface DietSelectDao {
    //お気に入り登録
    int insertDietFavorite(UserFavoriteForm userFavoriteForm);

    //実施ダイエットの登録
    int insertDiet(UserDietForm userDietForm);

    //実施ダイエットの更新
    int updateDiet(UserDietForm userDietForm);

    //実施ダイエットの削除
    int deleteDiet(int id);
}
