package it.matteoavanzini.survey.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import it.matteoavanzini.survey.model.Option;
import it.matteoavanzini.survey.model.Question;

@Service
public class QuestionServiceImpl implements QuestionService {

    private Map<Integer, Question> map;

    public QuestionServiceImpl() {
        map = new HashMap<>();
        map.put(1, getSimpleQuestion());
        map.put(2, getMultipleQuestion());
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
    public Question get(int id) {
        return map.get(id);
    }
    
}