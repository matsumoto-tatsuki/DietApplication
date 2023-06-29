package com.example.dietApplication.controller.test;

import com.example.dietApplication.dao.DietSelectDao;
import com.example.dietApplication.entity.UserLogin;
import com.example.dietApplication.form.UserDietForm;
import com.example.dietApplication.form.UserEditDietForm;
import com.example.dietApplication.form.UserSelectForm;
import com.example.dietApplication.service.DietDbService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestDietDbController {
    @Autowired
    private HttpSession session;
    @Autowired
    private DietDbService dietDbService;

    @PostMapping("/api/userSelect/{id}")
    public UserDietForm userSelect(@ModelAttribute("userSelectForm")UserSelectForm userSelectForm,@PathVariable("id") int id){
        UserLogin userInfo = (UserLogin)session.getAttribute("user");
        System.out.println(userSelectForm);
       // var select = dietDbService.getDietSelect(userSelectForm);
        var select = dietDbService.getDietSelect(id,userInfo.getUserId());
        System.out.println(select);

        return select;
    }

    @PostMapping("/api/db-register")
    public int apiDbRegister(@ModelAttribute("dietDb")UserDietForm userDietForm){
        UserLogin userInfo = (UserLogin)session.getAttribute("user");
        System.out.println("登録：" + userDietForm);
        return dietDbService.insertUserSelect(userDietForm,userInfo.getUserId());
    }
    @PutMapping("/api/db-edit")
    public int apiDbEdit(@ModelAttribute("editDietDb") UserEditDietForm userEditDietForm){

        System.out.println("編集：" + userEditDietForm);

        return dietDbService.updateUserSelect(userEditDietForm);
    }

    @DeleteMapping("/api/db-delete/{id}")
    public int apiDbDelete(@PathVariable("id") int id){

        System.out.println("削除：" + id);

        return dietDbService.deleteUserSelect(id);
    }
}
