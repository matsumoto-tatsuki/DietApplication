package com.example.dietApplication.controller.dietListController;

import com.example.dietApplication.form.UserDietForm;
import com.example.dietApplication.form.UserEditDietForm;
import com.example.dietApplication.service.DietDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class DietListController {
//    @Autowired

    @Autowired
    private DietDbService dietDbService;

    @GetMapping("/diet-list")
    public String getDietList(@ModelAttribute("dietDb") UserDietForm userDietForm, @ModelAttribute("editDietDb") UserEditDietForm userEditDietForm){
        return "/diet/diet_list";
    }
    /* この先下は登録更新削除の画面遷移のメソッド（現在JSに切り替えたため使用していない）by松本 */
    @PostMapping("/db-register")
    public String dbRegister(@ModelAttribute("dietDb")UserDietForm userDietForm){
        String userId = "testuser";
        System.out.println("登録：" + userDietForm);
        var num = dietDbService.insertUserSelect(userDietForm,userId);
        return "redirect:/diet-list";
    }
    @PostMapping("/db-edit")
    public String dbEdit(@ModelAttribute("editDietDb") UserEditDietForm userEditDietForm){

        System.out.println("編集：" + userEditDietForm);
        var num = dietDbService.updateUserSelect(userEditDietForm);

        return "redirect:/diet-list";
    }

    @PostMapping("/db-delete/{id}")
    public String dbDelete(@PathVariable("id") int id){

        System.out.println("削除：" + id);
        var num = dietDbService.deleteUserSelect(id);

        return "redirect:/diet-list";
    }
}
