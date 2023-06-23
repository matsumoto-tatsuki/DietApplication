package com.example.dietApplication.dao;

import com.example.dietApplication.entity.Calendar;
import com.example.dietApplication.entity.Memo;
import com.example.dietApplication.form.MemoForm;

public interface MemoDao {
    //メモ登録
    int insertMemo(String user_id, MemoForm memoForm);

    //メモ取得
    Memo getMemo(Calendar calendar,String userId);

    //メモの更新
    int updateMemo(Calendar calendar,String memo,String userId);
}
