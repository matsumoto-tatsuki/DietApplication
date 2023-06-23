package com.example.dietApplication.controller;

import com.example.dietApplication.dao.DietSelectDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TopController {
    @Autowired
    private DietSelectDao dietSelectDao;
    @GetMapping("/top")
    public String getTop(Model model){
        var userId = "testuser";
        var list = dietSelectDao.getDietSelect(userId);
        model.addAttribute("selectDiet",list);
        return "/top";
    }

    @GetMapping("/register-conf")
    public String register(){
        return "/popup/register-confirmation";
    }
    @GetMapping("/update-conf")
    public String edit(){
        return "/popup/update-confirmation";
    }
}
