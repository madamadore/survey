package it.matteoavanzini.survey.model;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.CascadeType;
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
public class Survey {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String description;
    
    @OneToMany(mappedBy = "survey", cascade = CascadeType.ALL)
    private List<QuestionSurvey> questions;

    public Survey() {
        questions = new LinkedList<>();
    }

    public Survey(String title) {
        this();
        this.title = title;
    }

    public Survey(String title, List<Question> questions) {
        this(title);
        for (Question question : questions) {
            addQuestion(question);
        }
    }

    public void addQuestion(Question q) {
        int size = questions.size();
        questions.add(new QuestionSurvey(q, this, size));
    }

    public void addQuestion(Question q, int position) {
        QuestionSurvey cs = new QuestionSurvey(q, this, position);
        questions.add(cs);
    }

}