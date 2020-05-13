package it.matteoavanzini.survey.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.matteoavanzini.survey.model.Question;
import it.matteoavanzini.survey.service.QuestionService;

@Controller
@RequestMapping("/question")
public class QuestionController {

    Logger logger = LoggerFactory.getLogger(QuestionController.class);
    
    @Autowired
    QuestionService service;

    @GetMapping("/{qid:[\\d]+}/show")
    public String show(Model model, @PathVariable("qid") int questionId) {
        Question question = service.get(questionId);
        if (question != null) {
            model.addAttribute("question", question);
            return "question/show";
        }
        return "thanks";
    }

    @GetMapping("/edit")
    public String edit() {
        return "question/edit";
    }   

    @PostMapping("/save")
    public String save(@ModelAttribute Question question) {
        logger.info("Question title: " + question.getTitle());
        logger.info("Question description: " + question.getDescription());

        // salvo l'oggetto sul db

        return "redirect:/";
    }

    // @PostMapping("/save")
    // public String saveWithParam(@RequestParam String title, @RequestParam String description) {
    //     Question q = new Question();
    //     q.setTitle(title);
    //     q.setDescription(description);

    //     // salvo l'oggetto sul db

    //     return "redirect:/";
    // }

    // @PostMapping("/save")
    // public String saveWithRequest(HttpServletRequest req) {
    //     String title = req.getParameter("title");
    //     String description = req.getParameter("description");

    //     Question q = new Question();
    //     q.setTitle(title);
    //     q.setDescription(description);

    //     // salvo l'oggetto sul db

    //     return "redirect:/";
    // }
}