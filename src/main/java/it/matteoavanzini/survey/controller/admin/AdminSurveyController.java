package it.matteoavanzini.survey.controller.admin;

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
import it.matteoavanzini.survey.repository.SurveyRepository;
import it.matteoavanzini.survey.service.QuestionService;

@Controller
@RequestMapping("/admin/survey")
public class AdminSurveyController {
    
    @Autowired
    SurveyRepository surveyRepository;

    @Autowired
    QuestionService questionService;
    
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