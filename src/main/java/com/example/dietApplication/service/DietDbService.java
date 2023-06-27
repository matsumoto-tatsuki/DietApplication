package com.example.dietApplication.service;

import com.example.dietApplication.dao.DietSelectDao;
import com.example.dietApplication.entity.Calendar;
import com.example.dietApplication.entity.DietSelect;
import com.example.dietApplication.form.UserDietForm;
import com.example.dietApplication.form.UserEditDietForm;
import com.example.dietApplication.form.UserSelectForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DietDbService {
    @Autowired
    private DietSelectDao dietSelectDao;

    public UserDietForm getDietSelect(UserSelectForm userSelectForm){

//        var userSelect = dietSelectDao.getDietSelect(userSelectForm);
//        var date = userSelect.getDate().split("～");
//        var startDate = new Calendar(date[0]);
//        var finishDate = new Calendar(date[1]);
//
//        var userDietForm =new UserDietForm(userSelect.getDietName(),userSelect.getAction(),String.valueOf(startDate.getCalendar()),String.valueOf(finishDate.getCalendar()));
//
//        return userDietForm;
        return null;
    }
    public UserDietForm getDietSelect(int id){
        var userSelect = dietSelectDao.getDietSelect(id);
        var date = userSelect.getDate().split("～");
        return new UserDietForm(userSelect.getDietName(),userSelect.getAction(),date[0],date[1]);
    }

    public int insertUserSelect(UserDietForm userDietForm,String userId){
        return dietSelectDao.insertDiet(userDietForm,userId);
    }

    public int updateUserSelect(UserEditDietForm userEditDietForm){
        return dietSelectDao.updateDiet(userEditDietForm);
    }
    public int deleteUserSelect(int id){
        return dietSelectDao.deleteDiet(id);
    }
}
