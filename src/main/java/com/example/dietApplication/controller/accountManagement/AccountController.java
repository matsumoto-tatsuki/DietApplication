package com.example.dietApplication.controller.accountManagement;

import com.example.dietApplication.Service.AccountService;
import com.example.dietApplication.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class AccountController {

    @Autowired
    AccountService accountService;

    @GetMapping("/account-profile")
    public String getAccount(Model model) {
        var userList = accountService.getUserInfo("2");
        model.addAttribute("accountProfile",// -は使えない
                userList.getId());
        return "/account/account-profile";
    }

/*
    @GetMapping("//account-edit")
    public String getAccount2(){
        return "account/account-edit";
    }

    @PostMapping("//account-updata")
    public String postAccount(){
        return "/account-aicon";
    }
*/

}