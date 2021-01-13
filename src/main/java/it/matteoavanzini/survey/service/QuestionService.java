package it.matteoavanzini.survey.service;

import java.util.List;
import java.util.Optional;

import it.matteoavanzini.survey.model.Question;

public interface QuestionService {
    Question next(long surveyId, long fromQuestionId);  
    
    Optional<Question> getQuestion(long id);
    void saveQuestion(Question question);
    List<Question> getAllQuestions();
}