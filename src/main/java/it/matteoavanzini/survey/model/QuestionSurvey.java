package it.matteoavanzini.survey.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class QuestionSurvey implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Survey survey;

    @ManyToOne(cascade = CascadeType.ALL)
    private Question question;
    private int position;

    QuestionSurvey(Question q, Survey s, int p) {
        question = q;
        survey = s;
        position = p;
    }
}