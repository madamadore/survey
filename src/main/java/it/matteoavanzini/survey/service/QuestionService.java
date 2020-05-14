package it.matteoavanzini.survey.service;

import java.util.List;

import it.matteoavanzini.survey.model.Answer;
import it.matteoavanzini.survey.model.Question;
import it.matteoavanzini.survey.model.SurveyResult;

public interface QuestionService {
    Question next(long from);

    void createSurveyResult();
    void closeSurveyResult();

    void addAnswer(Answer answer);
    SurveyResult getResult();

    Question getQuestion(long id);
    void saveQuestion(Question question);
    List<Question> getAll();
}