package it.matteoavanzini.survey.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SurveyController {
    
    @RequestMapping("/")
    public String home() {
        return "home";
    }
}