package it.matteoavanzini.survey.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import it.matteoavanzini.survey.model.Survey;
import it.matteoavanzini.survey.model.SurveyResult;
import it.matteoavanzini.survey.model.User;

public interface SurveyResultRepository extends JpaRepository<SurveyResult, Long> {
    @Query("SELECT r FROM SurveyResult r WHERE user=:user")
    List<SurveyResult> findAllByUser(@Param("user") User user);

    @Query("SELECT r FROM SurveyResult r WHERE user=:user AND survey=:survey")
    Optional<SurveyResult> findByUserAndSurvey(@Param("user") User user, @Param("survey") Survey survey);
}