package com.example.dietApplication.controller.dietListController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DietListController {
    @Autowired
    DietListService dietListService;
    @GetMapping("/diet-list")
    public String getDietList(Model model){
        var dietLists = dietListService.getDietList();
        System.out.println("リストのサイズ" + dietLists.size());
        model.addAttribute("dietLists",dietLists);
        return "/diet/diet_list";
    }
}
