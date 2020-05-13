package it.matteoavanzini.survey.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import it.matteoavanzini.survey.model.Option;
import it.matteoavanzini.survey.service.QuestionService;

@Controller
@RequestMapping("/answer")
public class AnswerController {

    Logger logger = LoggerFactory.getLogger(QuestionController.class);
    
    @Autowired
    QuestionService service;

    @PostMapping("/save")
    public String save(@RequestParam List<Option> choose, 
                        @RequestParam("qid") String questionId) {

        logger.info("+++++ " + questionId);
        for (Option option : choose) {
            logger.info("val: " + option);
        }
        // salvo l'oggetto sul db

        return "thanks";
    }
}