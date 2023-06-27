package com.example.dietApplication.controller.accountManagement;

import com.example.dietApplication.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AccountController {

    @Autowired
    AccountService accountService;

    //プロフィル画面表示
    @GetMapping("/account-profile")
    public String getAccount(Model model) {
        var userList = accountService.getUserInfo("masaratown621");
        model.addAttribute("account1",
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
    public String postAccount(@ModelAttribute("userName") String username ,RedirectAttributes redirectAttributes) {
        if (username.length() == 0 || username.length() > 50) {
            redirectAttributes.addFlashAttribute("errorMessage", "ユーザー名は1文字以上50文字以下で入力してください。");
            return "redirect:account-edit";
        }
        accountService.userUp(username,"masaratown621");
        return "/account/account-update";
    }


    //アイコン更新画面遷移
    @GetMapping("/account-icon")
    public String getIcon(){
        return "/account/account-icon";
    }

    @PostMapping("/icon-update")
    public String postIcon(@ModelAttribute("icon") String icon , RedirectAttributes redirectAttributes){
        if (icon == null || icon.isEmpty()){
            redirectAttributes.addFlashAttribute("errorMessage","アイコンを選択してください");
            return "redirect:account-icon";
        }
        accountService.iconUp(icon,"masaratown621");
        return "/account/account-update";
    }

    @PostMapping("/account-delete")
    public String deleteAccount(){
    String userId = "masaratoen621";
            accountService.deleteUserInfo(userId);
        return "top";
    }

}