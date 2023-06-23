package com.example.dietApplication.controller;

import com.example.dietApplication.dao.DietSelectDao;
import com.example.dietApplication.entity.UserLogin;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TopController {
    @Autowired
    private HttpSession session;
    @Autowired
    private DietSelectDao dietSelectDao;
    @GetMapping("/top")
    public String getTop(Model model){
        UserLogin userInfo = (UserLogin)session.getAttribute("user");
        var userId = "testuser";
        var list = dietSelectDao.getDietSelect(userInfo.getUserId());
        model.addAttribute("selectDiet",list);
        model.addAttribute("userId",userInfo.getUserId());
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
