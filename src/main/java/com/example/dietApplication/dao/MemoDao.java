package com.example.dietApplication.dao;

import com.example.dietApplication.entity.Calender;
import com.example.dietApplication.entity.Memo;
import com.example.dietApplication.form.MemoForm;

public interface MemoDao {
    //メモ登録
    int insertMemo(String user_id, MemoForm insert_memo);

    //メモ取得
    Memo getMemo(Calender calender);

    //メモの更新
    int updateMemo(MemoForm memoForm);
}
