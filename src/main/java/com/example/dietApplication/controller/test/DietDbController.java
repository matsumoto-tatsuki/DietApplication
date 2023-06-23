package com.example.dietApplication.controller.test;

import com.example.dietApplication.form.UserDietForm;
import com.example.dietApplication.form.UserEditDietForm;
import com.example.dietApplication.service.DietDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DietDbController {

    @Autowired
    private DietDbService dietDbService;

    @GetMapping("/test")
    public String dietTable(@ModelAttribute("dietDb")UserDietForm userDietForm,@ModelAttribute("editDietDb")UserEditDietForm userEditDietForm){


        return "/dietdb/dietdb";
    }

    @PostMapping("/db-register")
    public String dbRegister(@ModelAttribute("dietDb")UserDietForm userDietForm){
        String userId = "testuser";
        System.out.println("登録：" + userDietForm);
        var num = dietDbService.insertUserSelect(userDietForm,userId);
        return "redirect:/test";
    }
    @PostMapping("/db-edit")
    public String dbEdit(@ModelAttribute("editDietDb") UserEditDietForm userEditDietForm){

        System.out.println("編集：" + userEditDietForm);
        var num = dietDbService.updateUserSelect(userEditDietForm);

        return "redirect:/test";
    }

    @PostMapping("/db-delete/{id}")
    public String dbDelete(@PathVariable("id") int id){

        System.out.println("削除：" + id);
        var num = dietDbService.deleteUserSelect(id);

        return "redirect:/test";
    }
}
