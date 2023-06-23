package com.example.dietApplication.controller.test;

import com.example.dietApplication.dao.DietSelectDao;
import com.example.dietApplication.form.UserDietForm;
import com.example.dietApplication.form.UserSelectForm;
import com.example.dietApplication.service.DietDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
