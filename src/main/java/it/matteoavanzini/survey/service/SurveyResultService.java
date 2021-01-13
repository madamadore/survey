package it.matteoavanzini.survey.service;

import java.util.List;

import it.matteoavanzini.survey.model.Answer;
import it.matteoavanzini.survey.model.Survey;
import it.matteoavanzini.survey.model.SurveyResult;
import it.matteoavanzini.survey.model.User;

public interface SurveyResultService {
    void createSurveyResult(User user, Survey survey);
    void closeSurveyResult(User user, Survey survey);
    SurveyResult getResult(User user, Survey survey);
    void addAnswer(User user, Answer answer, Survey survey);
    Answer createAnswer(long questionId, List<Long> choosedOptions);  
}