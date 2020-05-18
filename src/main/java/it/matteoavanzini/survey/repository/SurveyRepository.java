package it.matteoavanzini.survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.matteoavanzini.survey.model.Survey;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
    
}