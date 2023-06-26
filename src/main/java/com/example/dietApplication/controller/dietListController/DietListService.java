package com.example.dietApplication.controller.dietListController;

import com.example.dietApplication.dao.DietDao;
import com.example.dietApplication.dao.DietSelectDao;
import com.example.dietApplication.entity.DietDetail;
import com.example.dietApplication.entity.DietInfo;
import com.example.dietApplication.form.DietSearchForm;
import com.example.dietApplication.form.UserFavoriteForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DietListService {
    @Autowired
    DietDao dietDao;

    @Autowired
    DietSelectDao dietSelectDao;

    public List<DietInfo> getDietList(){
        return dietDao.getDietList();
    }

    public int insertDietFavorite(UserFavoriteForm userFavoriteForm){
        return dietSelectDao.insertDietFavorite(userFavoriteForm);
    }

    public int deleteDietFavorite(UserFavoriteForm userFavoriteForm){
        return dietSelectDao.deleteDietFavorite(userFavoriteForm);
    }

    public List<DietDetail> getDietDetail(String dietName){
        return dietDao.getDietDetail(dietName);
    }

    public List<DietInfo> getSearchDiet(DietSearchForm dietSearchForm){
        return dietDao.getSearchDiet(dietSearchForm);
    }
}
