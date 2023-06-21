package com.example.dietApplication.dao;

import com.example.dietApplication.entity.Calender;
import com.example.dietApplication.entity.Memo;
import com.example.dietApplication.form.MemoForm;

public interface MemoDao {
    //メモ登録
<<<<<<< HEAD
    int insertMemo(String user_id, MemoForm insert_memo);
=======
    int insertMemo(MemoForm memoForm);
>>>>>>> 92457e1b029ce863211736364ccf1efbf3297be1

    //メモ取得
    Memo getMemo(Calender calender);

    //メモの更新
    int updateMemo(MemoForm memoForm);
}
