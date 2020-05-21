package it.matteoavanzini.survey.controller;

import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

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
import it.matteoavanzini.survey.repository.SurveyRepository;
import it.matteoavanzini.survey.service.QuestionService;

@Controller
@RequestMapping("/survey")
public class SurveyController {
    
    @Autowired
    SurveyRepository surveyRepository;

    @Autowired
    QuestionService questionService;
    
    @RequestMapping("/{sid:[\\d]+}/thanks")
    public String thanks(Model model, @PathVariable("sid") long surveyId) {
        questionService.closeSurveyResult();
        SurveyResult result = questionService.getResult();
        model.addAttribute("result", result);
        return "thanks";
    }

    @GetMapping("/start")
    public String startSurvey(Model model) {
        List<Survey> surveys = surveyRepository.findAll();
        if (surveys.size() == 0) {
            // TODO: solleva un eccezione
            return "redirect:/";
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
    public String startSurvey(@PathVariable("sid") long surveyId, Model model) {
        questionService.createSurveyResult();
        Optional<Question> next = questionService.getQuestion(1);
        if (next.isPresent()) {
            long qid = next.get().getId();
            return "forward:/survey/" + surveyId +"/question/" + qid + "/show";
        }
        return "redirect:/survey/" + surveyId +"/thanks";
    }

    @GetMapping("/{sid:[\\d]+}/question/{qid:[\\d]+}/show")
    public String show(Model model, 
                        @PathVariable("sid") long surveyId,
                        @PathVariable("qid") long questionId) {
        Optional<Question> question = questionService.getQuestion(questionId);
        if (question.isPresent()) {
            model.addAttribute("question", question.get());
            model.addAttribute("surveyId", surveyId);
            return "question/show";
        }
        return "redirect:/"+surveyId+"/thanks";
    }

    @GetMapping(value="/new")
    public String create(Model model) {
        List<Question> allQuestions = questionService.getAllQuestions();
        model.addAttribute("survey", new Survey());
        model.addAttribute("allQuestions", allQuestions);
        return "survey/form";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Survey> surveys = surveyRepository.findAll();
        model.addAttribute("allSurveys", surveys);
        return "survey/list";
    }

    @GetMapping("/{id:[\\d]+}/edit")
    public String edit(@PathVariable("id") long id, Model model) {
        Optional<Survey> question = surveyRepository.findById(id);
        List<Question> allQuestions = questionService.getAllQuestions();

        model.addAttribute("survey", question.get());
        model.addAttribute("allQuestions", allQuestions);

        return "survey/form";
    }

    @PostMapping("/save")
    public String save(HttpServletRequest request) {
        
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        long id = Long.parseLong(request.getParameter("id"));

        String[] questionsId = request.getParameterValues("questions");

        Survey survey = new Survey();
        survey.setId(id);
        survey.setTitle(title);
        survey.setDescription(description);

        if (questionsId != null) {
            for (String qid : questionsId) {
                Long lid = Long.parseLong(qid);
                Optional<Question> q = questionService.getQuestion(lid);
                if (q.isPresent()) {
                    survey.addQuestion(q.get());
                }
            }
        }

        surveyRepository.save(survey);
        return "redirect:/survey/list";
    }
}