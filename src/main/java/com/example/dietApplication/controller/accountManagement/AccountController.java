package com.example.dietApplication.controller.accountManagement;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AccountController {

    @GetMapping("/account-profile")
    public String getAccount(){
        return "/account/account-profile";
    }

    @GetMapping("//account-edit")
    public String getAccount2(){
        return "account/account-edit";
    }

    @PostMapping("//account-updata")
    public String postAccount(){
        return "/account-aicon";
    }

}
