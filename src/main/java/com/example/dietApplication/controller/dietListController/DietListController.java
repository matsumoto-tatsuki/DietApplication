package com.example.dietApplication.controller.dietListController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DietListController {
    @GetMapping("/dietList")
    public String getDietList(){
        return "/diet_list";
    }
}
