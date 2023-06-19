package com.example.dietApplication.dao;

import com.example.dietApplication.entity.UserRemind;
import com.example.dietApplication.form.UserRemindForm;

public interface RemindDao {
    //remind設定
    UserRemind getUserRemind(String userId);

    //remind登録
    int insertUserRemind(UserRemindForm userRemindForm);

    //remind更新
    int updateUserRemind(UserRemindForm userRemindForm);
}
