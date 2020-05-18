package it.matteoavanzini.survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.matteoavanzini.survey.model.SurveyResult;

public interface SurveyResultRepository extends JpaRepository<SurveyResult, Long> {
    
}