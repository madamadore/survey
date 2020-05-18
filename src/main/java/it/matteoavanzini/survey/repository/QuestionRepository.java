package it.matteoavanzini.survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.matteoavanzini.survey.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    
}