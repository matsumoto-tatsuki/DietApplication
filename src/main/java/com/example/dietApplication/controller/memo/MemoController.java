package com.example.dietApplication.controller.memo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MemoController {

    @GetMapping("/memo")
    public String memo() {
        return "/memo/insert_memo";
    }

}
