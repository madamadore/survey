package it.matteoavanzini.survey.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.matteoavanzini.survey.model.Question;
import it.matteoavanzini.survey.model.Survey;
import it.matteoavanzini.survey.service.QuestionService;

@Component
public class SurveyRepositoryImpl {

    List<Survey> surveys;

    @Autowired
    QuestionService service;

    public SurveyRepositoryImpl() {
        surveys = new ArrayList<>();
        List<Question> allQuestions = service.getAll();
        surveys.add(new Survey("Animali", allQuestions));
        surveys.add(new Survey("Città", allQuestions));
        surveys.add(new Survey("Cose", allQuestions));
    }
    
    public List<Survey> findAll() {
        return surveys;
    }
    
    public Optional<Survey> findByID(final long id) {
        return surveys.stream()
            .filter(s -> (s.getId() == id))
            .findFirst();
    }

    public Optional<Survey> findByTitle(final String title) {
        return surveys.stream()
            .filter(s -> (s.getTitle() == title))
            .findFirst();
    }
}