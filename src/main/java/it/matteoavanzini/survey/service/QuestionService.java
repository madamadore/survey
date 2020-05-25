package it.matteoavanzini.survey.service;

import java.util.List;
import java.util.Optional;

import it.matteoavanzini.survey.model.Answer;
import it.matteoavanzini.survey.model.Question;
import it.matteoavanzini.survey.model.SurveyResult;
import it.matteoavanzini.survey.model.User;

public interface QuestionService {
    Question next(long serviceId, long fromQuestionId);

    void addAnswer(User user, Answer answer);
    void createSurveyResult(User user);
    void closeSurveyResult(User user);
    SurveyResult getResult(User user);
    
    Answer createAnswer(long questionId, List<Long> choosedOptions);    
    
    Optional<Question> getQuestion(long id);
    void saveQuestion(Question question);
    List<Question> getAllQuestions();
}