package it.matteoavanzini.survey.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.matteoavanzini.survey.model.Answer;
import it.matteoavanzini.survey.model.Question;
import it.matteoavanzini.survey.model.SurveyResult;
import it.matteoavanzini.survey.repository.QuestionRepository;

@Service
public class QuestionServiceImpl implements QuestionService {
    
    private Map<Long, Question> survey;
    private SurveyResult result;
    private Logger logger = LoggerFactory.getLogger(QuestionServiceImpl.class);

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository) {
        
        Optional<Question> simpleQuestion = questionRepository.findById(1L);
        Optional<Question> multipleQuestion = questionRepository.findById(2L);
        
        survey = new LinkedHashMap<>();
        if (simpleQuestion.isPresent())
            survey.put(1L, simpleQuestion.get());
        if (multipleQuestion.isPresent())
            survey.put(2L, multipleQuestion.get());
    }

    @Override
    public Question next(long from) {
        return survey.get(++from);
    }

    @Override
    public void createSurveyResult() {
        result = new SurveyResult();
    }

    @Override
    public void closeSurveyResult() {
        result.endSurvey();
    }

    @Override
    public Question getQuestion(long id) {
        return survey.get(id);
    }

    @Override
    public void addAnswer(Answer answer) {
        result.addAnswer(answer);
    }

    @Override
    public SurveyResult getResult() {
        return result;
    }

    @Override
    public void saveQuestion(Question question) {
        survey.put(question.getId(), question);
    }

    @Override
    public List<Question> getAll() {
        return survey.values()
            .stream()
            .collect(Collectors.toCollection(ArrayList::new));
    }
}