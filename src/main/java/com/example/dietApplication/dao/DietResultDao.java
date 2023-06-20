package com.example.dietApplication.dao;

import com.example.dietApplication.entity.Calendar;
import com.example.dietApplication.entity.DietResult;
import com.example.dietApplication.entity.DietResultDate;
import com.example.dietApplication.form.ResultForm;

import java.util.List;

public interface DietResultDao {
    //ダイエット成果,メモ、体重入力
    int insertResult(ResultForm resultForm);

    //ダイエット成果取得
    List<DietResult> getDietResult(Calendar calendar,String userId);

    //ダイエット成果更新
    int updateDietResult(DietResult dietResult,String userId);

    //calendarに色をつける
    List<DietResultDate> getCalenderResult(int year,int month,String userId);
}
