package com.example.dietApplication.service;

import com.example.dietApplication.dao.DietResultDao;
import com.example.dietApplication.dao.MemoDao;
import com.example.dietApplication.dao.MemoDaoMatsumoto;
import com.example.dietApplication.dao.WeightDao;
import com.example.dietApplication.entity.Calendar;
import com.example.dietApplication.entity.DietResult;
import com.example.dietApplication.entity.Memo;
import com.example.dietApplication.entity.UserWeight;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DietResultService {
    @Autowired
    private DietResultDao dietResultDao;

    @Autowired
    private MemoDao memoDao;
    @Autowired
    private WeightDao weightDao;

    public List<DietResult> getDietResult(Calendar calender,String userId){
        return dietResultDao.getDietResult(calender,userId);
    }

    public Memo getMemo(Calendar calendar,String userId){
        return memoDao.getMemo(calendar,userId);
    }
    public UserWeight getWeight(Calendar calendar, String userId){
        return weightDao.getUserWeight(calendar,userId);
    }
}
