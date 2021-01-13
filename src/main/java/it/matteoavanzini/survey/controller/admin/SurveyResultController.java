package it.matteoavanzini.survey.controller.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.matteoavanzini.survey.model.SurveyResult;
import it.matteoavanzini.survey.repository.SurveyResultRepository;

@Controller
@RequestMapping("/admin/result")
public class SurveyResultController {
    
    @Autowired
    SurveyResultRepository surveyResultRepository;

    @GetMapping("/list")
    public String list(Model model) {
        List<SurveyResult> result = surveyResultRepository.findAll();
        model.addAttribute("allResults", result);
        return "surveyresult/list";
    }

}