package it.matteoavanzini.survey.service;

import java.util.List;
import java.util.Optional;

import it.matteoavanzini.survey.model.Answer;
import it.matteoavanzini.survey.model.Question;
import it.matteoavanzini.survey.model.SurveyResult;

public interface QuestionService {
    Question next(long serviceId, long fromQuestionId);

    void createSurveyResult();
    void closeSurveyResult();
    SurveyResult getResult();

    Answer createAnswer(long questionId, List<Long> choosedOptions);    
    void addAnswer(Answer answer);
    
    Optional<Question> getQuestion(long id);
    void saveQuestion(Question question);
    List<Question> getAllQuestions();
}