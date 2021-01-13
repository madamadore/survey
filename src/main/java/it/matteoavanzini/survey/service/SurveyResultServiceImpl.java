package it.matteoavanzini.survey.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import it.matteoavanzini.survey.model.Answer;
import it.matteoavanzini.survey.model.Option;
import it.matteoavanzini.survey.model.Question;
import it.matteoavanzini.survey.model.Survey;
import it.matteoavanzini.survey.model.SurveyResult;
import it.matteoavanzini.survey.model.User;
import it.matteoavanzini.survey.repository.OptionRepository;
import it.matteoavanzini.survey.repository.QuestionRepository;
import it.matteoavanzini.survey.repository.SurveyResultRepository;

@Service
public class SurveyResultServiceImpl implements SurveyResultService {

    @Autowired
    SurveyResultRepository surveyResultRepository;

    @Autowired
    OptionRepository optionRepository;

    @Autowired
    QuestionRepository questionRepository;
    
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
    public Answer createAnswer(long questionId, List<Long> choosedOptions) {
        Optional<Question> question = questionRepository.findById(questionId);
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