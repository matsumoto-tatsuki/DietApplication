package com.example.dietApplication.controller.calendar;

import com.example.dietApplication.entity.Calendar;
import com.example.dietApplication.entity.Memo;
import com.example.dietApplication.form.MemoForm;
import com.example.dietApplication.form.ResultForm;
import com.example.dietApplication.service.CalenderService;
import com.example.dietApplication.service.DietResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;



@Controller
public class CalendarController {
    @Autowired
    private CalenderService calenderService;
    @Autowired
    private DietResultService dietResultService;

    @GetMapping("/calendar/{date}")
    public String calendarDate(Model model, @PathVariable("date") String date){
        System.out.println(date);
        String userId = "testuser";

        model.addAttribute("date",date);

        var result = dietResultService.getDietResult(new Calendar(date),userId);
        System.out.println(result);
        model.addAttribute("dietResult",result);


        var memo = dietResultService.getMemo(new Calendar(date),userId);
        var memoText = memo == null ? "" : memo.getMemo();
        model.addAttribute("memo",memoText);

        var weight = dietResultService.getWeight(new Calendar(date),userId);
        var weightText = weight == null ? "" : weight.getWeight();
        model.addAttribute("weight",weightText);

        return "/calendar/calendar-result";
    }

    @GetMapping("/dietResultUpdate/{date}")
    public String resultUpdate(@ModelAttribute("dietResultForm")ResultForm resultForm,Model model,@PathVariable("date") String date){
        String userId = "testuser";

        model.addAttribute("date",date);

        var result = dietResultService.getDietResult(new Calendar(date),userId);
        System.out.println(result);
        //model.addAttribute("dietResult",result);
        resultForm.setDietResults(result);

        String strId = String.valueOf(result.get(0).getDietResultId());
        for(var i = 1;i < result.size();i++){
            strId += "-" + result.get(i).getDietResultId();
        }
        System.out.println(strId);
        model.addAttribute("id",strId);


        var memo = dietResultService.getMemo(new Calendar(date),userId);
        var memoText = memo == null ? "" : memo.getMemo();
        //model.addAttribute("memo",memoText);
        resultForm.setMemo(memoText);

        var weight = dietResultService.getWeight(new Calendar(date),userId);
        int weightText = weight == null ? 0 : weight.getWeight();
//        model.addAttribute("weight",weightText);
        resultForm.setWeight(weightText);

        return "/calendar/result-update";
    }

    @PostMapping("/dietResultUpdate/{id}/{date}")
    public String resultUpdate2(@ModelAttribute("dietResultForm")ResultForm resultForm, @PathVariable("id") String id, @PathVariable("date") String date){
        String userId = "testuser";

        System.out.println(resultForm);
        System.out.println(id);
        System.out.println(date);

        var diet = dietResultService.updateDietResult(new Calendar(date),userId,resultForm,id);
        System.out.println(diet);

        return "redirect:/top";
    }

    @GetMapping("/dietResultInsert")
    public String resultInsert(@ModelAttribute("dietResultForm")ResultForm resultForm,Model model){
        String userId = "testuser";

        LocalDate currentDate = LocalDate.now();
        String date = String.valueOf(currentDate);
//        String date = "2023-06-19";
        model.addAttribute("date",date);

        var result = dietResultService.getDietResult(new Calendar(date),userId);
        System.out.println(result);
        model.addAttribute("dietResult",result);
        for(var i = 0;i < result.size();i++){
            result.get(i).setResult(false);
        }
        resultForm.setDietResults(result);


        var memo = dietResultService.getMemo(new Calendar(date),userId);
        var memoText = memo == null ? "" : memo.getMemo();
        model.addAttribute("memo",memoText);
        resultForm.setMemo(memoText);

        var weight = dietResultService.getWeight(new Calendar(date),userId);
        var weightText = weight == null ? 0 : weight.getWeight();
        model.addAttribute("weight",weightText);
        resultForm.setWeight(weightText);



        return "/calendar/result-insert";
    }
}
