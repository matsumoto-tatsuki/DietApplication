package com.example.dietApplication.controller.dietListController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DietListController {
//    @Autowired
    @GetMapping("/diet-list")
    public String getDietList(){
        return "/diet/diet_list";
    }
}
