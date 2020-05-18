package it.matteoavanzini.survey.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Survey {
    private static long ID = 0;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String title;
    private String description;
    
    @ManyToMany
    @JoinTable(name="SURVEY_QUESTION", 
     joinColumns=@JoinColumn(name="survey_id", referencedColumnName="id"),
     inverseJoinColumns=@JoinColumn(name="question_id", referencedColumnName="id"))
    private List<Question> questions;

    public Survey() {
        this.id = ++ID;
        questions = new ArrayList<>();
    }

    public Survey(String title) {
        this();
        this.title = title;
    }

    public Survey(String title, List<Question> questions) {
        this(title);
        this.questions = questions;
    }

    public void addQuestion(Question q) {
        questions.add(q);
    }
}