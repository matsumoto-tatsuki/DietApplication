package com.example.dietApplication.controller.adminmanagement;

import com.example.dietApplication.form.AdminIdForm;
import com.example.dietApplication.form.AdminPassForm;
import com.example.dietApplication.service.AdminManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AdminManagementController {

    @Autowired
    AdminManagementService adminManagementService;

    // 選択画面表示
    @GetMapping("/admin_select")
    public String adminselect(@ModelAttribute("AdminPassForm") AdminPassForm adminPassForm) {
        return "/adminaccount/select";
    }

    ////////////////////////////////////////////////////////////////////////////////////////////

    // パスワード変更画面表示
    @PostMapping("/password_change")
    public String change(@ModelAttribute("AdminPassForm") AdminPassForm adminPassForm){
        return "/adminaccount/changePasswordInsert";
    }

    // 更新確認画面表示
    @PostMapping("/confirm")
    public String confirm(@Validated @ModelAttribute("AdminPassForm") AdminPassForm adminPassForm, BindingResult bindingResult, Model model){
        // バリデーション
        if(bindingResult.hasErrors()) {
            return "/adminaccount/changePasswordInsert";
        }
        if (adminPassForm.getAdminPassword().equals(adminPassForm.getAdminPassword2())) {
            adminManagementService.updateAdminPass(adminPassForm);
            return "/popup/admin-update-confirmation";
        } else {
            model.addAttribute("errorMessage","パスワードが一致していません");
            return "/adminaccount/changePasswordInsert";
        }
    }

//    // 更新のお知らせ画面表示
//    @PostMapping("/notice")
//    public String notice(@ModelAttribute("AdminPassForm") AdminPassForm adminPassForm){
//        adminManagementService.updateAdminPass(adminPassForm);
//        return "/adminaccount/update_notice";
//    }

////////////////////////////////////////////////////////////////////////////////////////


    // ID変更画面表示
    @PostMapping("/id_change")
    public String change(@ModelAttribute("AdminIdForm") AdminIdForm adminIdForm){
        return "/adminaccount/changeIdInsert";
    }

    // ID更新確認画面表示
    @PostMapping("/id_confirm")
    public String Idconfirm(@Validated @ModelAttribute("AdminIdForm") AdminIdForm adminIdForm, BindingResult bindingResult, Model model){
        // バリデーション
        if(bindingResult.hasErrors()) {
            return "/adminaccount/changeIdInsert";
        }
        adminManagementService.updateAdminId(adminIdForm);
        return "/popup/admin-update-confirmation";
    }


//    // ID更新のお知らせ画面表示
//    @PostMapping("/id_notice")
//    public String Idnotice(@ModelAttribute("AdminIdForm") AdminIdForm adminIdForm){
//        adminManagementService.updateAdminId(adminIdForm);
//        return "/adminaccount/update_Idnotice";
//    }

///////////////////////////////////////////////////////////////////////////////////////////

    // Topへのリダイレクト
    @PostMapping("/redirect")
    public String redirect() {
        return "redirect:https://google.com";
    }


}
