package com.example.dietApplication.service;

import com.example.dietApplication.dao.DietDao;
import com.example.dietApplication.dao.DietSelectDao;
import com.example.dietApplication.entity.DietDetail;
import com.example.dietApplication.entity.DietInfo;
import com.example.dietApplication.form.DietSearchForm;
import com.example.dietApplication.form.UserFavoriteForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DietListService {
    @Autowired
    DietDao dietDao;

    @Autowired
    DietSelectDao dietSelectDao;

    public List<DietInfo> getDietList(String userId){
        return dietDao.getDietList(userId);
    }

    public int insertDietFavorite(UserFavoriteForm userFavoriteForm,String userId){
        return dietSelectDao.insertDietFavorite(userFavoriteForm,userId);
    }

    public int deleteDietFavorite(UserFavoriteForm userFavoriteForm,String userId){
        return dietSelectDao.deleteDietFavorite(userFavoriteForm,userId);
    }

    public List<DietDetail> getDietDetail(String dietName){
        return dietDao.getDietDetail(dietName);
    }

    public List<DietInfo> getSearchDiet(DietSearchForm dietSearchForm,String userId){
        return dietDao.getSearchDiet(dietSearchForm,userId);
    }
}
