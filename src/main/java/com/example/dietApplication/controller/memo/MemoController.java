package com.example.dietApplication.controller.memo;

import com.example.dietApplication.entity.Memo;
import com.example.dietApplication.form.MemoForm;
import com.example.dietApplication.service.MemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemoController {

    @Autowired
    MemoService memoService;

    @GetMapping("/memo")
    public String memo() {
        return "/memo/insert_memo";
    }

    @PostMapping("/memo")
    public String memo(@ModelAttribute("memo") MemoForm insert_memo) {

        String user_id = "5";

        memoService.insertMemo(user_id,insert_memo);

        return "/top";
    }

}
