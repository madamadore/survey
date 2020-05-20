package it.matteoavanzini.survey.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SurveyResult {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private Date startDate;
    private Date endDate;

    @OneToMany
    private List<Answer> answers;
    private int total;

    public SurveyResult() {
        startDate = new Date();
        answers = new ArrayList<>();
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

}