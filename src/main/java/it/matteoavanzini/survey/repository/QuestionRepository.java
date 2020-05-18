package it.matteoavanzini.survey.repository;

import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.matteoavanzini.survey.model.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    @Query("SELECT q FROM Question q WHERE title=:title")
    Question[] findByName(@Param("title") String title);
}