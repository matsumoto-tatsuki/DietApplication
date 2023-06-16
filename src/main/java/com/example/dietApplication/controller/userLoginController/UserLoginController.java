package com.example.dietApplication.controller.userLoginController;

import com.example.dietApplication.dao.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UserLoginController {
    @Autowired UsersDao usersDao;
    @GetMapping("/user-login")
    public String getLogin(){
        return "/login";
    }

    @PostMapping("/user-login")
    public String postLogin(@ModelAttribute UserLoginForm userLoginForm){
        var userData = usersDao.loginCheck(userLoginForm);
        if(userData != null) {
            if (userData.getUserId().equals(userLoginForm.getUserId()) && userData.getPassword().equals(userLoginForm.getPassword())) {
                return "redirect:/top";
            }
        }

        return "/login";
    }

    @GetMapping("/insert-user")
    public String getSingup(@ModelAttribute UserInsertForm userInsertForm){
        return "/Singup";
    }

    @PostMapping("/insert-user")
    public String psotSingup(@ModelAttribute UserInsertForm userInsertForm){
        usersDao.InsertUser(userInsertForm);
//        System.out.println("ユーザーid" + userInsertForm.getUserId());
//        System.out.println("ユーザーid" + userInsertForm.getPassword());
//        System.out.println("ユーザーid" + userInsertForm.getWeight());
        return "redirect:/user-login";
    }

    @GetMapping("/user-logout")
    public String getOut(){
        return "/logout";
    }
}
