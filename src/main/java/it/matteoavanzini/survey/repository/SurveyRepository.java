package it.matteoavanzini.survey.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.matteoavanzini.survey.model.Survey;

public interface SurveyRepository extends JpaRepository<Survey, Long> {
    @Query("SELECT s FROM Survey s WHERE title=:title")
    Optional<Survey> findByTitle(@Param("title") String title);
}