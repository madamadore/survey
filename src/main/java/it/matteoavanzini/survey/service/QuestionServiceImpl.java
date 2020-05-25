package it.matteoavanzini.survey.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.matteoavanzini.survey.model.Answer;
import it.matteoavanzini.survey.model.Option;
import it.matteoavanzini.survey.model.Question;
import it.matteoavanzini.survey.model.QuestionSurvey;
import it.matteoavanzini.survey.model.Survey;
import it.matteoavanzini.survey.model.SurveyResult;
import it.matteoavanzini.survey.model.User;
import it.matteoavanzini.survey.repository.OptionRepository;
import it.matteoavanzini.survey.repository.QuestionRepository;
import it.matteoavanzini.survey.repository.SurveyRepository;
import it.matteoavanzini.survey.repository.SurveyResultRepository;

@Service
public class QuestionServiceImpl implements QuestionService {

    @Autowired
    OptionRepository optionRepository;

    @Autowired
    SurveyResultRepository surveyResultRepository;

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
    public void createSurveyResult(User user, Survey survey) {
        SurveyResult r = new SurveyResult();
        r.setUser(user);
        r.setSurvey(survey);
        surveyResultRepository.save(r);
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRED)
    public void addAnswer(User user, Answer answer, Survey survey) {
        SurveyResult r = getResult(user, survey);
        r.addAnswer(answer);
        surveyResultRepository.save(r);
    }

    @Override
    public SurveyResult getResult(User user, Survey survey) {
        Optional<SurveyResult> opt = surveyResultRepository.findByUserAndSurvey(user, survey);
        return opt.isPresent() ? opt.get() : null;
    }

    @Override
    @Transactional(propagation=Propagation.REQUIRES_NEW)
    public void closeSurveyResult(User user, Survey survey) {
        SurveyResult r = getResult(user, survey);
        Date endDate = new Date();
        int total = 0;
        for (Answer a: r.getAnswers()) {
            total += calculateTotal(a);
        }

        r.setEndDate(endDate);
        r.setTotal(total);

        surveyResultRepository.save(r);
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

    @Override
    public Answer createAnswer(long questionId, List<Long> choosedOptions) {
        Optional<Question> question = getQuestion(questionId);
        List<Option> options = new ArrayList<>();
        for (Long l: choosedOptions) {
            Optional<Option> mayOption = optionRepository.findById(l);
            if (mayOption.isPresent()) {
                options.add(mayOption.get());
            }
        }
        return new Answer(question.get(), options);
    }
}