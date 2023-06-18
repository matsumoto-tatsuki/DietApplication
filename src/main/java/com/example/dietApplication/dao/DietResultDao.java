package com.example.dietApplication.dao;

import com.example.dietApplication.entity.Calender;
import com.example.dietApplication.entity.DietResult;
import com.example.dietApplication.form.ResultForm;

import java.util.List;

public interface DietResultDao {
    //ダイエット成果,メモ、体重入力
    int insertResult(ResultForm resultForm);

    //ダイエット成果取得
    List<DietResult> getDietResult(Calender calender);

    //ダイエット成果更新
    int updateDietResult(DietResult dietResult);
}
