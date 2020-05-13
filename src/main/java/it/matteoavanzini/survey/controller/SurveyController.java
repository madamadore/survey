package it.matteoavanzini.survey.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.matteoavanzini.survey.model.Question;
import it.matteoavanzini.survey.model.SurveyResult;
import it.matteoavanzini.survey.service.QuestionService;

@Controller
@RequestMapping("/survey")
public class SurveyController {
    
    @Autowired
    QuestionService service;
    
    @RequestMapping("/thanks")
    public String thanks(Model model) {
        service.closeSurveyResult();
        SurveyResult result = service.getResult();
        model.addAttribute("result", result);
        return "thanks";
    }

    @GetMapping("/start")
    public String startSurvey() {
        service.createSurveyResult();

        Question next = service.getQuestion(1);
        if (null != next) {
            return "redirect:/question/" + next.getId() + "/show";
        }
        return "redirect:/";
    }
}