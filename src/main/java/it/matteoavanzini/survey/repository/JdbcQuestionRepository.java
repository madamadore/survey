package it.matteoavanzini.survey.repository;

import java.util.List;
import java.util.Optional;

import it.matteoavanzini.survey.model.Question;

public interface JdbcQuestionRepository {
    int count();
    int save(Question question);
    int update(Question question);
    int deleteById(Long id);
    List<Question> findAll();
    Optional<Question> findById(Long id);
}