

package com.example.dietApplication.controller.memo;

import com.example.dietApplication.entity.Calendar;
import com.example.dietApplication.entity.Memo;
import com.example.dietApplication.entity.UserLogin;
import com.example.dietApplication.form.MemoForm;
import com.example.dietApplication.service.MemoService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;

@Controller
public class MemoController {

    @Autowired
    MemoService memoService;

    @Autowired
    private HttpSession session;

    @GetMapping("/memo/select")
    public String memo_select() {
        // メモ取得メソッド起動
        UserLogin userInfo = (UserLogin)session.getAttribute("user");
        var userId = userInfo.getUserId();
        LocalDate currentDate = LocalDate.now();
        String date = String.valueOf(currentDate);

        var result = memoService.getMemo(new Calendar(date),userId);
        if (result == null) {
            return "redirect:/memo_insert";
        }
        return "redirect:/memo_update";
    }


    // メモがまだ登録されてない場合このメソッドが働く
    @GetMapping("/memo_insert")
    public String memo_insert() {
        return "/memo/insert_memo";
    }

    // メモ内容を挿入する
    @PostMapping("/memo_register")
    public String memo_insert(@ModelAttribute("memo") MemoForm insert_memo) {

        // user_idを取得
        UserLogin userInfo = (UserLogin)session.getAttribute("user");
        String user_id = userInfo.getUserId();
        // メモを挿入
        memoService.insertMemo(user_id,insert_memo);

        return "redirect:/register-conf";
    }

    // メモ内容が既に登録されていた場合このメソッドが働く
//    @GetMapping("/memo_update") //表示
//    public String memo_update(Model model) {
//
//        return "/memo/update_memo";
//    }

    // メモ内容が既に登録されていた場合このメソッドが働く
    @GetMapping("/memo_update") //表示
    public String memo_update(Model model) {

        // メモ取得メソッド起動
        UserLogin userInfo = (UserLogin)session.getAttribute("user");
        var userId = userInfo.getUserId();
        LocalDate currentDate = LocalDate.now();
        String date = String.valueOf(currentDate);

        var result = memoService.getMemo(new Calendar(date),userId);
        model.addAttribute("result",result);
        System.out.println(result);

        return "/memo/update_memo";
    }


    // メモ内容をアップデートする
    @PostMapping("/memo_update")
    public String memo_update(MemoForm update_memo) {

        // メモの更新
        UserLogin userInfo = (UserLogin)session.getAttribute("user");
        var userId = userInfo.getUserId();
        LocalDate currentDate = LocalDate.now();
        String date = String.valueOf(currentDate);

        memoService.updateMemo(new Calendar(date),update_memo,userId);

        return "redirect:/update-conf";
    }
}
