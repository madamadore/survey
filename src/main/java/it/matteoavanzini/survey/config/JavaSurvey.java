package it.matteoavanzini.survey.config;

import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import it.matteoavanzini.survey.model.Question;
import it.matteoavanzini.survey.model.Survey;
import it.matteoavanzini.survey.repository.SurveyRepository;
import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties(prefix = "survey")
@Getter
@Setter
public class JavaSurvey {
    private String title;
    private String description;
    private List<Question> questions;

    @Autowired
    SurveyRepository surveyRepository;

    @PostConstruct
    public void create() {
        Survey survey = new Survey();
        survey.setTitle(this.getTitle());
        survey.setDescription(this.getDescription());
        for (Question q : this.getQuestions()) {
            survey.addQuestion(q);
        }
        surveyRepository.save(survey);        
    }
}