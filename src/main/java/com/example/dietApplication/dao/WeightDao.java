package com.example.dietApplication.dao;

import com.example.dietApplication.entity.Calender;
import com.example.dietApplication.entity.UserWeight;

import java.util.List;

public interface WeightDao {
    //体重を取得（複数日）
    List<UserWeight> getUsersWeight(Calender calender);

    //体重を取得（1日）
    UserWeight getUserWeight(Calender calender);

    //体重更新
    int updateUserWeight(UserWeight userWeight);
}