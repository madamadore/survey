package it.matteoavanzini.survey.service;

import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import it.matteoavanzini.survey.model.Answer;
import it.matteoavanzini.survey.model.Option;
import it.matteoavanzini.survey.model.Question;
import it.matteoavanzini.survey.model.SurveyResult;

@Service
public class QuestionServiceImpl implements QuestionService {

    private Map<Integer, Question> survey;
    private SurveyResult result;
    private Logger logger = LoggerFactory.getLogger(QuestionServiceImpl.class);

    public QuestionServiceImpl() {
        survey = new LinkedHashMap<>();
        survey.put(1, getSimpleQuestion());
        survey.put(2, getMultipleQuestion());
    }

    private Question getSimpleQuestion() {
        Question q = new Question();
        q.setTitle("Domanda");
        q.setDescription("di che colore era il cavallo bianco di Napoleone?");
        q.addOption(new Option("Bianco", 4));
        q.addOption(new Option("Nero", 0));
        return q;
    }

    private Question getMultipleQuestion() {
        Question q = new Question();
        q.setTitle("Domanda");
        q.setDescription("Quali sono i colori sociali del Bologna?");
        q.setMultiple(true);
        q.addOption(new Option("Rosso", 2));
        q.addOption(new Option("Nero", 0));
        q.addOption(new Option("Giallo", 0));
        q.addOption(new Option("Blu", 2));
        return q;
    }

    @Override
    public Question next(int from) {
        logger.info("FROM " + from);
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
    public Question getQuestion(int id) {
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
}