package it.matteoavanzini.survey.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.matteoavanzini.survey.model.Answer;
import it.matteoavanzini.survey.model.Option;
import it.matteoavanzini.survey.model.Question;
import it.matteoavanzini.survey.model.SurveyResult;
import it.matteoavanzini.survey.repository.OptionRepository;
import it.matteoavanzini.survey.repository.QuestionRepository;
import it.matteoavanzini.survey.repository.SurveyResultRepository;

@Service
public class QuestionServiceImpl implements QuestionService {
    
    private Map<Long, Question> survey;
    private SurveyResult result;
    private Logger logger = LoggerFactory.getLogger(QuestionServiceImpl.class);

    @Autowired
    OptionRepository optionRepository;

    @Autowired
    SurveyResultRepository surveyResultRepository;
    
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
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public void closeSurveyResult() {
        Date endDate = new Date();
        int total = 0;
        for (Answer a: result.getAnswers()) {
            total += calculateTotal(a);
        }

        result.setEndDate(endDate);
        result.setTotal(total);

        surveyResultRepository.save(result);
    }

    @Transactional(propagation=Propagation.SUPPORTS)
    protected int calculateTotal(Answer a) {
        int total = 0;
        for (Option o: a.getQuestion().getOptions()) {
            if (a.getChoosedOptions().contains(o)) {
                total += o.getValue();
            }
        }
        return total;
    }

    @Override
    public Question getQuestion(long id) {
        return survey.get(id);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public void addAnswer(Answer answer) {
        result.addAnswer(answer);
        surveyResultRepository.save(result);
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

    @Override
    public Answer createAnswer(long questionId, List<Long> choosedOptions) {
        Question question = getQuestion(questionId);
        List<Option> options = new ArrayList<>();
        for (Long l: choosedOptions) {
            Optional<Option> mayOption = optionRepository.findById(l);
            if (mayOption.isPresent()) {
                options.add(mayOption.get());
            }
        }
        return new Answer(question, options);
    }
}