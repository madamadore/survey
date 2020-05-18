package it.matteoavanzini.survey.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import it.matteoavanzini.survey.model.Answer;

public interface AnswerRepository extends JpaRepository<Answer, Long> {
    
}