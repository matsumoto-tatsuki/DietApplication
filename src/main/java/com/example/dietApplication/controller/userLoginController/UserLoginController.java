package com.example.dietApplication.controller.userLoginController;

import com.example.dietApplication.dao.UsersDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class UserLoginController {
    @Autowired UsersDao usersDao;
    @GetMapping("/user-login")
    public String getLogin(@ModelAttribute UserLoginForm userLoginForm){
        return "/login";
    }

    @PostMapping("/user-login")
    public String postLogin(@Validated @ModelAttribute UserLoginForm userLoginForm, BindingResult bindingResult,Model model){
        var userData = usersDao.loginCheck(userLoginForm);

        //バリデーション
        if(bindingResult.hasErrors()){
            System.out.println("error");
            return "/login";
        }

        //IDが存在したら。
        if(userData != null) {
            //比較を行う。
            if (userData.getUserId().equals(userLoginForm.getUserId()) && userData.getPassword().equals(userLoginForm.getPassword())) {
                return "redirect:/top";
            }else{
                model.addAttribute("idNotFoundError","パスワードが間違えています。");
            }
        }else{
            model.addAttribute("idNotFoundError","IDが存在しません。");
        }

        return "/login";
    }

    @GetMapping("/insert-user")
    public String getSignup(@ModelAttribute UserInsertForm userInsertForm){
        return "/Signup";
    }

    @PostMapping("/insert-user")
    public String postSignup(@Validated @ModelAttribute UserInsertForm userInsertForm ,BindingResult bindingResult,Model model){

        var addUserData = usersDao.UserIdCheck(userInsertForm);
        //入力チェックエラーがないとき　かつ　ユーザーが登録されていない時
        if(bindingResult.hasErrors()){
            List<ObjectError> errors = bindingResult.getAllErrors();
            for (ObjectError error : errors) {
                System.out.println(error.getDefaultMessage());
            }
            System.out.println("新規登録error");
            return "/signup";
        }

        if(addUserData == null) {
            //データベースに登録
            model.addAttribute("successPopup",true);
            usersDao.InsertUser(userInsertForm);
        }else{
            System.out.println("登録されています");
            //IDは登録されています。
            model.addAttribute("userInsertId","IDは登録されています。");
        }

        return "/signup";
    }

    @GetMapping("/user-logout")
    public String getOut(){
        return "/logout";
    }
}
