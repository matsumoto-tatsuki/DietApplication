package com.example.dietApplication.controller.calendar;

import com.example.dietApplication.entity.Calendar;
import com.example.dietApplication.entity.DietResult;
import com.example.dietApplication.entity.Memo;
import com.example.dietApplication.entity.UserLogin;
import com.example.dietApplication.form.MemoForm;
import com.example.dietApplication.form.ResultForm;
import com.example.dietApplication.service.CalenderService;
import com.example.dietApplication.service.DietResultService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Controller
public class CalendarController {
    @Autowired
    private HttpSession session;
    @Autowired
    private CalenderService calenderService;
    @Autowired
    private DietResultService dietResultService;

    @GetMapping("/calendar/{date}")
    public String calendarDate(Model model, @PathVariable("date") String date){
        System.out.println(date);
        UserLogin userInfo = (UserLogin)session.getAttribute("user");

        model.addAttribute("date",date);

        var result = dietResultService.getDietResult(new Calendar(date),userInfo.getUserId());
        System.out.println(result);
        model.addAttribute("dietResult",result);


        var memo = dietResultService.getMemo(new Calendar(date),userInfo.getUserId());
        var memoText = memo == null ? "" : memo.getMemo();
        model.addAttribute("memo",memoText);

        var weight = dietResultService.getWeight(new Calendar(date),userInfo.getUserId());
        var weightText = weight == null ? "" : weight.getWeight();
        model.addAttribute("weight",weightText);

        return "/calendar/calendar-result";
    }

    @GetMapping("/dietResultUpdate/{date}")
    public String resultUpdate(@ModelAttribute("dietResultForm")ResultForm resultForm,Model model,@PathVariable("date") String date){
        UserLogin userInfo = (UserLogin)session.getAttribute("user");

        model.addAttribute("date",date);

        var result = dietResultService.getDietResult(new Calendar(date),userInfo.getUserId());
        System.out.println(result);
        //model.addAttribute("dietResult",result);
        resultForm.setDietResults(result);

        String strId = String.valueOf(result.get(0).getDietResultId());
        for(var i = 1;i < result.size();i++){
            strId += "-" + result.get(i).getDietResultId();
        }
        System.out.println(strId);
        model.addAttribute("id",strId);


        var memo = dietResultService.getMemo(new Calendar(date),userInfo.getUserId());
        var memoText = memo == null ? "" : memo.getMemo();
        //model.addAttribute("memo",memoText);
        resultForm.setMemo(memoText);

        var weight = dietResultService.getWeight(new Calendar(date),userInfo.getUserId());
        int weightText = weight == null ? 0 : weight.getWeight();
//        model.addAttribute("weight",weightText);
        resultForm.setWeight(weightText);

        return "/calendar/result-update";
    }

    @PostMapping("/dietResultUpdate/{id}/{date}")
    public String resultUpdate2(@ModelAttribute("dietResultForm")ResultForm resultForm, @PathVariable("id") String id, @PathVariable("date") String date){
        UserLogin userInfo = (UserLogin)session.getAttribute("user");

        System.out.println(resultForm);
        System.out.println(id);
        System.out.println(date);

        var diet = dietResultService.updateDietResult(new Calendar(date),userInfo.getUserId(),resultForm,id);
        System.out.println(diet);

        return "redirect:/update-conf";
    }

    @GetMapping("/dietResultInsert")
    public String resultInsertIndex(@ModelAttribute("dietResultForm")ResultForm resultForm,Model model){
        UserLogin userInfo = (UserLogin)session.getAttribute("user");

        LocalDate currentDate = LocalDate.now();
        String date = String.valueOf(currentDate);
//        String date = "2023-06-19";
        model.addAttribute("date",date);

        var resultSelect = dietResultService.getDietSelect(userInfo.getUserId());
        var result = dietResultService.getDietResult(new Calendar(date),userInfo.getUserId());

        if(result.size() != resultSelect.size()){
            List<DietResult> dietResultList = new ArrayList<>();
            for(var i = 0;i < resultSelect.size();i++){
                int finalI = i;
                if(result.stream()
                        .noneMatch(e -> e.getDietName().equals(resultSelect.get(finalI).getDietName()))){
                    dietResultList.add(new DietResult(0,resultSelect.get(i).getDietName(),resultSelect.get(i).getAction(),false));
                }
            }
            var num = dietResultService.insertDietResult(userInfo.getUserId(),dietResultList);
            result = dietResultService.getDietResult(new Calendar(date),userInfo.getUserId());
        }
        System.out.println(result);

//        var resultSelect = dietResultService.getDietSelect(userId);
//        List<DietResult> dietResultList = new ArrayList<>();
//        for(var i = 0;i < resultSelect.size();i++){
//            dietResultList.add(new DietResult(0,resultSelect.get(i).getDietName(),resultSelect.get(i).getAction(),false));
//        }
        resultForm.setDietResults(result);


        var memo = dietResultService.getMemo(new Calendar(date),userInfo.getUserId());
        if(memo == null){
            var num = dietResultService.insertMemo(userInfo.getUserId(),new MemoForm("初期値だよ"));
            memo = dietResultService.getMemo(new Calendar(date),userInfo.getUserId());
        }
        resultForm.setMemo(memo.getMemo());


        var weight = dietResultService.getWeight(new Calendar(date),userInfo.getUserId());
        if(weight == null){
            var num = dietResultService.insertUserWeight(0,userInfo.getUserId());
            weight = dietResultService.getWeight(new Calendar(date),userInfo.getUserId());
        }
        resultForm.setWeight(weight.getWeight());

        return "/calendar/result-insert";
    }

    @PostMapping("/dietResultInsert")
    public String resultInsert(@ModelAttribute("dietResultForm")ResultForm resultForm,Model model){
        UserLogin userInfo = (UserLogin)session.getAttribute("user");

        LocalDate currentDate = LocalDate.now();
        String date = String.valueOf(currentDate);

        String strId = String.valueOf(resultForm.getDietResults().get(0).getDietResultId());
        for(var i = 1;i < resultForm.getDietResults().size();i++){
            strId += "-" + resultForm.getDietResults().get(i).getDietResultId();
        }

        System.out.println("Insert:" + resultForm);

        dietResultService.updateDietResult(new Calendar(date),userInfo.getUserId(),resultForm,strId);

        return "redirect:/register-conf";
    }
}
