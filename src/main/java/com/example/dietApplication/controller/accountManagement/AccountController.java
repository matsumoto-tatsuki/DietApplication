package com.example.dietApplication.controller.accountManagement;

import com.example.dietApplication.Service.AccountService;
import com.example.dietApplication.dao.UsersDaoKinjo;
import com.example.dietApplication.entity.UserInfo;
import com.example.dietApplication.form.UserForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

//バリデーションここ。後はhtmlで少々
@Controller
public class AccountController {

    @Autowired
    AccountService accountService;

    //プロフィル画面表示
    @GetMapping("/account-profile")
    public String getAccount(Model model) {
        var userList = accountService.getUserInfo("okinawa620");
        model.addAttribute("account1",// -は使えない thで使
                userList.getUserId());
        model.addAttribute("account2",
                userList.getUserName());
        model.addAttribute("account3",
                userList.getWeight());
        model.addAttribute("imagePath","/images/"+
                userList.getUserSymbol());

        return "/account/account-profile";
    }

    //ユーザー名編集画面遷移
    @GetMapping("/account-edit")
    public String editAccount(){
        return "/account/account-edit";
    }

    //更新画面遷移
    @PostMapping("/account-update")
    public String postAccount(@ModelAttribute("userName") String username) {
        System.out.println(username);
        accountService.userUp(username,"okinawa620");//更新実行。user_name指定し新しい名前を登録
        return "/account/account-update";
        //tdn画面
    }


    //アイコン更新画面遷移
    @GetMapping("/account-icon")
    public String getIcon(){
        return "/account/account-icon";
    }

    @PostMapping("/icon-update")
    public String postIcon(@ModelAttribute("icon") String icon){
        System.out.println(icon);
        accountService.iconUp(icon,"okinawa620");
        return "/account/account-update";
    }

}