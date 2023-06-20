package com.example.dietApplication.service;

import com.example.dietApplication.dao.DietResultDao;
import com.example.dietApplication.dao.MemoDao;
import com.example.dietApplication.dao.MemoDaoMatsumoto;
import com.example.dietApplication.dao.WeightDao;
import com.example.dietApplication.entity.Calendar;
import com.example.dietApplication.entity.DietResult;
import com.example.dietApplication.entity.Memo;
import com.example.dietApplication.entity.UserWeight;
import com.example.dietApplication.form.MemoForm;
import com.example.dietApplication.form.ResultForm;
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



    public int updateDietResult(Calendar calendar, String userId, ResultForm resultForm,String updateId){
        System.out.println(resultForm.getDietResults().get(0).getAction());
        System.out.println(resultForm.getDietResults().get(0).isResult());
        var id = updateId.split("-");
        int num1 = 1;
        for(var i = 0;i < id.length;i++){
            num1 *= dietResultDao.updateDietResult(new DietResult(Integer.parseInt(id[i]),null,resultForm.getDietResults().get(i).getAction(),resultForm.getDietResults().get(i).isResult()),userId);
        }

        var num2 = memoDao.updateMemo(calendar,resultForm.getMemo(),userId);
        var num3 = weightDao.updateUserWeight(calendar,resultForm.getWeight(),userId);
        return num1 * num2 * num3;
    }
}
