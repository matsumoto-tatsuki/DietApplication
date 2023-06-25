package com.example.dietApplication.controller.test;

import com.example.dietApplication.dao.DietSelectDao;
import com.example.dietApplication.form.UserDietForm;
import com.example.dietApplication.form.UserEditDietForm;
import com.example.dietApplication.form.UserSelectForm;
import com.example.dietApplication.service.DietDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestDietDbController {

    @Autowired
    private DietDbService dietDbService;

    @PostMapping("/api/userSelect/{id}")
    public UserDietForm userSelect(@ModelAttribute("userSelectForm")UserSelectForm userSelectForm,@PathVariable("id") int id){

        System.out.println(userSelectForm);
       // var select = dietDbService.getDietSelect(userSelectForm);
        var select = dietDbService.getDietSelect(id);
        System.out.println(select);

        return select;
    }

    @PostMapping("/api/db-register")
    public int apiDbRegister(@ModelAttribute("dietDb")UserDietForm userDietForm){
        String userId = "testuser";
        System.out.println("登録：" + userDietForm);
        return dietDbService.insertUserSelect(userDietForm,userId);
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
