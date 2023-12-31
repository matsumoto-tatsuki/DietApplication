package com.example.dietApplication.controller;

import com.example.dietApplication.dao.DietSelectDao;
import com.example.dietApplication.entity.UserLogin;
import com.example.dietApplication.service.AccountService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Locale;
import java.util.Random;

@Controller
public class TopController {
    @Autowired
    private HttpSession session;
    @Autowired
    private AccountService accountService;
    @Autowired
    private DietSelectDao dietSelectDao;
    @Autowired
    private MessageSource messageSource;
  
    @GetMapping("/top")
    public String getTop(Model model){
        UserLogin userInfo = (UserLogin)session.getAttribute("user");
        String userName = (String)session.getAttribute("userName");

        var list = dietSelectDao.getDietSelect(userInfo.getUserId());
        model.addAttribute("selectDiet",list);
        model.addAttribute("userName",userName);

        var accountInfo = accountService.getUserInfo(userInfo.getUserId());
        if(accountInfo.getUserSymbol() == null) {
            model.addAttribute("imagePath", "/images/defaultIcon.png");
        }else{
            model.addAttribute("imagePath", "/images/" + accountInfo.getUserSymbol());
        }

        var message = getRandomLine();
        System.out.println(message);
        model.addAttribute("message",message);
        return "/top";
    }

    @GetMapping("/register-conf")
    public String register(){
        return "/popup/register-confirmation";
    }
    @GetMapping("/update-conf")
    public String edit(){
        return "/popup/update-confirmation";
    }

    public String getRandomLine() {
        Random random = new Random();
        int lineNumber = random.nextInt(10) + 1; // ランダムな行番号を生成

        String messageKey = "top.messages" + lineNumber;
        String line = messageSource.getMessage(messageKey, null, Locale.getDefault());

        return line;
    }
}
