package com.example.dietApplication.controller.footercheck;

import com.example.dietApplication.entity.UserLogin;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class footerCheckController {

    @Autowired
    private HttpSession session;

    @GetMapping("/利用規約確認")
    public String checkterm() {
        UserLogin userInfo = (UserLogin)session.getAttribute("user");
        System.out.println(userInfo);

        if (userInfo.getPermission() == 1) {
            return "/admin/admin_termsOfService";
        }
        return "/footerContents/termsOfService";
    }

    @GetMapping("/プライバシーポリシー確認")
    public String checkpolicy() {
        UserLogin userInfo = (UserLogin) session.getAttribute("user");

        if (userInfo.getPermission() == 1) {
            return "/admin/admin_privacyPolicy";

        }

        return "/footerContents/privacyPolicy";

    }
}
