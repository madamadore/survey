package it.matteoavanzini.survey.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import it.matteoavanzini.survey.model.Question;
import it.matteoavanzini.survey.service.QuestionService;

@Controller
@RequestMapping(value = "/question", method = { RequestMethod.GET, RequestMethod.POST })
public class QuestionController {

    Logger logger = LoggerFactory.getLogger(QuestionController.class);
    
    @Autowired
    QuestionService service;

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("question", new Question());
        return "question/form";
    }

    @GetMapping("/list")
    public String list(Model model) {
        List<Question> questions = service.getAllQuestions();
        model.addAttribute("allQuestions", questions);
        return "question/list";
    }

    @GetMapping("/{id:[\\d]+}/edit")
    public String edit(@PathVariable("id") long id, Model model) {
        Optional<Question> question = service.getQuestion(id);
        if (question.isPresent()) {
            model.addAttribute("question", question.get());
            return "question/form";
        }
        return "redirect:/question/list";
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