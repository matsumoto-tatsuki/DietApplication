package com.example.dietApplication.service;

import com.example.dietApplication.dao.*;
import com.example.dietApplication.entity.*;
import com.example.dietApplication.form.MemoForm;
import com.example.dietApplication.form.ResultForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DietResultService {
    @Autowired
    private DietSelectDao dietSelectDao;
    @Autowired
    private DietResultDao dietResultDao;

    @Autowired
    private MemoDao memoDao;
    @Autowired
    private WeightDao weightDao;

    public List<DietSelect> getDietSelect(String userId){
        return dietSelectDao.getDietSelect(userId);
    }
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

    public int insertDietResult(Calendar calendar, String userId, ResultForm resultForm) {
        int num1 = 1;
        for(var i=0;i<resultForm.getDietResults().size();i++) {
            num1 *= dietResultDao.insertResult(
                    new DietResult(0, resultForm.getDietResults().get(i).getDietName(),
                            resultForm.getDietResults().get(i).getAction(),
                            resultForm.getDietResults().get(i).isResult()), userId);
        }
        var num2 = memoDao.updateMemo(calendar, resultForm.getMemo(), userId);
        var num3 = weightDao.updateUserWeight(calendar,resultForm.getWeight(),userId);

        return num1 * num2 * num3;
    }

    public int insertDietResult(String userId, List<DietResult> dietResults) {
        int num1 = 1;
        for (DietResult dietResult : dietResults) {
            num1 *= dietResultDao.insertResult(
                    new DietResult(0, dietResult.getDietName(),
                            dietResult.getAction(),
                            dietResult.isResult()), userId);
        }
        return num1;
    }

    public int insertMemo(String user_id, MemoForm insert_memo) {
        return memoDao.insertMemo(user_id,insert_memo);
    }

    public int insertUserWeight(int weight,String userId) {
        return weightDao.insertUserWeight(weight,userId);
    }
}
