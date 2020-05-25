package it.matteoavanzini.survey.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;
import javax.xml.ws.http.HTTPException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.matteoavanzini.survey.model.Question;
import it.matteoavanzini.survey.model.Survey;
import it.matteoavanzini.survey.model.SurveyResult;
import it.matteoavanzini.survey.model.User;
import it.matteoavanzini.survey.repository.SurveyRepository;
import it.matteoavanzini.survey.repository.UserRepository;
import it.matteoavanzini.survey.service.QuestionService;

@Controller
@RequestMapping("/survey")
public class SurveyController {
    
    @Autowired
    SurveyRepository surveyRepository;

    @Autowired
    QuestionService questionService;

    @Autowired
    UserRepository userRepository;
    
    @RequestMapping("/{sid:[\\d]+}/thanks")
    public String thanks(Principal principal, Model model, @PathVariable("sid") long surveyId) {
        Optional<User> user = userRepository.findByUsername(principal.getName());
        Optional<Survey> survey = surveyRepository.findById(surveyId);
        //TODO: add check isPresent()
        questionService.closeSurveyResult(user.get(), survey.get());
        SurveyResult result = questionService.getResult(user.get(), survey.get());
        model.addAttribute("result", result);
        return "thanks";
    }

    @GetMapping("/start")
    public String startSurvey(Model model) {
        List<Survey> surveys = surveyRepository.findAll();
        if (surveys.size() == 0) {
            throw new IllegalArgumentException("There is no Surveys");
        } else if (surveys.size() > 1) {
            model.addAttribute("surveys", surveys);
            return "survey/start";
        }
        return "redirect:/survey/" + surveys.get(0).getId() + "/start";
    }

    @PostMapping("/start")
    public String startSurvey(@RequestParam("sid") long surveyId) {
        return "redirect:/survey/" + surveyId + "/start";
    }

    @GetMapping("/{sid:[\\d]+}/start")
    public String startSurvey(HttpSession session, 
                                @PathVariable("sid") long surveyId, 
                                Principal principal, Model model) {
        Optional<User> user = userRepository.findByUsername(principal.getName());
        Optional<Survey> survey = surveyRepository.findById(surveyId);
        // TODO: add check isPresent()
        questionService.createSurveyResult(user.get(), survey.get());
        session.setAttribute("surveyId", surveyId);
        Optional<Question> next = questionService.getQuestion(1);
        if (next.isPresent()) {
            long qid = next.get().getId();
            return "redirect:/survey/" + surveyId +"/question/" + qid + "/show";
        }
        return "redirect:/survey/" + surveyId +"/thanks";
    }

    @GetMapping("/{sid:[\\d]+}/question/{qid:[\\d]+}/show")
    public String show(HttpSession session, Model model, 
                        @PathVariable("sid") long surveyId,
                        @PathVariable("qid") long questionId) {
        Long sessionSurveyId = (Long) session.getAttribute("surveyId");
        if (sessionSurveyId.longValue() != surveyId) {
            throw new HTTPException(400);
        }
        Optional<Question> question = questionService.getQuestion(questionId);
        if (question.isPresent()) {
            model.addAttribute("question", question.get());
            model.addAttribute("surveyId", surveyId);
            return "question/show";
        }
        return "redirect:/"+surveyId+"/thanks";
    }

}