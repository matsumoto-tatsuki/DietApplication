package com.example.dietApplication.controller.userLoginController;

import com.example.dietApplication.form.InsertUserForm;
import com.example.dietApplication.form.UserForm;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class UserLoginController {
    @Autowired
    private HttpSession session;
    @Autowired
    UserLoginService userLoginService;

    @GetMapping("/user-login")
    public String getLogin(@ModelAttribute UserForm userForm){
        return "/login";
    }

    @PostMapping("/user-login")
    public String postLogin(@Validated @ModelAttribute UserForm userForm, BindingResult bindingResult,Model model){
        //バリデーション
        if(bindingResult.hasErrors()){
            System.out.println("error");
            return "/login";
        }else{
            //IDが存在するか確認。
            System.out.println("取得");
            var userData = userLoginService.getUserLogin(userForm);
            System.out.println("取得error");
            //IDが存在したら。
            if(userData != null) {
                //比較を行う。
                if (userData.getUserId().equals(userForm.getUserId()) && userData.getPassword().equals(userForm.getPassword())) {
                    //ユーザー情報取得
                    //ユーザー情報をセッションとして持つ
                    var loginUser =  userLoginService.getUserLogin(userForm);
                    //var loginUser = userLoginService.getUserInfo(userData.getUserId());
                    session.setAttribute("user", loginUser);
                    session.setAttribute("userId",loginUser.getUserId());
                    System.out.println("session登録");
                    if(loginUser.getPermission() == 1){
                        return "redirect:/admin-top";
                    }
                    return "redirect:/top";
                }else{
                    model.addAttribute("idNotFoundError","パスワードが間違えています。");
                }
            }else{
                model.addAttribute("idNotFoundError","IDが存在しません。");
            }
        }
        System.out.println("ログイン画面返す");
        return "/login";
    }

    @GetMapping("/insert-user")
    public String getSignup(@ModelAttribute InsertUserForm insertUserForm){
        return "/Signup";
    }

    @PostMapping("/insert-user")
    public String postSignup(@Validated @ModelAttribute InsertUserForm insertUserForm ,BindingResult bindingResult,Model model){

        //iDチェック用のやつ
        //登録した時に
        //入力チェックエラーがないとき　かつ　ユーザーが登録されていない時
        if(bindingResult.hasErrors()){
            return "/signup";
        }else{
            //IDが重複するか確認
            var userData =  userLoginService.getUserIdCheck(insertUserForm);
            if(userData == null){
                //登録
                userLoginService.insertUser(insertUserForm);
                model.addAttribute("successPopup",true);
            }else{
                //IDは登録されています。
                model.addAttribute("userInsertId","IDは登録されています。");
            }
        }

        return "/signup";
    }

    @GetMapping("/user-logout")
    public String getOut(){
        session.removeAttribute("user");
        session.removeAttribute("userId");
        return "redirect:/user-login";
    }
}
