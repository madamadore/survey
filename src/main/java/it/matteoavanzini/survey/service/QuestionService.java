package it.matteoavanzini.survey.service;

import it.matteoavanzini.survey.model.Answer;
import it.matteoavanzini.survey.model.Question;
import it.matteoavanzini.survey.model.SurveyResult;

public interface QuestionService {
    Question next(int from);
    void createSurveyResult();
    void closeSurveyResult();
    Question getQuestion(int id);
    void addAnswer(Answer answer);
    SurveyResult getResult();
}