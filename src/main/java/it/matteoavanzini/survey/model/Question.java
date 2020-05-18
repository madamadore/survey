package it.matteoavanzini.survey.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
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
public class Question {
    public static long ID = 0;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name="titolo")
    private String title;
    private String description;
    private boolean multiple;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL)
    private List<Option> options;

    public Question() {
        this.id = ++Question.ID;
        this.options = new ArrayList<>();
    }
    
    public void addOption(Option o) {
        options.add(o);
    }
}