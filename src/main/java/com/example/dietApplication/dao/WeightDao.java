package com.example.dietApplication.dao;

import com.example.dietApplication.entity.Calendar;
import com.example.dietApplication.entity.UserWeight;

import java.util.List;

public interface WeightDao {
    //体重を取得（複数日）
    List<UserWeight> getUsersWeight(Calendar calendar,String userId);

    //体重を取得（1日）
    UserWeight getUserWeight(Calendar calendar,String userId);

    //体重更新
    int updateUserWeight(Calendar calendar,int userWeight,String userId);
}
