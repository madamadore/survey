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
public class SurveyController {
    
    @Autowired
    QuestionService service;
    
    @RequestMapping("/")
    public String home() {
        return "home";
    }

    @RequestMapping("/thanks")
    public String thanks(Model model) {
        service.closeSurveyResult();
        SurveyResult result = service.getResult();
        model.addAttribute("result", result);
        return "thanks";
    }

    @GetMapping("/survey/start")
    public String startSurvey() {
        service.createSurveyResult();

        Question next = service.getQuestion(1);
        if (null != next) {
            return "redirect:/question/" + next.getId() + "/show";
        }
        return "redirect:/";
    }
}