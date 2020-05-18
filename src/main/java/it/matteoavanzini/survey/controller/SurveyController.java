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

import it.matteoavanzini.survey.model.Question;
import it.matteoavanzini.survey.model.Survey;
import it.matteoavanzini.survey.model.SurveyResult;
import it.matteoavanzini.survey.repository.SurveyRepositoryImpl;
import it.matteoavanzini.survey.service.QuestionService;

@Controller
@RequestMapping("/survey")
public class SurveyController {
    
    @Autowired
    SurveyRepositoryImpl surveyRepository;

    @Autowired
    QuestionService questionService;
    
    @RequestMapping("/thanks")
    public String thanks(Model model) {
        questionService.closeSurveyResult();
        SurveyResult result = questionService.getResult();
        model.addAttribute("result", result);
        return "thanks";
    }

    @GetMapping("/start")
    public String startSurvey() {
        questionService.createSurveyResult();

        Question next = questionService.getQuestion(1);
        if (null != next) {
            return "redirect:/question/" + next.getId() + "/show";
        }
        return "redirect:/";
    }


    @GetMapping("/{qid:[\\d]+}/show")
    public String show(Model model, @PathVariable("qid") int questionId) {
        Question question = questionService.getQuestion(questionId);
        if (question != null) {
            model.addAttribute("question", question);
            return "question/show";
        }
        return "thanks";
    }

    @GetMapping("/new")
    public String create(Model model) {
        List<Question> allQuestions = questionService.getAll();
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
    public String edit(@PathVariable("id") int id, Model model) {
        Optional<Survey> question = surveyRepository.findByID(id);
        List<Question> allQuestions = questionService.getAll();

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

        for (String qid : questionsId) {
            Long lid = Long.parseLong(qid);
            Question q = questionService.getQuestion(lid);
            survey.addQuestion(q);
        }

        surveyRepository.save(survey);
        return "redirect:/survey/list";
    }
}