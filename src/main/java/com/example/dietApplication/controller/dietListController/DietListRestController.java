package com.example.dietApplication.controller.dietListController;

import com.example.dietApplication.entity.DietDetail;
import com.example.dietApplication.entity.DietInfo;
import com.example.dietApplication.entity.UserLogin;
import com.example.dietApplication.form.DietForm;
import com.example.dietApplication.form.DietSearchForm;
import com.example.dietApplication.form.UserFavoriteForm;
import com.example.dietApplication.service.DietListService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DietListRestController {
    @Autowired
    private HttpSession session;
    @Autowired
    private DietListService dietListService;
    @PostMapping("/api-diet-favorite")
    public void postDietFavorite(@RequestBody UserFavoriteForm userFavoriteForm){
        UserLogin userInfo = (UserLogin)session.getAttribute("user");
        var userId = userInfo.getUserId();
        //登録
        System.out.println(userFavoriteForm.getDietName());
        dietListService.insertDietFavorite(userFavoriteForm,userId);
    }

    @DeleteMapping("/api-diet-favorite")
    public void deleteDietFavorite(@RequestBody UserFavoriteForm userFavoriteForm){
        System.out.println(userFavoriteForm.getDietName());
        UserLogin userInfo = (UserLogin)session.getAttribute("user");
        var userId = userInfo.getUserId();
        dietListService.deleteDietFavorite(userFavoriteForm,userId);
    }

    @PostMapping("/api-diet-detail")
    public List<DietDetail> postDietDetail(@RequestBody DietForm dietForm){


        var dietDetails =  dietListService.getDietDetail(dietForm.getDietName());

        for(var dietDetail : dietDetails){
            System.out.println("png "+dietDetail.getId());
            System.out.println("png "+dietDetail.getDetailTitle());
            System.out.println("png "+dietDetail.getDetail());
            System.out.println("png "+dietDetail.getImg());
        }

//        System.out.println("id"+dietDetail.getId() +"詳細"+dietDetail.getDetail());
        return dietDetails;
        //ダイエット名前から詳細を取得する
//        return DietDetail;
    }

    @PostMapping("/api-filter")
    public List<DietInfo> postFilter(@RequestBody DietSearchForm dietSearchForm){
        System.out.println("フォーム成功");
        UserLogin userInfo = (UserLogin)session.getAttribute("user");
        var userId = userInfo.getUserId();
        var dietList = dietListService.getSearchDiet(dietSearchForm,userId);
        return dietList;
    }

    @GetMapping("/get-diet-list")
    List<DietInfo> getDietList(){
        //セッション
        UserLogin userInfo = (UserLogin)session.getAttribute("user");
        var userId = userInfo.getUserId();
        var dietLists = dietListService.getDietList(userId);
        System.out.println(dietLists);
        return dietLists;
    }
}
