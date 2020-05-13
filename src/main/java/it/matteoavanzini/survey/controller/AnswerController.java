package it.matteoavanzini.survey.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.matteoavanzini.survey.model.Answer;
import it.matteoavanzini.survey.model.Question;
import it.matteoavanzini.survey.service.QuestionService;

@Controller
@RequestMapping("/answer")
public class AnswerController {

    Logger logger = LoggerFactory.getLogger(QuestionController.class);
    
    @Autowired
    QuestionService service;

    @PostMapping("/{qid:[\\d]+}/save")
    public String save(@RequestParam List<Long> choose, 
                        @PathVariable("qid") int questionId) {

        Question question = service.getQuestion(questionId);
        Answer answer = new Answer(question, choose);
        service.addAnswer(answer);

        Question next = service.next(questionId);
        if (null != next) {
            return "redirect:/question/" + next.getId() + "/show";
        }
        
        return "redirect:/survey/thanks";
    }
}