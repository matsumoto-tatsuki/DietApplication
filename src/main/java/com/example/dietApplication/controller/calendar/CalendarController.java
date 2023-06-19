package com.example.dietApplication.controller.calendar;

import com.example.dietApplication.entity.Calendar;
import com.example.dietApplication.service.CalenderService;
import com.example.dietApplication.service.DietResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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
        var memo2 = memo == null ? "" : memo.getMemo();
        model.addAttribute("memo",memo2);

        var weight = dietResultService.getWeight(new Calendar(date),userId);
        var weight2 = weight == null ? "" : weight.getWeight();
        model.addAttribute("weight",weight2);

        return "/calendar/calendar-result";
    }
}
