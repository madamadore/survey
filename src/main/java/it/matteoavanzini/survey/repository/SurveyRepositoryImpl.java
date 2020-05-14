package it.matteoavanzini.survey.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import it.matteoavanzini.survey.model.Survey;

public class SurveyRepositoryImpl {

    List<Survey> surveys;

    public SurveyRepositoryImpl() {
        surveys = new ArrayList<>();
        surveys.add(new Survey("Animali"));
        surveys.add(new Survey("Città"));
        surveys.add(new Survey("Cose"));
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