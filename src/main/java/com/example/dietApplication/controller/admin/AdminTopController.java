package com.example.dietApplication.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminTopController {
    @GetMapping("/admin-top")
    public String admin() {

        return "/admin/admin-top";
    }

}
