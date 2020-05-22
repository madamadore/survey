package it.matteoavanzini.survey.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.matteoavanzini.survey.model.SurveyResult;
import it.matteoavanzini.survey.model.User;

public interface SurveyResultRepository extends JpaRepository<SurveyResult, Long> {
    @Query("SELECT r FROM SurveyResult r WHERE user=:user")
    Optional<SurveyResult> findByUser(@Param("user") User user);
}