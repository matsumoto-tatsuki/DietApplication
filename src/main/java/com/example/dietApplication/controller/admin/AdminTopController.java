package com.example.dietApplication.controller.admin;


import com.example.dietApplication.service.UserServiceKura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminTopController {

    @Autowired
    UserServiceKura userService;

    @GetMapping("/admin-top")
    public String admin(Model model) {

        model.addAttribute("admin-top",userService.getAllUser());


        return "/admin/admin-top";
    }

}
