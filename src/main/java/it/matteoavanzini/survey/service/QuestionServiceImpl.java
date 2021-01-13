package it.matteoavanzini.survey.service;

import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.matteoavanzini.survey.model.Question;
import it.matteoavanzini.survey.model.QuestionSurvey;
import it.matteoavanzini.survey.model.Survey;
import it.matteoavanzini.survey.repository.QuestionRepository;
import it.matteoavanzini.survey.repository.SurveyRepository;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    QuestionRepository questionRepository;

    @Autowired
    SurveyRepository surveyRepository;

    @Override
    public Question next(long surveyId, long fromQuestionId) {
        Optional<Survey> survey = surveyRepository.findById(surveyId);
        if (survey.isPresent()) {
            Survey currentSurvey = survey.get();
            List<QuestionSurvey> questionServices = currentSurvey.getQuestions();
            Iterator<QuestionSurvey> iterator = questionServices.iterator();
            while (iterator.hasNext()) {
                Question q = iterator.next().getQuestion();
                if (q.getId() == fromQuestionId) {
                    if (!iterator.hasNext()) break;
                    return iterator.next().getQuestion();
                }
            }
        }
        return null;
    }

    @Override
    public Optional<Question> getQuestion(long id) {
        return questionRepository.findById(id);
    }

    @Override
    public void saveQuestion(Question question) {
        questionRepository.save(question);
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

}