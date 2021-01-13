package it.matteoavanzini.survey.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.matteoavanzini.survey.model.Answer;
import it.matteoavanzini.survey.model.Question;
import it.matteoavanzini.survey.model.Survey;
import it.matteoavanzini.survey.model.User;
import it.matteoavanzini.survey.repository.SurveyRepository;
import it.matteoavanzini.survey.repository.UserRepository;
import it.matteoavanzini.survey.service.QuestionService;
import it.matteoavanzini.survey.service.SurveyResultService;

@Controller
@RequestMapping("/answer")
public class AnswerController {

    Logger logger = LoggerFactory.getLogger(AnswerController.class);
    
    @Autowired
    SurveyResultService surveyResultService;
    
    @Autowired
    QuestionService questionService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    SurveyRepository surveyRepository;

    @PostMapping("/save")
    public String save(@RequestParam(name="choose", required=false) List<Long> choosedOptionsId, 
                        @RequestParam("sid") long surveyId,
                        @RequestParam("qid") int questionId, Model model, Principal principal) {

        if (null == choosedOptionsId) choosedOptionsId = new ArrayList<>();
        Answer answer = surveyResultService.createAnswer(questionId, choosedOptionsId);
        Optional<User> user = userRepository.findByUsername(principal.getName());
        Optional<Survey> survey = surveyRepository.findById(surveyId);
        
        if (survey.isPresent()) {
            surveyResultService.addAnswer(user.get(), answer, survey.get());
        }

        Question next = questionService.next(surveyId, questionId);
        if (null != next) {
            return "redirect:/survey/" + surveyId + "/question/show";
        }
        
        return "redirect:/survey/" + surveyId + "/thanks";
    }

}