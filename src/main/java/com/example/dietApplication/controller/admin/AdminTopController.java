package com.example.dietApplication.controller.admin;


import com.example.dietApplication.entity.AdminDateSearch;
import com.example.dietApplication.entity.User;
import com.example.dietApplication.Service.UserServiceKura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class AdminTopController {

    @Autowired
    UserServiceKura userService;


    @GetMapping("/admin-top")
    public String index(@ModelAttribute("adminDate") AdminDateSearch adminDate, Model model) {
        int count = userService.getAllUserNum();
        int AdminDietNum = userService.getAllDietNum();

        model.addAttribute("adminCount", count);
        model.addAttribute("adminTop", userService.getAllUser());
        model.addAttribute("admin_Alldiet", userService.getAllDiet());
        model.addAttribute("diet_count", AdminDietNum);

        return "/admin/admin-top";
}

    @PostMapping("/admin_date")
    public String admin(@Validated @ModelAttribute("adminDate") AdminDateSearch adminDate, BindingResult bindingResult,  Model model) {
        int count = userService.getAllUserNum();
        int AdminDietNum = userService.getAllDietNum();

        model.addAttribute("adminCount", count);
        model.addAttribute("adminTop", userService.getAllUser());
//        model.addAttribute("adminTop", userService.getAllDiet());
        model.addAttribute("diet_count", AdminDietNum);
//        model.addAttribute("admindate",userService.userDateResult());

        if (bindingResult.hasErrors()) {
            model.addAttribute("adminTop", userService.getAllUser());
            return "/admin/admin-top";
        }
        List<User> admin = userService.userDate(adminDate);
        model.addAttribute("adminTop",admin);
        return "/admin/admin-top";

    }

//    @PostMapping("/admin_date")
//    public Striate( @ModelAttribute("adminDate") AdminDateSearch adminDate, Model model) {
//        List<User> admng userDin = userService.userDate(adminDate);
//        model.addAttribute("admin_date",admin);
////       model.addAttribute("admin_list",list);
//
//        return "/admin/admin_date";
//    }

}
