package com.example.dietApplication.controller.dietListController;

import com.example.dietApplication.form.DietSearchForm;
import com.example.dietApplication.form.UserFavoriteForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
