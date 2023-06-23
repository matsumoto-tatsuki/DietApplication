package com.example.dietApplication.controller.dietListController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DietListController {
    @GetMapping("/dietList")
    public String getDietList(){
        return "/diet_list";
    }
}
