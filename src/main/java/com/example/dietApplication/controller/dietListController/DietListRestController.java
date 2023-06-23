package com.example.dietApplication.controller.dietListController;

import com.example.dietApplication.entity.DietDetail;
import com.example.dietApplication.entity.DietInfo;
import com.example.dietApplication.form.DietForm;
import com.example.dietApplication.form.DietSearchForm;
import com.example.dietApplication.form.UserFavoriteForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DietListRestController {
    @Autowired
    DietListService dietListService;
    @PostMapping("/api-diet-favorite")
    public void postDietFavorite(@RequestBody UserFavoriteForm userFavoriteForm){
        //登録
        dietListService.insertDietFavorite(userFavoriteForm);
    }

    @DeleteMapping("/api-diet-favorite")
    public void deleteDietFavorite(@RequestBody UserFavoriteForm userFavoriteForm){
        dietListService.deleteDietFavorite(userFavoriteForm);
    }

    @PostMapping("/api-diet-detail")
    public DietDetail postDietDetail(@RequestBody DietForm dietForm){


        var dietDetail =  dietListService.getDietDetail(dietForm.getDietName());

        System.out.println("png "+dietDetail.getImg());
//        System.out.println("id"+dietDetail.getId() +"詳細"+dietDetail.getDetail());
        return dietDetail;
        //ダイエット名前から詳細を取得する
//        return DietDetail;
    }

    @PostMapping("/api-filter")
    public List<DietInfo> postFilter(@RequestBody DietSearchForm dietSearchForm){
        System.out.println("フォーム成功");
        var dietList = dietListService.getSearchDiet(dietSearchForm);
        return dietList;
    }

    @GetMapping("/get-diet-list")
    List<DietInfo> getDietList(){
        var dietLists = dietListService.getDietList();
        return dietLists;
    }
}
