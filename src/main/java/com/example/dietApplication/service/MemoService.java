package com.example.dietApplication.service;

import com.example.dietApplication.dao.MemoDao;
import com.example.dietApplication.entity.Calendar;
import com.example.dietApplication.entity.Calender;
import com.example.dietApplication.entity.Memo;
import com.example.dietApplication.form.MemoForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemoService {

    @Autowired
    private MemoDao memoDao;

    public int insertMemo(String user_id, MemoForm insert_memo) {
        return memoDao.insertMemo(user_id,insert_memo);
    }

    public Memo getMemo(Calendar calendar,String userId) {
        return memoDao.getMemo(calendar,userId);
    }

    public int updateMemo(Calendar calendar,MemoForm memoForm,String userId) {
        return memoDao.updateMemo(calendar,memoForm.getMemo(),userId);
    }

}
