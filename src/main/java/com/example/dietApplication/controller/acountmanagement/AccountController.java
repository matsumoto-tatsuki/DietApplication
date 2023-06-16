package com.example.dietApplication.controller.acountmanagement;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AccountController {

    @GetMapping("/account-profile")
    public String getAccount(){
        return "account-profile";
    }

    @GetMapping("//account-aicon")
    public String getAccount2(){
        return "/account-aicon";
    }

    @PostMapping("//account-updata")
    public String postAccount(){
        return "/account-aicon";
    }

}
