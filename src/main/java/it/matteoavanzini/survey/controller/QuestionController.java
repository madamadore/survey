package it.matteoavanzini.survey.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.matteoavanzini.survey.model.Option;
import it.matteoavanzini.survey.model.Question;
import it.matteoavanzini.survey.service.QuestionService;

@Controller
@RequestMapping(value = "/question", method = { RequestMethod.GET, RequestMethod.POST })
public class QuestionController {

    Logger logger = LoggerFactory.getLogger(QuestionController.class);
    
    @Autowired
    QuestionService service;

    @GetMapping("/{qid:[\\d]+}/show")
    public String show(Model model, @PathVariable("qid") int questionId) {
        Question question = service.getQuestion(questionId);
        if (question != null) {
            model.addAttribute("question", question);
            return "question/show";
        }
        return "thanks";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("question", new Question());
        return "question/form";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Question> questions = service.getAll();
        model.addAttribute("allQuestions", questions);
        return "question/list";
    }

    @GetMapping("/{id:[\\d]+}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        Question question = service.getQuestion(id);
        model.addAttribute("question", question);
        return "question/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Question question) {
        service.saveQuestion(question);
        return "redirect:/question/list";
    }

    // metodo inutile, solo per esempio
    public String saveWithParam(@RequestParam("title") String title, @RequestParam String description) {
        Question q = new Question();
        q.setTitle(title);
        q.setDescription(description);

        // salvo l'oggetto sul db

        return "redirect:/";
    }

    // metodo inutile, solo per esempio
    public String saveWithRequest(HttpServletRequest req) {
        String title = req.getParameter("title");
        String description = req.getParameter("description");

        Question q = new Question();
        q.setTitle(title);
        q.setDescription(description);

        // salvo l'oggetto sul db

        return "redirect:/";
    }
}