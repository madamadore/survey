package it.matteoavanzini.survey.service;

import java.util.List;
import java.util.Optional;

import it.matteoavanzini.survey.model.Answer;
import it.matteoavanzini.survey.model.Question;
import it.matteoavanzini.survey.model.Survey;
import it.matteoavanzini.survey.model.SurveyResult;
import it.matteoavanzini.survey.model.User;

public interface QuestionService {
    Question next(long serviceId, long fromQuestionId);

    void addAnswer(User user, Answer answer, Survey survey);
    void createSurveyResult(User user, Survey survey);
    void closeSurveyResult(User user, Survey survey);
    SurveyResult getResult(User user, Survey survey);
    
    Answer createAnswer(long questionId, List<Long> choosedOptions);    
    
    Optional<Question> getQuestion(long id);
    void saveQuestion(Question question);
    List<Question> getAllQuestions();
}