package it.matteoavanzini.survey.model;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class SurveyResult {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    
    private Date startDate;
    private Date endDate;

    @OneToOne
    User user;

    @ManyToOne
    Survey survey;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Answer> answers;
    private int total;

    public SurveyResult() {
        startDate = new Date();
        answers = new LinkedList<>();
    }

    public void addAnswer(Answer answer) {
        answers.add(answer);
    }

    public boolean isCompleted() {
        if (endDate != null) return true;
        return false;
    }
}