package com.example.dietApplication.controller.accountManagement;

import com.example.dietApplication.dao.UsersDaoKinjo;
import com.example.dietApplication.entity.UserLogin;
import com.example.dietApplication.form.UserForm;
import jakarta.servlet.http.HttpSession;
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

    @Autowired
    private HttpSession session;

    //プロフィル画面表示
    @GetMapping("/account-profile")
    public String getAccount(Model model) {

        UserLogin userInfo = (UserLogin)session.getAttribute("user");//userの情報があるセッション(userId,password,permission)をuserInfoに情報を入れる

        var userList = accountService.getUserInfo(userInfo.getUserId());//サービスに送り、データ(userId,password,permission)の内、UserIdを抜粋して代入。
        System.out.println(userList);
        model.addAttribute("account1",
                userList.getUserId());
        model.addAttribute("account2",
                userList.getUserName());
//        model.addAttribute("account3",
//                userList.getWeight());

        Integer weight = userList.getWeight(); // 体重を取得
        if (weight != null) {
            model.addAttribute("account3", weight);
        } else {
            model.addAttribute("account3", 0); // 体重がnullの場合は空文字列を表示
        }

        model.addAttribute("imagePath","/images/"+
                userList.getUserSymbol());

        return "/account/account-profile";
    }

    //ユーザー名編集画面遷移
    @GetMapping("/account-edit")
    public String editAccount(){
        UserLogin userInfo = (UserLogin)session.getAttribute("user");
        return "/account/account-edit";
    }

    //更新画面遷移
    @PostMapping("/account-update")
    public String postAccount(@ModelAttribute("userName") String username ,RedirectAttributes redirectAttributes) {
        if (username.trim().isEmpty() || username.length() > 50) {
            redirectAttributes.addFlashAttribute("errorMessage", "ユーザー名は1文字以上50文字以下で入力してください。");
            return "redirect:account-edit";
        }
        String UserId = (String) session.getAttribute("userId");
        accountService.userUp(username,UserId);
        return "/account/account-update";
    }

    //アイコン更新画面遷移
    @GetMapping("/account-icon")
    public String getIcon(){
        return "/account/account-icon";
    }

    @PostMapping("/icon-update")
    public String postIcon(@ModelAttribute("icon") String icon , RedirectAttributes redirectAttributes){
        if (icon.trim().isEmpty() || icon.isEmpty()){
            redirectAttributes.addFlashAttribute("errorMessage","アイコンを選択してください");
            return "redirect:account-icon";
        }
        System.out.println("ただの文字");
        String userId = (String) session.getAttribute("userId");
        var newIcon = accountService.iconUp(icon,userId);
        System.out.println("ただの文字");
        return "/account/account-update";
    }

    @GetMapping("/account-delete")
    public String deleteAccount(){
        UserLogin userInfo = (UserLogin)session.getAttribute("user");
        //var num = accountService.deleteUserInfo(userInfo.getUserId());
        return "redirect:/user-logout";
    }
}