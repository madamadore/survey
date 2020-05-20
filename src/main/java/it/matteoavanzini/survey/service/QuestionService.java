package it.matteoavanzini.survey.service;

import java.util.List;

import it.matteoavanzini.survey.model.Answer;
import it.matteoavanzini.survey.model.Question;
import it.matteoavanzini.survey.model.SurveyResult;

public interface QuestionService {
    Question next(long from);

    void createSurveyResult();
    void closeSurveyResult();
    SurveyResult getResult();

    Answer createAnswer(long questionId, List<Long> choosedOptions);    
    void addAnswer(Answer answer);
    
    Question getQuestion(long id);
    void saveQuestion(Question question);
    List<Question> getAllQuestions();
}