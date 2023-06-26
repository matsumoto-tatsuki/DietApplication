package com.example.dietApplication.controller.footercheck;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class footerCheckController {

    @GetMapping("/利用規約確認")
    public String checkterm() {
        return "/footerContents/termsOfService";
    }

    @GetMapping("/プライバシーポリシー確認")
    public String checkpolicy() {
        return "/footerContents/privacyPolicy";
    }

}
